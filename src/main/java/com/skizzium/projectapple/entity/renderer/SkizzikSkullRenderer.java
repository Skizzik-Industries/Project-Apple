package com.skizzium.projectapple.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.skizzium.projectapple.entity.SkizzikSkull;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzikSkullRenderer extends EntityRenderer<SkizzikSkull> {
   private static final ResourceLocation LEVEL_1 = new ResourceLocation("skizzik:textures/entity/skizzik_skull/level_1.png");
   private static final ResourceLocation LEVEL_2 = new ResourceLocation("skizzik:textures/entity/skizzik_skull/level_2.png");
   private static final ResourceLocation LEVEL_3 = new ResourceLocation("skizzik:textures/entity/skizzik_skull/level_3.png");
   private final GenericHeadModel model = new GenericHeadModel(0, 0, 32, 16);

   public SkizzikSkullRenderer(EntityRendererManager renderer) {
      super(renderer);
   }

   @Override
   protected int getBlockLightLevel(SkizzikSkull skull, BlockPos pos) {
      return 15;
   }

   @Override
   public void render(SkizzikSkull skull, float yaw, float pitch, MatrixStack matrix, IRenderTypeBuffer renderer, int light) {
      matrix.pushPose();
      matrix.scale(-1.0F, -1.0F, 1.0F);

      float yRot = MathHelper.rotlerp(skull.yRotO, skull.yRot, pitch);
      float xRot = MathHelper.lerp(pitch, skull.xRotO, skull.xRot);

      IVertexBuilder vertex = renderer.getBuffer(this.model.renderType(this.getTextureLocation(skull)));
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