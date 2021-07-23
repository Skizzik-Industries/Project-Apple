package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.init.PA_Entities;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import static net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent;

public class SkizzikSkull extends AbstractHurtingProjectile {
    private static final EntityDataAccessor<Integer> DATA_LEVEL = SynchedEntityData.defineId(SkizzikSkull.class, EntityDataSerializers.INT);

    public SkizzikSkull(EntityType<? extends SkizzikSkull> entity, Level world) {
        super(entity, world);
    }

    public SkizzikSkull(Level world, LivingEntity entity, double x, double y, double z) {
        super(PA_Entities.SKIZZIK_SKULL, entity, x, y, z, world);
    }

    @OnlyIn(Dist.CLIENT)
    public SkizzikSkull(Level world, double d0, double d1, double d2, double d3, double d4, double d5) {
        super(PA_Entities.SKIZZIK_SKULL, d0, d1, d2, d3, d4, d5, world);
    }

    public SkizzikSkull(Level world) {
        super(PA_Entities.SKIZZIK_SKULL, world);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return this.getLevel() <= 2 ? ParticleTypes.FLAME
                : ParticleTypes.SOUL_FIRE_FLAME;
    }

    @Override
    protected float getInertia() {
        return this.getLevel() == 1 ? super.getInertia()
                : this.getLevel() == 2 ? 0.73F
                : 0.50F;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public float getBlockExplosionResistance(Explosion explosion, BlockGetter world, BlockPos pos, BlockState state, FluidState fluidState, float f1) {
        return this.getLevel() >= 2 && state.canEntityDestroy(world, pos, this) ? Math.min(0.8F, f1) : f1;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        return false;
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_LEVEL, 1);
    }

    public int getLevel() {
        return this.entityData.get(DATA_LEVEL);
    }

    public void setLevel(int level) {
        this.entityData.set(DATA_LEVEL, level);
    }

    protected boolean shouldBurn() {
        return false;
    }

    public static DamageSource skizzikSkull(SkizzikSkull skull, Entity entity) {
        return (new IndirectEntityDamageSource("skizzikSkull", skull, entity)).setProjectile();
    }

    @Override
    protected void onHitEntity(EntityHitResult entity) {
        super.onHitEntity(entity);
        if (!this.level.isClientSide) {
            Entity target = entity.getEntity();
            Entity source = this.getOwner();
            boolean hurt;
            if (source instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)source;
                hurt = this.getLevel() == 1 ? target.hurt(skizzikSkull(this, livingentity), 8.0F)
                        : this.getLevel() == 2 ? target.hurt(skizzikSkull(this, livingentity), 10.0F)
                        : target.hurt(skizzikSkull(this, livingentity), 15.0F);
                if (hurt) {
                    if (target.isAlive()) {
                        this.doEnchantDamageEffects(livingentity, target);
                    }
                    else {
                        livingentity.heal(5.0F);
                    }
                }
            }
            else {
                hurt = target.hurt(DamageSource.MAGIC, 5.0F);
            }

            if (hurt && target instanceof LivingEntity) {
                if (this.getLevel() >= 3) {
                    ((LivingEntity)target).addEffect(new MobEffectInstance(MobEffects.WITHER, 400, 2));
                }
                else {
                    ((LivingEntity)target).addEffect(new MobEffectInstance(MobEffects.WITHER, 800, 1));
                }

                if (!target.fireImmune()) {
                    target.setRemainingFireTicks(15);
                }
            }

        }
    }

    @Override
    protected void onHit(HitResult entity) {
        super.onHit(entity);
        if (!this.level.isClientSide) {
            Explosion.BlockInteraction explosion = getMobGriefingEvent(this.level, this.getOwner()) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.0F, false, explosion);
            this.remove();
        }
    }
}
