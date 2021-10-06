package com.skizzium.projectapple.entity.boss.skizzik.ai;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.init.PA_Config;
import com.skizzium.projectapple.util.SafeSpotConditions;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class SkizzikRunAwayGoal extends Goal {
    protected final Skizzik skizzik;
    protected final SafeSpotConditions conditions;
    protected final PathNavigation pathNav;
    protected boolean foundSafeSpot;

    public SkizzikRunAwayGoal(Skizzik skizzik, SafeSpotConditions conditions) {
        this.skizzik = skizzik;
        this.conditions = conditions;
        this.pathNav = skizzik.getNavigation();
        this.foundSafeSpot = skizzik.isRegenerating();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }
    
    @Override
    public boolean canUse() {
        return PA_Config.commonInstance.entities.skizzikComeback.get() && !skizzik.isRegenerating();
    }

    @Override
    public void tick() {
        while (!foundSafeSpot) {
            skizzik.move(MoverType.SELF, new Vec3(skizzik.getX() + 15, skizzik.getY(), skizzik.getZ()));
            Vec3 pos = DefaultRandomPos.getPos(skizzik, 50, (int) Math.round(skizzik.getY()));
            if (conditions.test(skizzik, pos)) {
                foundSafeSpot = true;
                skizzik.move(MoverType.SELF, pos);
                break;
            }
        }
    }
}
