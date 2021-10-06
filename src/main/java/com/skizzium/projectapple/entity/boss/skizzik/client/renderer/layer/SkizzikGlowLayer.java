package com.skizzium.projectapple.entity.boss.skizzik.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class SkizzikGlowLayer extends GeoLayerRenderer<Skizzik> {
    private static final ResourceLocation EMPTY_GLOW = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_empty_glow.png");

    private static final ResourceLocation SLEEPING_GLOW = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_sleeping_glow.png");
    private static final ResourceLocation NORMAL_GLOW = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_glow.png");
    private static final ResourceLocation STAGE_5_GLOW = new ResourceLocation("skizzik:textures/entity/skizzik/skizzik_stage-5_glow.png");

    private static final ResourceLocation SPOOKZIK_SLEEPING_GLOW = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzik_sleeping_glow.png");
    private static final ResourceLocation SPOOKZIK_GLOW = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzik_glow.png");
    private static final ResourceLocation SPOOKZIK_STAGE_5_GLOW = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzik_stage-5_glow.png");
    private static final ResourceLocation SPOOKZIK_FINISH_HIM_GLOW = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzik_finish-him_glow.png");

    public SkizzikGlowLayer(IGeoRenderer<Skizzik> renderer) {
        super(renderer);
    }

    private ResourceLocation getTexture(Skizzik entity) {
        int stage = entity.stageManager.getCurrentStage().getStage().getId();

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
    public void render(PoseStack matrix, MultiBufferSource buffer, int light, Skizzik entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float headYaw, float headPitch) {
        VertexConsumer builder = buffer.getBuffer(RenderType.eyes(getTexture(entity)));
        this.getRenderer().render(this.getEntityModel().getModel(new ResourceLocation(ProjectApple.MOD_ID, "textures/entity/skizzik/skizzik_sleeping.png")), entity, partialTicks, RenderType.eyes(getTexture(entity)), matrix, buffer, builder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1); 
        //matrix, builder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}
