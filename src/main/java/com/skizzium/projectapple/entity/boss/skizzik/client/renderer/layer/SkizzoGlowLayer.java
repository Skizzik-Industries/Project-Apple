package com.skizzium.projectapple.entity.boss.skizzik.client.renderer.layer;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzo;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.SkizzoModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkizzoGlowLayer<T extends LivingEntity> extends EyesLayer<T, SkizzoModel<T>> {
    private static final RenderType SKIZZO_GLOW = RenderType.eyes(new ResourceLocation("skizzik:textures/entity/skizzik/skizzo_glow.png"));
    private static final RenderType SPOOKZO_GLOW = RenderType.eyes(new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzo_glow.png"));

    public SkizzoGlowLayer(RenderLayerParent<T, SkizzoModel<T>> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return ProjectApple.holiday == 1 ? SPOOKZO_GLOW : SKIZZO_GLOW;
    }
}
