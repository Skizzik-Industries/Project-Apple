package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;

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
