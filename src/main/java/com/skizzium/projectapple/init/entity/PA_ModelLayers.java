package com.skizzium.projectapple.init.entity;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class PA_ModelLayers {
    public static final ModelLayerLocation SKIZZIK_HEAD_LAYER = register("skizzik_head");
    public static final ModelLayerLocation SKIZZIK_HEAD_WITH_GEMS_LAYER = register("skizzik_head_with_gems");

    private static ModelLayerLocation register(String layer) {
        return new ModelLayerLocation(new ResourceLocation(ProjectApple.MOD_ID, layer), "main");
    }
}
