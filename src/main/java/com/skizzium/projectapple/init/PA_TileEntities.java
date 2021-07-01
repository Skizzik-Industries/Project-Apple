package com.skizzium.projectapple.init;

import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.tileentity.PA_Sign;
import com.skizzium.projectapple.tileentity.PA_Skull;
import net.minecraft.block.SkullBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class PA_TileEntities {
    public static final RegistryObject<TileEntityType<PA_Sign>> PA_SIGN = PA_Registry.TILE_ENTITIES.register("pa_sign", () -> TileEntityType.Builder.of(PA_Sign::new, PA_Blocks.CANDY_SIGN.get(), PA_Blocks.CANDY_WALL_SIGN.get()).build(null));
    public static final RegistryObject<TileEntityType<PA_Skull>> PA_SKULL = PA_Registry.TILE_ENTITIES.register("pa_skull", () -> TileEntityType.Builder.of(PA_Skull::new, PA_Blocks.SKIZZIK_HEAD.get(), PA_Blocks.SKIZZIK_WALL_HEAD.get()).build(null));

    public enum CustomSkullTypes implements SkullBlock.ISkullType {
        SKIZZIK
    }

    public static void register() {}
}
