package xyz.skizzikindustries.projectapple.item;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

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
        
        if ((!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.BEDROCK.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.WATER.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.LAVA.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.AIR.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.CAVE_AIR.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.MOVING_PISTON.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.PISTON_HEAD.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.SPAWNER.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.COMMAND_BLOCK.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.REPEATING_COMMAND_BLOCK.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.CHAIN_COMMAND_BLOCK.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.END_PORTAL.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.VOID_AIR.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.END_GATEWAY.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.END_PORTAL_FRAME.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.BARRIER.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.STRUCTURE_VOID.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.STRUCTURE_BLOCK.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.JIGSAW.defaultBlockState().getBlock()))
                && (!((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.DRAGON_EGG.defaultBlockState().getBlock()))) {

            world.setBlock(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState(), 3);

            if (world instanceof ServerWorld) {
                ((ServerWorld) world).sendParticles(ParticleTypes.PORTAL, x, y, z, 15, 1, 1, 1, 1);
            }

            {
                ItemStack stack = (itemstack);
                stack.hurtAndBreak(1, entity, (breakevent) -> {breakevent.broadcastBreakEvent(context.getHand());});
            }
        }
        return retrieval;
    }
}
