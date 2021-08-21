package com.skizzium.projectapple.init.world;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderConfiguration;

public class PA_SurfaceBuilders {
    public static final BlockState GRAVEL = Blocks.GRAVEL.defaultBlockState();
    public static final BlockState CANDY_NYLIUM = PA_Blocks.CANDY_NYLIUM.get().defaultBlockState();
    public static final BlockState CANDRACK = PA_Blocks.CANDYRACK.get().defaultBlockState();

    public static final SurfaceBuilderBaseConfiguration CONFIG_CANDY = new SurfaceBuilderBaseConfiguration(CANDY_NYLIUM, CANDRACK, GRAVEL);

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> CANDY = register("candy", SurfaceBuilder.DEFAULT.configured(PA_SurfaceBuilders.CONFIG_CANDY));

    private static <SC extends SurfaceBuilderConfiguration> ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> builder) {
        return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(ProjectApple.MOD_ID, name), builder);
    }
}
