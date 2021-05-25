package xyz.skizzikindustries.projectapple.data.tags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import xyz.skizzikindustries.projectapple.ProjectApple;
import xyz.skizzikindustries.projectapple.init.block.ModFluids;
import xyz.skizzikindustries.projectapple.init.ModTags;

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