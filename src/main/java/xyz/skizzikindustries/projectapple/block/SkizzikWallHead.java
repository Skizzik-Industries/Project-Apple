package xyz.skizzikindustries.projectapple.block;

import net.minecraft.block.*;
import xyz.skizzikindustries.projectapple.init.block.ModBlocks;

public class SkizzikWallHead extends WallSkullBlock {
    public SkizzikWallHead(AbstractBlock.Properties properties) {
        super(ModBlocks.ModSkullTypes.SKIZZIK, properties);
    }

    /* public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        ModBlocks.SKIZZIK_HEAD.setPlacedBy(world, pos, state, entity, stack);
    } */
}
