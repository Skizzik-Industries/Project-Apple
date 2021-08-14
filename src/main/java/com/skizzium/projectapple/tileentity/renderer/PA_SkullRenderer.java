package com.skizzium.projectapple.tileentity.renderer;

import com.google.common.collect.ImmutableMap;
import com.skizzium.projectapple.init.block.PA_TileEntities;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import com.skizzium.projectapple.tileentity.model.PA_SkullModel;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.dragon.DragonHeadModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class PA_SkullRenderer extends SkullBlockRenderer implements BlockEntityRenderer<SkullBlockEntity> {
    public static Map<SkullBlock.Type, SkullModelBase> createSkullRenderers(EntityModelSet set) {
        ImmutableMap.Builder<SkullBlock.Type, SkullModelBase> builder = ImmutableMap.builder();

        builder.put(SkullBlock.Types.SKELETON, new SkullModel(set.bakeLayer(ModelLayers.SKELETON_SKULL)));
        builder.put(SkullBlock.Types.WITHER_SKELETON, new SkullModel(set.bakeLayer(ModelLayers.WITHER_SKELETON_SKULL)));
        builder.put(SkullBlock.Types.PLAYER, new SkullModel(set.bakeLayer(ModelLayers.PLAYER_HEAD)));
        builder.put(SkullBlock.Types.ZOMBIE, new SkullModel(set.bakeLayer(ModelLayers.ZOMBIE_HEAD)));
        builder.put(SkullBlock.Types.CREEPER, new SkullModel(set.bakeLayer(ModelLayers.CREEPER_HEAD)));
        builder.put(SkullBlock.Types.DRAGON, new DragonHeadModel(set.bakeLayer(ModelLayers.DRAGON_SKULL)));

        builder.put(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK, new PA_SkullModel(set.bakeLayer(PA_ModelLayers.SMALL_SKIZZIK_HEAD_LAYER)));
        builder.put(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK_WITH_GEMS, new PA_SkullModel(set.bakeLayer(PA_ModelLayers.SMALL_SKIZZIK_HEAD_WITH_GEMS_LAYER)));
        builder.put(PA_TileEntities.CustomSkullTypes.SKIZZIK, new PA_SkullModel(set.bakeLayer(PA_ModelLayers.SKIZZIK_HEAD_LAYER)));
        builder.put(PA_TileEntities.CustomSkullTypes.SKIZZIK_WITH_GEMS, new PA_SkullModel(set.bakeLayer(PA_ModelLayers.SKIZZIK_HEAD_WITH_GEMS_LAYER)));

        return builder.build();
    }

    public PA_SkullRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        super.modelByType = createSkullRenderers(context.getModelSet());

        super.SKIN_BY_TYPE.put(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK, new ResourceLocation("skizzik:textures/block/small_skizzik_head.png"));
        super.SKIN_BY_TYPE.put(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK_WITH_GEMS, new ResourceLocation("skizzik:textures/block/small_skizzik_head_with_gems.png"));
        super.SKIN_BY_TYPE.put(PA_TileEntities.CustomSkullTypes.SKIZZIK, new ResourceLocation("skizzik:textures/block/skizzik_head.png"));
        super.SKIN_BY_TYPE.put(PA_TileEntities.CustomSkullTypes.SKIZZIK_WITH_GEMS, new ResourceLocation("skizzik:textures/entity/skizzik/skizzik.png"));

        /*Field ModelField;
        Field SkinField;

        try {
            ModelField = SkullBlockRenderer.class.getDeclaredField("modelByType");
            ModelField.setAccessible(true);
            Map<SkullBlock.Type, SkullModelBase> Model = createSkullRenderers(context.getModelSet());
            ModelField.set(this, Model);

            SkinField = SkullBlockRenderer.class.getDeclaredField("SKIN_BY_TYPE");
            SkinField.setAccessible(true);
            Map<SkullBlock.Type, ResourceLocation> Skin = (Map<SkullBlock.Type, ResourceLocation>) SkinField.get(null);
            Skin.put(PA_TileEntities.CustomSkullTypes.SKIZZIK, new ResourceLocation("skizzik:textures/block/skizzik_head.png"));
            Skin.put(PA_TileEntities.CustomSkullTypes.SKIZZIK_WITH_GEMS, new ResourceLocation("skizzik:textures/entity/skizzik/skizzik.png"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }*/
    }
}
