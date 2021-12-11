package com.skizzium.projectapple.entity.boss;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.UUID;

public abstract class AbstractSkizzo extends Monster implements IAnimatable {
    private UUID ownerUUID;
    private int ownerNetworkId;
    private AnimationFactory factory = new AnimationFactory(this);
    
    protected AbstractSkizzo(EntityType<? extends Monster> entity, Level level) {
        super(entity, level);
        this.xpReward = 25;
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
        return 0.65F;
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
    public boolean isInvulnerableTo(DamageSource source) {
        return super.isInvulnerableTo(source) || (!(source.getDirectEntity() instanceof Player) && source != DamageSource.OUT_OF_WORLD);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.D, 0.0F));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
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

    private <E extends IAnimatable> PlayState animations(AnimationEvent<E> event) {
        if (this.attackAnim > 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.skizzo.attack"));
        }
        else if (event.getController().getAnimationState() == AnimationState.Stopped) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.skizzo.ambient"));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "animations", 0, this::animations));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.setNoGravity(true);
    }
    
    @Override
    protected void customServerAiStep() {
        LivingEntity target = this.getTarget();
        if (target != null) {
            Vec3 vector = this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D);
            double vectorY = vector.y;
            if (this.getY() + this.getEyeHeight() < target.getY() + target.getEyeHeight()) {
                vectorY = Math.max(0.0D, vectorY);
                vectorY = vectorY + (0.3D - vectorY * (double)0.6F);
            }

            vector = new Vec3(vector.x, vectorY, vector.z);
            Vec3 vector1 = new Vec3(target.getX() - this.getX(), 0.0D, target.getZ() - this.getZ());
            if (vector1.horizontalDistanceSqr() > 9.0D) {
                Vec3 vector2 = vector1.normalize();
                vector = vector.add(vector2.x * 0.3D - vector.x * 0.6D, 0.0D, vector2.z * 0.3D - vector.z * 0.6D);
            }

            this.setDeltaMovement(vector);
        }
        else {
            BlockPos pos = this.blockPosition().below().below();
            BlockState state = this.level.getBlockState(pos);
            if (!state.getBlock().getCollisionShape(state, this.level, pos, CollisionContext.of(this)).isEmpty()) {
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y + 0.004D, this.getDeltaMovement().z);
            }
        }
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
}
