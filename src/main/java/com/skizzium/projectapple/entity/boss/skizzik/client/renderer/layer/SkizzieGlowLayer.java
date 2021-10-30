package com.skizzium.projectapple.entity.boss.skizzik.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.CorruptedSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.KaboomSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.SkizzieModel;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.friendly.FriendlySkizzie;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzieGlowLayer<T extends LivingEntity> extends RenderLayer<T, SkizzieModel<T>> {
    private static final ResourceLocation FRIENDLY_SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/friendly_skizzie/friendly_skizzie_glow.png");
    private static final ResourceLocation SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/skizzie/skizzie_glow.png");
    private static final ResourceLocation KABOOM_SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/skizzie/kaboom_skizzie_glow.png");
    private static final ResourceLocation CORRUPTED_SKIZZIE_GLOW = new ResourceLocation("skizzik:textures/entity/skizzie/corrupted_skizzie_glow.png");

    private static final ResourceLocation FRIENDLY_SPOOKZIE_GLOW = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/friendly_spookzie/friendly_spookzie_glow.png");
    private static final ResourceLocation SPOOKZIE_GLOW = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzie/spookzie_glow.png");
    private static final ResourceLocation KABOOM_SPOOKZIE_GLOW = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzie/kaboom_spookzie_glow.png");
    private static final ResourceLocation CORRUPTED_SPOOKZIE_GLOW = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzie/corrupted_spookzie_glow.png");

    public SkizzieGlowLayer(RenderLayerParent<T, SkizzieModel<T>> renderer) {
        super(renderer);
    }

    private ResourceLocation getTexture(T entity) {
        if (entity instanceof FriendlySkizzie) {
            return ((FriendlySkizzie) entity).getHoliday() == 1 ? FRIENDLY_SPOOKZIE_GLOW : FRIENDLY_SKIZZIE_GLOW;
        }

        if (((Skizzie) entity).getHoliday() == 1) {
            return entity instanceof KaboomSkizzie ? KABOOM_SPOOKZIE_GLOW :
                   entity instanceof CorruptedSkizzie ? CORRUPTED_SPOOKZIE_GLOW : SPOOKZIE_GLOW;
        }

        return entity instanceof KaboomSkizzie ? KABOOM_SKIZZIE_GLOW :
               entity instanceof CorruptedSkizzie ? CORRUPTED_SKIZZIE_GLOW : SKIZZIE_GLOW;
    }

    @Override
    public void render(PoseStack pose, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float headYaw, float headPitch) {
        VertexConsumer vertex = buffer.getBuffer(RenderType.eyes(getTexture(entity)));
        this.getParentModel().renderToBuffer(pose, vertex, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
