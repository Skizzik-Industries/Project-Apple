package com.skizzium.projectapple.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;

public class CommandBlock extends Block {
    public CommandBlock(Properties properties) {
        super(properties);
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.BLOCK;
    }
}
