package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.init.PA_Entities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
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

public class SkizzikSkullOld extends DamagingProjectileEntity {
    private static final DataParameter<Integer> DATA_LEVEL = EntityDataManager.defineId(SkizzikSkullOld.class, DataSerializers.INT);

    public SkizzikSkullOld(EntityType<? extends SkizzikSkullOld> entity, World world) {
        super(entity, world);
    }

    public SkizzikSkullOld(World world, LivingEntity entity, float power, double damage, int knockback) {
        super(PA_Entities.SKIZZIK_SKULL, entity, power, damage, knockback, world);
    }

    @OnlyIn(Dist.CLIENT)
    public SkizzikSkullOld(World world, double d0, double d1, double d2, double d3, double d4, double d5) {
        super(PA_Entities.SKIZZIK_SKULL, d0, d1, d2, d3, d4, d5, world);
    }

    /*@Override
    protected float getInertia() {
        int level = this.getLevel();

        if (level == 1) {
            return super.getInertia();
        }
        else if (level == 2) {
            return 0.73F;
        }
        else {
            return 0.50F;
        }
    } */

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public float getBlockExplosionResistance(Explosion explosion, IBlockReader world, BlockPos pos, BlockState state, FluidState fluidState, float f1) {
        return /* this.getLevel() >= 2 && state.canEntityDestroy(world, pos, this) ? Math.min(0.8F, f1) :*/ f1;
    }

    public static DamageSource skizzikSkull(SkizzikSkullOld skull, Entity entity) {
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
                LivingEntity livingentity = (LivingEntity)source;
                hurt = /*this.getLevel() == 1 ? target.hurt(skizzikSkull(this, livingentity), 8.0F)
                        : this.getLevel() == 2 ? target.hurt(skizzikSkull(this, livingentity), 10.0F)
                        :*/ target.hurt(skizzikSkull(this, livingentity), 15.0F);
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
                //if (this.getLevel() >= 2) {
                //    ((LivingEntity)target).addEffect(new EffectInstance(Effects.WITHER, 400, 2));
                //}
                //else {
                    ((LivingEntity)target).addEffect(new EffectInstance(Effects.WITHER, 800, 1));
                //}

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
            Explosion.Mode explosion = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner()) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.0F, false, explosion);
            this.remove();
        }

    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        return false;
    }

    /* @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_LEVEL, 1);
    }

    public int getLevel() {
        return this.entityData.get(DATA_LEVEL);
    }

    public void setLevel(int level) {
        this.entityData.set(DATA_LEVEL, level);
    } */

    protected boolean shouldBurn() {
        return false;
    }
}
