package com.skizzium.projectapple.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.skizzium.projectapple.entity.SkizzikSkull;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import com.skizzium.projectapple.tileentity.model.PA_SkullModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzikSkullRenderer extends EntityRenderer<SkizzikSkull> {
   private static final ResourceLocation LEVEL_1 = new ResourceLocation("skizzik:textures/entity/skizzik_skull/level_1.png");
   private static final ResourceLocation LEVEL_2 = new ResourceLocation("skizzik:textures/entity/skizzik_skull/level_2.png");
   private static final ResourceLocation LEVEL_3 = new ResourceLocation("skizzik:textures/entity/skizzik_skull/level_3.png");
   private final PA_SkullModel model;

   public SkizzikSkullRenderer(EntityRendererProvider.Context renderer) {
      super(renderer);
      model = new PA_SkullModel(renderer.bakeLayer(PA_ModelLayers.SKIZZIK_HEAD_LAYER));
   }

   @Override
   protected int getBlockLightLevel(SkizzikSkull skull, BlockPos pos) {
      return 15;
   }

   @Override
   public void render(SkizzikSkull skull, float yaw, float pitch, PoseStack matrix, MultiBufferSource renderer, int light) {
      matrix.pushPose();
      matrix.scale(-1.0F, -1.0F, 1.0F);

      float yRot = Mth.rotlerp(skull.yRotO, skull.getYRot(), pitch);
      float xRot = Mth.lerp(pitch, skull.xRotO, skull.getXRot());

      VertexConsumer vertex = renderer.getBuffer(this.model.renderType(this.getTextureLocation(skull)));
      this.model.setupAnim(0.0F, yRot, xRot);
      this.model.renderToBuffer(matrix, vertex, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

      matrix.popPose();
      super.render(skull, yaw, pitch, matrix, renderer, light);
   }

   @Override
   public ResourceLocation getTextureLocation(SkizzikSkull skull) {
      return skull.getLevel() == 1 ? LEVEL_1
              : skull.getLevel() == 2 ? LEVEL_2
              : LEVEL_3;
   }
}