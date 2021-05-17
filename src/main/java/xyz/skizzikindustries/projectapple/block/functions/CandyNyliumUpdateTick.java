package xyz.skizzikindustries.projectapple.block.functions;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import xyz.skizzikindustries.projectapple.init.ModBlocks;

import java.util.Map;

public class CandyNyliumUpdateTick {
    public static void execute(Map<String, Object> dependencies) {
        double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
        double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
        double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
        IWorld world = (IWorld) dependencies.get("world");
        if ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).isCollisionShapeFullBlock(world, new BlockPos((int) x, (int) (y + 1), (int) z)))) {
            world.setBlock(new BlockPos((int) x, (int) y, (int) z), ModBlocks.CANDYRACK.get().defaultBlockState(), 3);
        }
    }

}
