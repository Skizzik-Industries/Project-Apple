package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameType;
import net.minecraft.world.World;

public class Skizzie extends MonsterEntity {
    public Skizzie(EntityType<? extends Skizzie> entity, World world) {
        super(entity, world);
        this.xpReward = 7;
        this.moveControl = new FlyingMovementController(this, 10, true);
        this.navigation = new FlyingPathNavigator(this, this.getCommandSenderWorld());
        this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return 1.30F;
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
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEAD;
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
                source == DamageSource.HOT_FLOOR ||
                source == DamageSource.IN_FIRE ||
                source == DamageSource.LAVA ||
                source == DamageSource.ON_FIRE ||
                source == DamageSource.WITHER) {
            return false;
        }
        return super.hurt(source, amount);
    }

    public static AttributeModifierMap.MutableAttribute buildAttributes() {
        return MonsterEntity.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FLYING_SPEED, 0.3D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 0, false, false, ModEntities.LIVING_ENTITY_SELECTOR));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.D, 0.0F));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);

        World world = this.getCommandSenderWorld();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        LightningBoltEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
        lightning.moveTo(Vector3d.atCenterOf(new BlockPos(x, y, z)));
        world.addFreshEntity(lightning);
    }

    /* @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {

    } */

    @Override
    public void playerTouch(PlayerEntity entity) {
        super.playerTouch(entity);
        World world = entity.getCommandSenderWorld();

        if (entity instanceof ServerPlayerEntity) {
            if (((ServerPlayerEntity) entity).gameMode.isSurvival()) {
                entity.setSecondsOnFire(10);
            }
        }
        else if (entity instanceof PlayerEntity && world.isClientSide()) {
            NetworkPlayerInfo network = Minecraft.getInstance().getConnection().getPlayerInfo(entity.getGameProfile().getId());

            if (network.getGameMode() == GameType.SURVIVAL || network.getGameMode() == GameType.ADVENTURE) {
                entity.setSecondsOnFire(10);
            }
        }
        else {
            if (!entity.fireImmune()) {
                entity.setSecondsOnFire(10);
            }
        }
    }
}
