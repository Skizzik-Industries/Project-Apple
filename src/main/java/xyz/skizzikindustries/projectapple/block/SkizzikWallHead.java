package xyz.skizzikindustries.projectapple.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.skizzikindustries.projectapple.init.ModBlocks;

import javax.annotation.Nullable;

public class SkizzikWallHead extends WallSkullBlock {
    public SkizzikWallHead(AbstractBlock.Properties properties) {
        super(ModBlocks.ModSkullTypes.SKIZZIK, properties);
    }

    /* public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        ModBlocks.SKIZZIK_HEAD.setPlacedBy(world, pos, state, entity, stack);
    } */
}
