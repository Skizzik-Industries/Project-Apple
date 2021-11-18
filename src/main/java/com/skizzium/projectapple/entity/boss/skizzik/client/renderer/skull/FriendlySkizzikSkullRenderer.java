package com.skizzium.projectapple.entity.boss.skizzik.client.renderer.skull;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.FriendlySkizzikSkull;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FriendlySkizzikSkullRenderer<T extends FriendlySkizzikSkull> extends AbstractSkizzikSkullRenderer<T> {
   private static final ResourceLocation SKULL_LOCATION = new ResourceLocation("skizzik:textures/entity/friendly_skizzik/friendly_skizzik_skull.png");
   private static final ResourceLocation SPOOKTOBER_SKULL_LOCATION = new ResourceLocation("skizzik:textures/entity/holidays/spooktober/friendly_spookzik/friendly_spookzik_skull.png");

   public FriendlySkizzikSkullRenderer(EntityRendererProvider.Context renderer) {
      super(renderer);
   }

   @Override
   public ResourceLocation getTextureLocation(T skull) {
      if (ProjectApple.holiday == 1) {
         return SPOOKTOBER_SKULL_LOCATION;
      }

      return SKULL_LOCATION;
   }
}