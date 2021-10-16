package com.skizzium.projectapple.potion;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base.SkizzikFinishHim;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ConversionEffect extends MobEffect {
    public ConversionEffect(MobEffectCategory harmful, int amplifier) {
        super(harmful, amplifier);
    }

    @Override
    public boolean isBeneficial() {
        return true;
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier) {
        System.out.println("SUP BISH");
        if (entity instanceof Skizzik && ((Skizzik) entity).stageManager.getCurrentStage() instanceof SkizzikFinishHim) {
            entity.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(1020);
            entity.setInvulnerable(true);
        }
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        super.applyEffectTick(entity, amplifier);
        if (entity instanceof Skizzik && ((Skizzik) entity).stageManager.getCurrentStage() instanceof SkizzikFinishHim) {
            entity.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(1020);
        }
    }
}
