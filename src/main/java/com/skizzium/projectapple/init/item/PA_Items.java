package com.skizzium.projectapple.init.item;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.PA_Entities;
import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.block.PA_Fluids;
import com.skizzium.projectapple.item.RainbowSword;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.tileentity.SkullTileEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_Items {
    public static final RegistryObject<WallOrFloorItem> SMALL_SKIZZIK_HEAD = PA_Registry.ITEMS.register("small_skizzik_head", () -> new WallOrFloorItem(PA_Blocks.SMALL_SKIZZIK_HEAD.get(), PA_Blocks.SMALL_SKIZZIK_HEAD.get(), new Item.Properties().rarity(Rarity.UNCOMMON).tab(PA_Registry.MAIN_SKIZZIK_TAB).fireResistant()));
    public static final RegistryObject<WallOrFloorItem> SMALL_SKIZZIK_HEAD_WITH_GEMS = PA_Registry.ITEMS.register("small_skizzik_head_with_gems", () -> new WallOrFloorItem(PA_Blocks.SMALL_SKIZZIK_HEAD_WITH_GEMS.get(), PA_Blocks.SMALL_SKIZZIK_HEAD_WITH_GEMS.get(), new Item.Properties().rarity(Rarity.RARE).tab(PA_Registry.MAIN_SKIZZIK_TAB).fireResistant()));
    public static final RegistryObject<WallOrFloorItem> SKIZZIK_HEAD = PA_Registry.ITEMS.register("skizzik_head", () -> new WallOrFloorItem(PA_Blocks.SKIZZIK_HEAD.get(), PA_Blocks.SKIZZIK_WALL_HEAD.get(), new Item.Properties().rarity(Rarity.UNCOMMON).tab(PA_Registry.MAIN_SKIZZIK_TAB).fireResistant()));
    public static final RegistryObject<WallOrFloorItem> SKIZZIK_HEAD_WITH_GEMS = PA_Registry.ITEMS.register("skizzik_head_with_gems", () -> new WallOrFloorItem(PA_Blocks.SKIZZIK_HEAD_WITH_GEMS.get(), PA_Blocks.SKIZZIK_WALL_HEAD_WITH_GEMS.get(), new Item.Properties().rarity(Rarity.RARE).tab(PA_Registry.MAIN_SKIZZIK_TAB).fireResistant()));

    public static final RegistryObject<Item> SKIZZIK_BONE = PA_Registry.ITEMS.register("skizzik_bone", () -> new Item(new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).fireResistant().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> RAW_SKIZZIK_FLESH = PA_Registry.ITEMS.register("raw_skizzik_flesh", () -> new Item(new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SKIZZIK_FLESH = PA_Registry.ITEMS.register("skizzik_flesh", () -> new Item(new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).fireResistant().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<ArmorItem> SKIZZIK_FLESH_CAP = PA_Registry.ITEMS.register("skizzik_flesh_cap", () -> new ArmorItem(PA_ArmorMaterials.SKIZZIK_FLESH, EquipmentSlotType.HEAD, new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).fireResistant().rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> SKIZZIK_FLESH_TUNIC = PA_Registry.ITEMS.register("skizzik_flesh_tunic", () -> new ArmorItem(PA_ArmorMaterials.SKIZZIK_FLESH, EquipmentSlotType.CHEST, new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).fireResistant().rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> SKIZZIK_FLESH_PANTS = PA_Registry.ITEMS.register("skizzik_flesh_pants", () -> new ArmorItem(PA_ArmorMaterials.SKIZZIK_FLESH, EquipmentSlotType.LEGS, new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).fireResistant().rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> SKIZZIK_FLESH_BOOTS = PA_Registry.ITEMS.register("skizzik_flesh_boots", () -> new ArmorItem(PA_ArmorMaterials.SKIZZIK_FLESH, EquipmentSlotType.FEET, new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).fireResistant().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> SALT = PA_Registry.ITEMS.register("salt", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> BUTTER = PA_Registry.ITEMS.register("butter", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    public static final RegistryObject<BlockItem> CANDY_CANE = PA_Registry.ITEMS.register("candy_cane", () -> new BlockItem(PA_Blocks.CANDY_CANE.get(), new Item.Properties().tab(PA_Registry.LIVING_CANDY_TAB).food(PA_Foods.CANDY_CANE)));
    public static final RegistryObject<SignItem> CANDY_SIGN = PA_Registry.ITEMS.register("candy_sign", () -> new SignItem((new Item.Properties()).stacksTo(16).tab(PA_Registry.LIVING_CANDY_TAB), PA_Blocks.CANDY_SIGN.get(), PA_Blocks.CANDY_WALL_SIGN.get()));

    public static final RegistryObject<BucketItem> MAPLE_SYRUP_BUCKET = PA_Registry.ITEMS.register("maple_syrup_bucket", () -> new BucketItem(PA_Fluids.MAPLE_SYRUP, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> WAFFLE = PA_Registry.ITEMS.register("waffle", () -> new Item(new Item.Properties().tab(PA_Registry.LIVING_CANDY_TAB).food(PA_Foods.WAFFLE)));

    public static final RegistryObject<Item> PANCAKES = PA_Registry.ITEMS.register("pancakes", () -> new Item(new Item.Properties().tab(PA_Registry.LIVING_CANDY_TAB).food(PA_Foods.PANCAKES)));
    public static final RegistryObject<Item> SYRUP_PANCAKES = PA_Registry.ITEMS.register("syrup_pancakes", () -> new Item(new Item.Properties().tab(PA_Registry.LIVING_CANDY_TAB).food(PA_Foods.SYRUP_PANCAKES)));

    public static final RegistryObject<Item> CHOCOLATE_BAR_WRAP = PA_Registry.ITEMS.register("chocolate_bar_wrap", () -> new Item(new Item.Properties().tab(PA_Registry.LIVING_CANDY_TAB)));
    public static final RegistryObject<Item> WHITE_CHOCOLATE_BAR = PA_Registry.ITEMS.register("white_chocolate_bar", () -> new Item(new Item.Properties().tab(PA_Registry.LIVING_CANDY_TAB).food(PA_Foods.CHOCOLATE_BAR)));
    public static final RegistryObject<Item> CHOCOLATE_BAR = PA_Registry.ITEMS.register("chocolate_bar", () -> new Item(new Item.Properties().tab(PA_Registry.LIVING_CANDY_TAB).food(PA_Foods.CHOCOLATE_BAR)));
    public static final RegistryObject<Item> DARK_CHOCOLATE_BAR = PA_Registry.ITEMS.register("dark_chocolate_bar", () -> new Item(new Item.Properties().tab(PA_Registry.LIVING_CANDY_TAB).food(PA_Foods.CHOCOLATE_BAR)));

    public static final RegistryObject<Item> CANDIANITE_NUGGET = PA_Registry.ITEMS.register("candianite_nugget", () -> new Item(new Item.Properties().tab(PA_Registry.LIVING_CANDY_TAB)));
    public static final RegistryObject<Item> CANDIANITE_INGOT = PA_Registry.ITEMS.register("candianite_ingot", () -> new Item(new Item.Properties().tab(PA_Registry.LIVING_CANDY_TAB)));

    public static final RegistryObject<SwordItem> CANDIANITE_SWORD = PA_Registry.ITEMS.register("candianite_sword", () -> new SwordItem(PA_Tiers.CANDIANITE, 3, -2.4F, (new Item.Properties().durability(350).tab(PA_Registry.LIVING_CANDY_TAB).rarity(Rarity.COMMON))));
    public static final RegistryObject<PickaxeItem> CANDIANITE_PICKAXE = PA_Registry.ITEMS.register("candianite_pickaxe", () -> new PickaxeItem(PA_Tiers.CANDIANITE, 3, -2.4F, (new Item.Properties().durability(350).tab(PA_Registry.LIVING_CANDY_TAB).fireResistant().rarity(Rarity.COMMON))));
    public static final RegistryObject<AxeItem> CANDIANITE_AXE = PA_Registry.ITEMS.register("candianite_axe", () -> new AxeItem(PA_Tiers.CANDIANITE, 3, -2.4F, (new Item.Properties().durability(350).tab(PA_Registry.LIVING_CANDY_TAB).fireResistant().rarity(Rarity.COMMON))));
    public static final RegistryObject<ShovelItem> CANDIANITE_SHOVEL = PA_Registry.ITEMS.register("candianite_shovel", () -> new ShovelItem(PA_Tiers.CANDIANITE, 3, -2.4F, (new Item.Properties().durability(350).tab(PA_Registry.LIVING_CANDY_TAB).fireResistant().rarity(Rarity.COMMON))));
    public static final RegistryObject<HoeItem> CANDIANITE_HOE = PA_Registry.ITEMS.register("candianite_hoe", () -> new HoeItem(PA_Tiers.CANDIANITE, 3, -2.4F, (new Item.Properties().durability(350).tab(PA_Registry.LIVING_CANDY_TAB).fireResistant().rarity(Rarity.COMMON))));

    public static final RegistryObject<HorseArmorItem> CANDIANITE_HORSE_ARMOR = PA_Registry.ITEMS.register("candianite_horse_armor", () -> new HorseArmorItem(5, "candianite", (new Item.Properties()).stacksTo(1).tab(PA_Registry.LIVING_CANDY_TAB)));
    public static final RegistryObject<ArmorItem> CANDIANITE_HELMET = PA_Registry.ITEMS.register("candianite_helmet", () -> new ArmorItem(PA_ArmorMaterials.CANDIANITE, EquipmentSlotType.HEAD, new Item.Properties().tab(PA_Registry.LIVING_CANDY_TAB).rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> CANDIANITE_CHESTPLATE = PA_Registry.ITEMS.register("candianite_chestplate", () -> new ArmorItem(PA_ArmorMaterials.CANDIANITE, EquipmentSlotType.CHEST, new Item.Properties().tab(PA_Registry.LIVING_CANDY_TAB).rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> CANDIANITE_LEGGINGS = PA_Registry.ITEMS.register("candianite_leggings", () -> new ArmorItem(PA_ArmorMaterials.CANDIANITE, EquipmentSlotType.LEGS, new Item.Properties().tab(PA_Registry.LIVING_CANDY_TAB).rarity(Rarity.COMMON)));
    public static final RegistryObject<ArmorItem> CANDIANITE_BOOTS = PA_Registry.ITEMS.register("candianite_boots", () -> new ArmorItem(PA_ArmorMaterials.CANDIANITE, EquipmentSlotType.FEET, new Item.Properties().tab(PA_Registry.LIVING_CANDY_TAB).rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> PLATINUM_NUGGET = PA_Registry.ITEMS.register("platinum_nugget", () -> new Item(new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).rarity(Rarity.create("PLATINUM", TextFormatting.BOLD))));
    public static final RegistryObject<Item> PLATINUM_INGOT = PA_Registry.ITEMS.register("platinum_ingot", () -> new Item(new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).fireResistant().rarity(Rarity.create("PLATINUM", TextFormatting.BOLD))));

    public static final RegistryObject<SwordItem> RAINBOW_SWORD = PA_Registry.ITEMS.register("rainbow_sword", () -> new RainbowSword(PA_Tiers.RAINBOW, 3, -2.4F, (new Item.Properties().durability(2100).tab(PA_Registry.MAIN_SKIZZIK_TAB).fireResistant().rarity(Rarity.EPIC))));

    public static final RegistryObject<Item> RAINBOW_GEM = PA_Registry.ITEMS.register("rainbow_gem", () -> new Item(new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> BLACK_GEM = PA_Registry.ITEMS.register("black_gem", () -> new Item(new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> BLUE_GEM = PA_Registry.ITEMS.register("blue_gem", () -> new Item(new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> BROWN_GEM = PA_Registry.ITEMS.register("brown_gem", () -> new Item(new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> YELLOW_GEM = PA_Registry.ITEMS.register("yellow_gem", () -> new Item(new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> ORANGE_GEM = PA_Registry.ITEMS.register("orange_gem", () -> new Item(new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> GREEN_GEM = PA_Registry.ITEMS.register("green_gem", () -> new Item(new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> PINK_GEM = PA_Registry.ITEMS.register("pink_gem", () -> new Item(new Item.Properties().tab(PA_Registry.MAIN_SKIZZIK_TAB).rarity(Rarity.COMMON)));

    public static void register() {}

    @SubscribeEvent
    public static void registerSpawnEggs(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(PA_Entities.CANDY_PIG_SPAWN_EGG);

        event.getRegistry().register(PA_Entities.FRIENDLY_SKIZZIE_SPAWN_EGG);
        event.getRegistry().register(PA_Entities.FRIENDLY_WITCH_SKIZZIE_SPAWN_EGG);

        event.getRegistry().register(PA_Entities.SKIZZIE_SPAWN_EGG);
        event.getRegistry().register(PA_Entities.KABOOM_SKIZZIE_SPAWN_EGG);
        //event.getRegistry().register(ModEntities.MINIGUN_SKIZZIE_SPAWN_EGG);
        event.getRegistry().register(PA_Entities.CORRUPTED_SKIZZIE_SPAWN_EGG);
        event.getRegistry().register(PA_Entities.WITCH_SKIZZIE_SPAWN_EGG);

        event.getRegistry().register(PA_Entities.SKIZZO_SPAWN_EGG);
    }
}