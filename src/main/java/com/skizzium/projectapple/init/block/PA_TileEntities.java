package com.skizzium.projectapple.init.block;

import com.google.common.collect.ImmutableMap;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import com.skizzium.projectapple.tileentity.PA_SignTileEntity;
import com.skizzium.projectapple.tileentity.PA_Skull;
import com.skizzium.projectapple.tileentity.model.PA_SkullModel;
import com.skizzium.projectapple.tileentity.renderer.PA_SkullRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.dragon.DragonHeadModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_TileEntities {
    public static final RegistryObject<BlockEntityType<PA_SignTileEntity>> PA_SIGN = PA_Registry.TILE_ENTITIES.register("pa_sign", () -> BlockEntityType.Builder.of(PA_SignTileEntity::new, PA_Blocks.CANDY_SIGN.get(), PA_Blocks.CANDY_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<PA_Skull>> PA_SKULL = PA_Registry.TILE_ENTITIES.register("pa_skull", () -> BlockEntityType.Builder.of(PA_Skull::new, PA_Blocks.SKIZZIK_HEAD.get(), PA_Blocks.SKIZZIK_WALL_HEAD.get(),
            PA_Blocks.SKIZZIK_HEAD_WITH_GEMS.get(), PA_Blocks.SKIZZIK_WALL_HEAD_WITH_GEMS.get()).build(null));

    public enum CustomSkullTypes implements SkullBlock.Type {
        SKIZZIK,
        SKIZZIK_WITH_GEMS
    }

    @SubscribeEvent
    public static void registerTileEntityRenders(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PA_ModelLayers.SKIZZIK_HEAD_LAYER, PA_SkullModel::createSkizzikHeadLayer);
        event.registerLayerDefinition(PA_ModelLayers.SKIZZIK_HEAD_WITH_GEMS_LAYER, PA_SkullModel::createSkizzikHeadWithGemsLayer);

        BlockEntityRenderers.register(PA_SIGN.get(), SignRenderer::new);
        BlockEntityRenderers.register(PA_SKULL.get(), PA_SkullRenderer::new);
    }

    public static Map<SkullBlock.Type, SkullModelBase> createSkullRenderers() {
        ImmutableMap.Builder<SkullBlock.Type, SkullModelBase> builder = ImmutableMap.builder();

        builder.put(PA_TileEntities.CustomSkullTypes.SKIZZIK, new PA_SkullModel(Minecraft.getInstance().getEntityModels().bakeLayer(PA_ModelLayers.SKIZZIK_HEAD_LAYER)));
        builder.put(PA_TileEntities.CustomSkullTypes.SKIZZIK_WITH_GEMS, new PA_SkullModel(Minecraft.getInstance().getEntityModels().bakeLayer(PA_ModelLayers.SKIZZIK_HEAD_WITH_GEMS_LAYER)));

        return builder.build();
    }

    public static void registerSkullPlayerHeadLayers(EntityRenderersEvent.AddLayers event) {
        Map<EntityType<?>, EntityRenderer<?>> renderers = Minecraft.getInstance().getEntityRenderDispatcher().renderers;
        for(Map.Entry<EntityType<?>, EntityRenderer<?>> renderer : renderers.entrySet()) {
            if (renderer.getValue() instanceof LivingEntityRenderer) {
                List<? extends RenderLayer<?, ?>> layers = ((LivingEntityRenderer<?, ?>) renderer.getValue()).layers;
                for (RenderLayer<?, ?> layer : layers) {
                    if (layer instanceof CustomHeadLayer) {
                        ((CustomHeadLayer) layer).skullModels = createSkullRenderers();
                    }
                }
            }
        }

        Map<String, EntityRenderer<? extends Player>> skins = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap();
        for(Map.Entry<String, EntityRenderer<? extends Player>> renderer : skins.entrySet()) {
            if (renderer.getValue() instanceof LivingEntityRenderer) {
                List<? extends RenderLayer<?, ?>> layers = ((LivingEntityRenderer<?, ?>) renderer.getValue()).layers;
                for (RenderLayer<?, ?> layer : layers) {
                    if (layer instanceof CustomHeadLayer) {
                        ((CustomHeadLayer) layer).skullModels = createSkullRenderers();
                    }
                }
            }
        }
    }

    public static void register() {}
}
