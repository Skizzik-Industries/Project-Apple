package com.skizzium.projectapple.init.world;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderConfiguration;

public class PA_SurfaceBuilders {
    public static final SurfaceBuilderBaseConfiguration CONFIG_CANDY = new SurfaceBuilderBaseConfiguration(PA_Blocks.CANDY_NYLIUM.get().defaultBlockState(), PA_Blocks.CANDYRACK.get().defaultBlockState(), Blocks.GRAVEL.defaultBlockState());

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> CANDY_NYLIUM = register("candy_nylium", SurfaceBuilder.DEFAULT.configured(PA_SurfaceBuilders.CONFIG_CANDY));

    private static <SC extends SurfaceBuilderConfiguration> ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> builder) {
        return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(ProjectApple.MOD_ID, name), builder);
    }
}
