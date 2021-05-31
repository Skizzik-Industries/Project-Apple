package xyz.skizzikindustries.projectapple.init;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import xyz.skizzikindustries.projectapple.ProjectApple;

public final class ModTags {
    public static final class Blocks {
        public static final ITag.INamedTag<Block> PLANKS = minecraft("planks");

        public static final ITag.INamedTag<Block> FENCES = minecraft("fences");
        public static final ITag.INamedTag<Block> WOODEN_FENCES = minecraft("wooden_fences");

        public static final ITag.INamedTag<Block> LOGS = minecraft("logs");
        public static final ITag.INamedTag<Block> LOGS_THAT_BURN = minecraft("logs_that_burn");
        public static final ITag.INamedTag<Block> CANDY_LOGS = mod("candy_logs");

        public static final ITag.INamedTag<Block> BEACON_BASE_BLOCKS = minecraft("beacon_base_blocks");

        private static ITag.INamedTag<Block> minecraft(String path) {
            return BlockTags.bind(new ResourceLocation("minecraft", path).toString());
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
        public static final ITag.INamedTag<Item> PLANKS = minecraft("planks");

        public static final ITag.INamedTag<Item> FENCES = minecraft("fences");
        public static final ITag.INamedTag<Item> WOODEN_FENCES = minecraft("wooden_fences");

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

        private static ITag.INamedTag<Item> mod(String path) {
            return ItemTags.bind(new ResourceLocation(ProjectApple.MOD_ID, path).toString());
        }
    }
}
