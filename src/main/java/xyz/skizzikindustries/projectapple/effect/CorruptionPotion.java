package xyz.skizzikindustries.projectapple.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class CorruptionPotion extends Effect {

    public CorruptionPotion(EffectType harmful, int p_i50391_2_) {
        super(harmful, p_i50391_2_);
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
    public boolean shouldRenderHUD(EffectInstance effect) {
        return true;
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeModifierManager attributeMapIn, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMapIn, amplifier);
        if (entity instanceof LivingEntity) {
            entity.hurt(new DamageSource("corrupted").bypassArmor(), (float) 1000);
        }
    }
}
