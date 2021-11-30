package com.skizzium.projectapple.entity.boss.skizzik.client.model;


import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzo;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.base.SkizzikSleeping;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class SkizzoModel<T extends IAnimatable> extends AnimatedGeoModel<T> {
	@Override
	public ResourceLocation getTextureLocation(T skizzo) {
		if (skizzo instanceof FriendlySkizzo) {
			return new ResourceLocation(ProjectApple.MOD_ID, "textures/entity/friendly_skizzik/friendly_skizzo.png");
		}
		return new ResourceLocation(ProjectApple.MOD_ID, "textures/entity/skizzik/skizzo.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(T skizzo) {
		return new ResourceLocation(ProjectApple.MOD_ID, "animations/skizzo.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(T skizzo) {
		return new ResourceLocation(ProjectApple.MOD_ID, "geo/skizzo.geo.json");
	}
	
	public static ResourceLocation modelLocation(IAnimatable skizzo) {
		return new ResourceLocation(ProjectApple.MOD_ID, "geo/skizzo.geo.json");
	}

	@Override
	public void setLivingAnimations(T skizzik, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
		super.setLivingAnimations(skizzik, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("head");
		
		EntityModelData data = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		head.setRotationX(data.headPitch * ((float) Math.PI / 180F));
		head.setRotationY(data.netHeadYaw * ((float) Math.PI / 180F));
	}
}