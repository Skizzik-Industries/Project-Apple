package com.skizzium.projectapple.init.data.server.tags;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.tags.*;
import net.minecraft.resources.ResourceLocation;

public final class PA_Tags {
    public static final class Blocks {
        public static final Tag.Named<Block> MINEABLE_WITH_PICKAXE = minecraft("mineable/pickaxe");
        public static final Tag.Named<Block> MINEABLE_WITH_HOE = minecraft("mineable/hoe");

        public static final Tag.Named<Block> NEEDS_STONE_TOOL = minecraft("needs_stone_tool");
        public static final Tag.Named<Block> NEEDS_IRON_TOOL = minecraft("needs_iron_tool");
        public static final Tag.Named<Block> NEEDS_DIAMOND_TOOL = minecraft("needs_diamond_tool");

        public static final Tag.Named<Block> SKIZZIK_COMMAND_BLOCKS = forge("skizzik_command_blocks");
        public static final Tag.Named<Block> FRIENDLY_SKIZZIK_HEADS = forge("skizzik_heads");
        public static final Tag.Named<Block> SKIZZIK_HEADS = forge("skizzik_heads");

        public static final Tag.Named<Block> RAINBOW_SWORD_IMMUNE = forge("skizzik_rainbow_sword_immune");
        public static final Tag.Named<Block> CORRUPTION_IMMUNE = forge("skizzik_corruption_immune");

        public static final Tag.Named<Block> DRAGON_IMMUNE = minecraft("dragon_immune");
        public static final Tag.Named<Block> WITHER_IMMUNE = minecraft("wither_immune");

        public static final Tag.Named<Block> BEACON_BASE_BLOCKS = minecraft("beacon_base_blocks");

        public static final Tag.Named<Block> SKIZZIK_ALL_GEM_BLOCKS = forge("skizzik_all_gem_blocks");
        public static final Tag.Named<Block> SKIZZIK_BASE_GEM_BLOCKS = forge("skizzik_base_gem_blocks");

        private static Tag.Named<Block> minecraft(String path) {
            return BlockTags.bind(new ResourceLocation("minecraft", path).toString());
        }

        private static Tag.Named<Block> forge(String path) {
            return BlockTags.bind(new ResourceLocation("forge", path).toString());
        }
    }
    
    public static final class EntityTypes {
        public static final Tag.Named<EntityType<?>> SKIZZIES = forge("skizzies");
        public static final Tag.Named<EntityType<?>> FRIENDLY_SKIZZIES = forge("friendly_skizzies");

        private static Tag.Named<EntityType<?>> forge(String path) {
            return EntityTypeTags.bind(new ResourceLocation("forge", path).toString());
        }
    }
    
    public static final class Items {
        public static final Tag.Named<Item> SKIZZIK_COMMAND_BLOCKS = forge("skizzik_command_blocks");
        public static final Tag.Named<Item> SKIZZIK_HEADS = forge("skizzik_heads");
        
        public static final Tag.Named<Item> FRIENDLY_SKIZZIK_RIBS = forge("friendly_skizzik_ribs");
        
        public static final Tag.Named<Item> CURIOS_BACK = curios("back");

        public static final Tag.Named<Item> BEACON_PAYMENT_ITEMS = minecraft("beacon_payment_items");
        public static final Tag.Named<Item> BEACON_BASE_BLOCKS = minecraft("beacon_base_blocks");

        public static final Tag.Named<Item> SKIZZIK_ALL_GEMS = forge("skizzik_all_gems");
        public static final Tag.Named<Item> SKIZZIK_BASE_GEMS = forge("skizzik_base_gems");

        private static Tag.Named<Item> minecraft(String path) {
            return ItemTags.bind(new ResourceLocation("minecraft", path).toString());
        }

        private static Tag.Named<Item> forge(String path) {
            return ItemTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static Tag.Named<Item> curios(String path) {
            return ItemTags.bind(new ResourceLocation("curios", path).toString());
        }
    }
}
