package xyz.skizzikindustries.projectapple.block.custom_blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class CorruptedBlock extends Block {
    public CorruptedBlock(Properties properties) { super(properties); }

    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        super.entityInside(state, world, pos, entity);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        {
            Map<String, Object> $_dependencies = new HashMap<>();
            $_dependencies.put("entity", entity);
            //CorruptedBlockCorruption.execute($_dependencies);
        }
    }

}
