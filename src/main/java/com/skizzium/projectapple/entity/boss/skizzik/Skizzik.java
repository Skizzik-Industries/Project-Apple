package com.skizzium.projectapple.entity.boss.skizzik;

import com.google.common.collect.ImmutableList;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.*;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStage;
import com.skizzium.projectapple.init.PA_PacketHandler;
import com.skizzium.projectapple.init.PA_SoundEvents;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.network.BossMusicStartPacket;
import com.skizzium.projectapple.network.BossMusicStopPacket;
import com.skizzium.projectapple.util.PA_BossEvent;
import com.skizzium.projectapple.util.PA_ServerBossEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fmllegacy.network.NetworkDirection;
import net.minecraftforge.fmllegacy.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent;
import static net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock;

public class Skizzik extends Monster implements RangedAttackMob {
    private static final EntityDataAccessor<Integer> DATA_TARGET_A = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_B = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_C = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_D = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_E = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.INT);
    private static final List<EntityDataAccessor<Integer>> DATA_TARGETS = ImmutableList.of(DATA_TARGET_A, DATA_TARGET_B, DATA_TARGET_C, DATA_TARGET_D, DATA_TARGET_E);

    public static final EntityDataAccessor<Integer> DATA_STAGE = SynchedEntityData.defineId(Skizzik.class, EntityDataSerializers.INT);

    private int activeHeads = 4;

    private final float[] xRotHeads = new float[activeHeads];
    private final float[] yRotHeads = new float[activeHeads];

    private final float[] xRotHeads1 = new float[activeHeads];
    private final float[] yRotHeads1 = new float[activeHeads];

    private final int[] nextHeadUpdate = new int[activeHeads];
    private final int[] idleHeadUpdates = new int[activeHeads];

    //private final Skizzo[] skizzos = new Skizzo[4];

    public final SkizzikStageManager stageManager;
    private int destroyBlocksTick;
    private int spawnSkizzieTicks;

    private static final TargetingConditions TARGETING_CONDITIONS = TargetingConditions.forCombat().range(20.0D).selector(PA_Entities.SKIZZIK_SELECTOR);
    private final PA_ServerBossEvent bossBar = (PA_ServerBossEvent) (new PA_ServerBossEvent(this.getDisplayName(), PA_BossEvent.PA_BossBarColor.WHITE, PA_BossEvent.PA_BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    private boolean preview = false;
    
    public Skizzik(EntityType<? extends Skizzik> entity, Level world) {
        super(entity, world);
        this.stageManager = new SkizzikStageManager(this);
        this.setHealth(this.getMaxHealth());
        this.getNavigation().setCanFloat(true);
        this.xpReward = 0;
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
        return this.getStage() == 0 ? 1.5F : this.getStage() == 5 ? 2.75F : this.getStage() == 6 ? 2.25F : 2.45F;
    }

    @Override
    public void startSeenByPlayer(ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.bossBar.addPlayer(serverPlayer);
        if (!this.preview && this.getStage() != 0 && this.getStage() != 6) {
            PA_PacketHandler.INSTANCE.sendTo(new BossMusicStartPacket(PA_SoundEvents.MUSIC_SKIZZIK_LAZY.get()), serverPlayer.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.bossBar.removePlayer(serverPlayer);
        PA_PacketHandler.INSTANCE.sendTo(new BossMusicStopPacket(), serverPlayer.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);

        int stage = this.getStage();
        if (stage == 0) {
            this.bossBar.setName(new TextComponent(this.getDisplayName().getString() + " - Sleeping"));
        }
        else if (stage >= 1 && stage <= 5) {
            this.bossBar.setName(new TextComponent(this.getDisplayName().getString() + " - Stage " + stage));
        }
        else if (stage == 6) {
            this.bossBar.setName(new TextComponent(this.getDisplayName().getString() + " - FINISH HIM!"));
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
    public EntityDimensions getDimensions(Pose pose) {
        if (this.getStage() == 0) {
            return new EntityDimensions(2.5F, 2.1F, true);
        }
        else if (this.getStage() == 3) {
            return new EntityDimensions(2.5F, 3.0F, true);
        }
        else if (this.getStage() == 5) {
            return new EntityDimensions(2.5F, 3.4F, true);
        }
        else if (this.getStage() == 6) {
            return new EntityDimensions(1.2F, 2.8F, true);
        }
        return super.getDimensions(pose);
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

    public int getStage() {
        return this.entityData.get(DATA_STAGE);
    }

    public void setStage(int stage) {
        this.entityData.set(DATA_STAGE, stage);
    }

    public boolean getPreview() {
        return this.preview;
    }
    
    public void setPreview(boolean flag) {
        this.preview = flag;
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
            float f1 = Mth.cos(f);

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

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(DATA_STAGE, 0);

        this.entityData.define(DATA_TARGET_A, 0);
        this.entityData.define(DATA_TARGET_B, 0);
        this.entityData.define(DATA_TARGET_C, 0);
        this.entityData.define(DATA_TARGET_D, 0);
        this.entityData.define(DATA_TARGET_E, 0);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (DATA_STAGE.equals(key) && this.level.isClientSide) {
            this.stageManager.setStage(SkizzikStage.getById(this.getEntityData().get(DATA_STAGE)));
        }

        super.onSyncedDataUpdated(key);
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.putInt("Stage", this.stageManager.getCurrentStage().getStage().getId());
        nbt.putBoolean("Preview", this.getPreview());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

        this.stageManager.setStage(SkizzikStage.getById(nbt.getInt("Stage")));
        this.setPreview(nbt.getBoolean("Preview"));

        if (this.hasCustomName()) {
            this.bossBar.setName(this.getDisplayName());
        }
    }
    
    @Override
    public void performRangedAttack(LivingEntity entity, float f) {
        this.performRangedAttack(0, entity);
    }

    private void performRangedAttack(int head, LivingEntity entity) {
        this.performRangedAttack(head, entity.getX(), entity.getY() + (double)entity.getEyeHeight() * 0.5D, entity.getZ(), this.getStage() == 1 || this.getStage() == 2 ? 1 :
                                                                                                                                this.getStage() == 3 || this.getStage() == 4 ? 2 :
                                                                                                                                3);
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
        super.registerGoals();
    }

    private void killAllSkizzies(Level world, boolean skizziesOnly) {
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
        
        this.stageManager.updateStage();
        this.refreshDimensions();

        activeHeads = this.getStage() == 1 ? 4 :
                            this.getStage() == 2 ? 3 :
                                    this.getStage() == 3 ? 2 :
                                            this.getStage() == 4 ? 1 :
                                                    0;

        float health = this.getHealth();
        int currentStage = this.getStage();
        int newStage = health > 1020 ? 0 :
                            health > 820 ? 1 :
                                    health > 620 ? 2 :
                                            health > 420 ? 3 :
                                                    health > 220 ? 4 :
                                                            health > 20 ? 5 :
                                                                    6;

        Level world = getCommandSenderWorld();
        
        if (this.preview) {
            killAllSkizzies(world, false);
        }
        
        if (!this.preview) {
            if (world instanceof ServerLevel) {
                if (ProjectApple.holiday == 1) {
                    ((ServerLevel) world).setDayTime(18000);
                } else {
                    if (currentStage == 3) {
                        ((ServerLevel) world).setDayTime(13000);
                    } else if (currentStage == 4 || currentStage == 5) {
                        ((ServerLevel) world).setDayTime(18000);
                    }
                }
            }
        }

        if (currentStage != newStage) {
            if (world instanceof ServerLevel) {
                if (newStage != 0 && newStage != 6) {
                    PA_PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new BossMusicStartPacket(PA_SoundEvents.MUSIC_SKIZZIK_LAZY.get()));
                } else {
                    PA_PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new BossMusicStopPacket());
                }
            }

            if (currentStage == 0 && newStage == 1) {
                this.setHealth(1020);
            }

            this.getAttributes().getInstance(Attributes.ARMOR).setBaseValue(this.stageManager.getCurrentStage().armorValue());
            this.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(this.stageManager.getCurrentStage().maxHealth());
            
            if (newStage == 6) {
                if (!world.isClientSide) {
                    world.setBlock(new BlockPos(this.getX(), this.getY(), this.getZ()), PA_Blocks.SKIZZIK_LOOT_BAG.get().defaultBlockState(), 2);
                    ((ServerLevel) world).setDayTime(1000);
                }

                this.killAllSkizzies(world, false);

                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), PA_SoundEvents.FINISH_HIM_LAZY.get(), SoundSource.HOSTILE, 10000.0F, 1.0F);
            }

            this.killAllSkizzies(world, true);

            if (!this.preview) {
                if (world instanceof ServerLevel) {
                    if (newStage > 1 && newStage <= 5) {
                        int difference;

                        if (currentStage == 0) {
                            difference = newStage - currentStage - 1;
                        } else if (currentStage == 6) {
                            difference = newStage - currentStage + 1;
                        } else {
                            difference = newStage - currentStage;
                        }

                        if (difference > 0) {
                            for (int i = 1; i <= difference; i++) {
                                Skizzo skizzo = (Skizzo) PA_Entities.SKIZZO.get().spawn((ServerLevel) world, null, null, this.blockPosition(), MobSpawnType.MOB_SUMMONED, true, true);
                                skizzo.setOwner(this);
                                //skizzos[i - 1] = skizzo;
                            }
                        }
                        /* else {
                            difference = Math.abs(difference);
                            for (int i = 1; i <= difference; i++) {
                                ((ServerLevel) world).removeEntity(skizzos[i]);
                            }
                        } */
                    }
                }
            }
        }

        AvoidEntityGoal avoidPlayerGoal = new AvoidEntityGoal<>(this, Player.class, 25, 1.2D, 1.7D);
        PanicGoal panicGoal = new PanicGoal(this, 1.5D);

        HurtByTargetGoal hurtGoal = new HurtByTargetGoal(this);
        NearestAttackableTargetGoal attackGoal = new NearestAttackableTargetGoal<>(this, Mob.class, 0, false, false, PA_Entities.SKIZZIK_SELECTOR);
        RangedAttackGoal rangedAttackGoal = new RangedAttackGoal(this, 1.0D, 40, 20.0F);
        WaterAvoidingRandomStrollGoal avoidWaterGoal = new WaterAvoidingRandomStrollGoal(this, 1.0D);
        LookAtPlayerGoal lookGoal = new LookAtPlayerGoal(this, Player.class, 8.0F);
        RandomLookAroundGoal lookRandomlyGoal = new RandomLookAroundGoal(this);

        if (currentStage == 0 || this.preview) {
            this.goalSelector.removeAllGoals();
        }
        else if (currentStage == 6) {
            this.targetSelector.removeGoal(hurtGoal);
            this.targetSelector.removeGoal(attackGoal);
            this.goalSelector.removeGoal(rangedAttackGoal);
            this.goalSelector.removeGoal(avoidWaterGoal);
            this.goalSelector.removeGoal(lookGoal);
            this.goalSelector.removeGoal(lookRandomlyGoal);

            this.goalSelector.addGoal(1, avoidPlayerGoal);
            this.goalSelector.addGoal(2, panicGoal);
            this.goalSelector.addGoal(3, avoidWaterGoal);
            this.goalSelector.addGoal(4, lookGoal);
            this.goalSelector.addGoal(5, lookRandomlyGoal);
        }
        else {
            this.goalSelector.removeGoal(avoidPlayerGoal);
            this.goalSelector.removeGoal(panicGoal);

            this.targetSelector.addGoal(1, hurtGoal);
            this.targetSelector.addGoal(2, attackGoal);
            this.goalSelector.addGoal(2, rangedAttackGoal);
            this.goalSelector.addGoal(5, avoidWaterGoal);
            this.goalSelector.addGoal(6, lookGoal);
            this.goalSelector.addGoal(7, lookRandomlyGoal);
        }

        //After Invul - world.playSound(null, new BlockPos(x, y,z), SoundEvents.ENDER_DRAGON_GROWL, SoundCategory.HOSTILE, (float) 10, (float) 1);

        this.bossBar.setName(this.stageManager.getCurrentStage().displayName());
        this.bossBar.setColor(this.stageManager.getCurrentStage().barColor());
        this.bossBar.setOverlay(this.stageManager.getCurrentStage().barOverlay());

        this.setStage(newStage);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        int stage = this.getStage();

        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Level world = this.getCommandSenderWorld();

        if (stage == 0 || stage == 1) {
            if (source == DamageSource.CACTUS ||
                    source == DamageSource.FALL ||
                    source.isExplosion() ||
                    source == DamageSource.LIGHTNING_BOLT ||
                    source == DamageSource.HOT_FLOOR ||
                    source == DamageSource.IN_FIRE ||
                    source == DamageSource.LAVA ||
                    source == DamageSource.ON_FIRE ||
                    source == DamageSource.SWEET_BERRY_BUSH ||
                    source == DamageSource.WITHER ||
                    source == DamageSource.STALAGMITE) {
                return false;
            }
        }
        else if (stage == 2) {
            if (source == DamageSource.ANVIL ||
                    source == DamageSource.CACTUS ||
                    source == DamageSource.FALL ||
                    source.isExplosion() ||
                    source == DamageSource.LIGHTNING_BOLT ||
                    source == DamageSource.HOT_FLOOR ||
                    source == DamageSource.IN_FIRE ||
                    source == DamageSource.LAVA ||
                    source == DamageSource.ON_FIRE ||
                    source == DamageSource.SWEET_BERRY_BUSH ||
                    source == DamageSource.WITHER ||
                    source == DamageSource.STALAGMITE) {
                return false;
            }
        }
        else if (stage == 3) {
            if (source == DamageSource.ANVIL ||
                    source == DamageSource.CACTUS ||
                    source == DamageSource.DRAGON_BREATH ||
                    source == DamageSource.FALL ||
                    source.isExplosion() ||
                    source == DamageSource.LIGHTNING_BOLT ||
                    source == DamageSource.HOT_FLOOR ||
                    source == DamageSource.IN_FIRE ||
                    source == DamageSource.LAVA ||
                    source == DamageSource.ON_FIRE ||
                    source.getDirectEntity() instanceof ThrownPotion ||
                    source == DamageSource.SWEET_BERRY_BUSH ||
                    source == DamageSource.WITHER ||
                    source == DamageSource.STALAGMITE) {
                return false;
            }
        }
        else if (stage == 4) {
            if (source == DamageSource.ANVIL ||
                    source == DamageSource.CACTUS ||
                    source == DamageSource.DRAGON_BREATH ||
                    source == DamageSource.FALL ||
                    source.isExplosion() ||
                    source == DamageSource.LIGHTNING_BOLT ||
                    source == DamageSource.HOT_FLOOR ||
                    source == DamageSource.IN_FIRE ||
                    source == DamageSource.LAVA ||
                    source == DamageSource.ON_FIRE ||
                    source.getDirectEntity() instanceof ThrownPotion ||
                    source == DamageSource.SWEET_BERRY_BUSH ||
                    source.getDirectEntity() instanceof ThrownTrident ||
                    source == DamageSource.WITHER ||
                    source == DamageSource.STALAGMITE) {
                return false;
            }
        }
        else if (stage == 5) {
            if (source == DamageSource.ANVIL ||
                    source.getDirectEntity() instanceof Arrow ||
                    source == DamageSource.CACTUS ||
                    source == DamageSource.DRAGON_BREATH ||
                    source == DamageSource.FALL ||
                    source.isExplosion() ||
                    source == DamageSource.LIGHTNING_BOLT ||
                    source == DamageSource.HOT_FLOOR ||
                    source == DamageSource.IN_FIRE ||
                    source == DamageSource.LAVA ||
                    source == DamageSource.ON_FIRE ||
                    source.getDirectEntity() instanceof ThrownPotion ||
                    source == DamageSource.SWEET_BERRY_BUSH ||
                    source.getDirectEntity() instanceof ThrownTrident ||
                    source == DamageSource.WITHER ||
                    source == DamageSource.STALAGMITE) {
                return false;
            }
        }
        else if (stage == 6) {
            if (source == DamageSource.LIGHTNING_BOLT ||
                    source == DamageSource.HOT_FLOOR ||
                    source == DamageSource.IN_FIRE ||
                    source == DamageSource.LAVA ||
                    source == DamageSource.ON_FIRE) {
                return false;
            }
            
            if (preview &&
                    source != DamageSource.CRAMMING &&
                    source != DamageSource.OUT_OF_WORLD &&
                    source != DamageSource.MAGIC) {
                return false;
            }
        }

        Entity entity = source.getEntity();
        if (!(entity instanceof Player) && entity instanceof LivingEntity && ((LivingEntity) entity).getMobType() == this.getMobType()) {
            return false;
        }
        else {
            if (this.destroyBlocksTick <= 0) {
                if (this.getStage() == 1 || this.getStage() == 2) {
                    this.destroyBlocksTick = 35;
                }
                else if (this.getStage() == 3 || this.getStage() == 4) {
                    this.destroyBlocksTick = 20;
                }
                else if (this.getStage() == 5) {
                    this.destroyBlocksTick = 10;
                }
                else {
                    this.destroyBlocksTick = 35;
                }
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

            return super.hurt(source, amount);
        }
    }

    private void spawnSkizzie(Skizzie entity, double x, double y, double z, Level world) {
        if (world instanceof ServerLevel) {
            entity.setHoliday(ProjectApple.holiday);
            entity.moveTo(x, y, z);
            entity.finalizeSpawn((ServerLevel) world, world.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            entity.setOwner(this);
            world.addFreshEntity(entity);
        }
    }

    @Override
    public void aiStep() {
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

        if (!this.preview && this.getStage() != 0 && this.getStage() != 6) {
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

            for(int l = 0; l < activeHeads + 1; ++l) {
                double headX = this.getHeadX(l);
                double heaxY = this.getHeadY(l);
                double headZ = this.getHeadZ(l);
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

        if (this.spawnSkizzieTicks <= 0) {
            if (this.getStage() == 1 || this.getStage() == 2) {
                this.spawnSkizzieTicks = 60;
            }
            else if (this.getStage() == 3 || this.getStage() == 4) {
                this.spawnSkizzieTicks = 40;
            }
            else if (this.getStage() == 5) {
                this.spawnSkizzieTicks = 30;
            }
            else {
                this.spawnSkizzieTicks = 0;
            }
        }

        if (!this.preview && this.getStage() != 0 && this.getStage() != 6) {
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

                        this.performRangedAttack(headIndex + 1, x, y, z, this.getStage() == 1 || this.getStage() == 2 ? 1 :
                                this.getStage() == 3 || this.getStage() == 4 ? 2 :
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
                                this.performRangedAttack(headIndex + 1, target.getX(), target.getY() + (double) target.getEyeHeight() * 0.5D, target.getZ(), this.getStage() == 1 || this.getStage() == 2 ? 1 :
                                        this.getStage() == 3 || this.getStage() == 4 ? 2 :
                                                3);
                                this.nextHeadUpdate[headIndex - 1] = this.tickCount + 40 + this.random.nextInt(20);
                                this.idleHeadUpdates[headIndex - 1] = 0;
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

        if (this.getTarget() != null) {
            this.setAlternativeTarget(0, this.getTarget().getId());
        }
        else {
            this.setAlternativeTarget(0, 0);
        }

        if (!this.preview) {
            if (this.destroyBlocksTick > 0) {
                if (this.getStage() != 0 && this.getStage() != 6) {
                    --this.destroyBlocksTick;

                    if (this.destroyBlocksTick == 0 && getMobGriefingEvent(this.level, this)) {
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
                    this.destroyBlocksTick = 35;
                }
            }

            if (this.spawnSkizzieTicks > 0) {
                --this.spawnSkizzieTicks;

                if (this.spawnSkizzieTicks == 0) {
                    Level world = this.getCommandSenderWorld();

                    if (this.getStage() == 1) {
                        if (Math.random() < 0.05) {
                            spawnSkizzie(new KaboomSkizzie(PA_Entities.KABOOM_SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                        } else {
                            spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                        }
                    } else if (this.getStage() == 2) {
                        if (Math.random() < 0.05) {
                            spawnSkizzie(new WitchSkizzie(PA_Entities.WITCH_SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                        } else {
                            if (Math.random() < 0.5) {
                                spawnSkizzie(new KaboomSkizzie(PA_Entities.KABOOM_SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                            } else {
                                spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                            }
                        }
                    } else if (this.getStage() == 3) {
                        /*if (Math.random() < 0.05) {
                            spawnSkizzie(new MinigunSkizzie(PA_Entities.MINIGUN_SKIZZIE, world), this.getX(), this.getY(), this.getZ(), world);
                        }
                        else {*/
                        if (Math.random() < 0.5) {
                            spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                        } else {
                            if (Math.random() < 0.5) {
                                spawnSkizzie(new KaboomSkizzie(PA_Entities.KABOOM_SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                            } else {
                                spawnSkizzie(new WitchSkizzie(PA_Entities.WITCH_SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                            }
                        }
                        //}
                    } else if (this.getStage() == 4) {
                        if (Math.random() < 0.05) {
                            spawnSkizzie(new CorruptedSkizzie(PA_Entities.CORRUPTED_SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                        } else {
                            if (Math.random() < 0.5) {
                                spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                                spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                            } else {
                                if (Math.random() < 0.5) {
                                    spawnSkizzie(new KaboomSkizzie(PA_Entities.KABOOM_SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                                } else {
                                    //if (Math.random() < 0.5) {
                                    spawnSkizzie(new WitchSkizzie(PA_Entities.WITCH_SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                                    /*}
                                    else {
                                        spawnSkizzie(new MinigunSkizzie(PA_Entities.MINIGUN_SKIZZIE, world), this.getX(), this.getY(), this.getZ(), world);
                                    }*/
                                }
                            }
                        }
                    } else if (this.getStage() == 5) {
                        if (Math.random() < 0.5) {
                            spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                            spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                            spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                        } else {
                            if (Math.random() < 0.5) {
                                spawnSkizzie(new KaboomSkizzie(PA_Entities.KABOOM_SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                                spawnSkizzie(new KaboomSkizzie(PA_Entities.KABOOM_SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                            } else {
                                if (Math.random() < 0.5) {
                                    spawnSkizzie(new WitchSkizzie(PA_Entities.WITCH_SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                                } else {
                                    /*if (Math.random() < 0.5) {
                                        spawnSkizzie(new MinigunSkizzie(PA_Entities.MINIGUN_SKIZZIE, world), this.getX(), this.getY(), this.getZ(), world);
                                    }
                                    else {*/
                                    spawnSkizzie(new CorruptedSkizzie(PA_Entities.CORRUPTED_SKIZZIE.get(), world), this.getX(), this.getY(), this.getZ(), world);
                                    //}
                                }
                            }
                        }
                    }
                }
            }
        }

        float health = this.getHealth();
        int stage = this.getStage();

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
