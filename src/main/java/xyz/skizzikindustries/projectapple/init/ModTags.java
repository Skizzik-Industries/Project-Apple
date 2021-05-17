package xyz.skizzikindustries.projectapple.init;

import net.minecraft.item.Item;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import xyz.skizzikindustries.projectapple.ProjectApple;

public final class ModTags {
    public static final class Items {
        public static final ITag.INamedTag<Item> ALL_GEMS = mod("items/fuck");

        private static ITag.INamedTag<Item> forge(String path) {
            return ItemTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Item> mod(String path) {
            return ItemTags.bind(new ResourceLocation(ProjectApple.MOD_ID, path).toString());
        }
    }
}
