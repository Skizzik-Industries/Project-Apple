package com.skizzium.projectapple.block;

import com.skizzium.projectapple.item.PA_SkullItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class SmallSkullItem extends PA_SkullItem {
    public SmallSkullItem(Block head, Block wallHead, Properties properties) {
        super(head, wallHead, properties);
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
        return false;
    }
}
