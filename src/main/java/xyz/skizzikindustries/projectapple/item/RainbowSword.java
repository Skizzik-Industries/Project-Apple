package xyz.skizzikindustries.projectapple.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.skizzikindustries.projectapple.item.functions.RainbowSwordAbility;

import java.util.HashMap;
import java.util.Map;

public class RainbowSword extends SwordItem {
    public RainbowSword(IItemTier iitemtier, int p_i48460_2_, float p_i48460_3_, Properties properties) {
        super(iitemtier, p_i48460_2_, p_i48460_3_, properties);
    }

    public float getDamage() {
        return 9.5F;
    }

    public ActionResultType useOn(ItemUseContext context) {
        ActionResultType retrieval = super.useOn(context);
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        PlayerEntity entity = context.getPlayer();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        ItemStack itemstack = context.getItemInHand();
        {
            Map<String, Object> $_dependencies = new HashMap<>();
            $_dependencies.put("itemstack", itemstack);
            $_dependencies.put("x", x);
            $_dependencies.put("y", y);
            $_dependencies.put("z", z);
            $_dependencies.put("world", world);
            RainbowSwordAbility.execute($_dependencies, entity, context);
        }
        return retrieval;
    }
}
