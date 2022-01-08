package com.skizzium.projectapple.entity.boss.skizzik.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzo;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.SkizzoModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@OnlyIn(Dist.CLIENT)
public class SkizzoGlowLayer<T extends LivingEntity & IAnimatable> extends GeoLayerRenderer<T> {
    private final IGeoRenderer<T> renderer;
    
    private static final ResourceLocation SKIZZO_GLOW = new ResourceLocation("skizzik:textures/entity/skizzik/skizzo_glow.png");
    private static final ResourceLocation SPOOKZO_GLOW = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzo_glow.png");

    private static final ResourceLocation FRIENDLY_SKIZZO_GLOW = new ResourceLocation("skizzik:textures/entity/friendly_skizzik/friendly_skizzo_glow.png");

    public SkizzoGlowLayer(IGeoRenderer<T> renderer) {
        super(renderer);
        this.renderer = renderer;
    }

    private ResourceLocation getTexture(T entity) {
        if (entity instanceof FriendlySkizzo) {
            return FRIENDLY_SKIZZO_GLOW;
        }
        return SKIZZO_GLOW;
    }

    @Override
    public void render(PoseStack matrix, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float headYaw, float headPitch) {
        RenderType texture = this.getRenderType(this.getTexture(entity));
        this.renderer.render(this.getEntityModel().getModel(SkizzoModel.modelLocation(entity)), entity, partialTicks, texture, matrix, buffer, buffer.getBuffer(texture), 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
