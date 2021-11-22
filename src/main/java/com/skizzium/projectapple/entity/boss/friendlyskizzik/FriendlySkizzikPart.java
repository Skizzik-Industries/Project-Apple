package com.skizzium.projectapple.entity.boss.friendlyskizzik;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.entity.PartEntity;

public class FriendlySkizzikPart extends PartEntity<FriendlySkizzik> {
    public final FriendlySkizzik skizzik;
    public final String name;
    private final EntityDimensions size;
    
    public FriendlySkizzikPart(FriendlySkizzik skizzik, String name, float width, float height) {
        super(skizzik);
        this.skizzik = skizzik;
        this.name = name;
        this.size = EntityDimensions.scalable(width, height);
        this.refreshDimensions();
    }
    
    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {}

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (this == skizzik.commandBlockPart) {
            if (this.skizzik.isCommandBlockPlaced()) {
                return this.skizzik.mobInteract(this, player, hand);
            }
            return InteractionResult.PASS;
        }
        return this.skizzik.mobInteract(this, player, hand);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this == skizzik.commandBlockPart) {
            return this.skizzik.isCommandBlockPlaced() && this.skizzik.hurt(this, source, amount);
        }
        return this.skizzik.hurt(this, source, amount);
    }
    
    @Override
    public boolean is(Entity entity) {
        return this == entity || this.skizzik == entity;
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return this.size;
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }
}
