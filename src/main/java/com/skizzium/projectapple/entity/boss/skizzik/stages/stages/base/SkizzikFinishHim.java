package com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageManager;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStages;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.AbstractPassiveSkizzikStage;
import com.skizzium.projectapple.init.PA_SoundEvents;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.level.Level;

public class SkizzikFinishHim extends AbstractPassiveSkizzikStage {
    public SkizzikFinishHim(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public TextComponent displayName() {
        return new TextComponent(String.format("%s - %s", skizzik.getDisplayName().getString(), new TranslatableComponent("entity.skizzik.skizzik.finish_him").getString()));
    }

    @Override
    public String textureLocation() {
        return String.format("%s/%s_finish-him", skizzik.getTranslationKey().getString().toLowerCase(), skizzik.getTranslationKey().getString().toLowerCase());
    }
    
    @Override
    public String modelLocation() {
        return "skizzik/skizzik_finish-him";
    }
    
    @Override
    public int maxHealth() {
        return 20;
    }

    @Override
    public float eyeHeight() {
        return 2.25F;
    }

    @Override
    public EntityDimensions hitbox() {
        return new EntityDimensions(1.2F, 2.8F, true);
    }
    
    @Override
    public void begin(SkizzikStageManager stageManager) {
        super.begin(stageManager);

        Level world = skizzik.level;
        if (world instanceof ServerLevel) {
            world.setBlock(new BlockPos(skizzik.getX(), skizzik.getY(), skizzik.getZ()), PA_Blocks.SKIZZIK_LOOT_BAG.get().defaultBlockState(), 2);
            ((ServerLevel) world).setDayTime(1000);
        }

        skizzik.killAllSkizzies(world, false);
        skizzik.level.playSound(null, skizzik.getX(), skizzik.getY(), skizzik.getZ(), PA_SoundEvents.FINISH_HIM_LAZY.get(), SoundSource.HOSTILE, 10000.0F, 1.0F);

        skizzik.targetSelector.removeGoal(skizzik.hurtGoal);
        skizzik.targetSelector.removeGoal(skizzik.attackGoal);
        skizzik.goalSelector.removeGoal(skizzik.rangedAttackGoal);
        skizzik.goalSelector.removeGoal(skizzik.avoidWaterGoal);
        skizzik.goalSelector.removeGoal(skizzik.lookGoal);
        skizzik.goalSelector.removeGoal(skizzik.lookRandomlyGoal);

        skizzik.goalSelector.addGoal(1, skizzik.avoidPlayerGoal);
        skizzik.goalSelector.addGoal(2, skizzik.panicGoal);
        skizzik.goalSelector.addGoal(3, skizzik.avoidWaterGoal);
        skizzik.goalSelector.addGoal(4, skizzik.lookGoal);
        skizzik.goalSelector.addGoal(5, skizzik.lookRandomlyGoal);
    }

    @Override
    public SkizzikStages<? extends SkizzikStageInterface> getStage() {
        return SkizzikStages.FINISH_HIM;
    }
}
