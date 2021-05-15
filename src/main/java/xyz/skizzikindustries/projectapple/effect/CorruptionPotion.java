package xyz.skizzikindustries.projectapple.effect;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.World;
import xyz.skizzikindustries.projectapple.effect.functions.CorruptionPotionExpire;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CorruptionPotion extends Effect {

    protected CorruptionPotion(EffectType harmful, int p_i50391_2_) {
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
        World world = entity.level;
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        {
            Map<String, Object> $_dependencies = new HashMap<>();
            $_dependencies.put("entity", entity);
            CorruptionPotionExpire.execute($_dependencies);
        }
    }

}
