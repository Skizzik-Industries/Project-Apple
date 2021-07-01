package com.skizzium.projectapple.init.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.skizzium.projectapple.tileentity.renderer.PA_SkullRenderer;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PA_ItemRenderer extends ItemStackTileEntityRenderer {
    public static final ItemStackTileEntityRenderer instance = new PA_ItemRenderer();

    @Override
    public void renderByItem(ItemStack stack, ItemCameraTransforms.TransformType transform, MatrixStack matrix, IRenderTypeBuffer renderer, int light, int overlay) {
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
            if (block instanceof AbstractSkullBlock) {
                PA_SkullRenderer.renderSkull(null, 180.0F, ((AbstractSkullBlock) block).getType(), null, 0.0F, matrix, renderer, light);
            }
        }
    }
}
