package com.skizzium.projectapple.entity.boss.skizzik;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.ai.FriendlySkizzoReattachGoal;
import com.skizzium.projectapple.init.PA_ClientHelper;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.util.SkizzieConversion;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.UUID;

public class FriendlySkizzo extends Monster {
    private int head;
    private UUID ownerUUID;
    private int ownerNetworkId;

    public FriendlySkizzo(EntityType<? extends FriendlySkizzo> entity, Level world) {
        super(entity, world);
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
        return 1.4F;
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
    protected void doPush(Entity entity) {
        if (this.isPushable() && !(entity instanceof FriendlySkizzo) && !(entity instanceof FriendlySkizzik)) {
            super.doPush(entity);
        }
    }

    @Override
    public boolean isPushable() {
        return super.isPushable();
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
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
                source == DamageSource.WITHER) {
            return false;
        }
        return super.hurt(source, amount);
    }

    public static AttributeSupplier.Builder buildAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.FLYING_SPEED, 0.2D);
    }

    protected void registerGoals() {
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 0, true, true, PA_Entities.FRIENDLY_SKIZZIK_SELECTOR));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.D, 0.0F));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    public int getHead() {
        return this.head;
    }

    public void setHead(int head) {
        this.head = head;
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

    public void setOwner(@Nullable Entity entity) {
        if (entity != null) {
            this.ownerUUID = entity.getUUID();
            this.ownerNetworkId = entity.getId();
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putInt("Head", this.head);
        if (this.ownerUUID != null) {
            nbt.putUUID("Owner", this.ownerUUID);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        this.head = nbt.getInt("Head");
        if (nbt.hasUUID("Owner")) {
            this.ownerUUID = nbt.getUUID("Owner");
        }
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

    private void doPlayerRide(Player player) {
        if (!this.level.isClientSide) {
            player.setYRot(this.getYRot());
            player.setXRot(this.getXRot());
            player.startRiding(this);
        }
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        this.doPlayerRide(player);
        return InteractionResult.sidedSuccess(player.level.isClientSide);
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
                this.yHeadRot = this.yBodyRot;
                this.yBodyRot = this.getYRot();

                float moveX = livingentity.xxa * 0.5F;
                double moveY = pos.y;
                float moveZ = livingentity.zza;
                if (moveZ <= 0.0F) {
                    moveZ *= 0.25F;
                }

                this.flyingSpeed = this.getSpeed() * 0.05F;
                if (this.isControlledByLocalInstance()) {
                    if (PA_ClientHelper.getClient().options.keyJump.isDown()) {
                        moveY = 0.35F;
                    }
                    else if (PA_ClientHelper.getClient().options.keySprint.isDown()) {
                        moveY = -0.35F;
                    }
                    else {
                        moveY = 0.0F;
                    }

                    this.setSpeed((float)this.getAttributeValue(Attributes.MOVEMENT_SPEED));
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
}
