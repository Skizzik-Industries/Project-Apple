package com.skizzium.projectapple.init.world;

import com.google.common.collect.ImmutableSet;
import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.registries.RegistryObject;

public class PA_PoiTypes {
    public static final RegistryObject<PoiType> SKIZZIK_LOOT_BAG = PA_Registry.POI_TYPES.register("skizzik_loot_bag", () -> new PoiType(
            "skizzik_loot_bag", 
            ImmutableSet.copyOf(PA_Blocks.SKIZZIK_LOOT_BAG.get().getStateDefinition().getPossibleStates()),
            0, 1));
    
    public static void register() {}
}
