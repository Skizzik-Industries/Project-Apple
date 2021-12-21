package com.skizzium.projectapple.init.block;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.block.*;
import com.skizzium.projectapple.block.heads.SkizzikHead;
import com.skizzium.projectapple.block.heads.SkizzikHeadWithGems;
import com.skizzium.projectapple.block.heads.SkizzikWallHead;
import com.skizzium.projectapple.block.heads.SkizzikWallHeadWithGems;
import com.skizzium.projectapple.block.heads.small.SmallSkizzikHead;
import com.skizzium.projectapple.block.heads.small.SmallSkizzikHeadWithGems;
import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_Blocks {
    public static final RegistryObject<Block> COMMAND_BLOCK = register("command_block", () -> new CommandBlock(PushReaction.BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(65.0F,3_600_000.0F).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel((blockstate) -> 4).emissiveRendering(PA_Blocks::always)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.EPIC, false);
    public static final RegistryObject<Block> DEACTIVATED_COMMAND_BLOCK = register("deactivated_command_block", () -> new CommandBlock(PushReaction.BLOCK, BlockBehaviour.Properties.of(Material.METAL).strength(65.0F,3_600_000.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.RARE, false);
    public static final RegistryObject<Block> BROKEN_COMMAND_BLOCK = register("broken_command_block", () -> new CommandBlock(PushReaction.NORMAL, BlockBehaviour.Properties.of(Material.METAL).strength(45.0F,1200.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.UNCOMMON, false);

    public static final RegistryObject<Block> SMALL_FRIENDLY_SKIZZIK_HEAD = registerNoItem("small_friendly_skizzik_head", () -> new SmallSkizzikHead(PA_TileEntities.CustomSkullTypes.SMALL_FRIENDLY_SKIZZIK, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> SMALL_FRIENDLY_SKIZZIK_WALL_HEAD = registerNoItem("small_friendly_skizzik_wall_head", () -> new SmallSkizzikHead(PA_TileEntities.CustomSkullTypes.SMALL_FRIENDLY_SKIZZIK, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(SMALL_FRIENDLY_SKIZZIK_HEAD.get())));
    public static final RegistryObject<Block> SMALL_FRIENDLY_SKIZZIK_HEAD_WITH_GEMS = registerNoItem("small_friendly_skizzik_head_with_gems", () -> new SmallSkizzikHead(PA_TileEntities.CustomSkullTypes.SMALL_FRIENDLY_SKIZZIK_WITH_GEMS, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> SMALL_FRIENDLY_SKIZZIK_WALL_HEAD_WITH_GEMS = registerNoItem("small_friendly_skizzik_wall_head_with_gems", () -> new SmallSkizzikHead(PA_TileEntities.CustomSkullTypes.SMALL_FRIENDLY_SKIZZIK_WITH_GEMS, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(SMALL_FRIENDLY_SKIZZIK_HEAD_WITH_GEMS.get())));

    public static final RegistryObject<Block> FRIENDLY_SKIZZIK_HEAD = registerNoItem("friendly_skizzik_head", () -> new SkizzikHead(PA_TileEntities.CustomSkullTypes.FRIENDLY_SKIZZIK, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> FRIENDLY_SKIZZIK_WALL_HEAD = registerNoItem("friendly_skizzik_wall_head", () -> new SkizzikWallHead(PA_TileEntities.CustomSkullTypes.FRIENDLY_SKIZZIK, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(FRIENDLY_SKIZZIK_HEAD.get())));
    public static final RegistryObject<Block> FRIENDLY_SKIZZIK_HEAD_WITH_GEMS = registerNoItem("friendly_skizzik_head_with_gems", () -> new SkizzikHead(PA_TileEntities.CustomSkullTypes.FRIENDLY_SKIZZIK_WITH_GEMS, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> FRIENDLY_SKIZZIK_WALL_HEAD_WITH_GEMS = registerNoItem("friendly_skizzik_wall_head_with_gems", () -> new SkizzikWallHead(PA_TileEntities.CustomSkullTypes.FRIENDLY_SKIZZIK_WITH_GEMS, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(FRIENDLY_SKIZZIK_HEAD_WITH_GEMS.get())));

    public static final RegistryObject<Block> SMALL_SKIZZIK_HEAD = registerNoItem("small_skizzik_head", () -> new SmallSkizzikHead(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> SMALL_SKIZZIK_WALL_HEAD = registerNoItem("small_skizzik_wall_head", () -> new SmallSkizzikHead(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(SMALL_SKIZZIK_HEAD.get())));
    public static final RegistryObject<Block> SMALL_SKIZZIK_HEAD_WITH_GEMS = registerNoItem("small_skizzik_head_with_gems", () -> new SmallSkizzikHeadWithGems(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK_WITH_GEMS, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> SMALL_SKIZZIK_WALL_HEAD_WITH_GEMS = registerNoItem("small_skizzik_wall_head_with_gems", () -> new SmallSkizzikHeadWithGems(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK_WITH_GEMS, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(SMALL_SKIZZIK_HEAD_WITH_GEMS.get())));
    
    public static final RegistryObject<Block> SKIZZIK_HEAD = registerNoItem("skizzik_head", () -> new SkizzikHead(PA_TileEntities.CustomSkullTypes.SKIZZIK, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> SKIZZIK_WALL_HEAD = registerNoItem("skizzik_wall_head", () -> new SkizzikWallHead(PA_TileEntities.CustomSkullTypes.SKIZZIK, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(SKIZZIK_HEAD.get())));
    public static final RegistryObject<Block> SKIZZIK_HEAD_WITH_GEMS = registerNoItem("skizzik_head_with_gems", () -> new SkizzikHeadWithGems(PA_TileEntities.CustomSkullTypes.SKIZZIK_WITH_GEMS, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> SKIZZIK_WALL_HEAD_WITH_GEMS = registerNoItem("skizzik_wall_head_with_gems", () -> new SkizzikWallHeadWithGems(PA_TileEntities.CustomSkullTypes.SKIZZIK_WITH_GEMS, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(SKIZZIK_HEAD_WITH_GEMS.get())));

    public static final RegistryObject<Block> FRIENDLY_SKIZZIK_FLESH_BLOCK = register("friendly_skizzik_flesh_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_BLUE).strength(0.8F,0.8F).sound(SoundType.SLIME_BLOCK).emissiveRendering(PA_Blocks::always)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.UNCOMMON, true);
    public static final RegistryObject<Block> SKIZZIK_FLESH_BLOCK = register("skizzik_flesh_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(0.8F,0.8F).sound(SoundType.SLIME_BLOCK).emissiveRendering(PA_Blocks::always)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.UNCOMMON, true);

    public static final RegistryObject<SkizzieStatue> SKIZZIE_STATUE = register("skizzie_statue", () -> new SkizzieStatue(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F,6.0F).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.UNCOMMON, false);
    public static final RegistryObject<SkizzieStatue> SPOOKZIE_STATUE = register("spookzie_statue", () -> new SkizzieStatue(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F,6.0F).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.UNCOMMON, false);

    public static final RegistryObject<Block> CORRUPTED_BLOCK = register("corrupted_block", () -> new CorruptedBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PINK).strength(1.5F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel((blockstate) -> 3).emissiveRendering(PA_Blocks::always)), PA_Registry.MAIN_SKIZZIK_TAB, PA_Items.corruptedRarity, false);
    public static final RegistryObject<FallingBlock> SKIZZIK_LOOT_BAG = register("skizzik_loot_bag", () -> new SkizzikLootBag(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(0F,3_600_000.0F).noOcclusion().sound(SoundType.WOOL).emissiveRendering(PA_Blocks::always).lightLevel((blockstate) -> 16)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.EPIC, true);
    
    public static final RegistryObject<Block> RAINBOW_ORE = register("rainbow_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F,3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel((blockstate) -> 3), UniformInt.of(2, 9)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.EPIC, false);

    public static final RegistryObject<Block> RAINBOW_GEM_BLOCK = register("rainbow_gem_block", () -> new RainbowGemBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_PURPLE).strength(50.0F,1200.0F).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel((blockstate) -> 7).emissiveRendering(PA_Blocks::always)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.EPIC, true);
    public static final RegistryObject<Block> BLACK_GEM_BLOCK = register("black_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(5.0F,6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> BLUE_GEM_BLOCK = register("blue_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLUE).strength(5.0F,6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> BROWN_GEM_BLOCK = register("brown_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).strength(5.0F,6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> YELLOW_GEM_BLOCK = register("yellow_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW).strength(5.0F,6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> ORANGE_GEM_BLOCK = register("orange_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_ORANGE).strength(5.0F,6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> GREEN_GEM_BLOCK = register("green_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN).strength(5.0F,6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> PINK_GEM_BLOCK = register("pink_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_PINK).strength(5.0F,6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);

    public static void register() {}

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block){
        return PA_Registry.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, CreativeModeTab group, Rarity rarity, boolean isItemFireResistent){
        RegistryObject<T> object = registerNoItem(name, block);
        if(isItemFireResistent) {
            PA_Registry.ITEMS.register(name, () -> new BlockItem(object.get(), new Item.Properties().tab(group).rarity(rarity).fireResistant()));
        }
        else {
            PA_Registry.ITEMS.register(name, () -> new BlockItem(object.get(), new Item.Properties().tab(group).rarity(rarity)));
        }
        return object;
    }

    private static boolean always(BlockState blockstate, BlockGetter iblockreader, BlockPos blockpos) {
        return true;
    }
    private static boolean never(BlockState blockstate, BlockGetter iblockreader, BlockPos blockpos) { return false; }
}
