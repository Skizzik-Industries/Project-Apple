package xyz.skizzikindustries.projectapple.block;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import xyz.skizzikindustries.projectapple.init.*;
import xyz.skizzikindustries.projectapple.init.block.ModBlocks;
import xyz.skizzikindustries.projectapple.init.block.ModFluids;
import xyz.skizzikindustries.projectapple.init.item.ModItems;

public class MapleSyrup extends ForgeFlowingFluid {
    protected MapleSyrup(Properties properties) {
        super(properties);
    }

    public static ForgeFlowingFluid.Properties createProperties() {
        return new ForgeFlowingFluid.Properties(ModFluids.MAPLE_SYRUP, ModFluids.FLOWING_MAPLE_SYRUP, FluidAttributes.builder(new ResourceLocation("skizzik:block/maple_syrup_still"), new ResourceLocation("skizzik:block/maple_syrup_flow")).overlay(new ResourceLocation("skizzik:block/maple_syrup_overlay")).density(1000).viscosity(1000).luminosity(0)).canMultiply().bucket(ModItems.MAPLE_SYRUP_BUCKET).block(ModBlocks.MAPLE_SYRUP);
    }

    @Override
    public boolean isSource(FluidState state) {
        return false;
    }

    @Override
    public int getAmount(FluidState state) {
        return state.getValue(LEVEL);
    }
}