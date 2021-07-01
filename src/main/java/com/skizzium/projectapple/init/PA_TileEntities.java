package com.skizzium.projectapple.init;

import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.tileentity.PA_Sign;
import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.tileentity.SkullTileEntityRenderer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.lang.reflect.Field;
import java.util.Map;

public class PA_TileEntities {
    public static final RegistryObject<TileEntityType<PA_Sign>> PA_SIGN = PA_Registry.TILE_ENTITIES.register("pa_sign", () -> TileEntityType.Builder.of(PA_Sign::new, PA_Blocks.CANDY_SIGN.get(), PA_Blocks.CANDY_WALL_SIGN.get()).build(null));
    public static final RegistryObject<TileEntityType<PA_Skull>> PA_SKULL = PA_Registry.TILE_ENTITIES.register("pa_skull", () -> TileEntityType.Builder.of(PA_Skull::new, PA_Blocks.SKIZZIK_HEAD.get(), PA_Blocks.SKIZZIK_WALL_HEAD.get()).build(null));

    public enum CustomSkullTypes implements SkullBlock.ISkullType {
        SKIZZIK
    }

    public static void registerCustomSkullRenderers(final FMLClientSetupEvent event) {
        Field ModelField;
        Field SkinField;
        try {
            ModelField = SkullTileEntityRenderer.class.getDeclaredField("MODEL_BY_TYPE");
            ModelField.setAccessible(true);
            Map<SkullBlock.ISkullType, GenericHeadModel> Model = (Map<SkullBlock.ISkullType, GenericHeadModel>) ModelField.get(SkullTileEntityRenderer.class);
            Model.put(PA_TileEntities.CustomSkullTypes.SKIZZIK, new GenericHeadModel(0, 0, 32, 32));

            SkinField = SkullTileEntityRenderer.class.getDeclaredField("SKIN_BY_TYPE");
            SkinField.setAccessible(true);
            Map<SkullBlock.ISkullType, ResourceLocation> Skin = (Map<SkullBlock.ISkullType, ResourceLocation>) SkinField.get(SkullTileEntityRenderer.class);
            Skin.put(PA_TileEntities.CustomSkullTypes.SKIZZIK, new ResourceLocation("skizzik:textures/block/skizzik_head.png"));
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void register() {}
}
