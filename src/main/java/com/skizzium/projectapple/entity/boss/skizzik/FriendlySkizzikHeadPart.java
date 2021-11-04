package com.skizzium.projectapple.entity.boss.skizzik;

import net.minecraft.network.protocol.game.ServerboundPaddleBoatPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class FriendlySkizzikHeadPart extends FriendlySkizzikPart {
    public final FriendlySkizzik.Heads head;
    
    public FriendlySkizzikHeadPart(FriendlySkizzik skizzik, String name, @Nullable FriendlySkizzik.Heads head, float width, float height) {
        super(skizzik, name, width, height);
        this.head = head;
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
