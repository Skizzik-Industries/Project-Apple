package com.skizzium.projectapple.data.tags;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import com.skizzium.projectapple.init.block.ModFluids;

public class ModFluidTagsProvider extends FluidTagsProvider {
    public ModFluidTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(ModTags.Fluids.WATER).add(ModFluids.MAPLE_SYRUP.get());
        tag(ModTags.Fluids.WATER).add(ModFluids.FLOWING_MAPLE_SYRUP.get());

        tag(ModTags.Fluids.CANDY_FLUIDS).add(ModFluids.MAPLE_SYRUP.get());
        tag(ModTags.Fluids.CANDY_FLUIDS).add(ModFluids.FLOWING_MAPLE_SYRUP.get());
    }
}