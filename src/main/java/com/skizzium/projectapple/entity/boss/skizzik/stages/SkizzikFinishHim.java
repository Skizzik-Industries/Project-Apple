package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.init.PA_SoundEvents;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.Constants;

public class SkizzikFinishHim extends AbstractPassiveSkizzikStage {
    public SkizzikFinishHim(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public TextComponent displayName() {
        return new TextComponent(String.format("%s - %s", skizzik.getDisplayName().getString(), new TranslatableComponent("entity.skizzik.skizzik.finish_him").getString()));
    }

    @Override
    public int maxHealth() {
        return 20;
    }

    @Override
    public void begin(SkizzikStageManager stageManager) {
        super.begin(stageManager);

        Level world = skizzik.level;
        if (world instanceof  ServerLevel) {
            world.setBlock(new BlockPos(skizzik.getX(), skizzik.getY(), skizzik.getZ()), PA_Blocks.SKIZZIK_LOOT_BAG.get().defaultBlockState(), 2);
            ((ServerLevel) world).setDayTime(1000);
        }

        skizzik.killAllSkizzies(world, false);

        skizzik.level.playSound(null, skizzik.getX(), skizzik.getY(), skizzik.getZ(), PA_SoundEvents.FINISH_HIM_LAZY.get(), SoundSource.HOSTILE, 10000.0F, 1.0F);
    }

    @Override
    public SkizzikStage<? extends SkizzikStageInterface> getStage() {
        return SkizzikStage.FINISH_HIM;
    }
}
