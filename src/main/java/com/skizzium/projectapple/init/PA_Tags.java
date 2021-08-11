package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.tags.*;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.server.players.ServerOpList;

public final class PA_Tags {
    public static final class Blocks {
        public static final Tag.Named<Block> MINEABLE_WITH_PICKAXE = minecraft("mineable/pickaxe");
        public static final Tag.Named<Block> MINEABLE_WITH_HOE = minecraft("mineable/hoe");
        public static final Tag.Named<Block> MINEABLE_WITH_AXE = minecraft("mineable/axe");

        public static final Tag.Named<Block> SKIZZIK_COMMAND_BLOCKS = forge("skizzik_command_blocks");
        public static final Tag.Named<Block> SKIZZIK_HEADS = forge("skizzik_heads");

        public static final Tag.Named<Block> RAINBOW_SWORD_IMMUNE = forge("rainbow_sword_immune");
        public static final Tag.Named<Block> CORRUPTION_IMMUNE = forge("corruption_immune");

        public static final Tag.Named<Block> DRAGON_IMMUNE = minecraft("dragon_immune");
        public static final Tag.Named<Block> WITHER_IMMUNE = minecraft("wither_immune");

        public static final Tag.Named<Block> SKIZZIK_CHOCOLATE_BLOCKS = forge("skizzik_chocolate_blocks");

        public static final Tag.Named<Block> PRESSURE_PLATES = minecraft("pressure_plates");
        public static final Tag.Named<Block> WALL_POST_OVERRIDE = minecraft("wall_post_override");
        public static final Tag.Named<Block> WOODEN_PRESSURE_PLATES = minecraft("wooden_pressure_plates");

        public static final Tag.Named<Block> BUTTONS = minecraft("buttons");
        public static final Tag.Named<Block> WOODEN_BUTTONS = minecraft("wooden_buttons");

        public static final Tag.Named<Block> SIGNS = minecraft("signs");
        public static final Tag.Named<Block> STANDING_SIGNS = minecraft("standing_signs");
        public static final Tag.Named<Block> WALL_SIGNS = minecraft("wall_signs");

        public static final Tag.Named<Block> PLANKS = minecraft("planks");
        public static final Tag.Named<Block> SLABS = minecraft("slabs");
        public static final Tag.Named<Block> WOODEN_SLABS = minecraft("wooden_slabs");
        public static final Tag.Named<Block> STAIRS = minecraft("stairs");
        public static final Tag.Named<Block> WOODEN_STAIRS = minecraft("wooden_stairs");

        public static final Tag.Named<Block> FENCES = minecraft("fences");
        public static final Tag.Named<Block> WOODEN_FENCES = minecraft("wooden_fences");
        public static final Tag.Named<Block> FENCE_GATES = minecraft("fence_gates");
        public static final Tag.Named<Block> UNSTABLE_BOTTOM_CENTER = minecraft("unstable_bottom_center");

        public static final Tag.Named<Block> TRAPDOORS = minecraft("trapdoors");
        public static final Tag.Named<Block> WOODEN_TRAPDOORS = minecraft("wooden_trapdoors");
        public static final Tag.Named<Block> DOORS = minecraft("doors");
        public static final Tag.Named<Block> WOODEN_DOORS = minecraft("wooden_doors");

        public static final Tag.Named<Block> LEAVES = minecraft("leaves");
        public static final Tag.Named<Block> ENDERMAN_HOLDABLE = minecraft("enderman_holdable");
        public static final Tag.Named<Block> VALID_SPAWN = minecraft("valid_spawn");

        public static final Tag.Named<Block> LOGS = minecraft("logs");
        public static final Tag.Named<Block> LOGS_THAT_BURN = minecraft("logs_that_burn");
        public static final Tag.Named<Block> CANDY_LOGS = forge("candy_logs");

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
    public static final class Fluids {
        public static final Tag.Named<Fluid> WATER = minecraft("water");
        public static final Tag.Named<Fluid> SKIZZIK_CANDY_FLUIDS = forge("skizzik_candy_fluids");

        private static Tag.Named<Fluid> minecraft(String path) {
            return FluidTags.bind(new ResourceLocation("minecraft", path).toString());
        }

        private static Tag.Named<Fluid> forge(String path) {
            return FluidTags.bind(new ResourceLocation("forge", path).toString());
        }
    }
    public static final class Items {
        public static final Tag.Named<Item> SKIZZIK_COMMAND_BLOCKS = forge("skizzik_command_blocks");
        public static final Tag.Named<Item> SKIZZIK_HEADS = forge("skizzik_heads");

        public static final Tag.Named<Item> SKIZZIK_PANCAKES = forge("skizzik_pancakes");
        public static final Tag.Named<Item> SKIZZIK_CHOCOLATE = forge("skizzik_chocolate");
        public static final Tag.Named<Item> SKIZZIK_CHOCOLATE_BLOCKS = forge("skizzik_chocolate_blocks");

        public static final Tag.Named<Item> WOODEN_PRESSURE_PLATES = minecraft("wooden_pressure_plates");
        public static final Tag.Named<Item> BUTTONS = minecraft("buttons");
        public static final Tag.Named<Item> WOODEN_BUTTONS = minecraft("wooden_buttons");

        public static final Tag.Named<Item> SIGNS = minecraft("signs");

        public static final Tag.Named<Item> PLANKS = minecraft("planks");
        public static final Tag.Named<Item> SLABS = minecraft("slabs");
        public static final Tag.Named<Item> WOODEN_SLABS = minecraft("wooden_slabs");
        public static final Tag.Named<Item> STAIRS = minecraft("stairs");
        public static final Tag.Named<Item> WOODEN_STAIRS = minecraft("wooden_stairs");

        public static final Tag.Named<Item> FENCES = minecraft("fences");
        public static final Tag.Named<Item> WOODEN_FENCES = minecraft("wooden_fences");

        public static final Tag.Named<Item> TRAPDOORS = minecraft("trapdoors");
        public static final Tag.Named<Item> WOODEN_TRAPDOORS = minecraft("wooden_trapdoors");
        public static final Tag.Named<Item> DOORS = minecraft("doors");
        public static final Tag.Named<Item> WOODEN_DOORS = minecraft("wooden_doors");

        public static final Tag.Named<Item> LEAVES = minecraft("leaves");
        public static final Tag.Named<Item> LOGS = minecraft("logs");
        public static final Tag.Named<Item> LOGS_THAT_BURN = minecraft("logs_that_burn");
        public static final Tag.Named<Item> CANDY_LOGS = forge("candy_logs");

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
    }
}
