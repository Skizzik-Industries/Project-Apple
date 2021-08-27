package com.skizzium.projectapple.init.world;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class PA_SurfaceBuilders {
    public static final BlockState GRAVEL = Blocks.GRAVEL.defaultBlockState();
    public static final BlockState CANDY_NYLIUM = PA_Blocks.CANDY_NYLIUM.get().defaultBlockState();
    public static final BlockState CANDRACK = PA_Blocks.CANDYRACK.get().defaultBlockState();

    public static final SurfaceBuilderConfig CONFIG_CANDY = new SurfaceBuilderConfig(CANDY_NYLIUM, CANDRACK, GRAVEL);

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CANDY = register("candy", SurfaceBuilder.DEFAULT.configured(PA_SurfaceBuilders.CONFIG_CANDY));

    private static <SC extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> builder) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(ProjectApple.MOD_ID, name), builder);
    }
}
