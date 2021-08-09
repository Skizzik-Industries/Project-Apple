package com.skizzium.projectapple.init.entity;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class PA_ModelLayers {
    public static final ModelLayerLocation SKIZZIE_LAYER = register("skizzie");
    public static final ModelLayerLocation WITCH_SKIZZIE_LAYER = register("witch_skizzie");
    public static final ModelLayerLocation SKIZZO_LAYER = register("skizzo");

    public static final ModelLayerLocation SMALL_SKIZZIK_HEAD_LAYER = register("small_skizzik_head");
    public static final ModelLayerLocation SMALL_SKIZZIK_HEAD_WITH_GEMS_LAYER = register("small_skizzik_head_with_gems");
    public static final ModelLayerLocation SKIZZIK_HEAD_LAYER = register("skizzik_head");
    public static final ModelLayerLocation SKIZZIK_HEAD_WITH_GEMS_LAYER = register("skizzik_head_with_gems");

    private static ModelLayerLocation register(String layer) {
        return new ModelLayerLocation(new ResourceLocation(ProjectApple.MOD_ID, layer), "main");
    }
}
