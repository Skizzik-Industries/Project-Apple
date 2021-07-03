package com.skizzium.projectapple.block;

import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class SmallSkizzikHead extends SkullBlock {
    protected static final VoxelShape CUSTOM_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);

    public SmallSkizzikHead(ISkullType skull, Properties properties) {
        super(skull, properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return CUSTOM_SHAPE;
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return new PA_Skull();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
