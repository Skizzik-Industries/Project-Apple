package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.util.PA_BossEvent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

public class SkizzikFinishHim extends AbstractSkizzikStage {
    public SkizzikFinishHim(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public TextComponent displayName() {
        return new TextComponent(String.format("%s - %s", skizzik.getDisplayName().getString(), new TranslatableComponent("entity.skizzik.skizzik.finish_him").getString()));
    }

    @Override
    public PA_BossEvent.PA_BossBarColor barColor() {
        return PA_BossEvent.PA_BossBarColor.WHITE;
    }

    @Override
    public PA_BossEvent.PA_BossBarOverlay barOverlay() {
        return PA_BossEvent.PA_BossBarOverlay.PROGRESS;
    }
    
    @Override
    public SkizzikStage<? extends SkizzikStageInterface> getStage() {
        return SkizzikStage.FINISH_HIM;
    }
}
