package com.skizzium.projectapple.entity.boss.friendlyskizzik;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.entity.PA_Entities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

import static net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent;

public class FriendlySkizzikSkull extends AbstractHurtingProjectile {
    public FriendlySkizzikSkull(EntityType<? extends FriendlySkizzikSkull> entity, Level world) {
        super(entity, world);
    }

    public FriendlySkizzikSkull(LivingEntity source, Level world) {
        super(PA_Entities.FRIENDLY_SKIZZIK_SKULL.get(), world);
        this.setPos(source.getX(), source.getEyeY() - (double)0.1F, source.getZ());
        this.setOwner(source);
    }

    public FriendlySkizzikSkull(Level world, LivingEntity entity, double x, double y, double z) {
        super(PA_Entities.FRIENDLY_SKIZZIK_SKULL.get(), entity, x, y, z, world);
    }

    @OnlyIn(Dist.CLIENT)
    public FriendlySkizzikSkull(Level world, double x, double y, double z, double xPower, double yPower, double zPower) {
        super(PA_Entities.FRIENDLY_SKIZZIK_SKULL.get(), x, y, z, xPower, yPower, zPower, world);
    }

    public FriendlySkizzikSkull(Level world) {
        super(PA_Entities.FRIENDLY_SKIZZIK_SKULL.get(), world);
    }

    @Override
    protected Component getTypeName() {
        return new TranslatableComponent(ProjectApple.getThemedDescriptionId(super.getType().getDescriptionId()));
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.FALLING_WATER;
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
    public float getBlockExplosionResistance(Explosion explosion, BlockGetter world, BlockPos pos, BlockState state, FluidState fluidState, float f1) {
        if (this.getOwner() instanceof Player && this.getOwner().getVehicle() != null && this.getOwner().getVehicle() instanceof FriendlySkizzik) {
            return FriendlySkizzik.canDestroy(state) ? Math.min(0.5F, f1) : f1;
        }
        return super.getBlockExplosionResistance(explosion, world, pos, state, fluidState, f1);
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        return false;
    }

    protected boolean shouldBurn() {
        return false;
    }

    public static DamageSource skizzikSkull(FriendlySkizzikSkull skull, Entity entity) {
        return (new IndirectEntityDamageSource("skizzikSkull", skull, entity) {
            @Override
            public Component getLocalizedDeathMessage(LivingEntity entity) {
                return new TranslatableComponent(ProjectApple.getThemedDescriptionId("death.attack.skizzikSkull"));
            }
        }).setProjectile();
    }

    @Override
    protected void onHitEntity(EntityHitResult entity) {
        super.onHitEntity(entity);
        if (!this.level.isClientSide) {
            if (!this.getOwner().equals(entity.getEntity()) && !(entity.getEntity() instanceof FriendlySkizzik)) {
                Entity target = entity.getEntity();
                Entity source = this.getOwner();
                boolean hurt;
                if (source instanceof FriendlySkizzik) {
                    FriendlySkizzik skizzik = (FriendlySkizzik) source;
                    hurt = target.hurt(skizzikSkull(this, skizzik), 10.0F);
                    if (hurt) {
                        if (target.isAlive()) {
                            this.doEnchantDamageEffects(skizzik, target);
                        }
                        //                    else {
                        //                        float health = skizzik.getHealth();
                        //                        SkizzikStageInterface stage = skizzik.stageManager.getCurrentStage();
                        //
                        //                        if (stage.getStage().getId() == 5 && health <= stage.maxStageHealth() - 6.0F) {
                        //                            skizzik.heal(6.0F);
                        //                        }
                        //                        else if (stage.getStage().getId() == 4 && health <= stage.maxStageHealth() - 2.25F) {
                        //                            skizzik.heal(5.25F);
                        //                        }
                        //                        else if (stage.getStage().getId() == 3 && health <= stage.maxStageHealth() - 4.5F) {
                        //                            skizzik.heal(4.5F);
                        //                        }
                        //                        else if (stage.getStage().getId() == 2 && health <= stage.maxStageHealth() - 3.75F) {
                        //                            skizzik.heal(3.75F);
                        //                        }
                        //                        else if (stage.getStage().getId() == 1 && health <= stage.maxStageHealth() - 3.0F) {
                        //                            skizzik.heal(3.0F);
                        //                        }
                        //                    }
                    }
                } else {
                    hurt = target.hurt(DamageSource.MAGIC, 5.0F);
                }

                if (hurt && target instanceof LivingEntity) {
                    ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.WITHER, 800, 1));

                    if (!target.fireImmune()) {
                        target.setRemainingFireTicks(15);
                    }
                }
            }
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level.isClientSide) {
            Explosion.BlockInteraction explosion = getMobGriefingEvent(this.level, this.getOwner()) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.0F, false, explosion);
            this.discard();
        }
    }
}
