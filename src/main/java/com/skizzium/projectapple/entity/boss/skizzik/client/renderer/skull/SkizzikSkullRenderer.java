package com.skizzium.projectapple.entity.boss.skizzik.client.renderer.skull;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.SkizzikSkull;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzikSkullRenderer<T extends SkizzikSkull> extends AbstractSkizzikSkullRenderer<T> {
   private static final ResourceLocation LEVEL_1 = new ResourceLocation("skizzik:textures/entity/skizzik_skull/level_1.png");
   private static final ResourceLocation LEVEL_2 = new ResourceLocation("skizzik:textures/entity/skizzik_skull/level_2.png");
   private static final ResourceLocation LEVEL_3 = new ResourceLocation("skizzik:textures/entity/skizzik_skull/level_3.png");

   private static final ResourceLocation SPOOKZIK_LEVEL_1 = new ResourceLocation(ProjectApple.MOD_ID, "textures/entity/holidays/spooktober/spookzik_skull/level_1.png");
   private static final ResourceLocation SPOOKZIK_LEVEL_2 = new ResourceLocation(ProjectApple.MOD_ID, "textures/entity/holidays/spooktober/spookzik_skull/level_2.png");
   private static final ResourceLocation SPOOKZIK_LEVEL_3 = new ResourceLocation(ProjectApple.MOD_ID, "textures/entity/holidays/spooktober/spookzik_skull/level_3.png");

   public SkizzikSkullRenderer(EntityRendererProvider.Context renderer) {
      super(renderer);
   }

   @Override
   public ResourceLocation getTextureLocation(T skull) {
      if (ProjectApple.holiday == 1) {
         return skull.getLevel() == 1 ? SPOOKZIK_LEVEL_1
                 : skull.getLevel() == 2 ? SPOOKZIK_LEVEL_2 : SPOOKZIK_LEVEL_3;
      }

      return skull.getLevel() == 1 ? LEVEL_1
              : skull.getLevel() == 2 ? LEVEL_2
              : LEVEL_3;
   }
}