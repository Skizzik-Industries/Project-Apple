package xyz.skizzikindustries.projectapple.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;
import xyz.skizzikindustries.projectapple.Register;

import java.util.function.Supplier;

public class ModItems {
    public static final RegistryObject<Item> SKIZZIK_BONE = Register.ITEMS.register("skizzik_bone", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).fireResistant().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> RAW_SKIZZIK_FLESH = Register.ITEMS.register("raw_skizzik_flesh", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SKIZZIK_FLESH = Register.ITEMS.register("skizzik_flesh", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).fireResistant().rarity(Rarity.UNCOMMON)));

    public static void register() {}
}