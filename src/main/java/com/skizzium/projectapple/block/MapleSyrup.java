package com.skizzium.projectapple.block;

import com.skizzium.projectapple.init.block.ModBlocks;
import com.skizzium.projectapple.init.item.ModItems;
import net.minecraft.fluid.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import com.skizzium.projectapple.init.*;
import com.skizzium.projectapple.init.block.ModFluids;

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