package com.skizzium.projectapple.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.skizzium.projectapple.entity.SkizzikSkull;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.gui.spectator.categories.TeleportToPlayerMenuCategory;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import ResourceLocation;

@OnlyIn(Dist.CLIENT)
public class SkizzikSkullRenderer extends TeleportToPlayerMenuCategory<SkizzikSkull> {
   private static final ResourceLocation LEVEL_1 = new ResourceLocation("skizzik:textures/entity/skizzik_head/level_1.png");
   private static final ResourceLocation LEVEL_2 = new ResourceLocation("skizzik:textures/entity/skizzik_head/level_2.png");
   private static final ResourceLocation LEVEL_3 = new ResourceLocation("skizzik:textures/entity/skizzik_head/level_3.png");
   private final SkullModel model = new SkullModel(0, 0, 32, 16);

   public SkizzikSkullRenderer(EntityRenderDispatcher renderer) {
      super(renderer);
   }

   protected int getBlockLightLevel(SkizzikSkull skull, BlockPos pos) {
      return 15;
   }

   public void render(SkizzikSkull skull, float yaw, float pitch, PoseStack matrix, MultiBufferSource renderer, int light) {
      matrix.pushPose();
      matrix.scale(-1.0F, -1.0F, 1.0F);

      float yRot = Mth.rotlerp(skull.yRotO, skull.yRot, pitch);
      float xRot = Mth.lerp(pitch, skull.xRotO, skull.xRot);

      VertexConsumer vertex = renderer.getBuffer(this.model.renderType(this.getTextureLocation(skull)));
      this.model.setupAnim(0.0F, yRot, xRot);
      this.model.renderToBuffer(matrix, vertex, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

      matrix.popPose();
      super.render(skull, yaw, pitch, matrix, renderer, light);
   }

   public ResourceLocation getTextureLocation(SkizzikSkull skull) {
      return skull.getLevel() == 1 ? LEVEL_1
              : skull.getLevel() == 2 ? LEVEL_2
              : LEVEL_3;
   }
}