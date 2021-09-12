package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.util.SkizzieConversion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.UUID;

public class Skizzie extends Monster {
    private static EntityDataAccessor<Integer> DATA_HOLIDAY_VARIATION = SynchedEntityData.defineId(Skizzie.class, EntityDataSerializers.INT);
    private UUID ownerUUID;
    private int ownerNetworkId;

    public Skizzie(EntityType<? extends Skizzie> entity, Level world) {
        super(entity, world);
        this.xpReward = 7;
        this.moveControl = new FlyingMoveControl(this, 10, true);
        this.navigation = new FlyingPathNavigation(this, this.getCommandSenderWorld());
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    }

    @Override
    protected Component getTypeName() {
        return new TranslatableComponent(ProjectApple.getThemedDescriptionId(super.getType().getDescriptionId()));
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return 1.30F;
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.GENERIC_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_DEATH;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean isSensitiveToWater() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source == DamageSource.DRAGON_BREATH ||
                source == DamageSource.FALL ||
                source.isExplosion() ||
                source == DamageSource.LIGHTNING_BOLT ||
                source == DamageSource.HOT_FLOOR ||
                source == DamageSource.IN_FIRE ||
                source == DamageSource.LAVA ||
                source == DamageSource.ON_FIRE ||
                source == DamageSource.WITHER ||
                source.getDirectEntity() instanceof WitherSkull) {
            return false;
        }
        return super.hurt(source, amount);
    }

    public static AttributeSupplier.Builder buildAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FLYING_SPEED, 0.3D);
    }

    public void setHolidayVariation(int variation) {
        this.entityData.set(DATA_HOLIDAY_VARIATION, variation);
    }

    public int getHolidayVariation() {
        return this.entityData.get(DATA_HOLIDAY_VARIATION);
    }
    
    public void setOwner(@Nullable Entity entity) {
        if (entity != null) {
            this.ownerUUID = entity.getUUID();
            this.ownerNetworkId = entity.getId();
        }
    }

    @Nullable
    public Entity getOwner() {
        if (this.ownerUUID != null && this.level instanceof ServerLevel) {
            return ((ServerLevel)this.level).getEntity(this.ownerUUID);
        }
        else {
            return this.ownerNetworkId != 0 ? this.level.getEntity(this.ownerNetworkId) : null;
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        if (this.ownerUUID != null) {
            nbt.putUUID("Owner", this.ownerUUID);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        if (nbt.hasUUID("Owner")) {
            this.ownerUUID = nbt.getUUID("Owner");
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_HOLIDAY_VARIATION, 0);
    }
    
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, FriendlySkizzie.class, true, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 0, true, true, PA_Entities.SKIZZIE_SELECTOR));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.D, 0.0F));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData data, @Nullable CompoundTag nbt) {
        if (reason == MobSpawnType.SPAWN_EGG || reason == MobSpawnType.SPAWNER) {
            this.setHolidayVariation(ProjectApple.holiday);
        }

        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }
    
    @Override
    public void baseTick() {
        super.baseTick();
        this.setNoGravity(true);
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);

        Level world = this.getCommandSenderWorld();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(world);
        lightning.moveTo(Vec3.atCenterOf(new BlockPos(x, y, z)));
        world.addFreshEntity(lightning);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        return SkizzieConversion.convert(this, player);
    }

    @Override
    public void playerTouch(Player player) {
        super.playerTouch(player);
        Level world = player.getCommandSenderWorld();

        if (player instanceof ServerPlayer) {
            if (((ServerPlayer) player).gameMode.isSurvival()) {
                player.setSecondsOnFire(10);
            }
        }
        else if (player instanceof Player && world.isClientSide()) {
            PlayerInfo network = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());

            if (network.getGameMode() == GameType.SURVIVAL || network.getGameMode() == GameType.ADVENTURE) {
                player.setSecondsOnFire(10);
            }
        }
        else {
            if (!player.fireImmune()) {
                player.setSecondsOnFire(10);
            }
        }
    }
}
