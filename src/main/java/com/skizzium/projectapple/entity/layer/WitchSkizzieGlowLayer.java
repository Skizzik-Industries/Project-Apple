package com.skizzium.projectapple.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.CorruptedSkizzie;
import com.skizzium.projectapple.entity.FriendlySkizzie;
import com.skizzium.projectapple.entity.FriendlyWitchSkizzie;
import com.skizzium.projectapple.entity.KaboomSkizzie;
import com.skizzium.projectapple.entity.model.WitchSkizzieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitchSkizzieGlowLayer<T extends LivingEntity> extends RenderLayer<T, WitchSkizzieModel<T>> {
    private static final ResourceLocation FRIENDLY_WITCH_SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/friendly_witch_skizzie/friendly_witch_skizzie_glow.png");
    private static final ResourceLocation WITCH_SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/witch_skizzie/witch_skizzie_glow.png");

    public WitchSkizzieGlowLayer(RenderLayerParent<T, WitchSkizzieModel<T>> renderer) {
        super(renderer);
    }

    private ResourceLocation getTexture(T entity) {
        return entity instanceof FriendlyWitchSkizzie ? FRIENDLY_WITCH_SKIZZIE_GLOW : WITCH_SKIZZIE_GLOW;
    }

    @Override
    public void render(PoseStack pose, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float headYaw, float headPitch) {
        VertexConsumer vertex = buffer.getBuffer(RenderType.eyes(getTexture(entity)));
        this.getParentModel().renderToBuffer(pose, vertex, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
