package com.skizzium.projectapple.entity.boss.skizzik;

import com.google.common.collect.ImmutableList;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.entity.boss.skizzik.util.FriendlySkizzikGoalController;
import com.skizzium.projectapple.gui.bossevent.PA_BossEvent;
import com.skizzium.projectapple.gui.bossevent.PA_ServerBossEvent;
import com.skizzium.projectapple.init.PA_ClientHelper;
import com.skizzium.projectapple.init.PA_GUI;
import com.skizzium.projectapple.init.PA_Tags;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.init.item.PA_Items;
import com.skizzium.projectapple.init.network.PA_PacketRegistry;
import com.skizzium.projectapple.item.Gem;
import com.skizzium.projectapple.network.BossMusicStopPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.fmllegacy.network.NetworkDirection;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent;

public class FriendlySkizzik extends Monster implements RangedAttackMob, IAnimatable {
    private static final EntityDataAccessor<Integer> DATA_ADDED_GEMS = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ADDED_HEADS = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_RIDDEN_HEADS = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_COMMAND_BLOCK = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_CONVERTED = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.BOOLEAN);
    
    private static final EntityDataAccessor<Integer> DATA_TARGET_A = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_B = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_C = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_D = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_E = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.INT);
    private static final List<EntityDataAccessor<Integer>> DATA_TARGETS = ImmutableList.of(DATA_TARGET_A, DATA_TARGET_B, DATA_TARGET_C, DATA_TARGET_D, DATA_TARGET_E);

    private EnumSet<Gem.GemType> addedGems = EnumSet.noneOf(Gem.GemType.class);
    private EnumSet<FriendlySkizzik.Heads> riddenHeads = EnumSet.noneOf(FriendlySkizzik.Heads.class);

    private final float[] xRotHeads = new float[4];
    private final float[] yRotHeads = new float[4];

    private final float[] xRotHeads1 = new float[4];
    private final float[] yRotHeads1 = new float[4];

    private float skullCooldown;
    private final int[] nextHeadUpdate = new int[4];
    
    private final AnimationFactory factory = new AnimationFactory(this);
    private final FriendlySkizzikGoalController goalController;

    private static final TargetingConditions TARGETING_CONDITIONS = TargetingConditions.forCombat().range(20.0D).selector(PA_Entities.FRIENDLY_SKIZZIK_SELECTOR);
    public final PA_ServerBossEvent bossBar = new PA_ServerBossEvent(this.getDisplayName(), PA_BossEvent.PA_BossBarColor.AQUA, PA_BossEvent.PA_BossBarOverlay.PROGRESS);
    
    public FriendlySkizzik(EntityType<? extends FriendlySkizzik> entity, Level world) {
        super(entity, world);
        this.setHealth(this.getMaxHealth());
        this.getNavigation().setCanFloat(true);
        this.xpReward = 0;
        this.goalController = new FriendlySkizzikGoalController(this);
    }

    @Override
    protected Component getTypeName() {
        return new TranslatableComponent(ProjectApple.getThemedDescriptionId(super.getType().getDescriptionId()));
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
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
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return 2.45F;
    }

    @Override
    public void startSeenByPlayer(ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.bossBar.addPlayer(serverPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.bossBar.removePlayer(serverPlayer);
        PA_PacketRegistry.INSTANCE.sendTo(new BossMusicStopPacket(), serverPlayer.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        this.bossBar.setName(new TextComponent(this.getDisplayName().getString()));
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

//    @Override
//    public EntityDimensions getDimensions(Pose pose) {
//        return this.stageManager.getCurrentStage().hitbox();
//    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }

    @Override
    public boolean causeFallDamage(float f0, float f1, DamageSource source) {
        return false;
    }

    @Override
    public boolean addEffect(MobEffectInstance effect, @Nullable Entity entity) {
        return false;
    }

    @Override
    public void makeStuckInBlock(BlockState state, Vec3 vector) {}

    public static boolean canDestroy(BlockState state) {
        return !state.isAir() && !state.is(BlockTags.WITHER_IMMUNE); //BlockTags.WITHER_IMMUNE.contains(state.getBlock());
    }

    public static AttributeSupplier.Builder buildAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1020.0D)
                .add(Attributes.ARMOR, 0.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.FLYING_SPEED, 0.6D);
    }

    public boolean isCommandBlockPlaced() {
        return this.entityData.get(DATA_COMMAND_BLOCK);
    }

    public void setCommandBlockPlaced(boolean flag) {
        this.entityData.set(DATA_COMMAND_BLOCK, flag);
    }
    
    public boolean isConverted() {
        return this.entityData.get(DATA_CONVERTED);
    }

    public void setConverted(boolean flag) {
        this.entityData.set(DATA_CONVERTED, flag);
    }
    
    public int getAddedHeads() {
        return this.entityData.get(DATA_ADDED_HEADS);
    }

    public void addHead() {
        if (this.getAddedHeads() + 1 <= 4) {
            this.entityData.set(DATA_ADDED_HEADS, this.getAddedHeads() + 1);
        }
    }
    
    public void setAddedHeads(int value) {
        this.entityData.set(DATA_ADDED_HEADS, value);
    }
    
    public int getAlternativeTarget(int head) {
        return this.entityData.get(DATA_TARGETS.get(head));
    }

    public void setAlternativeTarget(int head, int target) {
        this.entityData.set(DATA_TARGETS.get(head), target);
    }

    @Override
    public boolean canBeAffected(MobEffectInstance effect) {
        return false;
    }

    private <E extends IAnimatable> PlayState ambient(AnimationEvent<E> event) {
        if (this.isConverted()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.skizzik.end_convert"));
            if (event.getController().getAnimationState() == AnimationState.Stopped) {
                this.setConverted(false);
            }
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.skizzik.body_movement"));
        return PlayState.CONTINUE;
    }
    
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "ambient", 0, this::ambient));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadYRot(int head) {
        return this.yRotHeads[head];
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadXRot(int head) {
        return this.xRotHeads[head];
    }

    public double getHeadX(int head) {
        if (head <= 0) {
            return this.getX();
        }
        else {
            float f = (this.yBodyRot + (float)(180 * (head - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.cos(f);

            return head <= 2 ? this.getX() + (double)f1 * 1.3D :
                    head == 3 ? this.getX() + f1 * 1.2D :
                            this.getX() + (double)f1 * 0.8D;
        }
    }

    public double getHeadY(int head) {
        return head == 0 ? this.getY() + 2.5D :
                head == 1 || head == 2 ? this.getY() + 1.8 :
                        head == 3 ? this.getY() + 3.3 :
                                this.getY() + 3.4;
    }

    public double getHeadZ(int head) {
        if (head <= 0) {
            return this.getZ();
        } else {
            float f = (this.yBodyRot + (float)(180 * (head - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.sin(f);
            return this.getZ() + (double)f1 * 1.3D;
        }
    }

    private float rotlerp(float f1, float f2, float f3) {
        float f = Mth.wrapDegrees(f2 - f1);
        if (f > f3) {
            f = f3;
        }

        if (f < -f3) {
            f = -f3;
        }

        return f1 + f;
    }

    public void addGem(Gem.GemType gem) {
        this.entityData.set(DATA_ADDED_GEMS, this.entityData.get(DATA_ADDED_GEMS) | 1 << gem.ordinal());
    }

    public Set<Gem.GemType> getGems() {
        return addedGems;
    }

    private void updateAddedGems() {
        var set = EnumSet.noneOf(Gem.GemType.class);
        int addedGems = this.entityData.get(DATA_ADDED_GEMS);
        for (Gem.GemType gem : Gem.GemType.values()) {
            if ((addedGems & (1 << gem.ordinal())) != 0) {
                set.add(gem);
            }
        }
        this.addedGems = set;
    }

    public void addRiddenHead(FriendlySkizzik.Heads head) {
        this.entityData.set(DATA_RIDDEN_HEADS, this.entityData.get(DATA_RIDDEN_HEADS) | 1 << head.ordinal());
    }

    public Set<FriendlySkizzik.Heads> getRiddenHeads() {
        return riddenHeads;
    }
    
    public void setRiddenHeads(int number) {
        var set = EnumSet.noneOf(FriendlySkizzik.Heads.class);
        for (FriendlySkizzik.Heads head : FriendlySkizzik.Heads.values()) {
            if (head.ordinal() <= number) {
                set.add(head);
            }
        }
        this.riddenHeads = set;
    }

    private void updateRiddenHeads() {
        var set = EnumSet.noneOf(FriendlySkizzik.Heads.class);
        int riddenHeads = this.entityData.get(DATA_RIDDEN_HEADS);
        for (FriendlySkizzik.Heads head : FriendlySkizzik.Heads.values()) {
            if ((riddenHeads & (1 << head.ordinal())) != 0) {
                set.add(head);
            }
        }
        this.riddenHeads = set;
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (key.equals(DATA_ADDED_GEMS)) {
            this.updateAddedGems();
        }
        if (key.equals(DATA_RIDDEN_HEADS)) {
            this.updateRiddenHeads();
        }
    }
    
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(DATA_ADDED_GEMS, 0);
        this.entityData.define(DATA_ADDED_HEADS, 0);
        this.entityData.define(DATA_RIDDEN_HEADS, 0);
        this.entityData.define(DATA_COMMAND_BLOCK, false);
        this.entityData.define(DATA_CONVERTED, false);
        
        this.entityData.define(DATA_TARGET_A, 0);
        this.entityData.define(DATA_TARGET_B, 0);
        this.entityData.define(DATA_TARGET_C, 0);
        this.entityData.define(DATA_TARGET_D, 0);
        this.entityData.define(DATA_TARGET_E, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);

        ListTag gemNBTList = new ListTag();
        ListTag headNBTList = new ListTag();
        
        for (Gem.GemType gem : this.getGems()) {
            gemNBTList.add(StringTag.valueOf(gem.name().toLowerCase()));
        }

        for (FriendlySkizzik.Heads head : this.getRiddenHeads()) {
            headNBTList.add(StringTag.valueOf(head.name().toLowerCase()));
        }
        
        nbt.put("Gems", gemNBTList);
        nbt.put("RiddenHeads", headNBTList);
        nbt.putInt("AddedHeads", this.getAddedHeads());
        nbt.putBoolean("CommandBlock", this.isCommandBlockPlaced());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

        this.setAddedHeads(nbt.getInt("AddedHeads"));
        this.setCommandBlockPlaced(nbt.getBoolean("CommandBlock"));
        
        int addedGems = 0;
        for (Tag nbtTag : nbt.getList("Gems", Tag.TAG_STRING)) {
            addedGems |= 1 << Gem.GemType.valueOf(nbtTag.getAsString().toUpperCase()).ordinal();
        }

        int riddenHeads = 0;
        for (Tag nbtTag : nbt.getList("RiddenHeads", Tag.TAG_STRING)) {
            riddenHeads |= 1 << FriendlySkizzik.Heads.valueOf(nbtTag.getAsString().toUpperCase()).ordinal();
        }
        
        this.entityData.set(DATA_ADDED_GEMS, addedGems);
        this.entityData.set(DATA_RIDDEN_HEADS, riddenHeads);
        
        if (this.hasCustomName()) {
            this.bossBar.setName(this.getDisplayName());
        }
    }

    @Override
    public void positionRider(Entity passenger) {
        if (this.hasPassenger(passenger)) {
            Vec3 vec3 = new Vec3(this.getHeadX(this.getPassengers().indexOf(passenger)), this.getHeadY(this.getPassengers().indexOf(passenger)), this.getHeadZ(this.getPassengers().indexOf(passenger)));
            passenger.setPos(vec3.x, vec3.y, vec3.z);
        }
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().size() < 5;
    }

    @Nullable
    @Override
    public Entity getControllingPassenger() {
        return this.getFirstPassenger();
    }

    @Override
    public boolean canBeControlledByRider() {
        return this.getControllingPassenger() instanceof LivingEntity;
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity entity) {
        this.setRiddenHeads(this.getPassengers().size() - 1);
        
        if (this.getPassengers().size() <= 0) {
            this.setNoGravity(false);
        }
        
        if (this.level.isClientSide) {
            OverlayRegistry.enableOverlay(PA_GUI.HEAD_STATUS, false);
        }
        
        return super.getDismountLocationForPassenger(entity);
    }

    private void doPlayerRide(Player player) {
        if (!this.level.isClientSide) {
            player.setYRot(this.getYRot());
            player.setXRot(this.getXRot());
            player.startRiding(this);
            this.setNoGravity(true);
            this.setRiddenHeads(this.getPassengers().size() - 1);
        }
        else {
            if (this.getRiddenHeads().size() < 1) {
                OverlayRegistry.enableOverlay(PA_GUI.HEAD_STATUS, true);
            }        
        }
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        Item item = player.getItemInHand(hand).getItem();
        if (item instanceof Gem && PA_Tags.Items.SKIZZIK_BASE_GEMS.contains(item)) {
            if (!this.getGems().contains(((Gem) item).getType()) && !this.level.isClientSide) {
                this.addGem(((Gem) item).getType());
            }
            return InteractionResult.sidedSuccess(player.level.isClientSide);
        }
        else if (item == PA_Items.SMALL_SKIZZIK_HEAD_WITH_GEMS.get()) {
            // TODO: Check if there are available heads
            this.addHead();
            return InteractionResult.sidedSuccess(player.level.isClientSide);
        }
        else if (item == PA_Blocks.COMMAND_BLOCK.get().asItem()) {
            this.setCommandBlockPlaced(true);
            return InteractionResult.sidedSuccess(player.level.isClientSide);
        }
        
        this.doPlayerRide(player);
        return super.mobInteract(player, hand);
    }

    @Override
    public void travel(Vec3 pos) {
        if (this.isAlive()) {
            if (this.isVehicle() && this.canBeControlledByRider()) {
                LivingEntity livingentity = (LivingEntity)this.getControllingPassenger();

                this.setRot(this.getYRot(), this.getXRot());
                this.setXRot(livingentity.getXRot() * 0.5F);
                this.setYRot(livingentity.getYRot());

                this.yRotO = this.getYRot();
                this.yHeadRot = livingentity.yHeadRot;
                this.yBodyRot = this.getYRot();

                float moveX = livingentity.xxa * 0.5F;
                double moveY;
                float moveZ = livingentity.zza;
                if (moveZ <= 0.0F) {
                    moveZ *= 0.25F;
                }

                this.flyingSpeed = this.getSpeed() * 0.5F;
                if (this.isControlledByLocalInstance()) {
                    if (PA_ClientHelper.getClient().options.keyJump.isDown()) {
                        moveY = 0.8F;
                    }
                    else if (PA_ClientHelper.getClient().options.keySprint.isDown()) {
                        moveY = -0.8F;
                    }
                    else {
                        moveY = 0.0F;
                    }

                    this.setSpeed((float)this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    this.setDeltaMovement(this.getDeltaMovement().scale(0.9F));
                    super.travel(new Vec3(moveX, moveY, moveZ));
                }
                else if (livingentity instanceof Player) {
                    this.setDeltaMovement(Vec3.ZERO);
                }

                this.calculateEntityAnimation(this, false);
                this.tryCheckInsideBlocks();
            }
            else {
                this.flyingSpeed = 0.02F;
                super.travel(pos);
            }
        }
    }
    
    @Override
    public void performRangedAttack(LivingEntity entity, float f) {
        this.performRangedAttack(0, entity);
    }

    private void performRangedAttack(int head, LivingEntity entity) {
        this.performRangedAttack(head, entity.getX(), entity.getY() + (double)entity.getEyeHeight() * 0.5D, entity.getZ());
    }

    private void performRangedAttack(int head, double x, double y, double z) {
        if (!this.isSilent()) {
            this.level.levelEvent(null, 1024, this.blockPosition(), 0);
        }

        double headX = this.getHeadX(head);
        double headY = this.getHeadY(head);
        double headZ = this.getHeadZ(head);

        double targetX = x - headX;
        double targetY = y - headY;
        double targetZ = z - headZ;

        FriendlySkizzikSkull skull = new FriendlySkizzikSkull(this.level, this, targetX, targetY, targetZ);

        skull.setPosRaw(headX, headY, headZ);
        this.level.addFreshEntity(skull);
    }

    private void performRangedAttack(Player player) {
        if (!this.isSilent()) {
            this.level.levelEvent(null, 1024, this.blockPosition(), 0);
        }

        float xRot = player.getXRot();
        float yRot = player.getYRot();
        float zRot = 0.0F;
        
        float x = -Mth.sin(yRot * ((float)Math.PI / 180F)) * Mth.cos(xRot * ((float)Math.PI / 180F));
        float y = -Mth.sin((xRot + zRot) * ((float)Math.PI / 180F));
        float z = Mth.cos(yRot * ((float)Math.PI / 180F)) * Mth.cos(xRot * ((float)Math.PI / 180F));

        FriendlySkizzikSkull skull = new FriendlySkizzikSkull(this.level, player, x, y, z);
        skull.setPosRaw(this.getX(), this.getEyeY() + (player.getEyeY() - this.getEyeY()), this.getZ());
        this.level.addFreshEntity(skull);
    }

    public void killAllSkizzies(Level world, boolean skizziesOnly) {
        if (world instanceof ServerLevel) {
            LevelEntityGetter<Entity> entityGetter = ((ServerLevel) world).getEntities();
            Iterable<Entity> entities = entityGetter.getAll();
            for (Entity entity : entities) {
                if (entity instanceof Skizzie) {
                    if (((Skizzie) entity).getOwner() == this) {
                        entity.kill();
                    }
                }
                else if (!skizziesOnly && entity instanceof Skizzo) {
                    if (((Skizzo) entity).getOwner() == this) {
                        entity.kill();
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag nbt) {
        if (spawnReason == MobSpawnType.CONVERSION) {
            this.setConverted(true);
        }
        
        Explosion.BlockInteraction explosion = getMobGriefingEvent(this.level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 3.0F, false, explosion);
        
        return super.finalizeSpawn(level, difficulty, spawnReason, spawnData, nbt);
    }

    @Override
    public void baseTick() {
        super.baseTick();

        this.refreshDimensions();
//        this.stageManager.updateStage();

//        int currentStageId = this.stageManager.getCurrentStage().getStage().getId();
//        this.activeHeads = currentStageId == 1 ? 4 :
//                                currentStageId == 2 ? 3 :
//                                        currentStageId == 3 ? 2 :
//                                                currentStageId == 4 ? 1 : 0;
//        this.activeHeads = 4;
        Level world = this.level;

//        this.getAttributes().getInstance(Attributes.ARMOR).setBaseValue(this.stageManager.getCurrentStage().armorValue());
//        this.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(this.stageManager.getCurrentStage().maxHealth());
//
//        if (!this.isInvul()) {
//            this.bossBar.setName(this.stageManager.getCurrentStage().displayName());
//            this.bossBar.setColor(this.stageManager.getCurrentStage().barColor());
//        }
//        else {
//            this.bossBar.setName(new TextComponent(String.format("%s - %s", this.getDisplayName().getString(), new TranslatableComponent("entity.skizzik.skizzik.invulnerable").getString())));
//            this.bossBar.setColor(PA_BossEvent.PA_BossBarColor.AQUA);
//        }
//        this.bossBar.setOverlay(this.stageManager.getCurrentStage().barOverlay());

//        if (world instanceof ServerLevel) {
//            if (ProjectApple.holiday == 1 && !(this.stageManager.getCurrentStage() instanceof SkizzikFinishHim)) {
//                ((ServerLevel) world).setDayTime(18000);
//            }
//            else {
//                if (this.stageManager.getCurrentStage() instanceof SkizzikStage3) {
//                    ((ServerLevel) world).setDayTime(13000);
//                }
//                else if (this.stageManager.getCurrentStage() instanceof SkizzikStage4 || this.stageManager.getCurrentStage() instanceof SkizzikStage5) {
//                    ((ServerLevel) world).setDayTime(18000);
//                }
//            }
//        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Entity entity = source.getEntity();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Level world = this.level;

        if ((!(entity instanceof Player) && entity instanceof LivingEntity && ((LivingEntity) entity).getMobType() == this.getMobType()) /*|| SkizzikStages.isImmune(this, source)*/) {
            return false;
        }
        else {
//            if (this.destroyBlocksTicks <= 0) {
//                this.destroyBlocksTicks = 35;
//            }

            if (world instanceof ServerLevel) {
                LightningBolt[] lightnings = {EntityType.LIGHTNING_BOLT.create(world), EntityType.LIGHTNING_BOLT.create(world), EntityType.LIGHTNING_BOLT.create(world), EntityType.LIGHTNING_BOLT.create(world)};

                lightnings[0].moveTo(Vec3.atBottomCenterOf(new BlockPos(x, y, z + 100)));
                lightnings[1].moveTo(Vec3.atBottomCenterOf(new BlockPos(x, y, z - 100)));
                lightnings[2].moveTo(Vec3.atBottomCenterOf(new BlockPos(x + 100, y, z)));
                lightnings[3].moveTo(Vec3.atBottomCenterOf(new BlockPos(x - 100, y, z)));

                for (LightningBolt lightning : lightnings) {
                    lightning.setVisualOnly(true);
                    world.addFreshEntity(lightning);
                }
            }

            return super.hurt(source, amount);
        }
    }

    @Override
    public void aiStep() {
        if (this.getFirstPassenger() == null) {
            Vec3 vector = this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D);
            if (!this.level.isClientSide && this.getAlternativeTarget(0) > 0) {
                Entity entity = this.level.getEntity(this.getAlternativeTarget(0));
                if (entity != null) {
                    double vectorY = vector.y;
                    if (this.getY() < entity.getY()) {
                        vectorY = Math.max(0.0D, vectorY);
                        vectorY = vectorY + (0.3D - vectorY * (double) 0.6F);
                    }
    
                    vector = new Vec3(vector.x, vectorY, vector.z);
                    Vec3 vector1 = new Vec3(entity.getX() - this.getX(), 0.0D, entity.getZ() - this.getZ());
                    if (vector1.horizontalDistanceSqr() > 9.0D) {
                        Vec3 vector2 = vector1.normalize();
                        vector = vector.add(vector2.x * 0.3D - vector.x * 0.6D, 0.0D, vector2.z * 0.3D - vector.z * 0.6D);
                    }
                }
            }
    
            this.setDeltaMovement(vector);
            if (vector.horizontalDistanceSqr() > 0.05D) {
                this.setYRot((float) Mth.atan2(vector.z, vector.x) * (180F / (float) Math.PI) - 90.0F);
            }
        }

        super.aiStep();

        for (int i = 0; i < this.getAddedHeads(); ++i) {
            this.yRotHeads1[i] = this.yRotHeads[i];
            this.xRotHeads1[i] = this.xRotHeads[i];
        }

        for (int j = 0; j < this.getAddedHeads(); ++j) {
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

                double d7 = Math.sqrt(entityX * entityX + entityZ * entityZ);

                float f = (float) (Mth.atan2(entityZ, entityX) * (double) (180F / (float) Math.PI)) - 90.0F;
                float f1 = (float) (-(Mth.atan2(entityY, d7) * (double) (180F / (float) Math.PI)));

                this.xRotHeads[j] = this.rotlerp(this.xRotHeads[j], f1, 40.0F);
                this.yRotHeads[j] = this.rotlerp(this.yRotHeads[j], f, 10.0F);
            }
            else {
                this.yRotHeads[j] = this.rotlerp(this.yRotHeads[j], this.yBodyRot, 10.0F);
            }
        }

        for(int l = 0; l < this.getAddedHeads() + 1; ++l) {
            double headX = this.getHeadX(l);
            double heaxY = this.getHeadY(l);
            double headZ = this.getHeadZ(l);
            if (l == 0) {
                this.level.addParticle(ParticleTypes.DRIPPING_WATER, headX + this.random.nextGaussian() * (double)0.5F, heaxY + this.random.nextGaussian() * (double)0.5F, headZ + this.random.nextGaussian() * (double)0.5F, 0.0D, 0.0D, 0.0D);
            }
            else {
                this.level.addParticle(ParticleTypes.DRIPPING_WATER, headX + this.random.nextGaussian() * (double) 0.3F, heaxY + this.random.nextGaussian() * (double) 0.3F, headZ + this.random.nextGaussian() * (double) 0.3F, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();

//        if (this.spawnSkizzieTicks <= 0) {
//            this.spawnSkizzieTicks = 60;
//        }

        if (this.getPassengers().isEmpty()) {
            this.goalController.addDefaultGoals();
            
            for (int headIndex = 1; headIndex < this.getAddedHeads() + 1; ++headIndex) {
                if (this.tickCount >= this.nextHeadUpdate[headIndex - 1]) {
                    this.nextHeadUpdate[headIndex - 1] = this.tickCount + 10 + this.random.nextInt(10);

                    int alternativeTarget = this.getAlternativeTarget(headIndex);

                    if (alternativeTarget > 0) {
                        Entity target = this.level.getEntity(alternativeTarget);
                        if (target != null && target.isAlive() && !(this.distanceToSqr(target) > 900.0D) && this.hasLineOfSight(target)) {
                            if (target instanceof Player && ((Player) target).getAbilities().invulnerable) {
                                this.setAlternativeTarget(headIndex, 0);
                            } else {
                                this.performRangedAttack(headIndex + 1, target.getX(), target.getY() + (double) target.getEyeHeight() * 0.5D, target.getZ());
                                this.nextHeadUpdate[headIndex - 1] = this.tickCount + 40 + this.random.nextInt(20);
                            }
                        } else {
                            this.setAlternativeTarget(headIndex, 0);
                        }
                    } else {
                        List<LivingEntity> list = this.level.getNearbyEntities(LivingEntity.class, TARGETING_CONDITIONS, this, this.getBoundingBox().inflate(20.0D, 8.0D, 20.0D));

                        for (int i = 0; i < 10 && !list.isEmpty(); ++i) {
                            LivingEntity target = list.get(this.random.nextInt(list.size()));
                            if (target != this && target.isAlive() && this.hasLineOfSight(target)) {
                                if (target instanceof Player) {
                                    if (!((Player) target).getAbilities().invulnerable) {
                                        this.setAlternativeTarget(headIndex, target.getId());
                                    }
                                } else {
                                    this.setAlternativeTarget(headIndex, target.getId());
                                }
                                break;
                            }
                            list.remove(target);
                        }
                    }
                }
            }
        }
        else {
            if (skullCooldown <= 0.0F && PA_ClientHelper.getClient().options.keyUse.isDown()) {
                this.performRangedAttack(PA_ClientHelper.getClient().player);
                skullCooldown = 0.5F;
            }
            skullCooldown -= 0.1F;
        }
        
        if (this.getTarget() != null) {
            this.setAlternativeTarget(0, this.getTarget().getId());
        }
        else {
            this.setAlternativeTarget(0, 0);
        }

        this.bossBar.setProgress(this.getHealth() / this.getMaxHealth());
    }

    public enum Heads {
        BOTTOM_RIGHT_HEAD,
        BOTTOM_LEFT_HEAD,
        TOP_RIGHT_HEAD,
        TOP_LEFT_HEAD
    }
}
