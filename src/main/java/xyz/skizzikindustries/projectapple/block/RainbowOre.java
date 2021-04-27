package xyz.skizzikindustries.projectapple.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.extensions.IForgeBlock;

import java.util.Random;

public class RainbowOre extends OreBlock implements IForgeBlock {

    public RainbowOre(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    public int xpOnDrop(Random p_220281_1_) {
        if (this == Blocks.COAL_ORE) {
            return MathHelper.nextInt(p_220281_1_, 0, 2);
        }
        else if (this == Blocks.DIAMOND_ORE) {
            return MathHelper.nextInt(p_220281_1_, 3, 7);
        }
        else if (this == Blocks.EMERALD_ORE) {
            return MathHelper.nextInt(p_220281_1_, 3, 7);
        }
        else if (this == Blocks.LAPIS_ORE) {
            return MathHelper.nextInt(p_220281_1_, 2, 5);
        }
        else if (this == Blocks.NETHER_QUARTZ_ORE) {
            return MathHelper.nextInt(p_220281_1_, 2, 5);
        }
        else if (this == ModBlocks.RAINBOW_ORE.get()) {
            return MathHelper.nextInt(p_220281_1_, 2, 9);
        }
        else {
            return this == Blocks.NETHER_GOLD_ORE ? MathHelper.nextInt(p_220281_1_, 0, 1) : 0;
        }
    }
}
