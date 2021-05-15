package xyz.skizzikindustries.projectapple.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.RegistryObject;
import xyz.skizzikindustries.projectapple.Register;
import xyz.skizzikindustries.projectapple.item.materials.CandyArmorMaterial;
import xyz.skizzikindustries.projectapple.item.materials.RainbowTier;
import xyz.skizzikindustries.projectapple.item.materials.SkizzikFleshArmorMaterial;

public class ModItems {
    public static final IArmorMaterial SkizzikFleshArmorMaterial = new SkizzikFleshArmorMaterial();
    public static final IArmorMaterial CandyArmorMaterial = new CandyArmorMaterial();
    public static final IItemTier RAINBOW = new RainbowTier();

    public static final RegistryObject<Item> SKIZZIK_BONE = Register.ITEMS.register("skizzik_bone", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).fireResistant().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> RAW_SKIZZIK_FLESH = Register.ITEMS.register("raw_skizzik_flesh", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SKIZZIK_FLESH = Register.ITEMS.register("skizzik_flesh", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).fireResistant().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<ArmorItem> SKIZZIK_FLESH_CAP = Register.ITEMS.register("skizzik_flesh_cap", () -> new ArmorItem(SkizzikFleshArmorMaterial, EquipmentSlotType.HEAD, new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant().rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> SKIZZIK_FLESH_TUNIC = Register.ITEMS.register("skizzik_flesh_tunic", () -> new ArmorItem(SkizzikFleshArmorMaterial, EquipmentSlotType.CHEST, new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant().rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> SKIZZIK_FLESH_PANTS = Register.ITEMS.register("skizzik_flesh_pants", () -> new ArmorItem(SkizzikFleshArmorMaterial, EquipmentSlotType.LEGS, new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant().rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> SKIZZIK_FLESH_BOOTS = Register.ITEMS.register("skizzik_flesh_boots", () -> new ArmorItem(SkizzikFleshArmorMaterial, EquipmentSlotType.FEET, new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> CANDY_NUGGET  = Register.ITEMS.register("candy_nugget", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> CANDY_INGOT  = Register.ITEMS.register("candy_ingot", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    public static final RegistryObject<ArmorItem> CANDY_HELMET = Register.ITEMS.register("candy_helmet", () -> new ArmorItem(CandyArmorMaterial, EquipmentSlotType.HEAD, new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant().rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> CANDY_CHESTPLATE = Register.ITEMS.register("candy_chestplate", () -> new ArmorItem(CandyArmorMaterial, EquipmentSlotType.CHEST, new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant().rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> CANDY_LEGGINGS = Register.ITEMS.register("candy_leggings", () -> new ArmorItem(CandyArmorMaterial, EquipmentSlotType.LEGS, new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant().rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> CANDY_BOOTS = Register.ITEMS.register("candy_boots", () -> new ArmorItem(CandyArmorMaterial, EquipmentSlotType.FEET, new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> PLATINUM_NUGGET = Register.ITEMS.register("platinum_nugget", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).rarity(Rarity.create("PLATINUM", TextFormatting.BOLD))));
    public static final RegistryObject<Item> PLATINUM_INGOT = Register.ITEMS.register("platinum_ingot", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).fireResistant().rarity(Rarity.create("PLATINUM", TextFormatting.BOLD))));

    public static final RegistryObject<SwordItem> RAINBOW_SWORD = Register.ITEMS.register("rainbow_sword", () -> new RainbowSword(ModItems.RAINBOW, 3, -2.4F, (new Item.Properties().durability(2100).tab(ItemGroup.TAB_COMBAT).fireResistant().rarity(Rarity.COMMON))));

    public static final RegistryObject<Item> RAINBOW_GEM = Register.ITEMS.register("rainbow_gem", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> BLACK_GEM = Register.ITEMS.register("black_gem", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> BLUE_GEM = Register.ITEMS.register("blue_gem", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> BROWN_GEM = Register.ITEMS.register("brown_gem", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> YELLOW_GEM = Register.ITEMS.register("yellow_gem", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> ORANGE_GEM = Register.ITEMS.register("orange_gem", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> GREEN_GEM = Register.ITEMS.register("green_gem", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> PINK_GEM = Register.ITEMS.register("pink_gem", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).rarity(Rarity.COMMON)));

    public static void register() {}
}