package com.skizzium.projectapple.entity.boss.skizzik.util;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.base.*;
import com.skizzium.projectapple.init.PA_Config;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.ThrownTrident;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class SkizzikStages<T extends SkizzikStageInterface> {
    private static SkizzikStages<?>[] stages = new SkizzikStages[0];
    public static final SkizzikStages<SkizzikSleeping> SLEEPING = create(SkizzikSleeping.class);
    public static final SkizzikStages<SkizzikStage1> STAGE_1 = create(SkizzikStage1.class);
    public static final SkizzikStages<SkizzikStage2> STAGE_2 = create(SkizzikStage2.class);
    public static final SkizzikStages<SkizzikStage3> STAGE_3 = create(SkizzikStage3.class);
    public static final SkizzikStages<SkizzikStage4> STAGE_4 = create(SkizzikStage4.class);
    public static final SkizzikStages<SkizzikStage5> STAGE_5 = create(SkizzikStage5.class);
    public static final SkizzikStages<SkizzikFinishHim> FINISH_HIM = create(SkizzikFinishHim.class);
    
    private final Class<? extends SkizzikStageInterface> stageClass;
    private final int id;
    
    private SkizzikStages(int id, Class<? extends SkizzikStageInterface> clazz) {
        this.id = id;
        this.stageClass = clazz;
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

    public Class<T> getStageClass() {
        return (Class<T>) this.stageClass;
    }
    
    public int getId() {
        return this.id;
    }

    public static SkizzikStages<?> getById(int id) {
        return id >= 0 && id < stages.length ? stages[id] : SLEEPING;
    }

    public static int getCount() {
        return stages.length;
    }

    private static <T extends SkizzikStageInterface> SkizzikStages<T> create(Class<T> stageClass) {
        SkizzikStages<T> stage = new SkizzikStages<>(stages.length, stageClass);
        stages = Arrays.copyOf(stages, stages.length + 1);
        stages[stage.getId()] = stage;
        return stage;
    }
}
