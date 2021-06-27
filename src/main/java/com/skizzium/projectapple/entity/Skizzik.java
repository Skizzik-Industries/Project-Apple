package com.skizzium.projectapple.entity;

import com.google.common.collect.ImmutableList;
import com.skizzium.projectapple.init.PA_Entities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;

public class Skizzik extends MonsterEntity /* implements IChargeableMob, IRangedAttackMob */ {
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
    private final ServerBossInfo bossBar = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenScreen(true);
    private static final EntityPredicate TARGETING_CONDITIONS = (new EntityPredicate()).range(20.0D).selector(PA_Entities.LIVING_ENTITY_SELECTOR);

    public Skizzik(EntityType<? extends Skizzik> entity, World world) {
        super(entity, world);
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
        this.bossBar.setName(this.getDisplayName());
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

    public static boolean canDestroy(BlockState state) {
        return !state.isAir() && !BlockTags.WITHER_IMMUNE.contains(state.getBlock());
    }

    @Override
    public void makeStuckInBlock(BlockState state, Vector3d vector) {
    }

    public static AttributeModifierMap.MutableAttribute buildAttributes() {
        return MonsterEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0D)
                .add(Attributes.ARMOR, 8.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.FLYING_SPEED, 0.6D);
    }

    /* public int getStage() {
        return this.entityData.get(DATA_ID_STAGE);
    }

    public void setStage(int stage) {
        this.entityData.set(DATA_ID_STAGE, stage);
    } */

    /* @OnlyIn(Dist.CLIENT)
    public float getHeadYRot(int p_82207_1_) {
        return this.yRotHeads[p_82207_1_];
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadXRot(int p_82210_1_) {
        return this.xRotHeads[p_82210_1_];
    }

    private double getHeadX(int value) {
        if (value <= 0) {
            return this.getX();
        }
        else {
            float f = (this.yBodyRot + (float)(180 * (value - 1))) * ((float)Math.PI / 180F);
            float f1 = MathHelper.cos(f);
            return this.getX() + (double)f1 * 1.3D;
        }
    }

    private double getHeadY(int value) {
        return value <= 0 ? this.getY() + 3.0D : this.getY() + 2.2D;
    }

    private double getHeadZ(int value) {
        if (value <= 0) {
            return this.getZ();
        } else {
            float f = (this.yBodyRot + (float)(180 * (value - 1))) * ((float)Math.PI / 180F);
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
    } */

    @Override
    public void baseTick() {
        super.baseTick();

        World world = getCommandSenderWorld();
        if (world instanceof ServerWorld) {
            ((ServerWorld) world).setDayTime(18000);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        World world = this.getCommandSenderWorld();
        if (world instanceof ServerWorld) {
            LightningBoltEntity[] lightnings = {EntityType.LIGHTNING_BOLT.create(world), EntityType.LIGHTNING_BOLT.create(world), EntityType.LIGHTNING_BOLT.create(world), EntityType.LIGHTNING_BOLT.create(world)};

            lightnings[0].moveTo(Vector3d.atBottomCenterOf(new BlockPos(this.getX(), 0, this.getZ() + 150)));
            lightnings[1].moveTo(Vector3d.atBottomCenterOf(new BlockPos(this.getX(), 0, this.getZ() - 150)));
            lightnings[2].moveTo(Vector3d.atBottomCenterOf(new BlockPos(this.getX() + 150, 0, this.getZ())));
            lightnings[3].moveTo(Vector3d.atBottomCenterOf(new BlockPos(this.getX() - 150, 0, this.getZ())));

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

    /* @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MobEntity.class, 0, false, false, ModEntities.LIVING_ENTITY_SELECTOR));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TARGET_A, 0);
        this.entityData.define(DATA_TARGET_B, 0);
        this.entityData.define(DATA_TARGET_C, 0);
        this.entityData.define(DATA_TARGET_D, 0);
        this.entityData.define(DATA_TARGET_E, 0);
        this.entityData.define(DATA_ID_STAGE, 0);
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
    public void aiStep() {
        Vector3d vector = this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D);
        if (!this.level.isClientSide && this.getAlternativeTarget(0) > 0) {
            Entity entity = this.level.getEntity(this.getAlternativeTarget(0));
            if (entity != null) {
                double d0 = vector.y;
                if (this.getY() < entity.getY() || !this.isPowered() && this.getY() < entity.getY() + 5.0D) {
                    d0 = Math.max(0.0D, d0);
                    d0 = d0 + (0.3D - d0 * (double)0.6F);
                }

                vector = new Vector3d(vector.x, d0, vector.z);
                Vector3d vector1 = new Vector3d(entity.getX() - this.getX(), 0.0D, entity.getZ() - this.getZ());
                if (getHorizontalDistanceSqr(vector1) > 9.0D) {
                    Vector3d vector3d2 = vector1.normalize();
                    vector = vector.add(vector3d2.x * 0.3D - vector.x * 0.6D, 0.0D, vector3d2.z * 0.3D - vector.z * 0.6D);
                }
            }
        }

        this.setDeltaMovement(vector);
        if (getHorizontalDistanceSqr(vector) > 0.05D) {
            this.yRot = (float) MathHelper.atan2(vector.z, vector.x) * (180F / (float)Math.PI) - 90.0F;
        }

        super.aiStep();

        for(int i = 0; i < 2; ++i) {
            this.yRotHeads1[i] = this.yRotHeads[i];
            this.xRotHeads1[i] = this.xRotHeads[i];
        }

        for(int j = 0; j < 2; ++j) {
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

        for(int i = 1; i < 3; ++i) {
            if (this.tickCount >= this.nextHeadUpdate[i - 1]) {
                this.nextHeadUpdate[i - 1] = this.tickCount + 10 + this.random.nextInt(10);
                if (this.level.getDifficulty() == Difficulty.NORMAL || this.level.getDifficulty() == Difficulty.HARD) {
                    int j3 = i - 1;
                    int k3 = this.idleHeadUpdates[i - 1];
                    this.idleHeadUpdates[j3] = this.idleHeadUpdates[i - 1] + 1;
                    if (k3 > 15) {
                        double d0 = MathHelper.nextDouble(this.random, this.getX() - 10.0D, this.getX() + 10.0D);
                        double d1 = MathHelper.nextDouble(this.random, this.getY() - 5.0D, this.getY() + 5.0D);
                        double d2 = MathHelper.nextDouble(this.random, this.getZ() - 10.0D, this.getZ() + 10.0D);
                        this.performRangedAttack(i + 1, d0, d1, d2, true);
                        this.idleHeadUpdates[i - 1] = 0;
                    }
                }

                int k1 = this.getAlternativeTarget(i);
                if (k1 > 0) {
                    Entity target = this.level.getEntity(k1);
                    if (target != null && target.isAlive() && !(this.distanceToSqr(target) > 900.0D) && this.canSee(target)) {
                        if (target instanceof PlayerEntity && ((PlayerEntity)target).abilities.invulnerable) {
                            this.setAlternativeTarget(i, 0);
                        }
                        else {
                            this.performRangedAttack(i + 1, (LivingEntity)target);
                            this.nextHeadUpdate[i - 1] = this.tickCount + 40 + this.random.nextInt(20);
                            this.idleHeadUpdates[i - 1] = 0;
                        }
                    }
                    else {
                        this.setAlternativeTarget(i, 0);
                    }
                }
                else {
                    List<LivingEntity> list = this.level.getNearbyEntities(LivingEntity.class, TARGETING_CONDITIONS, this, this.getBoundingBox().inflate(20.0D, 8.0D, 20.0D));

                    for(int j2 = 0; j2 < 10 && !list.isEmpty(); ++j2) {
                        LivingEntity target = list.get(this.random.nextInt(list.size()));
                        if (target != this && target.isAlive() && this.canSee(target)) {
                            if (target instanceof PlayerEntity) {
                                if (!((PlayerEntity)target).abilities.invulnerable) {
                                    this.setAlternativeTarget(i, target.getId());
                                }
                            }
                            else {
                                this.setAlternativeTarget(i, target.getId());
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
            if (this.destroyBlocksTick == 0 && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
                int i1 = MathHelper.floor(this.getY());
                int l1 = MathHelper.floor(this.getX());
                int i2 = MathHelper.floor(this.getZ());
                boolean flag = false;

                for(int k2 = -1; k2 <= 1; ++k2) {
                    for(int l2 = -1; l2 <= 1; ++l2) {
                        for(int j = 0; j <= 3; ++j) {
                            int i3 = l1 + k2;
                            int k = i1 + j;
                            int l = i2 + l2;
                            BlockPos blockpos = new BlockPos(i3, k, l);
                            BlockState blockstate = this.level.getBlockState(blockpos);
                            if (blockstate.canEntityDestroy(this.level, blockpos, this) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this, blockpos, blockstate)) {
                                flag = this.level.destroyBlock(blockpos, true, this) || flag;
                            }
                        }
                    }
                }

                if (flag) {
                    this.level.levelEvent(null, 1022, this.blockPosition(), 0);
                }
            }
        }

        if (this.tickCount % 20 == 0) {
            this.heal(1.0F);
        }

        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    private void performRangedAttack(int i, LivingEntity entity) {
        this.performRangedAttack(i, entity.getX(), entity.getY() + (double)entity.getEyeHeight() * 0.5D, entity.getZ(), i == 0 && this.random.nextFloat() < 0.001F);
    }

    private void performRangedAttack(int i0, double d6, double d7, double d8, boolean bool) {
        if (!this.isSilent()) {
            this.level.levelEvent(null, 1024, this.blockPosition(), 0);
        }

        double d0 = this.getHeadX(i0);
        double d1 = this.getHeadY(i0);
        double d2 = this.getHeadZ(i0);
        double d3 = d6 - d0;
        double d4 = d7 - d1;
        double d5 = d8 - d2;
        WitherSkullEntity skull = new WitherSkullEntity(this.level, this, d3, d4, d5);
        skull.setOwner(this);
        if (bool) {
            skull.setDangerous(true);
        }

        skull.setPosRaw(d0, d1, d2);
        this.level.addFreshEntity(skull);
    }

    @Override
    public void performRangedAttack(LivingEntity entity, float p_82196_2_) {
        this.performRangedAttack(0, entity);
    }

    public int getAlternativeTarget(int p_82203_1_) {
        return this.entityData.get(DATA_TARGETS.get(p_82203_1_));
    }

    public void setAlternativeTarget(int p_82211_1_, int p_82211_2_) {
        this.entityData.set(DATA_TARGETS.get(p_82211_1_), p_82211_2_);
    }

    public boolean isPowered() {
        return this.getHealth() <= this.getMaxHealth() / 2.0F;
    }

    public boolean canBeAffected(EffectInstance effect) {
        return effect.getEffect() == Effects.WITHER ? false : super.canBeAffected(effect);
    } */
}
