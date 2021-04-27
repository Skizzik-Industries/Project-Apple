package xyz.skizzikindustries.projectapple.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import xyz.skizzikindustries.projectapple.ModSoundEvents;
import xyz.skizzikindustries.projectapple.ProjectApple;

public class SkizzikFleshArmorMaterial implements IArmorMaterial {
    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return new int[]{13, 15, 16, 11}[slot.getIndex()] * 15;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slot) {
        return new int[]{1, 2, 4, 1}[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return 9;
    }

    @Override
    public SoundEvent getEquipSound() {
        return ModSoundEvents.FLESH_EQUIP.get();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(ModItems.SKIZZIK_FLESH.get());
    }

    @Override
    public String getName() {
        return ProjectApple.MOD_ID+":"+"skizzik_flesh_armor";
    }

    @Override
    public float getToughness() {
        return 0.5f;

    }

    @Override
    public float getKnockbackResistance() {
        return 0f;
    }
}
