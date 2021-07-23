package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.init.PA_Entities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

public class Skizzie extends Monster {
    public Skizzie(EntityType<? extends Skizzie> entity, Level world) {
        super(entity, world);
        this.xpReward = 7;
        this.moveControl = new FlyingMoveControl(this, 10, true);
        this.navigation = new FlyingPathNavigation(this, this.getCommandSenderWorld());
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return 1.30F;
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    //protected SoundEvent getAmbientSound() {
    //    return SoundEvents.PIG_AMBIENT;
    //}

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
    public boolean causeFallDamage(float f0, float f1) {
        return false;
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

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, FriendlySkizzie.class, true, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 0, true, true, PA_Entities.LIVING_ENTITY_SELECTOR));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.D, 0.0F));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
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

    /* @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {

    } */

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
