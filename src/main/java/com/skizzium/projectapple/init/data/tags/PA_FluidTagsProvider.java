package com.skizzium.projectapple.init.data.tags;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.PA_Tags;
import com.skizzium.projectapple.init.block.PA_Fluids;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PA_FluidTagsProvider extends FluidTagsProvider {
    public PA_FluidTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(PA_Tags.Fluids.WATER).add(PA_Fluids.MAPLE_SYRUP.get(), PA_Fluids.FLOWING_MAPLE_SYRUP.get());
        tag(PA_Tags.Fluids.SKIZZIK_CANDY_FLUIDS).add(PA_Fluids.MAPLE_SYRUP.get(), PA_Fluids.FLOWING_MAPLE_SYRUP.get());
    }
}