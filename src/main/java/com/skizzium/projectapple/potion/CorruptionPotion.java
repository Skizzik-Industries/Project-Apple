package com.skizzium.projectapple.potion;

import com.google.common.collect.Maps;
import com.skizzium.projectapple.init.PA_Effects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.damagesource.DamageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CorruptionPotion extends MobEffect {
    private final Map<Attribute, AttributeModifier> attributeModifiers = Maps.newHashMap();

    public CorruptionPotion(MobEffectCategory harmful, int amplifier) {
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
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> items = new ArrayList<>();
        return items;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attribute, int amplifier) {
        super.removeAttributeModifiers(entity, attribute, amplifier);

            if (entity instanceof LivingEntity) {
                entity.hurt(new DamageSource("corrupted").bypassArmor(), Float.MAX_VALUE);
            }
    }
}
