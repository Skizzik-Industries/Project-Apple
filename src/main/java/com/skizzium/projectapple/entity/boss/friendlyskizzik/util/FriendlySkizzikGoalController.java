package com.skizzium.projectapple.entity.boss.friendlyskizzik.util;

import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzik;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.ai.FriendlySkizzikRangedAttackGoal;
import com.skizzium.projectapple.init.entity.PA_Entities;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;

public class FriendlySkizzikGoalController {
    public final AvoidEntityGoal avoidPlayerGoal;
    public final PanicGoal panicGoal;

    public final HurtByTargetGoal hurtGoal;
    public final NearestAttackableTargetGoal attackGoal;
    public final FriendlySkizzikRangedAttackGoal rangedAttackGoal;
    public final WaterAvoidingRandomStrollGoal avoidWaterGoal;
    public final LookAtPlayerGoal lookGoal;
    public final RandomLookAroundGoal lookRandomlyGoal;
    
    private final FriendlySkizzik skizzik;
    private boolean hasGoals;
    
    public FriendlySkizzikGoalController(FriendlySkizzik skizzik) {
        this.skizzik = skizzik;
        this.hasGoals = false;
        
        this.avoidPlayerGoal = new AvoidEntityGoal<>(skizzik, Player.class, 25, 1.2D, 1.7D);
        this.panicGoal = new PanicGoal(skizzik, 1.5D);

        this.hurtGoal = new HurtByTargetGoal(skizzik);
        this.attackGoal = new NearestAttackableTargetGoal<>(skizzik, Mob.class, 0, false, false, PA_Entities.FRIENDLY_SKIZZIK_SELECTOR);
        this.rangedAttackGoal = new FriendlySkizzikRangedAttackGoal(skizzik, 1.0D, 40, 20.0F);
        this.avoidWaterGoal = new WaterAvoidingRandomStrollGoal(skizzik, 1.0D);
        this.lookGoal = new LookAtPlayerGoal(skizzik, Player.class, 8.0F);
        this.lookRandomlyGoal = new RandomLookAroundGoal(skizzik);
    }
    
    public void addDefaultGoals() {
        if (!hasGoals) {
            skizzik.goalSelector.removeAllGoals();
            skizzik.targetSelector.removeAllGoals();
    
            skizzik.targetSelector.addGoal(1, this.hurtGoal);
            skizzik.targetSelector.addGoal(2, this.attackGoal);
            skizzik.goalSelector.addGoal(2, this.rangedAttackGoal);
            skizzik.goalSelector.addGoal(5, this.avoidWaterGoal);
            skizzik.goalSelector.addGoal(6, this.lookGoal);
            skizzik.goalSelector.addGoal(7, this.lookRandomlyGoal);
    
            this.hasGoals = true;
        }
    }
}
