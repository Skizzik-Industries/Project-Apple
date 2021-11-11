package com.skizzium.projectapple.entity.boss.skizzik;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;

public class FriendlySkizzikHeadPart extends FriendlySkizzikPart {
    private static final EntityDataAccessor<Float> DATA_HEALTH = SynchedEntityData.defineId(FriendlySkizzik.class, EntityDataSerializers.FLOAT);
    public final FriendlySkizzik.Heads head;
    
    public FriendlySkizzikHeadPart(FriendlySkizzik skizzik, String name, @Nullable FriendlySkizzik.Heads head, float width, float height) {
        super(skizzik, name, width, height);
        this.head = head;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_HEALTH, 150.0F);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putFloat("Health", this.getHealth());
    }
    
    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setHealth(nbt.getFloat("Health"));
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (this.skizzik.getAddedHeads().contains(this.head) && !this.skizzik.getDetachedHeads().contains(this.head)) {
            return this.skizzik.mobInteract(this, player, hand);
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return this.skizzik.getAddedHeads().contains(this.head) && !this.skizzik.getDetachedHeads().contains(this.head) && this.skizzik.hurt(this, source, amount);
    }

    public float getHealth() {
        return this.entityData.get(DATA_HEALTH);
    }

    public void setHealth(float health) {
        this.entityData.set(DATA_HEALTH, Mth.clamp(health, 0.0F, 150.0F));
    }

    private float getDamageAfterArmorAbsorb(DamageSource source, float damage) {
        if (!source.isBypassArmor()) {
            damage = CombatRules.getDamageAfterAbsorb(damage, skizzik.getArmorValue(), (float) skizzik.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
        }

        return damage;
    }
    
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } 
        else if (this.level.isClientSide) {
            return false;
        } 
        else {
            amount = this.getDamageAfterArmorAbsorb(source, amount);
            float f = Math.max(amount, 0.0F);
            float f1 = amount - f;
            if (f1 > 0.0F && f1 < 3.4028235E37F && source.getEntity() instanceof ServerPlayer) {
                ((ServerPlayer)source.getEntity()).awardStat(Stats.CUSTOM.get(Stats.DAMAGE_DEALT_ABSORBED), Math.round(f1 * 10.0F));
            }

            if (f != 0.0F) {
                float f2 = this.getHealth();
                if (f2 - f <= 0.0F) {
                    this.skizzik.removeHead(this.head);
                    this.setHealth(150.0F);
                    return true;
                }
                
                this.setHealth(f2 - f);
                this.gameEvent(GameEvent.ENTITY_DAMAGED, source.getEntity());
                skizzik.hurt(this, source, 0.000042069F);
            }
            
            return true;
        }
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().size() < 1;
    }

    @Override
    public Entity getControllingPassenger() {
        return this.getFirstPassenger();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isControlledByLocalInstance()) {
            if (this.level.isClientSide) {
                if (this.isVehicle()) {
                    this.setXRot(this.getControllingPassenger().getXRot());
                    this.setYRot(this.getControllingPassenger().getYRot());
                }
            }
        }
    }
}
