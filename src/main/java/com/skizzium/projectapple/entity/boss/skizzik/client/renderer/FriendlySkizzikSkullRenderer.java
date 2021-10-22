package com.skizzium.projectapple.entity.boss.skizzik.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.SkizzikSkull;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import com.skizzium.projectapple.tileentity.model.PA_SkullModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FriendlySkizzikSkullRenderer<T extends AbstractHurtingProjectile> extends EntityRenderer<T> {
   private static final ResourceLocation SKULL_LOCATION = new ResourceLocation("skizzik:textures/entity/friendly_skizzik/friendly_skizzik_skull.png");
   private final PA_SkullModel model;

   public FriendlySkizzikSkullRenderer(EntityRendererProvider.Context renderer) {
      super(renderer);
      model = new PA_SkullModel(renderer.bakeLayer(PA_ModelLayers.SKIZZIK_HEAD_LAYER));
   }

   @Override
   protected int getBlockLightLevel(T skull, BlockPos pos) {
      return 15;
   }

   @Override
   public void render(T skull, float yaw, float pitch, PoseStack matrix, MultiBufferSource renderer, int light) {
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
   public ResourceLocation getTextureLocation(T skull) {
      return SKULL_LOCATION;
      
//      if (ProjectApple.holiday == 1) {
//         return skull.getLevel() == 1 ? SPOOKZIK_LEVEL_1
//                 : skull.getLevel() == 2 ? SPOOKZIK_LEVEL_2 : SPOOKZIK_LEVEL_3;
//      }
//
//      return skull.getLevel() == 1 ? SKULL_LOCATION
//              : skull.getLevel() == 2 ? LEVEL_2
//              : LEVEL_3;
   }
}