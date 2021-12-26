package com.skizzium.projectapple.init;

import com.google.common.collect.ImmutableMap;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.block.heads.SkizzikHeadWithGems;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.block.PA_TileEntities;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import com.skizzium.projectapple.init.item.PA_Items;
import com.skizzium.projectapple.entity.client.model.PA_SkullModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.dragon.DragonHeadModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_ClientHelper {
    public static final PA_Keybinds keybinds = new PA_Keybinds();
    
    public static Minecraft getClient() {
        return Minecraft.getInstance();
    }
    
    @SubscribeEvent
    public static void renderLayers(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(PA_Blocks.SKIZZIK_LOOT_BAG.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(PA_Blocks.SKIZZIE_STATUE.get(), RenderType.cutoutMipped());
    }
    
    public static Map<SkullBlock.Type, SkullModelBase> createSkullRenderers(EntityModelSet set) {
        ImmutableMap.Builder<SkullBlock.Type, SkullModelBase> builder = ImmutableMap.builder();
        if (set == null) {
            set = Minecraft.getInstance().getEntityModels();
        }

        builder.put(SkullBlock.Types.SKELETON, new SkullModel(set.bakeLayer(ModelLayers.SKELETON_SKULL)));
        builder.put(SkullBlock.Types.WITHER_SKELETON, new SkullModel(set.bakeLayer(ModelLayers.WITHER_SKELETON_SKULL)));
        builder.put(SkullBlock.Types.PLAYER, new SkullModel(set.bakeLayer(ModelLayers.PLAYER_HEAD)));
        builder.put(SkullBlock.Types.ZOMBIE, new SkullModel(set.bakeLayer(ModelLayers.ZOMBIE_HEAD)));
        builder.put(SkullBlock.Types.CREEPER, new SkullModel(set.bakeLayer(ModelLayers.CREEPER_HEAD)));
        builder.put(SkullBlock.Types.DRAGON, new DragonHeadModel(set.bakeLayer(ModelLayers.DRAGON_SKULL)));

        builder.put(PA_TileEntities.CustomSkullTypes.SMALL_FRIENDLY_SKIZZIK, new PA_SkullModel(set.bakeLayer(PA_ModelLayers.SMALL_SKIZZIK_HEAD_LAYER)));
        builder.put(PA_TileEntities.CustomSkullTypes.SMALL_FRIENDLY_SKIZZIK_WITH_GEMS, new PA_SkullModel(set.bakeLayer(PA_ModelLayers.SMALL_SKIZZIK_HEAD_WITH_GEMS_LAYER)));
        builder.put(PA_TileEntities.CustomSkullTypes.FRIENDLY_SKIZZIK, new PA_SkullModel(set.bakeLayer(PA_ModelLayers.SKIZZIK_HEAD_LAYER)));
        builder.put(PA_TileEntities.CustomSkullTypes.FRIENDLY_SKIZZIK_WITH_GEMS, new PA_SkullModel(set.bakeLayer(PA_ModelLayers.SKIZZIK_HEAD_WITH_GEMS_LAYER)));
        
        builder.put(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK, new PA_SkullModel(set.bakeLayer(PA_ModelLayers.SMALL_SKIZZIK_HEAD_LAYER)));
        builder.put(PA_TileEntities.CustomSkullTypes.SMALL_SKIZZIK_WITH_GEMS, new PA_SkullModel(set.bakeLayer(PA_ModelLayers.SMALL_SKIZZIK_HEAD_WITH_GEMS_LAYER)));
        builder.put(PA_TileEntities.CustomSkullTypes.SKIZZIK, new PA_SkullModel(set.bakeLayer(PA_ModelLayers.SKIZZIK_HEAD_LAYER)));
        builder.put(PA_TileEntities.CustomSkullTypes.SKIZZIK_WITH_GEMS, new PA_SkullModel(set.bakeLayer(PA_ModelLayers.SKIZZIK_HEAD_WITH_GEMS_LAYER)));

        return builder.build();
    }

    @SubscribeEvent
    public static void registerSkullHeadLayers(EntityRenderersEvent.AddLayers event) {
        Map<EntityType<?>, EntityRenderer<?>> renderers = Minecraft.getInstance().getEntityRenderDispatcher().renderers;
        for(Map.Entry<EntityType<?>, EntityRenderer<?>> renderer : renderers.entrySet()) {
            if (renderer.getValue() instanceof LivingEntityRenderer) {
                List<? extends RenderLayer<?, ?>> layers = ((LivingEntityRenderer<?, ?>) renderer.getValue()).layers;
                for (RenderLayer<?, ?> layer : layers) {
                    if (layer instanceof CustomHeadLayer) {
                        ((CustomHeadLayer) layer).skullModels = createSkullRenderers(null);
                    }
                }
            }
        }

        Map<String, EntityRenderer<? extends Player>> skins = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap();
        for(Map.Entry<String, EntityRenderer<? extends Player>> renderer : skins.entrySet()) {
            if (renderer.getValue() instanceof LivingEntityRenderer) {
                List<? extends RenderLayer<?, ?>> layers = ((LivingEntityRenderer<?, ?>) renderer.getValue()).layers;
                for (RenderLayer<?, ?> layer : layers) {
                    if (layer instanceof CustomHeadLayer) {
                        ((CustomHeadLayer) layer).skullModels = createSkullRenderers(null);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerCauldronInteraction(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CauldronInteraction RAW_SKIZZIK_FLESH = (state, level, pos, player, hand, itemstack) -> {
                if (!level.isClientSide) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    ItemStack flesh = new ItemStack(PA_Items.RAW_SKIZZIK_FLESH.get());
                    player.addItem(flesh);
                    LayeredCauldronBlock.lowerFillLevel(state, level, pos);
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            };
            
            CauldronInteraction.POWDER_SNOW.put(PA_Items.SKIZZIK_FLESH.get(), RAW_SKIZZIK_FLESH);
            CauldronInteraction.POWDER_SNOW.put(PA_Items.FRIENDLY_SKIZZIK_FLESH.get(), RAW_SKIZZIK_FLESH);
            
            CauldronInteraction.WATER.put(PA_Items.RAW_SKIZZIK_FLESH.get(), (state, level, pos, player, hand, itemstack) -> {
                if (!level.isClientSide) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    ItemStack flesh = new ItemStack(PA_Items.FRIENDLY_SKIZZIK_FLESH.get());
                    player.addItem(flesh);
                    LayeredCauldronBlock.lowerFillLevel(state, level, pos);
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            });

            CauldronInteraction.LAVA.put(PA_Items.RAW_SKIZZIK_FLESH.get(), (state, level, pos, player, hand, itemstack) -> {
                if (!level.isClientSide) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    ItemStack flesh = new ItemStack(PA_Items.SKIZZIK_FLESH.get());
                    player.addItem(flesh);
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            });
        });
    }
    
    @SubscribeEvent
    public static void registerDisepnserBehavior(FMLClientSetupEvent event) {
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

        DispenseItemBehavior skullDispenseBehavior = new OptionalDispenseItemBehavior() {
            protected ItemStack execute(BlockSource source, ItemStack stack) {
                this.setSuccess(ArmorItem.dispenseArmor(source, stack));
                return stack;
            }
        };

        DispenserBlock.registerBehavior(PA_Items.FRIENDLY_SKIZZIK_HEAD.get(), skullDispenseBehavior);
        DispenserBlock.registerBehavior(PA_Items.FRIENDLY_SKIZZIK_HEAD_WITH_GEMS.get(), skullDispenseBehavior);
        DispenserBlock.registerBehavior(PA_Items.SKIZZIK_HEAD.get(), skullDispenseBehavior);

        DispenserBlock.registerBehavior(PA_Items.SMALL_SKIZZIK_HEAD_WITH_GEMS.get(), new OptionalDispenseItemBehavior() {
            protected ItemStack execute(BlockSource source, ItemStack itemStack) {
                Level world = source.getLevel();
                Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                BlockPos blockpos = source.getPos().relative(direction);

                if (world.isEmptyBlock(blockpos) && SkizzikHeadWithGems.canSpawnMob(world, blockpos, itemStack)) {
                    world.setBlock(blockpos, PA_Blocks.SMALL_SKIZZIK_HEAD_WITH_GEMS.get().defaultBlockState().setValue(SkullBlock.ROTATION, Integer.valueOf(direction.getAxis() == Direction.Axis.Y ? 0 : direction.getOpposite().get2DDataValue() * 4)), 3);
                    BlockEntity tileEntity = world.getBlockEntity(blockpos);

                    if (tileEntity instanceof SkullBlockEntity) {
                        SkizzikHeadWithGems.checkSpawn(world, blockpos, (SkullBlockEntity) tileEntity);
                    }

                    itemStack.shrink(1);
                    this.setSuccess(true);
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
    }
}
