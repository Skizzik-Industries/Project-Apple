package com.skizzium.projectapple.potion;

import com.google.common.collect.Maps;
import com.skizzium.projectapple.entity.CorruptedSkizzie;
import com.skizzium.projectapple.init.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CorruptionPotion extends Effect {
    private final Map<Attribute, AttributeModifier> attributeModifiers = Maps.newHashMap();

    public CorruptionPotion(EffectType harmful, int amplifier) {
        super(harmful, amplifier);
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }

    @Override
    public boolean isInstantenous() {
        return false;
    }

    @Override
    public boolean shouldRenderInvText(EffectInstance effect) {
        return true;
    }

    @Override
    public boolean shouldRender(EffectInstance effect) {
        return true;
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean shouldRenderHUD(EffectInstance effect) {
        return true;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> items = new ArrayList<>();
        return items;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!(entity instanceof CorruptedSkizzie)) {
            super.applyEffectTick(entity, amplifier);
        }
        else {
            entity.removeEffect(ModEffects.CORRUPTION.get());
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeModifierManager attribute, int amplifier) {
        super.removeAttributeModifiers(entity, attribute, amplifier);

        if (!(entity instanceof CorruptedSkizzie)) {
            if (entity instanceof LivingEntity) {
                entity.hurt(new DamageSource("corrupted").bypassArmor(), Float.MAX_VALUE);
            }
        }
    }
}
