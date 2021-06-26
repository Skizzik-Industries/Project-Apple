package com.skizzium.projectapple.init;

import com.skizzium.projectapple.init.block.ModBlocks;
import com.skizzium.projectapple.tileentity.SkizzikSign;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class ModTileEntities {
    public static final RegistryObject<TileEntityType<SkizzikSign>> SKIZZIK_SIGN = Register.TILE_ENTITIES.register("skizzik_sign", () -> TileEntityType.Builder.of(SkizzikSign::new, ModBlocks.CANDY_SIGN.get(), ModBlocks.CANDY_WALL_SIGN.get()).build(null));

    public static void register() {}
}
