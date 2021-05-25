package xyz.skizzikindustries.projectapple.init.item;

import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.RegistryObject;
import xyz.skizzikindustries.projectapple.init.block.ModBlocks;
import xyz.skizzikindustries.projectapple.init.Register;
import xyz.skizzikindustries.projectapple.init.block.ModFluids;
import xyz.skizzikindustries.projectapple.item.RainbowSword;

public class ModItems {
    public static final RegistryObject<Item> SKIZZIK_BONE = Register.ITEMS.register("skizzik_bone", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).fireResistant().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> RAW_SKIZZIK_FLESH = Register.ITEMS.register("raw_skizzik_flesh", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SKIZZIK_FLESH = Register.ITEMS.register("skizzik_flesh", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).fireResistant().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<ArmorItem> SKIZZIK_FLESH_CAP = Register.ITEMS.register("skizzik_flesh_cap", () -> new ArmorItem(ModArmorMaterials.SKIZZIK_FLESH, EquipmentSlotType.HEAD, new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant().rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> SKIZZIK_FLESH_TUNIC = Register.ITEMS.register("skizzik_flesh_tunic", () -> new ArmorItem(ModArmorMaterials.SKIZZIK_FLESH, EquipmentSlotType.CHEST, new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant().rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> SKIZZIK_FLESH_PANTS = Register.ITEMS.register("skizzik_flesh_pants", () -> new ArmorItem(ModArmorMaterials.SKIZZIK_FLESH, EquipmentSlotType.LEGS, new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant().rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> SKIZZIK_FLESH_BOOTS = Register.ITEMS.register("skizzik_flesh_boots", () -> new ArmorItem(ModArmorMaterials.SKIZZIK_FLESH, EquipmentSlotType.FEET, new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> SALT = Register.ITEMS.register("salt", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> BUTTER = Register.ITEMS.register("butter", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    public static final RegistryObject<Item> CANDY_CANE = Register.ITEMS.register("candy_cane", () -> new BlockItem(ModBlocks.CANDY_CANE.get(), new Item.Properties().tab(ItemGroup.TAB_FOOD).food(ModFoods.CANDY_CANE)));

    public static final RegistryObject<BucketItem> MAPLE_SYRUP_BUCKET = Register.ITEMS.register("maple_syrup_bucket", () -> new BucketItem(ModFluids.MAPLE_SYRUP, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> WAFFLE = Register.ITEMS.register("waffle", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(ModFoods.WAFFLE)));

    public static final RegistryObject<Item> PANCAKES = Register.ITEMS.register("pancakes", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(ModFoods.PANCAKES)));
    public static final RegistryObject<Item> SYRUP_PANCAKES = Register.ITEMS.register("syrup_pancakes", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(ModFoods.SYRUP_PANCAKES)));

    public static final RegistryObject<Item> CHOCOLATE_BAR_WRAP = Register.ITEMS.register("chocolate_bar_wrap", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> WHITE_CHOCOLATE_BAR = Register.ITEMS.register("white_chocolate_bar", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(ModFoods.CHOCOLATE_BAR)));
    public static final RegistryObject<Item> CHOCOLATE_BAR = Register.ITEMS.register("chocolate_bar", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(ModFoods.CHOCOLATE_BAR)));
    public static final RegistryObject<Item> DARK_CHOCOLATE_BAR = Register.ITEMS.register("dark_chocolate_bar", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(ModFoods.CHOCOLATE_BAR)));

    public static final RegistryObject<Item> CANDY_STICK = Register.ITEMS.register("candy_stick", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    public static final RegistryObject<Item> CANDIANITE_NUGGET = Register.ITEMS.register("candianite_nugget", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> CANDIANITE_INGOT = Register.ITEMS.register("candianite_ingot", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    public static final RegistryObject<SwordItem> CANDIANITE_SWORD = Register.ITEMS.register("candianite_sword", () -> new SwordItem(ModTiers.CANDIANITE, 3, -2.4F, (new Item.Properties().durability(350).tab(ItemGroup.TAB_COMBAT).rarity(Rarity.COMMON))));
    public static final RegistryObject<PickaxeItem> CANDIANITE_PICKAXE = Register.ITEMS.register("candianite_pickaxe", () -> new PickaxeItem(ModTiers.CANDIANITE, 3, -2.4F, (new Item.Properties().durability(350).tab(ItemGroup.TAB_TOOLS).fireResistant().rarity(Rarity.COMMON))));
    public static final RegistryObject<AxeItem> CANDIANITE_AXE = Register.ITEMS.register("candianite_axe", () -> new AxeItem(ModTiers.CANDIANITE, 3, -2.4F, (new Item.Properties().durability(350).tab(ItemGroup.TAB_TOOLS).fireResistant().rarity(Rarity.COMMON))));
    public static final RegistryObject<ShovelItem> CANDIANITE_SHOVEL = Register.ITEMS.register("candianite_shovel", () -> new ShovelItem(ModTiers.CANDIANITE, 3, -2.4F, (new Item.Properties().durability(350).tab(ItemGroup.TAB_TOOLS).fireResistant().rarity(Rarity.COMMON))));
    public static final RegistryObject<HoeItem> CANDIANITE_HOE = Register.ITEMS.register("candianite_hoe", () -> new HoeItem(ModTiers.CANDIANITE, 3, -2.4F, (new Item.Properties().durability(350).tab(ItemGroup.TAB_TOOLS).fireResistant().rarity(Rarity.COMMON))));

    public static final RegistryObject<HorseArmorItem> CANDIANITE_HORSE_ARMOR = Register.ITEMS.register("candianite_horse_armor", () -> new HorseArmorItem(5, "candianite", (new Item.Properties()).stacksTo(1).tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<ArmorItem> CANDIANITE_HELMET = Register.ITEMS.register("candianite_helmet", () -> new ArmorItem(ModArmorMaterials.CANDIANITE, EquipmentSlotType.HEAD, new Item.Properties().tab(ItemGroup.TAB_COMBAT).rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> CANDIANITE_CHESTPLATE = Register.ITEMS.register("candianite_chestplate", () -> new ArmorItem(ModArmorMaterials.CANDIANITE, EquipmentSlotType.CHEST, new Item.Properties().tab(ItemGroup.TAB_COMBAT).rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> CANDIANITE_LEGGINGS = Register.ITEMS.register("candianite_leggings", () -> new ArmorItem(ModArmorMaterials.CANDIANITE, EquipmentSlotType.LEGS, new Item.Properties().tab(ItemGroup.TAB_COMBAT).rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> CANDIANITE_BOOTS = Register.ITEMS.register("candianite_boots", () -> new ArmorItem(ModArmorMaterials.CANDIANITE, EquipmentSlotType.FEET, new Item.Properties().tab(ItemGroup.TAB_COMBAT).rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> PLATINUM_NUGGET = Register.ITEMS.register("platinum_nugget", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).rarity(Rarity.create("PLATINUM", TextFormatting.BOLD))));
    public static final RegistryObject<Item> PLATINUM_INGOT = Register.ITEMS.register("platinum_ingot", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).fireResistant().rarity(Rarity.create("PLATINUM", TextFormatting.BOLD))));

    public static final RegistryObject<SwordItem> RAINBOW_SWORD = Register.ITEMS.register("rainbow_sword", () -> new RainbowSword(ModTiers.RAINBOW, 3, -2.4F, (new Item.Properties().durability(2100).tab(ItemGroup.TAB_COMBAT).fireResistant().rarity(Rarity.EPIC))));

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