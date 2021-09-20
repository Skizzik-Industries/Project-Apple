package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.ThrownTrident;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class SkizzikStage<T extends SkizzikStageInterface> {
    private static SkizzikStage<?>[] stages = new SkizzikStage[0];
    public static final SkizzikStage<SkizzikSleeping> SLEEPING = create(SkizzikSleeping.class);
    public static final SkizzikStage<SkizzikStage1> STAGE_1 = create(SkizzikStage1.class);
    public static final SkizzikStage<SkizzikStage2> STAGE_2 = create(SkizzikStage2.class);
    public static final SkizzikStage<SkizzikStage3> STAGE_3 = create(SkizzikStage3.class);
    public static final SkizzikStage<SkizzikStage4> STAGE_4 = create(SkizzikStage4.class);
    public static final SkizzikStage<SkizzikStage5> STAGE_5 = create(SkizzikStage5.class);
    public static final SkizzikStage<SkizzikFinishHim> FINISH_HIM = create(SkizzikFinishHim.class);
    
    private final Class<? extends SkizzikStageInterface> stageClass;
    private final int id;
    
    private SkizzikStage(int id, Class<? extends SkizzikStageInterface> clazz) {
        this.id = id;
        this.stageClass = clazz;
    }
    
    public static boolean isImmune(Skizzik skizzik, DamageSource source) {
        int stage = skizzik.stageManager.getCurrentStage().getStage().getId();

        if (source == DamageSource.LIGHTNING_BOLT ||
                source == DamageSource.HOT_FLOOR ||
                source == DamageSource.IN_FIRE ||
                source == DamageSource.LAVA ||
                source == DamageSource.ON_FIRE) {
            return true;
        }
        
        if (stage < 6) {
            if (source == DamageSource.CACTUS ||
                    source == DamageSource.FALL ||
                    source.isExplosion() ||
                    source == DamageSource.SWEET_BERRY_BUSH ||
                    source == DamageSource.WITHER ||
                    source == DamageSource.STALAGMITE) {
                return true;
            }
            
            if (stage >= 2 && source == DamageSource.ANVIL)
                return true;

            if (stage >= 3 && source.getDirectEntity() instanceof ThrownPotion)
                return true;

            if (stage == 4 && source.getDirectEntity() instanceof ThrownTrident)
                return true;
            
            if (stage == 5 && (source.getDirectEntity() instanceof Arrow) || source == DamageSource.DRAGON_BREATH)
                return true;
        }
        else if (skizzik.getPreview() &&
                source != DamageSource.CRAMMING &&
                source != DamageSource.OUT_OF_WORLD &&
                source != DamageSource.MAGIC) {
            
            return true;
        }
        
        return false;
    }
    
    public SkizzikStageInterface createInstance(Skizzik skizzik) {
        try {
            Constructor<? extends SkizzikStageInterface> stageConstructor = this.getConstructor();
            return stageConstructor.newInstance(skizzik);
        } catch (Exception exception) {
            throw new Error(exception);
        }
    }

    protected Constructor<? extends SkizzikStageInterface> getConstructor() throws NoSuchMethodException {
        return this.stageClass.getConstructor(Skizzik.class);
    }

    public int getId() {
        return this.id;
    }

    public static SkizzikStage<?> getById(int id) {
        return id >= 0 && id < stages.length ? stages[id] : SLEEPING;
    }

    public static int getCount() {
        return stages.length;
    }

    private static <T extends SkizzikStageInterface> SkizzikStage<T> create(Class<T> stageClass) {
        SkizzikStage<T> stage = new SkizzikStage<>(stages.length, stageClass);
        stages = Arrays.copyOf(stages, stages.length + 1);
        stages[stage.getId()] = stage;
        return stage;
    }
}
