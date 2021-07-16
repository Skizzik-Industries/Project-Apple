package com.skizzium.projectapple.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.skizzium.projectapple.entity.SkizzikSkull;
import com.skizzium.projectapple.entity.SkizzikSkullOld;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzikSkullRenderer extends EntityRenderer<SkizzikSkull> {
   private static final ResourceLocation WITHER_INVULNERABLE_LOCATION = new ResourceLocation("minecraft:textures/entity/wither/wither_invulnerable.png");
   private static final ResourceLocation WITHER_LOCATION = new ResourceLocation("minecraft:textures/entity/wither/wither.png");
   private final GenericHeadModel model = new GenericHeadModel();

   public SkizzikSkullRenderer(EntityRendererManager p_i46129_1_) {
      super(p_i46129_1_);
   }

   protected int getBlockLightLevel(SkizzikSkull p_225624_1_, BlockPos p_225624_2_) {
      return 15;
   }

   public void render(SkizzikSkull p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
      p_225623_4_.pushPose();
      p_225623_4_.scale(-1.0F, -1.0F, 1.0F);
      float f = MathHelper.rotlerp(p_225623_1_.yRotO, p_225623_1_.yRot, p_225623_3_);
      float f1 = MathHelper.lerp(p_225623_3_, p_225623_1_.xRotO, p_225623_1_.xRot);
      IVertexBuilder ivertexbuilder = p_225623_5_.getBuffer(this.model.renderType(this.getTextureLocation(p_225623_1_)));
      this.model.setupAnim(0.0F, f, f1);
      this.model.renderToBuffer(p_225623_4_, ivertexbuilder, p_225623_6_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      p_225623_4_.popPose();
      super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
   }

   public ResourceLocation getTextureLocation(SkizzikSkull p_110775_1_) {
      return p_110775_1_.isDangerous() ? WITHER_INVULNERABLE_LOCATION : WITHER_LOCATION;
   }
   
   /* private static final ResourceLocation LEVEL_1 = new ResourceLocation("skizzik:textures/entity/skizzik_head/level_1.png");
   private static final ResourceLocation LEVEL_2 = new ResourceLocation("skizzik:textures/entity/skizzik_head/level_2.png");
   private static final ResourceLocation LEVEL_3 = new ResourceLocation("skizzik:textures/entity/skizzik_head/level_3.png");
   private final GenericHeadModel model = new GenericHeadModel();

   public SkizzikSkullRenderer(EntityRendererManager renderer) {
      super(renderer);
   }

   protected int getBlockLightLevel(SkizzikSkullOld skull, BlockPos pos) {
      return 15;
   }

   public void render(SkizzikSkullOld skull, float yaw, float pitch, MatrixStack matrix, IRenderTypeBuffer renderer, int light) {
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

   public ResourceLocation getTextureLocation(SkizzikSkullOld skull) {
      //int level = skull.getLevel();
      return LEVEL_1;
   } */
}