package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.CandyPig;
import com.skizzium.projectapple.entity.KaboomSkizzie;
import com.skizzium.projectapple.entity.Skizzie;
import com.skizzium.projectapple.entity.renderer.CandyPigRenderer;
import com.skizzium.projectapple.entity.renderer.KaboomSkizzieRenderer;
import com.skizzium.projectapple.entity.renderer.SkizzieRenderer;
import net.minecraft.entity.*;
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
import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR = (entity) -> entity.getMobType() != CreatureAttribute.UNDEAD && entity.attackable();

    public static final EntityType<CandyPig> CANDY_PIG = registerEntity("candy_pig", EntityType.Builder.of(CandyPig::new, EntityClassification.CREATURE).sized(0.9F, 0.9F).clientTrackingRange(10));
    public static final EntityType<Skizzie> SKIZZIE = registerEntity("skizzie", EntityType.Builder.of(Skizzie::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).fireImmune().sized(0.6F, 1.6F));
    public static final EntityType<KaboomSkizzie> KABOOM_SKIZZIE = registerEntity("kaboom_skizzie", EntityType.Builder.of(KaboomSkizzie::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).fireImmune().sized(0.6F, 1.6F));

    public static final Item CANDY_PIG_SPAWN_EGG = new SpawnEggItem(ModEntities.CANDY_PIG, 0XFF638C, 0XC92B60, (new Item.Properties()).tab(Register.LIVING_CANDY_TAB)).setRegistryName("skizzik:candy_pig_spawn_egg");
    public static final Item SKIZZIE_SPAWN_EGG = new SpawnEggItem(ModEntities.SKIZZIE, 0XB40A1A, 0X9A080F, (new Item.Properties()).tab(Register.MAIN_SKIZZIK_TAB)).setRegistryName("skizzik:skizzie_spawn_egg");
    public static final Item KABOOM_SKIZZIE_SPAWN_EGG = new SpawnEggItem(ModEntities.KABOOM_SKIZZIE, 0XB40A1A, 0X5BE9B7, (new Item.Properties()).tab(Register.MAIN_SKIZZIK_TAB)).setRegistryName("skizzik:kaboom_skizzie_spawn_egg");

    private static final EntityType registerEntity(String name, EntityType.Builder builder) {
        ResourceLocation location = new ResourceLocation(ProjectApple.MOD_ID, name);
        return (EntityType) builder.build(name).setRegistryName(location);
    }

    private static void registerAttributes() {
        GlobalEntityTypeAttributes.put(CANDY_PIG, CandyPig.buildAttributes().build());
        GlobalEntityTypeAttributes.put(SKIZZIE, Skizzie.buildAttributes().build());
        GlobalEntityTypeAttributes.put(KABOOM_SKIZZIE, KaboomSkizzie.buildAttributes().build());
    }

    public static void registerRenderers(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.CANDY_PIG, CandyPigRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.SKIZZIE, SkizzieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.KABOOM_SKIZZIE, KaboomSkizzieRenderer::new);
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

    public static void register() {}
}
