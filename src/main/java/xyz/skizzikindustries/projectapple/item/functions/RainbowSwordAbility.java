package xyz.skizzikindustries.projectapple.item.functions;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.Random;

public class RainbowSwordAbility {
    public static void execute(Map<String, Object> dependencies, LivingEntity entity, ItemUseContext context) {
        ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
        double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
        double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
        double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
        IWorld world = (IWorld) dependencies.get("world");
        if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.BEDROCK.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.WATER.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.LAVA.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.AIR.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.CAVE_AIR.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.MOVING_PISTON.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.PISTON_HEAD.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.SPAWNER.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.COMMAND_BLOCK.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.REPEATING_COMMAND_BLOCK.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.CHAIN_COMMAND_BLOCK.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.END_PORTAL.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.VOID_AIR.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.END_GATEWAY.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.END_PORTAL_FRAME.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.BARRIER.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.STRUCTURE_VOID.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.STRUCTURE_BLOCK.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.JIGSAW.defaultBlockState().getBlock()))
            && (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.DRAGON_EGG.defaultBlockState().getBlock()))) {

            world.setBlock(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.defaultBlockState(), 3);

            if (world instanceof ServerWorld) {
                ((ServerWorld) world).sendParticles(ParticleTypes.PORTAL, x, y, z, (int) 15, 1, 1, 1, 1);
            }

            {
                ItemStack stack = (itemstack);
                stack.hurtAndBreak(1, entity, (breakevent) -> {breakevent.broadcastBreakEvent(context.getHand());});
            }
        }
    }
}
