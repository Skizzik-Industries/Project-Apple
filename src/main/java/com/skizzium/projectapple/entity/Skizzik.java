package com.skizzium.projectapple.entity;

import com.google.common.collect.ImmutableList;
import com.skizzium.projectapple.init.PA_Entities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

import static net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent;
import static net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock;

public class Skizzik extends MonsterEntity implements /*IChargeableMob,*/ IRangedAttackMob {
    private static final DataParameter<Integer> DATA_TARGET_A = EntityDataManager.defineId(Skizzik.class, DataSerializers.INT);
    private static final DataParameter<Integer> DATA_TARGET_B = EntityDataManager.defineId(Skizzik.class, DataSerializers.INT);
    private static final DataParameter<Integer> DATA_TARGET_C = EntityDataManager.defineId(Skizzik.class, DataSerializers.INT);
    private static final DataParameter<Integer> DATA_TARGET_D = EntityDataManager.defineId(Skizzik.class, DataSerializers.INT);
    private static final DataParameter<Integer> DATA_TARGET_E = EntityDataManager.defineId(Skizzik.class, DataSerializers.INT);

    private static final List<DataParameter<Integer>> DATA_TARGETS = ImmutableList.of(DATA_TARGET_A, DATA_TARGET_B, DATA_TARGET_C, DATA_TARGET_D, DATA_TARGET_E);
    private static final DataParameter<Integer> DATA_ID_STAGE = EntityDataManager.defineId(Skizzik.class, DataSerializers.INT);

    private final float[] xRotHeads = new float[4];
    private final float[] yRotHeads = new float[4];

    private final float[] xRotHeads1 = new float[4];
    private final float[] yRotHeads1 = new float[4];

    private final int[] nextHeadUpdate = new int[4];
    private final int[] idleHeadUpdates = new int[4];

    private int destroyBlocksTick;
    private static final EntityPredicate TARGETING_CONDITIONS = (new EntityPredicate()).range(20.0D).selector(PA_Entities.LIVING_ENTITY_SELECTOR);

    private final ServerBossInfo bossBar = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenScreen(true);

    public Skizzik(EntityType<? extends Skizzik> entity, World world) {
        super(entity, world);
        this.setHealth(this.getMaxHealth());
        this.getNavigation().setCanFloat(true);
    }

    @Override
    public boolean canBeLeashed(PlayerEntity player) {
        return false;
    }

    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEAD;
    }

    @Override
    protected boolean canRide(Entity entity) {
        return false;
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return 2.4F;
    }

    @Override
    public void startSeenByPlayer(ServerPlayerEntity player) {
        super.startSeenByPlayer(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayerEntity player) {
        super.stopSeenByPlayer(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    public void setCustomName(@Nullable ITextComponent name) {
        super.setCustomName(name);

        int stage = this.getStage();
        if (stage == 0) {
            this.bossBar.setName(new StringTextComponent(this.getDisplayName().getString() + " - Sleeping"));
        }
        else if (stage >= 1 && stage <= 5) {
            this.bossBar.setName(new StringTextComponent(this.getDisplayName().getString() + " - Stage " + stage));
        }
        else if (stage == 6) {
            this.bossBar.setName(new StringTextComponent(this.getDisplayName().getString() + " - FINISH HIM!"));
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WITHER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.WITHER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WITHER_DEATH;
    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }

    @Override
    public boolean causeFallDamage(float f0, float f1) {
        return false;
    }

    @Override
    public boolean addEffect(EffectInstance effect) {
        return false;
    }

    @Override
    public void makeStuckInBlock(BlockState state, Vector3d vector) {
    }

    public static boolean canDestroy(BlockState state) {
        return !state.isAir() && !BlockTags.WITHER_IMMUNE.contains(state.getBlock());
    }

    public static AttributeModifierMap.MutableAttribute buildAttributes() {
        //this.getAttribute(Attributes.ARMOR)
        return MonsterEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1021.0D)
                .add(Attributes.ARMOR, 8.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.FLYING_SPEED, 0.6D);
    }

    public int getStage() {
        return this.entityData.get(DATA_ID_STAGE);
    }

    public void setStage(int stage) {
        this.entityData.set(DATA_ID_STAGE, stage);
    }

    public int getAlternativeTarget(int head) {
        return this.entityData.get(DATA_TARGETS.get(head));
    }

    public void setAlternativeTarget(int head, int target) {
        this.entityData.set(DATA_TARGETS.get(head), target);
    }

    @Override
    public boolean canBeAffected(EffectInstance effect) {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadYRot(int head) {
        return this.yRotHeads[head];
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadXRot(int head) {
        return this.xRotHeads[head];
    }

    private double getHeadX(int head) {
        if (head <= 0) {
            return this.getX();
        }
        else {
            float f = (this.yBodyRot + (float)(180 * (head - 1))) * ((float)Math.PI / 180F);
            float f1 = MathHelper.cos(f);

            return head <= 2 ? this.getX() + (double)f1 * 1.3D :
                    head == 3 ? this.getX() + f1 * 1.2D :
                    this.getX() + (double)f1 * 0.8D;
        }
    }

    private double getHeadY(int head) {
        return head == 0 ? this.getY() + 2.5D :
                head == 1 || head == 2 ? this.getY() + 1.8 :
                head == 3 ? this.getY() + 3.3 :
                this.getY() + 3.4;
    }

    private double getHeadZ(int head) {
        if (head <= 0) {
            return this.getZ();
        } else {
            float f = (this.yBodyRot + (float)(180 * (head - 1))) * ((float)Math.PI / 180F);
            float f1 = MathHelper.sin(f);
            return this.getZ() + (double)f1 * 1.3D;
        }
    }

    private float rotlerp(float f1, float f2, float f3) {
        float f = MathHelper.wrapDegrees(f2 - f1);
        if (f > f3) {
            f = f3;
        }

        if (f < -f3) {
            f = -f3;
        }

        return f1 + f;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(DATA_ID_STAGE, 0);

        this.entityData.define(DATA_TARGET_A, 0);
        this.entityData.define(DATA_TARGET_B, 0);
        this.entityData.define(DATA_TARGET_C, 0);
        this.entityData.define(DATA_TARGET_D, 0);
        this.entityData.define(DATA_TARGET_E, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("Stage", this.getStage());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.setStage(nbt.getInt("Stage"));
        if (this.hasCustomName()) {
            this.bossBar.setName(this.getDisplayName());
        }

    }

    @Override
    public void performRangedAttack(LivingEntity entity, float f) {
        this.performRangedAttack(0, entity);
    }

    private void performRangedAttack(int head, LivingEntity entity) {
    /* SKIZZIK SKULL LEVEL! */    this.performRangedAttack(head, entity.getX(), entity.getY() + (double)entity.getEyeHeight() * 0.5D, entity.getZ(), 1);
    }

    private void performRangedAttack(int head, double x, double y, double z, int level) {
        if (!this.isSilent()) {
            this.level.levelEvent(null, 1024, this.blockPosition(), 0);
        }

        double headX = this.getHeadX(head);
        double headY = this.getHeadY(head);
        double headZ = this.getHeadZ(head);

        double targetX = x - headX;
        double targetY = y - headY;
        double targetZ = z - headZ;

        SkizzikSkull skull = new SkizzikSkull(this.level, this, targetX, targetY, targetZ);
        skull.setOwner(this);
        skull.setLevel(level);

        skull.setPosRaw(headX, headY, headZ);
        this.level.addFreshEntity(skull);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MobEntity.class, 0, false, false, PA_Entities.LIVING_ENTITY_SELECTOR));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    @Override
    public void baseTick() {
        super.baseTick();

        World world = getCommandSenderWorld();
        if (world instanceof ServerWorld) {
            ((ServerWorld) world).setDayTime(18000);
        }

        float health = this.getHealth();
        int currentStage = this.getStage();
        int newStage = health > 1020 ? 0 :
                        health > 870 ? 1 :
                        health > 670 ? 2 :
                        health > 470 ? 3 :
                        health > 270 ? 4 :
                        health > 20 ? 5 :
                        6;

        // The sleeping stage's boss bar name is set separately instead of together with the other boss bars.
        // This is because otherwise this boss bar will appear as just "Skizzik" until the entity is named with a name tag
        // or it's health is changed to something else and then back to 1021
        if (health == 1021) {
            if (this.hasCustomName()) {
                this.bossBar.setName(new StringTextComponent(this.getDisplayName().getString() + " - Sleeping"));
            }
            else {
                this.bossBar.setName(new StringTextComponent("Skizzik - Sleeping"));
            }
        }

        if (currentStage != newStage) {
            // I was previously checking for both old and new stage below
            // but I decided not to do that since there can be modded weapons that allow people to deal enough damage to skip a stage.
            // Also, people can use command blocks to skip stages or come back to others (I know because I did.)
            if (newStage == 1) {
                this.setHealth(1020);
            }
            else if (newStage == 2) {
                this.setHealth(870);
            }
            else if (newStage == 3) {
                this.setHealth(670);
            }
            else if (newStage == 4) {
                this.setHealth(470);
            }
            else if (newStage == 5) {
                this.setHealth(270);
            }
            else if (newStage == 6) {
                this.setHealth(20);
                bossBar.setColor(BossInfo.Color.WHITE);
            }

            if (world instanceof ServerWorld) {
                PA_Entities.SKIZZO.spawn((ServerWorld)world, null, null, this.blockPosition(), SpawnReason.MOB_SUMMONED, true, true);
            }

            world.addFreshEntity(EntityType.LIGHTNING_BOLT.create(world));

            //After Invul - world.playSound(null, new BlockPos(x, y,z), SoundEvents.ENDER_DRAGON_GROWL, SoundCategory.HOSTILE, (float) 10, (float) 1);

            if (this.hasCustomName()) {
                if (newStage >= 1 && newStage <= 5) {
                    this.bossBar.setName(new StringTextComponent(this.getDisplayName().getString() + " - Stage " + newStage));
                }
                else if (newStage == 6) {
                    this.bossBar.setName(new StringTextComponent(this.getDisplayName().getString() + " - FINISH HIM!"));
                }
            }
            else {
                if (newStage >= 1 && newStage <= 5) {
                    this.bossBar.setName(new StringTextComponent("Skizzik - Stage " + newStage));
                }
                else if (newStage == 6) {
                    this.bossBar.setName(new StringTextComponent("Skizzik - FINISH HIM!"));
                }
            }

            this.setStage(newStage);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        World world = this.getCommandSenderWorld();

        if (world instanceof ServerWorld) {
            LightningBoltEntity[] lightnings = {EntityType.LIGHTNING_BOLT.create(world), EntityType.LIGHTNING_BOLT.create(world), EntityType.LIGHTNING_BOLT.create(world), EntityType.LIGHTNING_BOLT.create(world)};
            
            lightnings[0].moveTo(Vector3d.atBottomCenterOf(new BlockPos(x, y, z + 100)));
            lightnings[1].moveTo(Vector3d.atBottomCenterOf(new BlockPos(x, y, z - 100)));
            lightnings[2].moveTo(Vector3d.atBottomCenterOf(new BlockPos(x + 100, y, z)));
            lightnings[3].moveTo(Vector3d.atBottomCenterOf(new BlockPos(x - 100, y, z)));

            for (LightningBoltEntity lightning : lightnings) {
                lightning.setVisualOnly(true);
                world.addFreshEntity(lightning);
            }
        }

        Entity entity = source.getEntity();
        if (entity != null && !(entity instanceof PlayerEntity) && entity instanceof LivingEntity && ((LivingEntity) entity).getMobType() == this.getMobType()) {
            return false;
        }
        else {
            if (this.destroyBlocksTick <= 0) {
                this.destroyBlocksTick = 20;
            }

            for (int i = 0; i < this.idleHeadUpdates.length; ++i) {
                this.idleHeadUpdates[i] += 3;
            }

            return super.hurt(source, amount);
        }
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
    }

    @Override
    public void aiStep() {
        Vector3d vector = this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D);
        if (!this.level.isClientSide && this.getAlternativeTarget(0) > 0) {
            Entity entity = this.level.getEntity(this.getAlternativeTarget(0));
            if (entity != null) {
                double vectorY = vector.y;
                if (this.getY() < entity.getY()) {
                    vectorY = Math.max(0.0D, vectorY);
                    vectorY = vectorY + (0.3D - vectorY * (double)0.6F);
                }

                vector = new Vector3d(vector.x, vectorY, vector.z);
                Vector3d vector1 = new Vector3d(entity.getX() - this.getX(), 0.0D, entity.getZ() - this.getZ());
                if (getHorizontalDistanceSqr(vector1) > 9.0D) {
                    Vector3d vector2 = vector1.normalize();
                    vector = vector.add(vector2.x * 0.3D - vector.x * 0.6D, 0.0D, vector2.z * 0.3D - vector.z * 0.6D);
                }
            }
        }

        this.setDeltaMovement(vector);
        if (getHorizontalDistanceSqr(vector) > 0.05D) {
            this.yRot = (float) MathHelper.atan2(vector.z, vector.x) * (180F / (float)Math.PI) - 90.0F;
        }

        super.aiStep();

        for(int i = 0; i < 4; ++i) {
            this.yRotHeads1[i] = this.yRotHeads[i];
            this.xRotHeads1[i] = this.xRotHeads[i];
        }

        for(int j = 0; j < 4; ++j) {
            int k = this.getAlternativeTarget(j + 1);
            Entity entity1 = null;
            if (k > 0) {
                entity1 = this.level.getEntity(k);
            }

            if (entity1 != null) {
                double headX = this.getHeadX(j + 1);
                double headY = this.getHeadY(j + 1);
                double headZ = this.getHeadZ(j + 1);

                double entityX = entity1.getX() - headX;
                double entityY = entity1.getEyeY() - headY;
                double entityZ = entity1.getZ() - headZ;

                double d7 = MathHelper.sqrt(entityX * entityX + entityZ * entityZ);

                float f = (float)(MathHelper.atan2(entityZ, entityX) * (double)(180F / (float)Math.PI)) - 90.0F;
                float f1 = (float)(-(MathHelper.atan2(entityY, d7) * (double)(180F / (float)Math.PI)));

                this.xRotHeads[j] = this.rotlerp(this.xRotHeads[j], f1, 40.0F);
                this.yRotHeads[j] = this.rotlerp(this.yRotHeads[j], f, 10.0F);
            }
            else {
                this.yRotHeads[j] = this.rotlerp(this.yRotHeads[j], this.yBodyRot, 10.0F);
            }
        }
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();

        for(int headIndex = 1; headIndex < 5; ++headIndex) {
            if (this.tickCount >= this.nextHeadUpdate[headIndex - 1]) {
                this.nextHeadUpdate[headIndex - 1] = this.tickCount + 10 + this.random.nextInt(10);
                int head = headIndex - 1;
                int idleHeadUpdate = this.idleHeadUpdates[headIndex - 1];
                this.idleHeadUpdates[head] = this.idleHeadUpdates[headIndex - 1] + 1;

                if (idleHeadUpdate > 15) {
                    double x = MathHelper.nextDouble(this.random, this.getX() - 10.0D, this.getX() + 10.0D);
                    double y = MathHelper.nextDouble(this.random, this.getY() - 5.0D, this.getY() + 5.0D);
                    double z = MathHelper.nextDouble(this.random, this.getZ() - 10.0D, this.getZ() + 10.0D);

                    /* SKIZZIK SKULL LEVEL! */ this.performRangedAttack(headIndex + 1, x, y, z, 1);
                    this.idleHeadUpdates[headIndex - 1] = 0;
                }

                int alternativeTarget = this.getAlternativeTarget(headIndex);

                if (alternativeTarget > 0) {
                    Entity target = this.level.getEntity(alternativeTarget);
                    if (target != null && target.isAlive() && !(this.distanceToSqr(target) > 900.0D) && this.canSee(target)) {
                        if (target instanceof PlayerEntity && ((PlayerEntity)target).abilities.invulnerable) {
                            this.setAlternativeTarget(headIndex, 0);
                        }
                        else {
                            /* SKIZZIK SKULL LEVEL! */    this.performRangedAttack(headIndex + 1, target.getX(), target.getY() + (double)target.getEyeHeight() * 0.5D, target.getZ(), 1);
                            this.nextHeadUpdate[headIndex - 1] = this.tickCount + 40 + this.random.nextInt(20);
                            this.idleHeadUpdates[headIndex - 1] = 0;
                        }
                    }
                    else {
                        this.setAlternativeTarget(headIndex, 0);
                    }
                }
                else {
                    List<LivingEntity> list = this.level.getNearbyEntities(LivingEntity.class, TARGETING_CONDITIONS, this, this.getBoundingBox().inflate(20.0D, 8.0D, 20.0D));

                    for(int i = 0; i < 10 && !list.isEmpty(); ++i) {
                        LivingEntity target = list.get(this.random.nextInt(list.size()));
                        if (target != this && target.isAlive() && this.canSee(target)) {
                            if (target instanceof PlayerEntity) {
                                if (!((PlayerEntity)target).abilities.invulnerable) {
                                    this.setAlternativeTarget(headIndex, target.getId());
                                }
                            }
                            else {
                                this.setAlternativeTarget(headIndex, target.getId());
                            }
                            break;
                        }
                        list.remove(target);
                    }
                }
            }
        }

        if (this.getTarget() != null) {
            this.setAlternativeTarget(0, this.getTarget().getId());
        }
        else {
            this.setAlternativeTarget(0, 0);
        }

        if (this.destroyBlocksTick > 0) {
            --this.destroyBlocksTick;

            if (this.destroyBlocksTick == 0 && getMobGriefingEvent(this.level, this)) {
                int y = MathHelper.floor(this.getY());
                int x = MathHelper.floor(this.getX());
                int z = MathHelper.floor(this.getZ());
                boolean canDestroy = false;

                for(int k2 = -1; k2 <= 1; ++k2) {
                    for(int l2 = -1; l2 <= 1; ++l2) {
                        for(int j = 0; j <= 3; ++j) {
                            int i = x + k2;
                            int k = y + j;
                            int l = z + l2;
                            BlockPos pos = new BlockPos(i, k, l);
                            BlockState state = this.level.getBlockState(pos);
                            if (state.canEntityDestroy(this.level, pos, this) && canDestroy(state) && onEntityDestroyBlock(this, pos, state)) {
                                canDestroy = this.level.destroyBlock(pos, true, this) || canDestroy;
                            }
                        }
                    }
                }

                if (canDestroy) {
                    this.level.levelEvent(null, 1022, this.blockPosition(), 0);
                }
            }
        }

        float health = this.getHealth();
        int stage = this.getStage();

        if (stage == 5 && health < 269) {
            if (this.tickCount % 20 == 0) {
                this.heal(1.0F);
            }
        }
        else if (stage == 4 && health < 469.5) {
            if (this.tickCount % 30 == 0) {
                this.heal(0.5F);
            }
        }
        else if (stage == 3 && health < 669.5) {
            if (this.tickCount % 30 == 0) {
                this.heal(0.5F);
            }
        }
        else if (stage == 2 && health < 869.5) {
            if (this.tickCount % 40 == 0) {
                this.heal(0.5F);
            }
        }
        else if (stage == 1 && health < 1019.5) {
            if (this.tickCount % 40 == 0) {
                this.heal(0.5F);
            }
        }

        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }
}
