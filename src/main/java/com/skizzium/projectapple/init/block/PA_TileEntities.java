package com.skizzium.projectapple.init.block;

import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.tileentity.PA_SignTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fmllegacy.RegistryObject;

public class PA_TileEntities {
    public static final RegistryObject<BlockEntityType<PA_SignTileEntity>> PA_SIGN = PA_Registry.TILE_ENTITIES.register("pa_sign", () -> BlockEntityType.Builder.of(PA_SignTileEntity::new, PA_Blocks.CANDY_SIGN.get(), PA_Blocks.CANDY_WALL_SIGN.get()).build(null));

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerTileEntityRenders(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(PA_SIGN.get(), SignRenderer::new);
    }

    public static void register() {}
}
