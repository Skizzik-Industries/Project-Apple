package com.skizzium.projectapple.entity.boss.skizzik.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.skizzium.projectapple.entity.boss.skizzik.FriendlySkizzik;
import com.skizzium.projectapple.entity.boss.skizzik.SkizzikGem;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.FriendlySkizzikModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class FriendlySkizzikGemLayer<T extends FriendlySkizzik> extends GeoLayerRenderer<T> {
    private final IGeoRenderer<T> renderer;

    public FriendlySkizzikGemLayer(IGeoRenderer<T> renderer) {
        super(renderer);
        this.renderer = renderer;
    }

    private ResourceLocation getTexture(T entity) {
        return SkizzikGem.GemType.BLACK.getLocation();
    }

    @Override
    public void render(PoseStack matrix, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        for (SkizzikGem gem : entity.gems) {
            if (gem.isPlaced()) {
                RenderType texture = this.getRenderType(gem.getGemType().getLocation());
                this.renderer.render(this.getEntityModel().getModel(FriendlySkizzikModel.modelLocation(entity)), entity, partialTicks, texture, matrix, buffer, buffer.getBuffer(texture), 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}
