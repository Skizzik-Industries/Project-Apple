package com.skizzium.projectapple.init.block;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.block.CommandBlock;
import com.skizzium.projectapple.block.*;
import com.skizzium.projectapple.init.PA_Registry;
import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_Blocks {
    private static WoodType CANDY_WOOD_TYPE = WoodType.create("candy");
    
    public static final RegistryObject<Block> COMMAND_BLOCK = register("command_block", () -> new CommandBlock(BlockBehaviour.Properties.of(Material.METAL).strength(65.0F,3_600_000.0F).harvestLevel(2).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel((blockstate) -> 4).emissiveRendering(PA_Blocks::always)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.EPIC, false);
    public static final RegistryObject<Block> DEACTIVATED_COMMAND_BLOCK = register("deactivated_command_block", () -> new CommandBlock(BlockBehaviour.Properties.of(Material.METAL).strength(65.0F,3_600_000.0F).harvestLevel(2).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.RARE, false);
    public static final RegistryObject<Block> BROKEN_COMMAND_BLOCK = register("broken_command_block", () -> new CommandBlock(BlockBehaviour.Properties.of(Material.METAL).strength(45.0F,1200.0F).harvestLevel(1).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.UNCOMMON, false);

    public static final RegistryObject<Block> SMALL_SKIZZIK_HEAD = registerNoItem("small_skizzik_head", () -> new SmallSkizzikHead(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> SMALL_SKIZZIK_HEAD_WITH_GEMS = registerNoItem("small_skizzik_head_with_gems", () -> new SmallSkizzikHead(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK_WITH_GEMS, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));

    public static final RegistryObject<Block> SKIZZIK_HEAD = registerNoItem("skizzik_head", () -> new SkizzikHead(PA_TileEntities.CustomSkullTypes.SKIZZIK, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> SKIZZIK_WALL_HEAD = registerNoItem("skizzik_wall_head", () -> new SkizzikWallHead(PA_TileEntities.CustomSkullTypes.SKIZZIK, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(SKIZZIK_HEAD.get())));
    public static final RegistryObject<Block> SKIZZIK_HEAD_WITH_GEMS = registerNoItem("skizzik_head_with_gems", () -> new SkizzikHeadWithGems(PA_TileEntities.CustomSkullTypes.SKIZZIK_WITH_GEMS, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F)));
    public static final RegistryObject<Block> SKIZZIK_WALL_HEAD_WITH_GEMS = registerNoItem("skizzik_wall_head_with_gems", () -> new SkizzikWallHeadWithGems(PA_TileEntities.CustomSkullTypes.SKIZZIK_WITH_GEMS, BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F).dropsLike(SKIZZIK_HEAD_WITH_GEMS.get())));

    public static final RegistryObject<Block> SKIZZIK_FLESH_BLOCK = register("skizzik_flesh_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(0.8F,0.8F).sound(SoundType.SLIME_BLOCK).emissiveRendering(PA_Blocks::always)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.UNCOMMON, true);

    public static final RegistryObject<Block> CORRUPTED_BLOCK = register("corrupted_block", () -> new CorruptedBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PINK).strength(1.5F, 6.0F).harvestLevel(1).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel((blockstate) -> 3).emissiveRendering(PA_Blocks::always)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.create("CORRUPTED", ChatFormatting.OBFUSCATED), false);
    public static final RegistryObject<SkizzieStatue> SKIZZIE_STATUE = register("skizzie_statue", () -> new SkizzieStatue(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F,6.0F).noOcclusion().sound(SoundType.STONE).harvestLevel(0).requiresCorrectToolForDrops()), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.UNCOMMON, false);
    public static final RegistryObject<FallingBlock> SKIZZIK_LOOT_BAG = register("skizzik_loot_bag", () -> new SkizzikLootBag(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(0F,3_600_000.0F).noOcclusion().sound(SoundType.WOOL).emissiveRendering(PA_Blocks::always).lightLevel((blockstate) -> 16)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.EPIC, true);

    public static final RegistryObject<Block> CANDY_CANE = registerNoItem("candy_cane", () -> new CandyCane(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.CRIMSON_STEM).noCollission().randomTicks().instabreak().sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<LiquidBlock> MAPLE_SYRUP = registerNoItem("maple_syrup", () -> new LiquidBlock(PA_Fluids.MAPLE_SYRUP, BlockBehaviour.Properties.of(Material.WATER, MaterialColor.TERRACOTTA_RED).noCollission().strength(100.0F).noDrops()));
    public static final RegistryObject<Block> WAFFLE_BLOCK = register("waffle_block", () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_YELLOW).strength(0.2F,0.2F).sound(SoundType.SNOW)), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<Block> WHITE_CHOCOLATE_BLOCK = register("white_chocolate_block", () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).strength(1.5F,1.5F).sound(SoundType.STONE)), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> CHOCOLATE_BLOCK = register("chocolate_block", () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.PODZOL).strength(1.5F,1.5F).sound(SoundType.STONE)), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> DARK_CHOCOLATE_BLOCK = register("dark_chocolate_block", () -> new Block(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_GRAY).strength(1.5F,1.5F).sound(SoundType.STONE)), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<Block> CANDIANITE_ORE = register("candianite_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(3.0F,3.0F).harvestLevel(0).requiresCorrectToolForDrops().sound(SoundType.STONE)), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<Block> CANDY_PLANKS = register("candy_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.CRIMSON_STEM).strength(2.0F,3.0F).sound(SoundType.SLIME_BLOCK)), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<WoodButtonBlock> CANDY_BUTTON = register("candy_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5F).sound(SoundType.SLIME_BLOCK).noCollission()), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<PressurePlateBlock> CANDY_PRESSURE_PLATE = register("candy_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(0.5F).sound(SoundType.SLIME_BLOCK).noCollission()), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<StandingSignBlock> CANDY_SIGN = registerNoItem("candy_sign", () -> new PA_Sign(BlockBehaviour.Properties.of(Material.WOOD, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.SLIME_BLOCK), CANDY_WOOD_TYPE));
    public static final RegistryObject<WallSignBlock> CANDY_WALL_SIGN = registerNoItem("candy_wall_sign", () -> new PA_WallSign(BlockBehaviour.Properties.of(Material.WOOD, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.SLIME_BLOCK).dropsLike(CANDY_SIGN.get()), CANDY_WOOD_TYPE));

    public static final RegistryObject<SlabBlock> CANDY_SLAB = register("candy_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CANDY_PLANKS.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<StairBlock> CANDY_STAIRS = register("candy_stairs", () -> new StairBlock(CANDY_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(CANDY_PLANKS.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<FenceBlock> CANDY_FENCE = register("candy_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(CANDY_PLANKS.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<FenceGateBlock> CANDY_FENCE_GATE = register("candy_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(CANDY_PLANKS.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<TrapDoorBlock> CANDY_TRAPDOOR = register("candy_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.SLIME_BLOCK).noOcclusion()), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<DoorBlock> CANDY_DOOR = register("candy_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(CANDY_TRAPDOOR.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<RotatedPillarBlock> CANDY_LOG = register("candy_log", () -> new PA_StrippableLog(BlockBehaviour.Properties.of(Material.WOOD, (rotation) -> rotation.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor() : MaterialColor.CRIMSON_NYLIUM).strength(2.0F,2.0F).sound(SoundType.SLIME_BLOCK)), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_CANDY_LOG = register("stripped_candy_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(CANDY_LOG.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<RotatedPillarBlock> CANDY_WOOD = register("candy_wood", () -> new PA_StrippableLog(BlockBehaviour.Properties.copy(CANDY_LOG.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_CANDY_WOOD = register("stripped_candy_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(CANDY_LOG.get())), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<LeavesBlock> CANDY_LEAVES = register("candy_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(0.2F,0.2F).sound(SoundType.SLIME_BLOCK).randomTicks().isViewBlocking(PA_Blocks::never).isSuffocating(PA_Blocks::never).noOcclusion()), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> CANDY_NYLIUM = register("candy_nylium", () -> new CandyNylium(BlockBehaviour.Properties.of(Material.STONE, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(0.4F,0.4F).harvestLevel(0).sound(SoundType.SLIME_BLOCK).randomTicks()), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> CANDYRACK = register("candyrack", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, PA_Blocks.CANDY_PLANKS.get().defaultMaterialColor()).strength(0.4F,0.4F).harvestLevel(0).sound(SoundType.SLIME_BLOCK).requiresCorrectToolForDrops()), PA_Registry.LIVING_CANDY_TAB, Rarity.COMMON, false);

    public static final RegistryObject<Block> RAINBOW_ORE = register("rainbow_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F,3.0F).harvestLevel(3).requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel((blockstate) -> 3), UniformInt.of(2, 9)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.EPIC, false);

    public static final RegistryObject<Block> RAINBOW_GEM_BLOCK = register("rainbow_gem_block", () -> new RainbowGemBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_PURPLE).strength(50.0F,1200.0F).harvestLevel(3).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel((blockstate) -> 7).emissiveRendering(PA_Blocks::always)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.EPIC, true);
    public static final RegistryObject<Block> BLACK_GEM_BLOCK = register("black_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(5.0F,6.0F).harvestLevel(2).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> BLUE_GEM_BLOCK = register("blue_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLUE).strength(5.0F,6.0F).harvestLevel(2).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> BROWN_GEM_BLOCK = register("brown_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).strength(5.0F,6.0F).harvestLevel(2).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> YELLOW_GEM_BLOCK = register("yellow_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW).strength(5.0F,6.0F).harvestLevel(2).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> ORANGE_GEM_BLOCK = register("orange_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_ORANGE).strength(5.0F,6.0F).harvestLevel(2).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> GREEN_GEM_BLOCK = register("green_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN).strength(5.0F,6.0F).harvestLevel(2).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);
    public static final RegistryObject<Block> PINK_GEM_BLOCK = register("pink_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_PINK).strength(5.0F,6.0F).harvestLevel(2).requiresCorrectToolForDrops().sound(SoundType.METAL)), PA_Registry.MAIN_SKIZZIK_TAB, Rarity.COMMON, false);

    public static void register() {}

    public static void registerOtherStuff(final FMLClientSetupEvent event) {
        ComposterBlock.COMPOSTABLES.put(CANDY_CANE.get(), 0.5F);

        WoodType.register(PA_Blocks.CANDY_WOOD_TYPE);

        DispenseItemBehavior fluidDispenseBehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            public ItemStack execute(BlockSource source, ItemStack item) {
                BucketItem bucket = (BucketItem)item.getItem();
                BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
                Level world = source.getLevel();

                if (bucket.emptyContents(null, world, pos, null)) {
                    bucket.checkExtraContent(null, world, item, pos);
                    return new ItemStack(Items.BUCKET);
                }
                else {
                    return this.defaultDispenseItemBehavior.dispense(source, item);
                }
            }
        };

        DispenserBlock.registerBehavior(PA_Items.MAPLE_SYRUP_BUCKET.get(), fluidDispenseBehavior);

        DefaultDispenseItemBehavior entityDispenseBehavior = new DefaultDispenseItemBehavior() {
            public ItemStack execute(BlockSource source, ItemStack item) {
                Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                EntityType<?> entity = ((SpawnEggItem)item.getItem()).getType(item.getTag());

                entity.spawn(source.getLevel(), item, null, source.getPos().relative(direction), MobSpawnType.DISPENSER, direction != Direction.UP, false);
                item.shrink(1);

                return item;
            }
        };

        for(SpawnEggItem egg : SpawnEggItem.eggs()) {
            DispenserBlock.registerBehavior(egg, entityDispenseBehavior);
        }

        DispenserBlock.registerBehavior(PA_Items.SMALL_SKIZZIK_HEAD_WITH_GEMS.get(), new OptionalDispenseItemBehavior() {
            protected ItemStack execute(BlockSource source, ItemStack itemStack) {
                Level world = source.getLevel();
                Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                BlockPos blockpos = source.getPos().relative(direction);

                if (world.isEmptyBlock(blockpos) && SkizzikHeadWithGems.canSpawnMob(world, blockpos, itemStack)) {
                    world.setBlock(blockpos, PA_Blocks.SKIZZIK_HEAD_WITH_GEMS.get().defaultBlockState().setValue(SkullBlock.ROTATION, Integer.valueOf(direction.getAxis() == Direction.Axis.Y ? 0 : direction.getOpposite().get2DDataValue() * 4)), 3);
                    BlockEntity tileEntity = world.getBlockEntity(blockpos);

                    if (tileEntity instanceof SkullBlockEntity) {
                        SkizzikHeadWithGems.checkSpawn(world, blockpos, (SkullBlockEntity) tileEntity);
                    }

                    itemStack.shrink(1);
                    this.setSuccess(true);
                }
                else {
                    this.setSuccess(ArmorItem.dispenseArmor(source, itemStack));
                }

                return itemStack;
            }
        });

        DispenserBlock.registerBehavior(PA_Items.SKIZZIK_HEAD_WITH_GEMS.get(), new OptionalDispenseItemBehavior() {
            protected ItemStack execute(BlockSource source, ItemStack itemStack) {
                Level world = source.getLevel();
                Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                BlockPos blockpos = source.getPos().relative(direction);

                if (world.isEmptyBlock(blockpos) && SkizzikHeadWithGems.canSpawnMob(world, blockpos, itemStack)) {
                    world.setBlock(blockpos, PA_Blocks.SKIZZIK_HEAD_WITH_GEMS.get().defaultBlockState().setValue(SkullBlock.ROTATION, Integer.valueOf(direction.getAxis() == Direction.Axis.Y ? 0 : direction.getOpposite().get2DDataValue() * 4)), 3);
                    BlockEntity tileEntity = world.getBlockEntity(blockpos);

                    if (tileEntity instanceof SkullBlockEntity) {
                        SkizzikHeadWithGems.checkSpawn(world, blockpos, (SkullBlockEntity) tileEntity);
                    }

                    itemStack.shrink(1);
                    this.setSuccess(true);
                }
                else {
                    this.setSuccess(ArmorItem.dispenseArmor(source, itemStack));
                }

                return itemStack;
            }
        });

        event.enqueueWork(() -> {
            Sheets.addWoodType(PA_Blocks.CANDY_WOOD_TYPE);
        });
    }

    public static void renderLayers(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(SKIZZIK_LOOT_BAG.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(SKIZZIE_STATUE.get(), RenderType.cutoutMipped());

        ItemBlockRenderTypes.setRenderLayer(CANDY_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CANDY_DOOR.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(CANDY_CANE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PA_Fluids.MAPLE_SYRUP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(PA_Fluids.FLOWING_MAPLE_SYRUP.get(), RenderType.translucent());
    }

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
