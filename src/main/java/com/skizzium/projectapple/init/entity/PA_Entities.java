package com.skizzium.projectapple.init.entity;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.CandyPig;
import com.skizzium.projectapple.entity.KaboomSkizzie;
import com.skizzium.projectapple.entity.Skizzie;
import com.skizzium.projectapple.entity.model.SkizzieModel;
import com.skizzium.projectapple.entity.renderer.CandyPigRenderer;
import com.skizzium.projectapple.entity.renderer.KaboomSkizzieRenderer;
import com.skizzium.projectapple.entity.renderer.SkizzieRenderer;
import com.skizzium.projectapple.init.PA_Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import static com.skizzium.projectapple.ProjectApple.MOD_ID;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_Entities {
    public static final Predicate<LivingEntity> SKIZZIK_SELECTOR = (entity) -> entity.getMobType() != MobType.UNDEAD &&
                                                                                !(entity instanceof Skizzie) &&
    //                                                                            !(entity instanceof Skizzo) &&
    //                                                                            !(entity instanceof Skizzik) &&
                                                                                entity.attackable();

    public static final EntityType<CandyPig> CANDY_PIG = registerEntity("candy_pig", EntityType.Builder.of(CandyPig::new, MobCategory.CREATURE).sized(0.9F, 0.9F).clientTrackingRange(10));
    public static final EntityType<Skizzie> SKIZZIE = registerEntity("skizzie", EntityType.Builder.of(Skizzie::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(0.6F, 1.6F).clientTrackingRange(10));
    public static final EntityType<KaboomSkizzie> KABOOM_SKIZZIE = registerEntity("kaboom_skizzie", EntityType.Builder.of(KaboomSkizzie::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(0.6F, 1.6F).clientTrackingRange(10));

    public static final SpawnEggItem CANDY_PIG_SPAWN_EGG = (SpawnEggItem) new SpawnEggItem(PA_Entities.CANDY_PIG, 0XFF638C, 0XC92B60, (new Item.Properties()).tab(PA_Registry.LIVING_CANDY_TAB)).setRegistryName("skizzik:candy_pig_spawn_egg");
    public static final SpawnEggItem SKIZZIE_SPAWN_EGG = (SpawnEggItem) new SpawnEggItem(PA_Entities.SKIZZIE, 0XB40A1A, 0X9A080F, (new Item.Properties()).tab(PA_Registry.MAIN_SKIZZIK_TAB)).setRegistryName("skizzik:skizzie_spawn_egg");
    public static final SpawnEggItem KABOOM_SKIZZIE_SPAWN_EGG = (SpawnEggItem) new SpawnEggItem(PA_Entities.KABOOM_SKIZZIE, 0XB40A1A, 0X5BE9B7, (new Item.Properties()).tab(PA_Registry.MAIN_SKIZZIK_TAB)).setRegistryName("skizzik:kaboom_skizzie_spawn_egg");

    private static final EntityType registerEntity(String name, EntityType.Builder builder) {
        ResourceLocation location = new ResourceLocation(MOD_ID, name);
        return (EntityType) builder.build(name).setRegistryName(location);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(CANDY_PIG, CandyPig.buildAttributes().build());

        event.put(SKIZZIE, Skizzie.buildAttributes().build());
        event.put(KABOOM_SKIZZIE, KaboomSkizzie.buildAttributes().build());
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PA_ModelLayers.SKIZZIE_LAYER, SkizzieModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        //ClientRegistry.bindTileEntityRenderer(PA_TileEntities.PA_SIGN.get(), SignTileEntityRenderer::new);
        //ClientRegistry.bindTileEntityRenderer(PA_TileEntities.PA_SKULL.get(), SkullTileEntityRenderer::new);

        event.registerEntityRenderer(PA_Entities.CANDY_PIG, CandyPigRenderer::new);

        event.registerEntityRenderer(PA_Entities.SKIZZIE, SkizzieRenderer::new);
        event.registerEntityRenderer(PA_Entities.KABOOM_SKIZZIE, KaboomSkizzieRenderer::new);
    }

    static {
        SpawnPlacements.register(CANDY_PIG, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CandyPig::canEntitySpawn);
    }

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        try {
            for (Field field : PA_Entities.class.getDeclaredFields()) {
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
    }

    public static void register() {}
}
