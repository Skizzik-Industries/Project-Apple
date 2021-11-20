package com.skizzium.projectapple.entity.boss.skizzik.ai;

import com.skizzium.projectapple.entity.boss.skizzik.FriendlySkizzik;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;

public class FriendlySkizzikRangedAttackGoal extends RangedAttackGoal {
    private final FriendlySkizzik mob;
    
    public FriendlySkizzikRangedAttackGoal(RangedAttackMob pRangedAttackMob, double pSpeedModifier, int pAttackInterval, float pAttackRadius) {
        super(pRangedAttackMob, pSpeedModifier, pAttackInterval, pAttackRadius);
        this.mob = (FriendlySkizzik) pRangedAttackMob;
    }

    @Override
    public boolean canUse() {
        if (!mob.getPassengers().isEmpty()) {
            return false;
        }
        return super.canUse();
    }
}
