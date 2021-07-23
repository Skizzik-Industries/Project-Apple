package com.skizzium.projectapple.data.tags;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.PA_Tags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import com.skizzium.projectapple.init.block.PA_Fluids;

public class ModFluidTagsProvider extends FluidTagsProvider {
    public ModFluidTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(PA_Tags.Fluids.WATER).add(PA_Fluids.MAPLE_SYRUP.get());
        tag(PA_Tags.Fluids.WATER).add(PA_Fluids.FLOWING_MAPLE_SYRUP.get());

        tag(PA_Tags.Fluids.CANDY_FLUIDS).add(PA_Fluids.MAPLE_SYRUP.get());
        tag(PA_Tags.Fluids.CANDY_FLUIDS).add(PA_Fluids.FLOWING_MAPLE_SYRUP.get());
    }
}