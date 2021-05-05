package xyz.skizzikindustries.projectapple.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraftforge.common.extensions.IForgeBlock;

public class RainbowGemBlock extends Block implements IForgeBlock {

    public RainbowGemBlock(Properties properties) {
        super(properties);
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.PUSH_ONLY;
    }
}
