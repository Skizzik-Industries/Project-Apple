package com.skizzium.projectapple.entity.boss.skizzik;

import com.google.common.collect.ImmutableList;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.*;
import com.skizzium.projectapple.entity.boss.skizzik.stages.*;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base.*;
import com.skizzium.projectapple.gui.PA_BossEvent;
import com.skizzium.projectapple.gui.PA_ServerBossEvent;
import com.skizzium.projectapple.init.network.PA_PacketRegistry;
import com.skizzium.projectapple.init.PA_SoundEvents;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.network.BossMusicStartPacket;
import com.skizzium.projectapple.network.BossMusicStopPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundAddMobPacket;
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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.fmllegacy.network.NetworkDirection;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent;
import static net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock;

public class Skizzik extends Monster implements RangedAttackMob, IAnimatable {
    private static final EntityDataAccessor<Integer> DATA_TARGET_A = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_B = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_C = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_D = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_E = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.INT);
    private static final List<EntityDataAccessor<Integer>> DATA_TARGETS = ImmutableList.of(DATA_TARGET_A, DATA_TARGET_B, DATA_TARGET_C, DATA_TARGET_D, DATA_TARGET_E);

    public static final EntityDataAccessor<Integer> DATA_STAGE = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_TRANSITIONING = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_TRANSITION_TIME = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_INVUL = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.BOOLEAN);

    private int activeHeads = 4;

    private final float[] xRotHeads = new float[activeHeads];
    private final float[] yRotHeads = new float[activeHeads];

    private final float[] xRotHeads1 = new float[activeHeads];
    private final float[] yRotHeads1 = new float[activeHeads];

    private final int[] nextHeadUpdate = new int[activeHeads];
    private final int[] idleHeadUpdates = new int[activeHeads];

    //private final Skizzo[] skizzos = new Skizzo[4];

    public final SkizzikStageManager stageManager;
    private float eyeHeight;
    private boolean debug;
    private AnimationFactory factory = new AnimationFactory(this);
    
    private int destroyBlocksTicks;
    private int spawnSkizzieTicks;

    private final SkizzikPart[] parts;
    public final SkizzikPart topLeftHead;
    public final SkizzikPart topRightHead;
    public final SkizzikPart bottomLeftHead;
    public final SkizzikPart bottomRightHead;
    public final SkizzikPart centerHead;
    public final SkizzikPart commandBlockPart;
    public final SkizzikPart bodyPart;
    
    private static final TargetingConditions TARGETING_CONDITIONS = TargetingConditions.forCombat().range(20.0D).selector(PA_Entities.SKIZZIK_SELECTOR);
    public final PA_ServerBossEvent bossBar = (PA_ServerBossEvent) new PA_ServerBossEvent(this.getDisplayName(), PA_BossEvent.PA_BossBarColor.WHITE, PA_BossEvent.PA_BossBarOverlay.PROGRESS).setDarkenScreen(true);

    public AvoidEntityGoal avoidPlayerGoal = new AvoidEntityGoal<>(this, Player.class, 25, 1.2D, 1.7D);
    public PanicGoal panicGoal = new PanicGoal(this, 1.5D);

    public HurtByTargetGoal hurtGoal = new HurtByTargetGoal(this);
    public NearestAttackableTargetGoal attackGoal = new NearestAttackableTargetGoal<>(this, Mob.class, 0, false, false, PA_Entities.SKIZZIK_SELECTOR);
    public RangedAttackGoal rangedAttackGoal = new RangedAttackGoal(this, 1.0D, 40, 20.0F);
    public WaterAvoidingRandomStrollGoal avoidWaterGoal = new WaterAvoidingRandomStrollGoal(this, 1.0D);
    public LookAtPlayerGoal lookGoal = new LookAtPlayerGoal(this, Player.class, 8.0F);
    public RandomLookAroundGoal lookRandomlyGoal = new RandomLookAroundGoal(this);

    private boolean preview = false;
    
    public Skizzik(EntityType<? extends Skizzik> entity, Level world) {
        super(entity, world);
        this.stageManager = new SkizzikStageManager(this);
        this.setHealth(this.getMaxHealth());
        this.getNavigation().setCanFloat(true);
        this.xpReward = 0;
        this.eyeHeight = 1.5F;

        this.topLeftHead = new SkizzikPart(this, "topLeftHead", SkizzikStages.STAGE_2, 0.75F, 0.75F);
        this.topRightHead = new SkizzikPart(this, "topRightHead", SkizzikStages.STAGE_3, 0.75F, 0.75F);
        this.bottomLeftHead = new SkizzikPart(this, "bottomLeftHead", SkizzikStages.STAGE_4, 0.75F, 0.75F);
        this.bottomRightHead = new SkizzikPart(this, "bottomRightHead", SkizzikStages.STAGE_5, 0.75F, 0.75F);
        this.centerHead = new SkizzikPart(this, "centerHead", 1.0F, 1.0F);
        this.commandBlockPart = new SkizzikPart(this, "commandBlock", SkizzikStages.FINISH_HIM, 0.93F, 0.93F);
        this.bodyPart = new SkizzikPart(this, "body", 0.43F, 1.865F);
        this.parts = new SkizzikPart[]{this.topLeftHead, this.topRightHead, this.bottomLeftHead, this.bottomRightHead, this.centerHead, this.commandBlockPart, this.bodyPart};
    }

    public Component getTranslationKey() {
        return this.getTypeName();
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
        return eyeHeight;
    }

    @Override
    public void startSeenByPlayer(ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.bossBar.addPlayer(serverPlayer);
        if (stageManager.getCurrentStage().playMusic()) {
            PA_PacketRegistry.INSTANCE.sendTo(new BossMusicStartPacket(ProjectApple.holiday == 1 ? PA_SoundEvents.MUSIC_SPOOKZIK_LAZY.get() : PA_SoundEvents.MUSIC_SKIZZIK_LAZY.get()), serverPlayer.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    @Override
    protected void doPush(Entity entity) {
        if (this.isPushable()) {
            super.doPush(entity);
        }
    }

    @Override
    public boolean isPushable() {
        if (this.getPreview() || this.isTransitioning() || this.isInvul() || this.stageManager.getCurrentStage() instanceof SkizzikSleeping) {
            return false;
        }
        return super.isPushable();
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

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return this.stageManager.getCurrentStage().hitbox();
    }

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
    public void makeStuckInBlock(BlockState state, Vec3 vector) {
    }

    public static boolean canDestroy(BlockState state) {
        return !state.isAir() && !state.is(BlockTags.WITHER_IMMUNE); //BlockTags.WITHER_IMMUNE.contains(state.getBlock());
    }

    public static AttributeSupplier.Builder buildAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1021.0D)
                .add(Attributes.ARMOR, 0.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.FLYING_SPEED, 0.6D);
    }

    public boolean getPreview() {
        return this.preview;
    }
    
    public void setPreview(boolean flag) {
        this.preview = flag;
    }

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setEyeHeight(float height) {
        this.eyeHeight = height;
    }

    public boolean isTransitioning() {
        return this.entityData.get(DATA_TRANSITIONING);
    }

    public void setTransitioning(boolean flag) {
        this.entityData.set(DATA_TRANSITIONING, flag);
    }

    public int getTransitionTicks() {
        return this.entityData.get(DATA_TRANSITION_TIME);
    }

    public void setTransitionsTicks(int i) {
        this.entityData.set(DATA_TRANSITION_TIME, i);
    }

    public boolean isInvul() {
        return this.entityData.get(DATA_INVUL);
    }

    public void setInvul(boolean flag) {
        this.entityData.set(DATA_INVUL, flag);
    }

    public int getAlternativeTarget(int head) {
        return this.entityData.get(DATA_TARGETS.get(head));
    }

    public void setAlternativeTarget(int head, int target) {
        this.entityData.set(DATA_TARGETS.get(head), target);
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public PartEntity<?>[] getParts() {
        return this.parts;
    }

    @Override
    public void recreateFromPacket(ClientboundAddMobPacket packet) {
        super.recreateFromPacket(packet);
        PartEntity<?>[] parts = this.getParts();

        for(int i = 0; i < parts.length; ++i) {
            parts[i].setId(i + packet.getId());
        }
    }

    @Override
    public boolean canBeAffected(MobEffectInstance effect) {
        return false;
    }

    private <E extends IAnimatable> PlayState ambient(AnimationEvent<E> event) {
        if (!(this.stageManager.getCurrentStage() instanceof SkizzikSleeping)) {
            if (this.isTransitioning() && this.stageManager.getCurrentStage() instanceof SkizzikStage1) {
                return PlayState.STOP;
            }
            
            if (!this.isTransitioning() && this.stageManager.getCurrentStage() instanceof SkizzikStage5) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.skizzik.body_movement_stage-5"));
                return PlayState.CONTINUE;
            }
            
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.skizzik.body_movement"));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }
    
    private <E extends IAnimatable> PlayState transitions(AnimationEvent<E> event) {
        if (this.isTransitioning()) {
            if (this.stageManager.getCurrentStage() instanceof SkizzikFinishHim) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.skizzik.to_finish-him"));
            } 
            else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation(String.format("animation.skizzik.to_stage-%d", this.stageManager.getCurrentStage().getStage().getId())));
            }
        }
        return PlayState.CONTINUE;
    }
    
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "ambient", 0, this::ambient));
        data.addAnimationController(new AnimationController(this, "transitions", 0, this::transitions));
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

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(DATA_TRANSITION_TIME, 0);
        this.entityData.define(DATA_STAGE, 0);
        this.entityData.define(DATA_TRANSITIONING, false);
        this.entityData.define(DATA_INVUL, false);

        this.entityData.define(DATA_TARGET_A, 0);
        this.entityData.define(DATA_TARGET_B, 0);
        this.entityData.define(DATA_TARGET_C, 0);
        this.entityData.define(DATA_TARGET_D, 0);
        this.entityData.define(DATA_TARGET_E, 0);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (DATA_STAGE.equals(key) && this.level.isClientSide) {
            this.stageManager.setStage(SkizzikStages.getById(this.getEntityData().get(DATA_STAGE)));
        }

        super.onSyncedDataUpdated(key);
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.putInt("Stage", this.stageManager.getCurrentStage().getStage().getId());
        nbt.putBoolean("Transitioning", this.isTransitioning());
        nbt.putInt("TransitionTime", this.getTransitionTicks());
        nbt.putBoolean("Preview", this.getPreview());
        nbt.putBoolean("Debug", this.getDebug());
        nbt.putBoolean("Invul", this.isInvul());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

        this.stageManager.setStage(SkizzikStages.getById(nbt.getInt("Stage")));
        this.setTransitionsTicks(nbt.getInt("TransitionTime"));
        this.setTransitioning(nbt.getBoolean("Transitioning"));
        this.setPreview(nbt.getBoolean("Preview"));
        this.setDebug(nbt.getBoolean("Debug"));
        this.setInvul(nbt.getBoolean("Invul"));

        if (this.hasCustomName()) {
            this.bossBar.setName(this.getDisplayName());
        }
    }
    
    @Override
    public void performRangedAttack(LivingEntity entity, float f) {
        this.performRangedAttack(0, entity);
    }

    private void performRangedAttack(int head, LivingEntity entity) {
        this.performRangedAttack(head, entity.getX(), entity.getY() + (double)entity.getEyeHeight() * 0.5D, entity.getZ(), this.stageManager.getCurrentStage().skullLevel());
    }

    private void performRangedAttack(int head, double x, double y, double z, int level) {
        if (!this.isSilent()) {
            this.level.levelEvent(null, 1024, this.blockPosition(), 0);
        }

        double headX = this.stageManager.getCurrentStage().getHeadX(head);
        double headY = this.stageManager.getCurrentStage().getHeadY(head);
        double headZ = this.stageManager.getCurrentStage().getHeadZ(head);

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
        super.registerGoals();
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
        Explosion.BlockInteraction explosion = getMobGriefingEvent(this.level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.0F, false, explosion);
        return super.finalizeSpawn(level, difficulty, spawnReason, spawnData, nbt);
    }
    
    @Override
    public void baseTick() {
        super.baseTick();
        
        this.refreshDimensions();
        this.stageManager.updateStage();

        int currentStageId = this.stageManager.getCurrentStage().getStage().getId();
        this.activeHeads = currentStageId == 1 ? 4 :
                                currentStageId == 2 ? 3 :
                                        currentStageId == 3 ? 2 :
                                                currentStageId == 4 ? 1 : 0;
        Level world = this.level;

        this.getAttributes().getInstance(Attributes.ARMOR).setBaseValue(this.stageManager.getCurrentStage().armorValue());
        this.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(this.stageManager.getCurrentStage().maxHealth());

        if (!this.isInvul()) {
            this.bossBar.setName(this.stageManager.getCurrentStage().displayName());
            this.bossBar.setColor(this.stageManager.getCurrentStage().barColor());
        }
        else {
            this.bossBar.setName(new TextComponent(String.format("%s - %s", this.getDisplayName().getString(), new TranslatableComponent("entity.skizzik.skizzik.invulnerable").getString())));
            this.bossBar.setColor(PA_BossEvent.PA_BossBarColor.AQUA);
        }
        this.bossBar.setOverlay(this.stageManager.getCurrentStage().barOverlay());
        
        if (this.preview) {
            killAllSkizzies(world, false);
        }

        if (!this.preview) {
            if (world instanceof ServerLevel) {
                if (ProjectApple.holiday == 1 && !(this.stageManager.getCurrentStage() instanceof SkizzikFinishHim)) {
                    ((ServerLevel) world).setDayTime(18000);
                } 
                else {
                    if (this.stageManager.getCurrentStage() instanceof SkizzikStage3) {
                        ((ServerLevel) world).setDayTime(13000);
                    } 
                    else if (this.stageManager.getCurrentStage() instanceof SkizzikStage4 || this.stageManager.getCurrentStage() instanceof SkizzikStage5) {
                        ((ServerLevel) world).setDayTime(18000);
                    }
                }
            }
        }
    }

    public void kill() {
        this.remove(Entity.RemovalReason.KILLED);
    }
    
    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }
    
    public boolean hurt(SkizzikPart part, DamageSource source, float amount) {
        Entity entity = source.getEntity();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Level world = this.level;

        if ((!(entity instanceof Player) && entity instanceof LivingEntity && ((LivingEntity) entity).getMobType() == this.getMobType()) || SkizzikStages.isImmune(this, source)) {
            return false;
        }
        else {
            if (this.destroyBlocksTicks <= 0) {
                this.destroyBlocksTicks = this.stageManager.getCurrentStage().destroyBlocksTick();
            }

            for (int i = 0; i < this.idleHeadUpdates.length; ++i) {
                this.idleHeadUpdates[i] += 3;
            }

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

            if (part == this.commandBlockPart && this.stageManager.getCurrentStage() instanceof SkizzikStage5) {
                amount *= 1.5;
            }
            
            return super.hurt(source, amount);
        }
    }

    public void tickPart(SkizzikPart part, double x, double y, double z) {
        if (part.despawnStage != null) {
            if (part.despawnStage.getId() > this.stageManager.getCurrentStage().getStage().getId()) {
                part.setPos(x, y, z);
            }
            else {
                part.setPos(this.parts[4].xo, this.parts[4].yo + 0.05, this.parts[4].zo);
            }
        }
        else {
            part.setPos(x, y, z);
        }
    }
    
    public void tickPartOffset(SkizzikPart part, double offsetX, double offsetY, double offsetZ) {
        this.tickPart(part, this.getX() + offsetX, this.getY() + offsetY, this.getZ() + offsetZ);
    }

    @Override
    public void aiStep() {
        this.stageManager.getCurrentStage().tickParts();
        
        Vec3[] vec3 = new Vec3[this.parts.length];
        for(int i = 0; i < this.parts.length; ++i) {
            vec3[i] = new Vec3(this.parts[i].getX(), this.parts[i].getY(), this.parts[i].getZ());

            this.parts[i].xo = vec3[i].x;
            this.parts[i].yo = vec3[i].y;
            this.parts[i].zo = vec3[i].z;
            this.parts[i].xOld = vec3[i].x;
            this.parts[i].yOld = vec3[i].y;
            this.parts[i].zOld = vec3[i].z;
        }
        
        Vec3 vector = this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D);
        if (!this.level.isClientSide && this.getAlternativeTarget(0) > 0) {
            Entity entity = this.level.getEntity(this.getAlternativeTarget(0));
            if (entity != null) {
                double vectorY = vector.y;
                if (this.getY() < entity.getY()) {
                    vectorY = Math.max(0.0D, vectorY);
                    vectorY = vectorY + (0.3D - vectorY * (double)0.6F);
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
            this.setYRot((float) Mth.atan2(vector.z, vector.x) * (180F / (float)Math.PI) - 90.0F);
        }

        super.aiStep();

        if (stageManager.getCurrentStage().attackStatically()) {
            for (int i = 0; i < activeHeads; ++i) {
                this.yRotHeads1[i] = this.yRotHeads[i];
                this.xRotHeads1[i] = this.xRotHeads[i];
            }

            for (int j = 0; j < activeHeads; ++j) {
                int k = this.getAlternativeTarget(j + 1);
                Entity entity1 = null;
                if (k > 0) {
                    entity1 = this.level.getEntity(k);
                }

                if (entity1 != null) {
                    double headX = this.stageManager.getCurrentStage().getHeadX(j + 1);
                    double headY = this.stageManager.getCurrentStage().getHeadY(j + 1);
                    double headZ = this.stageManager.getCurrentStage().getHeadZ(j + 1);

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

            for(int l = 0; l < activeHeads + 1; ++l) {
                double headX = this.stageManager.getCurrentStage().getHeadX(l);
                double heaxY = this.stageManager.getCurrentStage().getHeadY(l);
                double headZ = this.stageManager.getCurrentStage().getHeadZ(l);
                if (l == 0) {
                    this.level.addParticle(ParticleTypes.FLAME, headX + this.random.nextGaussian() * (double)0.5F, heaxY + this.random.nextGaussian() * (double)0.5F, headZ + this.random.nextGaussian() * (double)0.5F, 0.0D, 0.0D, 0.0D);
                }
                else {
                    this.level.addParticle(ParticleTypes.FLAME, headX + this.random.nextGaussian() * (double) 0.3F, heaxY + this.random.nextGaussian() * (double) 0.3F, headZ + this.random.nextGaussian() * (double) 0.3F, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();

        int currentStageId = this.stageManager.getCurrentStage().getStage().getId();
        if (this.spawnSkizzieTicks <= 0) {
            this.spawnSkizzieTicks = this.stageManager.getCurrentStage().skizzieSpawnTicks();
        }

        if (stageManager.getCurrentStage().attackStatically()) {
            for (int headIndex = 1; headIndex < activeHeads + 1; ++headIndex) {
                if (this.tickCount >= this.nextHeadUpdate[headIndex - 1]) {
                    this.nextHeadUpdate[headIndex - 1] = this.tickCount + 10 + this.random.nextInt(10);
                    int head = headIndex - 1;
                    int idleHeadUpdate = this.idleHeadUpdates[headIndex - 1];
                    this.idleHeadUpdates[head] = this.idleHeadUpdates[headIndex - 1] + 1;

                    if (idleHeadUpdate > 15) {
                        double x = Mth.nextDouble(this.random, this.getX() - 10.0D, this.getX() + 10.0D);
                        double y = Mth.nextDouble(this.random, this.getY() - 5.0D, this.getY() + 5.0D);
                        double z = Mth.nextDouble(this.random, this.getZ() - 10.0D, this.getZ() + 10.0D);

                        this.performRangedAttack(headIndex + 1, x, y, z, currentStageId == 1 || currentStageId == 2 ? 1 :
                                currentStageId == 3 || currentStageId == 4 ? 2 :
                                        3);
                        this.idleHeadUpdates[headIndex - 1] = 0;
                    }

                    int alternativeTarget = this.getAlternativeTarget(headIndex);

                    if (alternativeTarget > 0) {
                        Entity target = this.level.getEntity(alternativeTarget);
                        if (target != null && target.isAlive() && !(this.distanceToSqr(target) > 900.0D) && this.hasLineOfSight(target)) {
                            if (target instanceof Player && ((Player) target).getAbilities().invulnerable) {
                                this.setAlternativeTarget(headIndex, 0);
                            } else {
                                this.performRangedAttack(headIndex + 1, target.getX(), target.getY() + (double) target.getEyeHeight() * 0.5D, target.getZ(), currentStageId == 1 || currentStageId == 2 ? 1 :
                                        currentStageId == 3 || currentStageId == 4 ? 2 :
                                                3);
                                this.nextHeadUpdate[headIndex - 1] = this.tickCount + 40 + this.random.nextInt(20);
                                this.idleHeadUpdates[headIndex - 1] = 0;
                            }
                        } else {
                            this.setAlternativeTarget(headIndex, 0);
                        }
                    } 
                    else {
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

        if (this.getTarget() != null) {
            this.setAlternativeTarget(0, this.getTarget().getId());
        }
        else {
            this.setAlternativeTarget(0, 0);
        }

        if (!this.preview) {
            if (this.destroyBlocksTicks > 0) {
                if (stageManager.getCurrentStage().attackStatically()) {
                    --this.destroyBlocksTicks;

                    if (this.destroyBlocksTicks == 0 && getMobGriefingEvent(this.level, this)) {
                        int y = Mth.floor(this.getY());
                        int x = Mth.floor(this.getX());
                        int z = Mth.floor(this.getZ());
                        boolean canDestroy = false;

                        for (int k2 = -1; k2 <= 1; ++k2) {
                            for (int l2 = -1; l2 <= 1; ++l2) {
                                for (int j = 0; j <= 3; ++j) {
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
                } else {
                    this.destroyBlocksTicks = 35;
                }
            }

            if (this.spawnSkizzieTicks > 0) {
                --this.spawnSkizzieTicks;

                if (this.spawnSkizzieTicks == 0) {
                    this.stageManager.getCurrentStage().skizzieSpawning();
                }
            }
        }

        float health = this.getHealth();
        int stage = currentStageId;

        if (stage == 5 && health <= 219) {
            if (this.tickCount % 20 == 0) {
                this.heal(1.0F);
            }
        }
        else if ((stage == 4 && health <= 419.5) || (stage == 3 && health <= 619.5)) {
            if (this.tickCount % 30 == 0) {
                this.heal(0.5F);
            }
        }
        else if ((stage == 2 && health <= 819.5) || (stage == 1 && health <= 1019.5)) {
            if (this.tickCount % 40 == 0) {
                this.heal(0.5F);
            }
        }

        if (stage != 6) {
            this.bossBar.setProgress((this.getHealth() - 20) / (this.getMaxHealth() - 20));
        }
        else {
            this.bossBar.setProgress(this.getHealth() / this.getMaxHealth());
        }
    }
}
