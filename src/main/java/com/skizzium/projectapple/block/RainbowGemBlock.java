package com.skizzium.projectapple.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.common.extensions.IForgeBlock;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class RainbowGemBlock extends Block implements IForgeBlock {
    public RainbowGemBlock(Properties properties) {
        super(properties);
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.PUSH_ONLY;
    }
}
