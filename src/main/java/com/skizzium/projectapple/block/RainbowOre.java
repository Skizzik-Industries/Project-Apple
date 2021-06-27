package com.skizzium.projectapple.block;

import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.extensions.IForgeBlock;
import com.skizzium.projectapple.init.block.PA_Blocks;

import java.util.Random;

public class RainbowOre extends OreBlock implements IForgeBlock {

    public RainbowOre(Properties properties) {
        super(properties);
    }

    @Override
    public int xpOnDrop(Random random) {
        if (this == Blocks.COAL_ORE) {
            return MathHelper.nextInt(random, 0, 2);
        }
        else if (this == Blocks.DIAMOND_ORE) {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == Blocks.EMERALD_ORE) {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == Blocks.LAPIS_ORE) {
            return MathHelper.nextInt(random, 2, 5);
        }
        else if (this == Blocks.NETHER_QUARTZ_ORE) {
            return MathHelper.nextInt(random, 2, 5);
        }
        else if (this == PA_Blocks.RAINBOW_ORE.get()) {
            return MathHelper.nextInt(random, 2, 9);
        }
        else {
            return this == Blocks.NETHER_GOLD_ORE ? MathHelper.nextInt(random, 0, 1) : 0;
        }
    }
}
