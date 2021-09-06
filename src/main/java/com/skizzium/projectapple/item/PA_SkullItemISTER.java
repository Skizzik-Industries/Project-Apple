package com.skizzium.projectapple.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.skizzium.projectapple.init.block.PA_TileEntities;
import com.skizzium.projectapple.tileentity.renderer.PA_SkullRenderer;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;

import java.util.Map;

public class PA_SkullItemISTER extends BlockEntityWithoutLevelRenderer {
    private Map<SkullBlock.Type, SkullModelBase> skullModels;

    public PA_SkullItemISTER(BlockEntityRenderDispatcher dispatcher, EntityModelSet set) {
        super(dispatcher, set);
        skullModels = PA_TileEntities.createSkullRenderers(set);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transform, PoseStack matrix, MultiBufferSource renderer, int light, int overlay) {
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
            if (block instanceof AbstractSkullBlock) {
                SkullBlock.Type type = ((AbstractSkullBlock)block).getType();
                SkullModelBase model = this.skullModels.get(type);
                RenderType renderType = SkullBlockRenderer.getRenderType(type, null);

                SkullBlockRenderer.renderSkull(null, 180.0F, 0.0F, matrix, renderer, light, model, renderType);
            }
        }
    }
}
