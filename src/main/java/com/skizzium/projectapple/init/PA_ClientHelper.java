package com.skizzium.projectapple.init;

import com.google.common.collect.ImmutableMap;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.block.SkizzikHeadWithGems;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.block.PA_Fluids;
import com.skizzium.projectapple.init.block.PA_TileEntities;
import com.skizzium.projectapple.init.entity.PA_ModelLayers;
import com.skizzium.projectapple.init.item.PA_Items;
import com.skizzium.projectapple.tileentity.model.PA_SkullModel;
import com.skizzium.projectapple.tileentity.renderer.PA_SkullRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.dragon.DragonHeadModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_ClientHelper {
    public static Minecraft getClient() {
        return Minecraft.getInstance();
    }
    
    @SubscribeEvent
    public static void renderLayers(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(PA_Blocks.SKIZZIK_LOOT_BAG.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(PA_Blocks.SKIZZIE_STATUE.get(), RenderType.cutoutMipped());

        ItemBlockRenderTypes.setRenderLayer(PA_Blocks.CANDY_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PA_Blocks.CANDY_DOOR.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(PA_Blocks.CANDY_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PA_Blocks.CANDY_CANE.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(PA_Fluids.MAPLE_SYRUP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(PA_Fluids.FLOWING_MAPLE_SYRUP.get(), RenderType.translucent());
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
    public static void registerOtherStuff(final FMLClientSetupEvent event) {
        ComposterBlock.COMPOSTABLES.put(PA_Blocks.CANDY_CANE.get(), 0.5F);

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
                    world.setBlock(blockpos, PA_Blocks.SMALL_SKIZZIK_HEAD_WITH_GEMS.get().defaultBlockState().setValue(SkullBlock.ROTATION, Integer.valueOf(direction.getAxis() == Direction.Axis.Y ? 0 : direction.getOpposite().get2DDataValue() * 4)), 3);
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
}