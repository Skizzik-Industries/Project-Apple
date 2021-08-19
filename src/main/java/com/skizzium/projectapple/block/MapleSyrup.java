package com.skizzium.projectapple.block;

import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import com.skizzium.projectapple.init.block.PA_Fluids;

import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class MapleSyrup extends ForgeFlowingFluid {
    protected MapleSyrup(Properties properties) {
        super(properties);
    }

    public static ForgeFlowingFluid.Properties createProperties() {
        return new ForgeFlowingFluid.Properties(PA_Fluids.MAPLE_SYRUP, PA_Fluids.FLOWING_MAPLE_SYRUP, FluidAttributes.builder(new ResourceLocation("skizzik:block/maple_syrup_still"), new ResourceLocation("skizzik:block/maple_syrup_flow")).overlay(new ResourceLocation("skizzik:block/maple_syrup_overlay")).density(1000).viscosity(1000).luminosity(0)).canMultiply().bucket(PA_Items.MAPLE_SYRUP_BUCKET);//.block(PA_Blocks.MAPLE_SYRUP_BLOCK);
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