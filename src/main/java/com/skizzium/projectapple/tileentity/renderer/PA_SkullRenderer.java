package com.skizzium.projectapple.tileentity.renderer;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.PA_ClientHelper;
import com.skizzium.projectapple.init.block.PA_TileEntities;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PA_SkullRenderer extends SkullBlockRenderer implements BlockEntityRenderer<SkullBlockEntity> {
    private static final ResourceLocation SMALL_FRIENDLY_SKIZZIK_LOCATION = ProjectApple.holiday == 1 ? new ResourceLocation("skizzik:textures/block/spooktober/small_friendly_spookzik_head.png") : new ResourceLocation("skizzik:textures/block/small_friendly_skizzik_head.png");
    private static final ResourceLocation SMALL_FRIENDLY_SKIZZIK_WITH_GEMS_LOCATION = ProjectApple.holiday == 1 ? new ResourceLocation("skizzik:textures/block/spooktober/small_friendly_spookzik_head_with_gems.png") : new ResourceLocation("skizzik:textures/block/small_friendly_skizzik_head_with_gems.png");
    private static final ResourceLocation FRIENDLY_SKIZZIK_LOCATION = ProjectApple.holiday == 1 ? new ResourceLocation("skizzik:textures/entity/holidays/spooktober/friendly_spookzik/friendly_spookzik.png") : new ResourceLocation("skizzik:textures/entity/friendly_skizzik/friendly_skizzik.png");
    private static final ResourceLocation FRIENDLY_SKIZZIK_WITH_GEMS_LOCATION = ProjectApple.holiday == 1 ? new ResourceLocation("skizzik:textures/block/spooktober/friendly_spookzik_head_with_gems.png") : new ResourceLocation("skizzik:textures/block/friendly_skizzik_head_with_gems.png");

    private static final ResourceLocation SMALL_SKIZZIK_LOCATION = ProjectApple.holiday == 1 ? new ResourceLocation("skizzik:textures/block/spooktober/small_spookzik_head.png") : new ResourceLocation("skizzik:textures/block/small_skizzik_head.png");
    private static final ResourceLocation SMALL_SKIZZIK_WITH_GEMS_LOCATION = ProjectApple.holiday == 1 ? new ResourceLocation("skizzik:textures/block/spooktober/small_spookzik_head_with_gems.png") : new ResourceLocation("skizzik:textures/block/small_skizzik_head_with_gems.png");
    private static final ResourceLocation SKIZZIK_LOCATION = ProjectApple.holiday == 1 ? new ResourceLocation("skizzik:textures/block/spooktober/spookzik_head.png") : new ResourceLocation("skizzik:textures/block/skizzik_head.png");
    private static final ResourceLocation SKIZZIK_WITH_GEMS_LOCATION = ProjectApple.holiday == 1 ? new ResourceLocation("skizzik:textures/entity/holidays/spooktober/spookzik/spookzik.png") : new ResourceLocation("skizzik:textures/entity/skizzik/skizzik.png");

    public PA_SkullRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        super.modelByType = PA_ClientHelper.createSkullRenderers(context.getModelSet());

        SKIN_BY_TYPE.put(PA_TileEntities.CustomSkullTypes.SMALL_FRIENDLY_SKIZZIK, SMALL_FRIENDLY_SKIZZIK_LOCATION);
        SKIN_BY_TYPE.put(PA_TileEntities.CustomSkullTypes.SMALL_FRIENDLY_SKIZZIK_WITH_GEMS, SMALL_FRIENDLY_SKIZZIK_WITH_GEMS_LOCATION);
        SKIN_BY_TYPE.put(PA_TileEntities.CustomSkullTypes.FRIENDLY_SKIZZIK, FRIENDLY_SKIZZIK_LOCATION);
        SKIN_BY_TYPE.put(PA_TileEntities.CustomSkullTypes.FRIENDLY_SKIZZIK_WITH_GEMS, FRIENDLY_SKIZZIK_WITH_GEMS_LOCATION);
        
        SKIN_BY_TYPE.put(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK, SMALL_SKIZZIK_LOCATION);
        SKIN_BY_TYPE.put(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK_WITH_GEMS, SMALL_SKIZZIK_WITH_GEMS_LOCATION);
        SKIN_BY_TYPE.put(PA_TileEntities.CustomSkullTypes.SKIZZIK, SKIZZIK_LOCATION);
        SKIN_BY_TYPE.put(PA_TileEntities.CustomSkullTypes.SKIZZIK_WITH_GEMS, SKIZZIK_WITH_GEMS_LOCATION);
    }
}
