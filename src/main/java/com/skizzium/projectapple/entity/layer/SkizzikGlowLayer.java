package com.skizzium.projectapple.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.Skizzik;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzikGlowLayer<T extends Skizzik, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation EMPTY_GLOW = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_empty_glow.png");

    private static final ResourceLocation SLEEPING_GLOW = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_sleeping_glow.png");
    private static final ResourceLocation NORMAL_GLOW = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_glow.png");
    private static final ResourceLocation STAGE_5_GLOW = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_stage-5_glow.png");

    private static final ResourceLocation SPOOKZIK_SLEEPING_GLOW = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzik_sleeping_glow.png");
    private static final ResourceLocation SPOOKZIK_GLOW = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzik_glow.png");
    private static final ResourceLocation SPOOKZIK_STAGE_5_GLOW = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzik_stage-5_glow.png");
    private static final ResourceLocation SPOOKZIK_FINISH_HIM_GLOW = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzik_finish-him_glow.png");

    public SkizzikGlowLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    private ResourceLocation getTexture(T entity) {
        int stage = entity.getStage();

        if (ProjectApple.holiday == 1) {
            return stage == 0 ? SPOOKZIK_SLEEPING_GLOW :
                   stage >= 1 && stage <= 4 ? SPOOKZIK_GLOW :
                   stage == 5 ? SPOOKZIK_STAGE_5_GLOW : SPOOKZIK_FINISH_HIM_GLOW;
        }

        return stage == 0 ? SLEEPING_GLOW :
               stage >= 1 && stage <= 4 ? NORMAL_GLOW :
               stage == 5 ? STAGE_5_GLOW : EMPTY_GLOW;
    }

    @Override
    public void render(PoseStack matrix, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float headYaw, float headPitch) {
        VertexConsumer builder = buffer.getBuffer(RenderType.eyes(getTexture(entity)));
        this.getParentModel().renderToBuffer(matrix, builder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}
