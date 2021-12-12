package com.skizzium.projectapple.entity.boss.friendlyskizzik;

import com.skizzium.projectapple.entity.boss.AbstractSkizzo;
import com.skizzium.projectapple.init.events.PA_ModClientEvents;
import com.skizzium.projectapple.init.network.PA_PacketRegistry;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.network.SkizzoConnectionParticlesPacket;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nullable;

public class FriendlySkizzo extends AbstractSkizzo {
    private int head;

    public FriendlySkizzo(EntityType<? extends FriendlySkizzo> entity, Level world) {
        super(entity, world);
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

    public static AttributeSupplier.Builder buildAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.FLYING_SPEED, 0.2D);
    }
    
    public static FriendlySkizzo getSkizzoWithHead(Level world, FriendlySkizzik.Heads head) {
        if (world instanceof ServerLevel) {
            LevelEntityGetter<Entity> entityGetter = ((ServerLevel) world).getEntities();
            Iterable<Entity> entities = entityGetter.getAll();
            for (Entity entity : entities) {
                if (entity instanceof FriendlySkizzo && ((FriendlySkizzo) entity).getHead() == head.ordinal()) {
                    return (FriendlySkizzo) entity;
                }
            }
        }
        return null;
    }
    
    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 0, true, true, PA_Entities.FRIENDLY_SKIZZIK_SELECTOR));
    }

    public int getHead() {
        return this.head;
    }

    public void setHead(int head) {
        this.head = head;
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
                    if (PA_ModClientEvents.getClient().options.keyJump.isDown()) {
                        moveY = 0.35F;
                    }
                    else if (PA_ModClientEvents.getClient().options.keySprint.isDown()) {
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
    public void aiStep() {
        super.aiStep();
        this.level.addParticle(ParticleTypes.DRIPPING_WATER, this.getX() + this.random.nextGaussian() * (double)0.2F, this.getY() + this.getEyeHeight() + this.random.nextGaussian() * (double)0.2F, this.getZ() + this.random.nextGaussian() * (double)0.2F, 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected void customServerAiStep() {
        if (this.getOwner() != null && this.getOwner() instanceof FriendlySkizzik && this.distanceTo(this.getOwner()) < 25) {
            PA_PacketRegistry.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new SkizzoConnectionParticlesPacket(this.getOwner().getId(), this.getId()));
        }
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        
        if (this.getOwner() != null && this.getOwner() instanceof FriendlySkizzik) {
            ((FriendlySkizzik) this.getOwner()).removeHead(FriendlySkizzik.Heads.values()[this.head]);
        }
    }
}
