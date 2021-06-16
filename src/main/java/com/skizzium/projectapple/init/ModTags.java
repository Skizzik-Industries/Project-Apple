package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;

public final class ModTags {
    public static final class Blocks {
        public static final ITag.INamedTag<Block> SKIZZIK_COMMAND_BLOCKS = forge("skizzik_command_blocks");

        public static final ITag.INamedTag<Block> RAINBOW_SWORD_IMMUNE = forge("rainbow_sword_immune");
        public static final ITag.INamedTag<Block> DRAGON_IMMUNE = minecraft("dragon_immune");
        public static final ITag.INamedTag<Block> WITHER_IMMUNE = minecraft("wither_immune");

        public static final ITag.INamedTag<Block> SKIZZIK_CHOCOLATE_BLOCKS = forge("skizzik_chocolate_blocks");

        public static final ITag.INamedTag<Block> PRESSURE_PLATES = minecraft("pressure_plates");
        public static final ITag.INamedTag<Block> WALL_POST_OVERRIDE = minecraft("wall_post_override");
        public static final ITag.INamedTag<Block> WOODEN_PRESSURE_PLATES = minecraft("wooden_pressure_plates");

        public static final ITag.INamedTag<Block> BUTTONS = minecraft("buttons");
        public static final ITag.INamedTag<Block> WOODEN_BUTTONS = minecraft("wooden_buttons");

        public static final ITag.INamedTag<Block> PLANKS = minecraft("planks");
        public static final ITag.INamedTag<Block> SLABS = minecraft("slabs");
        public static final ITag.INamedTag<Block> WOODEN_SLABS = minecraft("wooden_slabs");
        public static final ITag.INamedTag<Block> STAIRS = minecraft("stairs");
        public static final ITag.INamedTag<Block> WOODEN_STAIRS = minecraft("wooden_stairs");

        public static final ITag.INamedTag<Block> FENCES = minecraft("fences");
        public static final ITag.INamedTag<Block> WOODEN_FENCES = minecraft("wooden_fences");
        public static final ITag.INamedTag<Block> FENCE_GATES = minecraft("fence_gates");
        public static final ITag.INamedTag<Block> UNSTABLE_BOTTOM_CENTER = minecraft("unstable_bottom_center");

        public static final ITag.INamedTag<Block> TRAPDOORS = minecraft("trapdoors");
        public static final ITag.INamedTag<Block> WOODEN_TRAPDOORS = minecraft("wooden_trapdoors");
        public static final ITag.INamedTag<Block> DOORS = minecraft("doors");
        public static final ITag.INamedTag<Block> WOODEN_DOORS = minecraft("wooden_doors");

        public static final ITag.INamedTag<Block> LEAVES = minecraft("leaves");
        public static final ITag.INamedTag<Block> ENDERMAN_HOLDABLE = minecraft("enderman_holdable");
        public static final ITag.INamedTag<Block> VALID_SPAWN = minecraft("valid_spawn");

        public static final ITag.INamedTag<Block> LOGS = minecraft("logs");
        public static final ITag.INamedTag<Block> LOGS_THAT_BURN = minecraft("logs_that_burn");
        public static final ITag.INamedTag<Block> CANDY_LOGS = forge("candy_logs");

        public static final ITag.INamedTag<Block> BEACON_BASE_BLOCKS = minecraft("beacon_base_blocks");

        private static ITag.INamedTag<Block> minecraft(String path) {
            return BlockTags.bind(new ResourceLocation("minecraft", path).toString());
        }

        private static ITag.INamedTag<Block> forge(String path) {
            return BlockTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Block> mod(String path) {
            return BlockTags.bind(new ResourceLocation(ProjectApple.MOD_ID, path).toString());
        }
    }
    public static final class Fluids {
        public static final ITag.INamedTag<Fluid> WATER = minecraft("water");
        public static final ITag.INamedTag<Fluid> CANDY_FLUIDS = mod("candy_fluids");

        private static ITag.INamedTag<Fluid> minecraft(String path) {
            return FluidTags.bind(new ResourceLocation("minecraft", path).toString());
        }

        private static ITag.INamedTag<Fluid> mod(String path) {
            return FluidTags.bind(new ResourceLocation(ProjectApple.MOD_ID, path).toString());
        }
    }
    public static final class Items {
        public static final ITag.INamedTag<Item> SKIZZIK_COMMAND_BLOCKS = forge("skizzik_command_blocks");

        public static final ITag.INamedTag<Item> SKIZZIK_PANCAKES = forge("skizzik_pancakes");
        public static final ITag.INamedTag<Item> SKIZZIK_CHOCOLATE = forge("skizzik_chocolate");
        public static final ITag.INamedTag<Item> SKIZZIK_CHOCOLATE_BLOCKS = forge("skizzik_chocolate_blocks");

        public static final ITag.INamedTag<Item> WOODEN_PRESSURE_PLATES = minecraft("wooden_pressure_plates");
        public static final ITag.INamedTag<Item> BUTTONS = minecraft("buttons");
        public static final ITag.INamedTag<Item> WOODEN_BUTTONS = minecraft("wooden_buttons");

        public static final ITag.INamedTag<Item> PLANKS = minecraft("planks");
        public static final ITag.INamedTag<Item> SLABS = minecraft("slabs");
        public static final ITag.INamedTag<Item> WOODEN_SLABS = minecraft("wooden_slabs");
        public static final ITag.INamedTag<Item> STAIRS = minecraft("stairs");
        public static final ITag.INamedTag<Item> WOODEN_STAIRS = minecraft("wooden_stairs");

        public static final ITag.INamedTag<Item> FENCES = minecraft("fences");
        public static final ITag.INamedTag<Item> WOODEN_FENCES = minecraft("wooden_fences");

        public static final ITag.INamedTag<Item> TRAPDOORS = minecraft("trapdoors");
        public static final ITag.INamedTag<Item> WOODEN_TRAPDOORS = minecraft("wooden_trapdoors");
        public static final ITag.INamedTag<Item> DOORS = minecraft("doors");
        public static final ITag.INamedTag<Item> WOODEN_DOORS = minecraft("wooden_doors");

        public static final ITag.INamedTag<Item> LEAVES = minecraft("leaves");
        public static final ITag.INamedTag<Item> LOGS = minecraft("logs");
        public static final ITag.INamedTag<Item> LOGS_THAT_BURN = minecraft("logs_that_burn");
        public static final ITag.INamedTag<Item> CANDY_LOGS = mod("candy_logs");

        public static final ITag.INamedTag<Item> BEACON_PAYMENT_ITEMS = minecraft("beacon_payment_items");
        public static final ITag.INamedTag<Item> BEACON_BASE_BLOCKS = minecraft("beacon_base_blocks");

        public static final ITag.INamedTag<Item> ALL_GEMS = mod("all_gems");
        public static final ITag.INamedTag<Item> BASE_GEMS = mod("base_gems");

        private static ITag.INamedTag<Item> minecraft(String path) {
            return ItemTags.bind(new ResourceLocation("minecraft", path).toString());
        }

        private static ITag.INamedTag<Item> forge(String path) {
            return ItemTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Item> mod(String path) {
            return ItemTags.bind(new ResourceLocation(ProjectApple.MOD_ID, path).toString());
        }
    }
}
