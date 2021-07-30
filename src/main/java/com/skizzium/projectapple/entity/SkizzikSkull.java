package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.init.PA_Entities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import static net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent;

public class SkizzikSkull extends DamagingProjectileEntity {
    private static final DataParameter<Integer> DATA_LEVEL = EntityDataManager.defineId(SkizzikSkull.class, DataSerializers.INT);

    public SkizzikSkull(EntityType<? extends SkizzikSkull> entity, World world) {
        super(entity, world);
    }

    public SkizzikSkull(World world, LivingEntity entity, double x, double y, double z) {
        super(PA_Entities.SKIZZIK_SKULL, entity, x, y, z, world);
    }

    @OnlyIn(Dist.CLIENT)
    public SkizzikSkull(World world, double d0, double d1, double d2, double d3, double d4, double d5) {
        super(PA_Entities.SKIZZIK_SKULL, d0, d1, d2, d3, d4, d5, world);
    }

    public SkizzikSkull(World world) {
        super(PA_Entities.SKIZZIK_SKULL, world);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected IParticleData getTrailParticle() {
        return this.getLevel() <= 2 ? ParticleTypes.FLAME
                : ParticleTypes.SOUL_FIRE_FLAME;
    }

    @Override
    protected float getInertia() {
        return super.getInertia();
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public float getBlockExplosionResistance(Explosion explosion, IBlockReader world, BlockPos pos, BlockState state, FluidState fluidState, float f1) {
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
    protected void onHitEntity(EntityRayTraceResult entity) {
        super.onHitEntity(entity);
        if (!this.level.isClientSide) {
            Entity target = entity.getEntity();
            Entity source = this.getOwner();
            boolean hurt;
            if (source instanceof LivingEntity) {
                Skizzik skizzik = (Skizzik)source;
                hurt = this.getLevel() == 1 ? target.hurt(skizzikSkull(this, skizzik), 8.0F)
                        : this.getLevel() == 2 ? target.hurt(skizzikSkull(this, skizzik), 10.0F)
                        : target.hurt(skizzikSkull(this, skizzik), 15.0F);
                if (hurt) {
                    if (target.isAlive()) {
                        this.doEnchantDamageEffects(skizzik, target);
                    }
                    else {
                        float health = skizzik.getHealth();
                        int stage = skizzik.getStage();

                        if (stage == 5 && health <= 264) {
                            skizzik.heal(6.0F);
                        }
                        else if (stage == 4 && health <= 464.76) {
                            skizzik.heal(5.25F);
                        }
                        else if (stage == 3 && health <= 665.5) {
                            skizzik.heal(4.5F);
                        }
                        else if (stage == 2 && health <= 866.25) {
                            skizzik.heal(3.75F);
                        }
                        else if (stage == 1 && health <= 1017) {
                            skizzik.heal(3.0F);
                        }
                    }
                }
            }
            else {
                hurt = target.hurt(DamageSource.MAGIC, 5.0F);
            }

            if (hurt && target instanceof LivingEntity) {
                if (this.getLevel() >= 3) {
                    ((LivingEntity)target).addEffect(new EffectInstance(Effects.WITHER, 400, 2));
                }
                else {
                    ((LivingEntity)target).addEffect(new EffectInstance(Effects.WITHER, 800, 1));
                }

                if (!target.fireImmune()) {
                    target.setRemainingFireTicks(15);
                }
            }

        }
    }

    @Override
    protected void onHit(RayTraceResult entity) {
        super.onHit(entity);
        if (!this.level.isClientSide) {
            Explosion.Mode explosion = getMobGriefingEvent(this.level, this.getOwner()) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.0F, false, explosion);
            this.remove();
        }
    }
}
