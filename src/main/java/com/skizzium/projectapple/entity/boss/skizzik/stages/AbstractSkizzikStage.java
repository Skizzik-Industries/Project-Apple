package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

public abstract class AbstractSkizzikStage implements SkizzikStageInterface {
    protected final Skizzik skizzik;
    
    public AbstractSkizzikStage(Skizzik skizzik) {
        this.skizzik = skizzik;
    }

    @Override
    public TextComponent displayName() {
        return new TextComponent(String.format("%s - %s %s", skizzik.getDisplayName().getString(), new TranslatableComponent("entity.skizzik.skizzik.stage").getString(), this.getStage().getId()));
        //return new TextComponent(skizzik.getDisplayName().getString() + " - " + new TranslatableComponent("entity.skizzik.skizzik.stage").getString() + " " + skizzik.stageManager.getCurrentStage());
    }

    @Override
    public void begin() {
        System.out.println("Skizzik: Begin");
    }

    @Override
    public void end() {
        System.out.println("Skizzik: End");
    }
}
