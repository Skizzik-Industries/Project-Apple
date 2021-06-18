package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.entity.ai.KaboomSkizzieSwellGoal;
import com.skizzium.projectapple.init.ModEntities;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collection;

public class KaboomSkizzie extends Skizzie implements IChargeableMob {
    private static final DataParameter<Integer> DATA_SWELL_DIR = EntityDataManager.defineId(KaboomSkizzie.class, DataSerializers.INT);
    private static final DataParameter<Boolean> DATA_IS_POWERED = EntityDataManager.defineId(KaboomSkizzie.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> DATA_IS_IGNITED = EntityDataManager.defineId(KaboomSkizzie.class, DataSerializers.BOOLEAN);
    private int oldSwell;
    private int swell;
    private int maxSwell = 30;
    private int explosionRadius = 3;
    private int droppedSkulls;

    public KaboomSkizzie(EntityType<? extends Skizzie> entity, World world) {
        super(entity, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return 1.35F;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.CREEPER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CREEPER_DEATH;
    }

    public boolean isIgnited() {
        return this.entityData.get(DATA_IS_IGNITED);
    }

    public void ignite() {
        this.entityData.set(DATA_IS_IGNITED, true);
    }

    @Override
    public boolean isPowered() {
        return this.entityData.get(DATA_IS_POWERED);
    }

    @Override
    public void tick() {
        if (this.isAlive()) {
            this.oldSwell = this.swell;
            if (this.isIgnited()) {
                this.setSwellDir(1);
            }

            int i = this.getSwellDir();
            if (i > 0 && this.swell == 0) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
            }

            this.swell += i;
            if (this.swell < 0) {
                this.swell = 0;
            }

            if (this.swell >= this.maxSwell) {
                this.swell = this.maxSwell;
                this.explodeSkizzie();
            }
        }
        super.tick();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(4, new KaboomSkizzieSwellGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SWELL_DIR, -1);
        this.entityData.define(DATA_IS_POWERED, false);
        this.entityData.define(DATA_IS_IGNITED, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.putShort("Fuse", (short)this.maxSwell);
        nbt.putByte("ExplosionRadius", (byte)this.explosionRadius);
        nbt.putBoolean("ignited", this.isIgnited());

        if (this.entityData.get(DATA_IS_POWERED)) {
            nbt.putBoolean("powered", true);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.entityData.set(DATA_IS_POWERED, nbt.getBoolean("powered"));

        if (nbt.contains("Fuse", 99)) {
            this.maxSwell = nbt.getShort("Fuse");
        }

        if (nbt.contains("ExplosionRadius", 99)) {
            this.explosionRadius = nbt.getByte("ExplosionRadius");
        }

        if (nbt.getBoolean("ignited")) {
            this.ignite();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public float getSwelling(float f) {
        return MathHelper.lerp(f, (float)this.oldSwell, (float)this.swell) / (float)(this.maxSwell - 2);
    }

    public int getSwellDir() {
        return this.entityData.get(DATA_SWELL_DIR);
    }

    public void setSwellDir(int value) {
        this.entityData.set(DATA_SWELL_DIR, value);
    }

    @Override
    public void thunderHit(ServerWorld world, LightningBoltEntity lightning) {
        super.thunderHit(world, lightning);
        this.entityData.set(DATA_IS_POWERED, true);
    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack item = player.getItemInHand(hand);

        if (item.getItem() == Items.FLINT_AND_STEEL) {
            this.level.playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.FLINTANDSTEEL_USE, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);

            if (!this.level.isClientSide) {
                this.ignite();
                item.hurtAndBreak(1, player, (event) -> event.broadcastBreakEvent(hand));
            }

            return ActionResultType.sidedSuccess(this.level.isClientSide);
        }
        else {
            return super.mobInteract(player, hand);
        }
    }

    private void explodeSkizzie() {
        if (!this.level.isClientSide) {
            Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            float f = this.isPowered() ? 2.0F : 1.0F;
            this.dead = true;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, explosion$mode);
            this.remove();
            this.spawnLingeringCloud();
        }
    }

    private void spawnLingeringCloud() {
        Collection<EffectInstance> effects = this.getActiveEffects();

        if (!effects.isEmpty()) {
            AreaEffectCloudEntity effectCloud = new AreaEffectCloudEntity(this.level, this.getX(), this.getY(), this.getZ());
            effectCloud.setRadius(2.5F);
            effectCloud.setRadiusOnUse(-0.5F);
            effectCloud.setWaitTime(10);
            effectCloud.setDuration(effectCloud.getDuration() / 2);
            effectCloud.setRadiusPerTick(-effectCloud.getRadius() / (float)effectCloud.getDuration());

            for(EffectInstance instance : effects) {
                effectCloud.addEffect(new EffectInstance(instance));
            }

            this.level.addFreshEntity(effectCloud);
        }
    }
}
