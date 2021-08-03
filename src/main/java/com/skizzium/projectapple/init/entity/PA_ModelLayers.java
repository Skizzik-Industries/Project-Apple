package com.skizzium.projectapple.init.entity;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class PA_ModelLayers {
    public static final ModelLayerLocation SKIZZIE_LAYER = register("skizzie");
    public static final ModelLayerLocation WITCH_SKIZZIE_LAYER = register("witch_skizzie");
    public static final ModelLayerLocation SKIZZO_LAYER = register("skizzo");

    private static ModelLayerLocation register(String layer) {
        return new ModelLayerLocation(new ResourceLocation(ProjectApple.MOD_ID, layer), "main");
    }
}
