package com.skizzium.projectapple.init.block;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import com.skizzium.projectapple.tileentity.PA_SignTileEntity;
import com.skizzium.projectapple.tileentity.PA_Skull;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.PA_SkullModel;
import com.skizzium.projectapple.tileentity.renderer.PA_SkullRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_TileEntities {
    public static final RegistryObject<BlockEntityType<PA_SignTileEntity>> PA_SIGN = PA_Registry.TILE_ENTITIES.register("pa_sign", () -> BlockEntityType.Builder.of(PA_SignTileEntity::new, PA_Blocks.CANDY_SIGN.get(), PA_Blocks.CANDY_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<PA_Skull>> PA_SKULL = PA_Registry.TILE_ENTITIES.register("pa_skull", () -> BlockEntityType.Builder.of(PA_Skull::new, PA_Blocks.SMALL_FRIENDLY_SKIZZIK_HEAD.get(), PA_Blocks.SMALL_FRIENDLY_SKIZZIK_HEAD_WITH_GEMS.get(),
            PA_Blocks.FRIENDLY_SKIZZIK_HEAD.get(), PA_Blocks.FRIENDLY_SKIZZIK_WALL_HEAD.get(),
            PA_Blocks.FRIENDLY_SKIZZIK_HEAD_WITH_GEMS.get(), PA_Blocks.FRIENDLY_SKIZZIK_WALL_HEAD_WITH_GEMS.get(),
            
            PA_Blocks.SMALL_SKIZZIK_HEAD.get(), PA_Blocks.SMALL_SKIZZIK_HEAD_WITH_GEMS.get(),
            PA_Blocks.SKIZZIK_HEAD.get(), PA_Blocks.SKIZZIK_WALL_HEAD.get(),
            PA_Blocks.SKIZZIK_HEAD_WITH_GEMS.get(), PA_Blocks.SKIZZIK_WALL_HEAD_WITH_GEMS.get()).build(null));

    public enum CustomSkullTypes implements SkullBlock.Type {
        SMALL_FRIENDLY_SKIZZIK,
        SMALL_FRIENDLY_SKIZZIK_WITH_GEMS,
        FRIENDLY_SKIZZIK,
        FRIENDLY_SKIZZIK_WITH_GEMS,
        
        SMALL_SKIZZIK,
        SMALL_SKIZZIK_WITH_GEMS,
        SKIZZIK,
        SKIZZIK_WITH_GEMS
    }

    @SubscribeEvent
    public static void registerTileEntityRenders(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PA_ModelLayers.SMALL_SKIZZIK_HEAD_LAYER, PA_SkullModel::createSmallSkizzikHeadLayer);
        event.registerLayerDefinition(PA_ModelLayers.SMALL_SKIZZIK_HEAD_WITH_GEMS_LAYER, PA_SkullModel::createSmallSkizzikHeadLayer);
        event.registerLayerDefinition(PA_ModelLayers.SKIZZIK_HEAD_LAYER, PA_SkullModel::createSkizzikHeadLayer);
        event.registerLayerDefinition(PA_ModelLayers.SKIZZIK_HEAD_WITH_GEMS_LAYER, PA_SkullModel::createSkizzikHeadWithGemsLayer);

        BlockEntityRenderers.register(PA_TileEntities.PA_SIGN.get(), SignRenderer::new);
        BlockEntityRenderers.register(PA_TileEntities.PA_SKULL.get(), PA_SkullRenderer::new);
    }

    public static void register() {}
}
