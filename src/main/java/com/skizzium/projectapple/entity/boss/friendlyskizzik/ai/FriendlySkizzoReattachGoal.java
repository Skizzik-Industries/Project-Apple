package com.skizzium.projectapple.entity.boss.friendlyskizzik.ai;

import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzik;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzo;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class FriendlySkizzoReattachGoal extends Goal {
    private final Mob skizzo;
    
    public FriendlySkizzoReattachGoal(Mob entity) {
        this.skizzo = entity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean isInterruptable() {
        return false;
    }

    @Override
    public boolean canUse() {
        return skizzo instanceof FriendlySkizzo && ((FriendlySkizzo) skizzo).getOwner() != null && ((FriendlySkizzo) skizzo).getOwner().isAlive() && ((FriendlySkizzo) skizzo).getOwner() instanceof FriendlySkizzik;
    }
    
    private boolean canAttach() {
        boolean x = false;
        boolean y = false;
        boolean z = false;
        double offset = 0.5;
        FriendlySkizzik skizzik = (FriendlySkizzik) ((FriendlySkizzo) skizzo).getOwner();
        
        if (skizzo.getX() > skizzik.getHeadX(((FriendlySkizzo) skizzo).getHead()) - offset && skizzo.getX() < skizzik.getHeadX(((FriendlySkizzo) skizzo).getHead()) + offset) {
            x = true;
        }

        if (skizzo.getY() > skizzik.getHeadY(((FriendlySkizzo) skizzo).getHead()) - offset && skizzo.getY() < skizzik.getHeadY(((FriendlySkizzo) skizzo).getHead()) + offset) {
            y = true;
        }

        if (skizzo.getZ() > skizzik.getHeadZ(((FriendlySkizzo) skizzo).getHead()) - offset && skizzo.getZ() < skizzik.getHeadZ(((FriendlySkizzo) skizzo).getHead()) + offset) {
            z = true;
        }
        
        return x && y && z;
    }

    @Override
    public void tick() {
        if (skizzo instanceof FriendlySkizzo && ((FriendlySkizzo) skizzo).getOwner() instanceof FriendlySkizzik) {
            FriendlySkizzik skizzik = (FriendlySkizzik) ((FriendlySkizzo) skizzo).getOwner();
            if (this.canAttach()) {
                skizzik.reattachHead(FriendlySkizzik.Heads.values()[((FriendlySkizzo) skizzo).getHead() - 1]);
                
                if (!this.skizzo.getPassengers().isEmpty()) {
                    Entity passanger = this.skizzo.getControllingPassenger();
                    if (passanger != null) {
                        passanger.stopRiding();
                        passanger.startRiding(skizzik);
                    }
                }
                
                this.skizzo.discard();
            }
            else {
                skizzo.getLookControl().setLookAt(skizzik.getHeadX(((FriendlySkizzo) skizzo).getHead()), skizzik.getHeadY(((FriendlySkizzo) skizzo).getHead()), skizzik.getHeadZ(((FriendlySkizzo) skizzo).getHead()));
                skizzo.getMoveControl().setWantedPosition(skizzik.getHeadX(((FriendlySkizzo) skizzo).getHead()), skizzik.getHeadY(((FriendlySkizzo) skizzo).getHead()), skizzik.getHeadZ(((FriendlySkizzo) skizzo).getHead()), 5.0D);
            }
        }
    }
}
