package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.CandyPig;
import com.skizzium.projectapple.entity.renderer.CandyPigRenderer;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final Item CANDY_PIG_SPAWN_EGG = new SpawnEggItem(ModEntities.CANDY_PIG, 0XFF638C, 0XC92B60, (new Item.Properties()).tab(Register.LIVING_CANDY_TAB)).setRegistryName("skizzik:candy_pig_spawn_egg");

    public static final EntityType<CandyPig> CANDY_PIG = registerEntity("candy_pig", EntityType.Builder.of(CandyPig::new, EntityClassification.CREATURE).sized(1.45F, 1.75F).setTrackingRange(64).setUpdateInterval(1));

    private static final EntityType registerEntity(String name, EntityType.Builder builder) {
        ResourceLocation location = new ResourceLocation(ProjectApple.MOD_ID, name);
        return (EntityType) builder.build(name).setRegistryName(location);
    }

    static {
        EntitySpawnPlacementRegistry.register(CANDY_PIG, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CandyPig::canEntitySpawn);
    }

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        try {
            for (Field field : ModEntities.class.getDeclaredFields()) {
                Object object = field.get(null);
                if (object instanceof EntityType) {
                    event.getRegistry().register((EntityType) object);
                }
                else if (object instanceof EntityType[]) {
                    for (EntityType type : (EntityType[]) object) {
                        event.getRegistry().register(type);
                    }
                }
            }
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        registerAttributes();
    }

    private static void registerAttributes() {
        GlobalEntityTypeAttributes.put(CANDY_PIG, CandyPig.buildAttributes().build());
    }

    public static void registerRenderers(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CANDY_PIG, CandyPigRenderer::new);
    }

    public static void register() {}

    /* public static final RegistryObject<EntityType<CandyPig>> CANDY_PIG = createEntity("candy_pig", CandyPig::new, 0.9F, 0.9F, 0XFF638C, 0XC92B60, Register.LIVING_CANDY_TAB, EntityClassification.CREATURE);

    private static final List<Item> SPAWN_EGGS = Lists.newArrayList();
    private static <T extends AnimalEntity> RegistryObject<EntityType<T>> createEntity(String name, EntityType.IFactory<T> factory, float width, float height, int eggPrimary, int eggSecondary, ItemGroup itemGroup, EntityClassification classification) {
        ResourceLocation location = new ResourceLocation(ProjectApple.MOD_ID, name);
        EntityType<T> entity = EntityType.Builder.of(factory, classification).sized(width, height).setTrackingRange(64).setUpdateInterval(1).build(location.toString());

        Item egg = new SpawnEggItem(entity, eggPrimary, eggSecondary, (new Item.Properties()).tab(itemGroup));
        egg.setRegistryName(new ResourceLocation(ProjectApple.MOD_ID, name + "_spawn_egg"));
        SPAWN_EGGS.add(egg);

        return Register.ENTITIES.register(name, () -> entity);
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        EntitySpawnPlacementRegistry.register(CANDY_PIG.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CandyPig::canEntitySpawn);
    }

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.CANDY_PIG.get(), CandyPig.createAttributes().build());
    }

    public static void registerRenderers(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CANDY_PIG.get(), CandyPigRenderer::new);
    }

    @SubscribeEvent
    public static void registerSpawnEggs(RegistryEvent.Register<Item> event) {
        ProjectApple.LOGGER.debug("All Working, Chief!");
        for (Item spawnEgg : SPAWN_EGGS) {
            Preconditions.checkNotNull(spawnEgg.getRegistryName(), "registryName");
            event.getRegistry().register(spawnEgg);
        }
    }

    public static void register() {} */
}
