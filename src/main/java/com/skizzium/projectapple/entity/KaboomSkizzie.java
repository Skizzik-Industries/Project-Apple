package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.entity.ai.KaboomSkizzieSwellGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collection;

public class KaboomSkizzie extends Skizzie implements PowerableMob {
    private static final EntityDataAccessor<Integer> DATA_SWELL_DIR = SynchedEntityData.defineId(KaboomSkizzie.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_IS_POWERED = SynchedEntityData.defineId(KaboomSkizzie.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_IS_IGNITED = SynchedEntityData.defineId(KaboomSkizzie.class, EntityDataSerializers.BOOLEAN);
    private int oldSwell;
    private int swell;
    private int maxSwell = 30;
    private int explosionRadius = 3;

    public KaboomSkizzie(EntityType<? extends Skizzie> entity, Level world) {
        super(entity, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
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
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.putShort("Fuse", (short)this.maxSwell);
        nbt.putByte("ExplosionRadius", (byte)this.explosionRadius);
        nbt.putBoolean("ignited", this.isIgnited());

        if (this.entityData.get(DATA_IS_POWERED)) {
            nbt.putBoolean("powered", true);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
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
        return Mth.lerp(f, (float)this.oldSwell, (float)this.swell) / (float)(this.maxSwell - 2);
    }

    public int getSwellDir() {
        return this.entityData.get(DATA_SWELL_DIR);
    }

    public void setSwellDir(int value) {
        this.entityData.set(DATA_SWELL_DIR, value);
    }

    @Override
    public void thunderHit(ServerLevel world, LightningBolt lightning) {
        super.thunderHit(world, lightning);
        this.entityData.set(DATA_IS_POWERED, true);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack item = player.getItemInHand(hand);

        if (item.getItem() == Items.FLINT_AND_STEEL) {
            this.level.playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.FLINTANDSTEEL_USE, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);

            if (!this.level.isClientSide) {
                this.ignite();
                item.hurtAndBreak(1, player, (event) -> event.broadcastBreakEvent(hand));
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }
        else {
            return super.mobInteract(player, hand);
        }
    }

    private void explodeSkizzie() {
        if (!this.level.isClientSide) {
            Explosion.BlockInteraction explosion = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
            float f = this.isPowered() ? 2.0F : 1.0F;
            this.dead = true;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, explosion);
            this.remove(false);
            this.spawnLingeringCloud();
        }
    }

    private void spawnLingeringCloud() {
        Collection<MobEffectInstance> effects = this.getActiveEffects();

        if (!effects.isEmpty()) {
            AreaEffectCloud effectCloud = new AreaEffectCloud(this.level, this.getX(), this.getY(), this.getZ());
            effectCloud.setRadius(2.5F);
            effectCloud.setRadiusOnUse(-0.5F);
            effectCloud.setWaitTime(10);
            effectCloud.setDuration(effectCloud.getDuration() / 2);
            effectCloud.setRadiusPerTick(-effectCloud.getRadius() / (float)effectCloud.getDuration());

            for(MobEffectInstance instance : effects) {
                effectCloud.addEffect(new MobEffectInstance(instance));
            }

            this.level.addFreshEntity(effectCloud);
        }
    }
}
