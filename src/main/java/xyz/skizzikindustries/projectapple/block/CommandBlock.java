package xyz.skizzikindustries.projectapple.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;

public class CommandBlock extends Block {

    public CommandBlock(Properties properties) {
        super(properties);
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.BLOCK;
    }

    public MaterialColor getMaterialColor() {
        return MaterialColor.METAL;
    }
}
