package com.skizzium.projectapple.init.block;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.block.*;
import com.skizzium.projectapple.init.PA_Entities;
import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.init.PA_TileEntities;
import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.dispenser.OptionalDispenseBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_Blocks {
    private static WoodType CANDY_WOOD_TYPE = WoodType.create("candy");
    
    public static final RegistryObject<Block> COMMAND_BLOCK = register("command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(65.0F,3_600_000.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel((blockstate) -> 4).emissiveRendering(PA_Blocks::always)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.EPIC, false);
    public static final RegistryObject<Block> DEACTIVATED_COMMAND_BLOCK = register("deactivated_command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(65.0F,3_600_000.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.RARE, false);
    public static final RegistryObject<Block> BROKEN_COMMAND_BLOCK = register("broken_command_block", () -> new CommandBlock(AbstractBlock.Properties.of(Material.METAL).strength(45.0F,1200.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.UNCOMMON, false);

    public static final RegistryObject<Block> SMALL_SKIZZIK_HEAD = registerNoItem("small_skizzik_head", () -> new SmallSkizzikHead(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK, AbstractBlock.Properties.of(Material.DECORATION).strength(1.0F)));
    //public static final RegistryObject<Block> SMALL_SKIZZIK_WALL_HEAD = registerNoItem("small_skizzik_wall_head", () -> new SmallSkizzikWallHead(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK, AbstractBlock.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(SMALL_SKIZZIK_HEAD.get())));
    public static final RegistryObject<Block> SMALL_SKIZZIK_HEAD_WITH_GEMS = registerNoItem("small_skizzik_head_with_gems", () -> new SmallSkizzikHead(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK_WITH_GEMS, AbstractBlock.Properties.of(Material.DECORATION).strength(1.0F)));
    //public static final RegistryObject<Block> SMALL_SKIZZIK_WALL_HEAD_WITH_GEMS = registerNoItem("small_skizzik_wall_head_with_gems", () -> new SmallSkizzikWallHead(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK_WITH_GEMS, AbstractBlock.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(SMALL_SKIZZIK_HEAD_WITH_GEMS.get())));

    public static final RegistryObject<Block> SKIZZIK_HEAD = registerNoItem("skizzik_head", () -> new SkizzikHead(PA_TileEntities.CustomSkullTypes.SKIZZIK, AbstractBlock.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> SKIZZIK_WALL_HEAD = registerNoItem("skizzik_wall_head", () -> new SkizzikWallHead(PA_TileEntities.CustomSkullTypes.SKIZZIK, AbstractBlock.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(SKIZZIK_HEAD.get())));
    public static final RegistryObject<Block> SKIZZIK_HEAD_WITH_GEMS = registerNoItem("skizzik_head_with_gems", () -> new SkizzikHeadWithGems(PA_TileEntities.CustomSkullTypes.SKIZZIK_WITH_GEMS, AbstractBlock.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> SKIZZIK_WALL_HEAD_WITH_GEMS = registerNoItem("skizzik_wall_head_with_gems", () -> new SkizzikWallHeadWithGems(PA_TileEntities.CustomSkullTypes.SKIZZIK_WITH_GEMS, AbstractBlock.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(SKIZZIK_HEAD_WITH_GEMS.get())));

    public static final RegistryObject<Block> SKIZZIK_FLESH_BLOCK = register("skizzik_flesh_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(0.8F,0.8F).harvestTool(ToolType.HOE).sound(SoundType.SLIME_BLOCK).emissiveRendering(PA_Blocks::always)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.UNCOMMON, true);

    public static final RegistryObject<Block> CORRUPTED_BLOCK = register("corrupted_block", () -> new CorruptedBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_PINK).strength(1.5f, 6f).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel((blockstate) -> 3).emissiveRendering(PA_Blocks::always)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.create("CORRUPTED", TextFormatting.OBFUSCATED), false);
    public static final RegistryObject<SkizzieStatue> SKIZZIE_STATUE = register("skizzie_statue", () -> new SkizzieStatue(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F,6.0F).noOcclusion().sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(0).requiresCorrectToolForDrops()), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.UNCOMMON, false);
    public static final RegistryObject<FallingBlock> SKIZZIK_LOOT_BAG = register("skizzik_loot_bag", () -> new SkizzikLootBag(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(0F,3_600_000.0F).noOcclusion().sound(SoundType.WOOL).emissiveRendering(PA_Blocks::always).lightLevel((blockstate) -> 16)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.EPIC, true);

    public static final RegistryObject<Block> CANDY_CANE = registerNoItem("candy_cane", () -> new CandyCane(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.CRIMSON_STEM).noCollission().randomTicks().instabreak().sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<FlowingFluidBlock> MAPLE_SYRUP = registerNoItem("maple_syrup", () -> new FlowingFluidBlock(PA_Fluids.MAPLE_SYRUP, AbstractBlock.Properties.of(Material.WATER, MaterialColor.TERRACOTTA_RED).noCollission().strength(100.0F).noDrops()));
    public static final RegistryObject<Block> WAFFLE_BLOCK = register("waffle_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_YELLOW).strength(0.2F,0.2F).harvestTool(ToolType.HOE).sound(SoundType.SNOW)), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<Block> WHITE_CHOCOLATE_BLOCK = register("white_chocolate_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).strength(1.5F,1.5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> CHOCOLATE_BLOCK = register("chocolate_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.PODZOL).strength(1.5F,1.5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> DARK_CHOCOLATE_BLOCK = register("dark_chocolate_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_GRAY).strength(1.5F,1.5F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<Block> CANDIANITE_ORE = register("candianite_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).strength(3.0F,3.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<Block> CANDY_PLANKS = register("candy_planks", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.CRIMSON_STEM).strength(2.0F,3.0F).harvestTool(ToolType.AXE).sound(SoundType.SLIME_BLOCK)), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<WoodButtonBlock> CANDY_BUTTON = register("candy_button", () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.DECORATION, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(0.5F).sound(SoundType.SLIME_BLOCK).noCollission()), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<PressurePlateBlock> CANDY_PRESSURE_PLATE = register("candy_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(0.5F).sound(SoundType.SLIME_BLOCK).noCollission()), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<StandingSignBlock> CANDY_SIGN = registerNoItem("candy_sign", () -> new SkizzikSign(AbstractBlock.Properties.of(Material.WOOD, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.SLIME_BLOCK), CANDY_WOOD_TYPE));
    public static final RegistryObject<WallSignBlock> CANDY_WALL_SIGN = registerNoItem("candy_wall_sign", () -> new SkizzikWallSign(AbstractBlock.Properties.of(Material.WOOD, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.SLIME_BLOCK).dropsLike(CANDY_SIGN.get()), CANDY_WOOD_TYPE));

    public static final RegistryObject<SlabBlock> CANDY_SLAB = register("candy_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(CANDY_PLANKS.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<StairsBlock> CANDY_STAIRS = register("candy_stairs", () -> new StairsBlock(CANDY_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(CANDY_PLANKS.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<FenceBlock> CANDY_FENCE = register("candy_fence", () -> new FenceBlock(AbstractBlock.Properties.copy(CANDY_PLANKS.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<FenceGateBlock> CANDY_FENCE_GATE = register("candy_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.copy(CANDY_PLANKS.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<TrapDoorBlock> CANDY_TRAPDOOR = register("candy_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(3.0F).harvestTool(ToolType.AXE).sound(SoundType.SLIME_BLOCK).noOcclusion()), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<DoorBlock> CANDY_DOOR = register("candy_door", () -> new DoorBlock(AbstractBlock.Properties.copy(CANDY_TRAPDOOR.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<RotatedPillarBlock> CANDY_LOG = register("candy_log", () -> new PA_StrippableLog(AbstractBlock.Properties.of(Material.WOOD, (rotation) -> rotation.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor() : MaterialColor.CRIMSON_NYLIUM).strength(2.0F,2.0F).harvestTool(ToolType.AXE).sound(SoundType.SLIME_BLOCK)), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_CANDY_LOG = register("stripped_candy_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(CANDY_LOG.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<RotatedPillarBlock> CANDY_WOOD = register("candy_wood", () -> new PA_StrippableLog(AbstractBlock.Properties.copy(CANDY_LOG.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_CANDY_WOOD = register("stripped_candy_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(CANDY_LOG.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<LeavesBlock> CANDY_LEAVES = register("candy_leaves", () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(0.2F,0.2F).sound(SoundType.SLIME_BLOCK).randomTicks().isViewBlocking(PA_Blocks::never).isSuffocating(PA_Blocks::never).noOcclusion()), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> CANDY_NYLIUM = register("candy_nylium", () -> new CandyNylium(AbstractBlock.Properties.of(Material.STONE, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(0.4F,0.4F).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(SoundType.SLIME_BLOCK).randomTicks()), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> CANDYRACK = register("candyrack", () -> new Block(AbstractBlock.Properties.of(Material.STONE, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(0.4F,0.4F).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(SoundType.SLIME_BLOCK).requiresCorrectToolForDrops()), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<Block> RAINBOW_ORE = register("rainbow_ore", () -> new RainbowOre(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).strength(3.0F,3.0F).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel((blockstate) -> {return 3;} )), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.EPIC, false);

    public static final RegistryObject<Block> RAINBOW_GEM_BLOCK = register("rainbow_gem_block", () -> new RainbowGemBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_PURPLE).strength(55.0F,1200.0F).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel((blockstate) -> {return 7;} ).emissiveRendering(PA_Blocks::always)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.EPIC, true);
    public static final RegistryObject<Block> BLACK_GEM_BLOCK = register("black_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> BLUE_GEM_BLOCK = register("blue_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLUE).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> BROWN_GEM_BLOCK = register("brown_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> YELLOW_GEM_BLOCK = register("yellow_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> ORANGE_GEM_BLOCK = register("orange_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_ORANGE).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> GREEN_GEM_BLOCK = register("green_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> PINK_GEM_BLOCK = register("pink_gem_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_PINK).strength(5.0F,6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);

    public static void register() {}

    public static void registerOtherStuff(final FMLClientSetupEvent event) {
        ComposterBlock.COMPOSTABLES.put(CANDY_CANE.get(), 0.5F);

        WoodType.register(PA_Blocks.CANDY_WOOD_TYPE);

        IDispenseItemBehavior fluidDispenseBehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            public ItemStack execute(IBlockSource source, ItemStack item) {
                BucketItem bucket = (BucketItem)item.getItem();
                BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
                World world = source.getLevel();

                if (bucket.emptyBucket(null, world, pos, null)) {
                    bucket.checkExtraContent(world, item, pos);
                    return new ItemStack(Items.BUCKET);
                }
                else {
                    return this.defaultDispenseItemBehavior.dispense(source, item);
                }
            }
        };

        DispenserBlock.registerBehavior(PA_Items.MAPLE_SYRUP_BUCKET.get(), fluidDispenseBehavior);

        DefaultDispenseItemBehavior entityDispenseBehavior = new DefaultDispenseItemBehavior() {
            public ItemStack execute(IBlockSource source, ItemStack item) {
                Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                EntityType<?> entity = ((SpawnEggItem)item.getItem()).getType(item.getTag());

                entity.spawn(source.getLevel(), item, null, source.getPos().relative(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
                item.shrink(1);

                return item;
            }
        };

        for(SpawnEggItem egg : SpawnEggItem.eggs()) {
            DispenserBlock.registerBehavior(egg, entityDispenseBehavior);
        }

        event.enqueueWork(() -> {
            Atlases.addWoodType(PA_Blocks.CANDY_WOOD_TYPE);
        });
    }

    public static void renderLayers(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(SKIZZIK_LOOT_BAG.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(SKIZZIE_STATUE.get(), RenderType.cutoutMipped());

        RenderTypeLookup.setRenderLayer(CANDY_TRAPDOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CANDY_DOOR.get(), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(CANDY_CANE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(PA_Fluids.MAPLE_SYRUP.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(PA_Fluids.FLOWING_MAPLE_SYRUP.get(), RenderType.translucent());
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block){
        return PA_Registry.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, ItemGroup group, Rarity rarity, boolean isItemFireResistent){
        RegistryObject<T> object = registerNoItem(name, block);
        if(isItemFireResistent) {
            PA_Registry.ITEMS.register(name, () -> new BlockItem(object.get(), new Item.Properties().tab(group).rarity(rarity).fireResistant()));
        }
        else {
            PA_Registry.ITEMS.register(name, () -> new BlockItem(object.get(), new Item.Properties().tab(group).rarity(rarity)));
        }
        return object;
    }

    private static boolean always(BlockState blockstate, IBlockReader iblockreader, BlockPos blockpos) {
        return true;
    }
    private static boolean never(BlockState blockstate, IBlockReader iblockreader, BlockPos blockpos) { return false; }
}
