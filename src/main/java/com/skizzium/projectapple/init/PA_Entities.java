package com.skizzium.projectapple.init;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.*;
import com.skizzium.projectapple.entity.model.SkizzikModel;
import com.skizzium.projectapple.entity.renderer.*;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.entity.*;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import static com.skizzium.projectapple.ProjectApple.MOD_ID;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.SpawnPlacements;

import net.minecraft.util.profiling.metrics.storage.RecordedDeviation;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_Entities {
    public static final Predicate<RecordedDeviation> LIVING_ENTITY_SELECTOR = (entity) -> entity.getMobType() != MobType.UNDEAD && entity.attackable();

    public static final EntityType<CandyPig> CANDY_PIG = registerEntity("candy_pig", EntityType.Builder.of(CandyPig::new, MobCategory.CREATURE).sized(0.9F, 0.9F).clientTrackingRange(10));
    public static final EntityType<FriendlySkizzie> FRIENDLY_SKIZZIE = registerEntity("friendly_skizzie", EntityType.Builder.of(FriendlySkizzie::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).updateInterval(3).sized(0.6F, 1.6F).clientTrackingRange(10));
    public static final EntityType<FriendlyWitchSkizzie> FRIENDLY_WITCH_SKIZZIE = registerEntity("friendly_witch_skizzie", EntityType.Builder.of(FriendlyWitchSkizzie::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).updateInterval(3).sized(0.6F, 1.6F).clientTrackingRange(10));
    public static final EntityType<Skizzie> SKIZZIE = registerEntity("skizzie", EntityType.Builder.of(Skizzie::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(0.6F, 1.6F).clientTrackingRange(10));
    public static final EntityType<KaboomSkizzie> KABOOM_SKIZZIE = registerEntity("kaboom_skizzie", EntityType.Builder.of(KaboomSkizzie::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(0.6F, 1.6F).clientTrackingRange(10));
    //public static final EntityType<MinigunSkizzie> MINIGUN_SKIZZIE = registerEntity("minigun_skizzie", EntityType.Builder.of(MinigunSkizzie::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(0.6F, 1.6F).clientTrackingRange(10));
    public static final EntityType<CorruptedSkizzie> CORRUPTED_SKIZZIE = registerEntity("corrupted_skizzie", EntityType.Builder.of(CorruptedSkizzie::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).sized(0.6F, 1.6F).clientTrackingRange(10));
    public static final EntityType<WitchSkizzie> WITCH_SKIZZIE = registerEntity("witch_skizzie", EntityType.Builder.of(WitchSkizzie::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(0.6F, 1.6F).clientTrackingRange(10));
    public static final EntityType<Skizzo> SKIZZO = registerEntity("skizzo", EntityType.Builder.of(Skizzo::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(0.8F, 2.1F).clientTrackingRange(10));
    public static final EntityType<SkizzikSkull> SKIZZIK_SKULL = registerEntity("skizzik_skull", EntityType.Builder.<SkizzikSkull>of(SkizzikSkull::new, MobCategory.MISC).updateInterval(10).sized(0.3125F, 0.3125F).clientTrackingRange(4).setCustomClientFactory(((spawnEntity, world) -> new SkizzikSkull(world))));
    public static final EntityType<Skizzik> SKIZZIK = registerEntity("skizzik", EntityType.Builder.of(Skizzik::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(2.5F, 4F).clientTrackingRange(10));

    public static final SpawnEggItem CANDY_PIG_SPAWN_EGG = (SpawnEggItem) new SpawnEggItem(PA_Entities.CANDY_PIG, 0XFF638C, 0XC92B60, (new Item.Properties()).tab(PA_Registry.LIVING_CANDY_TAB)).setRegistryName("skizzik:candy_pig_spawn_egg");
    public static final SpawnEggItem FRIENDLY_SKIZZIE_SPAWN_EGG = (SpawnEggItem) new SpawnEggItem(PA_Entities.FRIENDLY_SKIZZIE, 0X17D1C7, 0X02A8C1, (new Item.Properties()).tab(PA_Registry.MAIN_SKIZZIK_TAB)).setRegistryName("skizzik:friendly_skizzie_spawn_egg");
    public static final SpawnEggItem FRIENDLY_WITCH_SKIZZIE_SPAWN_EGG = (SpawnEggItem) new SpawnEggItem(PA_Entities.FRIENDLY_WITCH_SKIZZIE, 0X17D1C7, 0X5349438, (new Item.Properties()).tab(PA_Registry.MAIN_SKIZZIK_TAB)).setRegistryName("skizzik:friendly_witch_skizzie_spawn_egg");
    public static final SpawnEggItem SKIZZIE_SPAWN_EGG = (SpawnEggItem) new SpawnEggItem(PA_Entities.SKIZZIE, 0XB40A1A, 0X9A080F, (new Item.Properties()).tab(PA_Registry.MAIN_SKIZZIK_TAB)).setRegistryName("skizzik:skizzie_spawn_egg");
    public static final SpawnEggItem KABOOM_SKIZZIE_SPAWN_EGG = (SpawnEggItem) new SpawnEggItem(PA_Entities.KABOOM_SKIZZIE, 0XB40A1A, 0X5BE9B7, (new Item.Properties()).tab(PA_Registry.MAIN_SKIZZIK_TAB)).setRegistryName("skizzik:kaboom_skizzie_spawn_egg");
    //public static final Item MINIGUN_SKIZZIE_SPAWN_EGG = new SpawnEggItem(ModEntities.MINIGUN_SKIZZIE, 0XB40A1A, 0X4F0000, (new Item.Properties()).tab(Register.MAIN_SKIZZIK_TAB)).setRegistryName("skizzik:minigun_skizzie_spawn_egg");
    public static final SpawnEggItem CORRUPTED_SKIZZIE_SPAWN_EGG = (SpawnEggItem) new SpawnEggItem(PA_Entities.CORRUPTED_SKIZZIE, 0XA90AB4, 0X91089A, (new Item.Properties()).rarity(Rarity.create("CORRUPTED", ChatFormatting.OBFUSCATED)).tab(PA_Registry.MAIN_SKIZZIK_TAB)).setRegistryName("skizzik:corrupted_skizzie_spawn_egg");
    public static final SpawnEggItem WITCH_SKIZZIE_SPAWN_EGG = (SpawnEggItem) new SpawnEggItem(PA_Entities.WITCH_SKIZZIE, 0XB40A1A, 0X5349438, (new Item.Properties()).tab(PA_Registry.MAIN_SKIZZIK_TAB)).setRegistryName("skizzik:witch_skizzie_spawn_egg");
    public static final SpawnEggItem SKIZZO_SPAWN_EGG = (SpawnEggItem) new SpawnEggItem(PA_Entities.SKIZZO, 0XFF0000, 0X330000, (new Item.Properties()).tab(PA_Registry.MAIN_SKIZZIK_TAB)).setRegistryName("skizzik:skizzo_spawn_egg");

    private static final EntityType registerEntity(String name, EntityType.Builder builder) {
        ResourceLocation location = new ResourceLocation(MOD_ID, name);
        return (EntityType) builder.build(name).setRegistryName(location);
    }

    private static void registerAttributes() {
        DefaultAttributes.put(CANDY_PIG, CandyPig.buildAttributes().build());

        DefaultAttributes.put(FRIENDLY_SKIZZIE, FriendlySkizzie.buildAttributes().build());
        DefaultAttributes.put(FRIENDLY_WITCH_SKIZZIE, FriendlyWitchSkizzie.buildAttributes().build());

        DefaultAttributes.put(SKIZZIE, Skizzie.buildAttributes().build());
        DefaultAttributes.put(KABOOM_SKIZZIE, KaboomSkizzie.buildAttributes().build());
        //GlobalEntityTypeAttributes.put(MINIGUN_SKIZZIE, MinigunSkizzie.buildAttributes().build());
        DefaultAttributes.put(CORRUPTED_SKIZZIE, CorruptedSkizzie.buildAttributes().build());
        DefaultAttributes.put(WITCH_SKIZZIE, WitchSkizzie.buildAttributes().build());

        DefaultAttributes.put(SKIZZO, Skizzo.buildAttributes().build());
        DefaultAttributes.put(SKIZZIK, Skizzik.buildAttributes().build());
    }

    public static void registerRenderers(final FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(PA_TileEntities.PA_SIGN.get(), SignRenderer::new);
        ClientRegistry.bindTileEntityRenderer(PA_TileEntities.PA_SKULL.get(), SkullBlockRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(PA_Entities.CANDY_PIG, CandyPigRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PA_Entities.FRIENDLY_SKIZZIE, FriendlySkizzieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PA_Entities.FRIENDLY_WITCH_SKIZZIE, FriendlyWitchSkizzieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PA_Entities.SKIZZIE, SkizzieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PA_Entities.KABOOM_SKIZZIE, KaboomSkizzieRenderer::new);
        //RenderingRegistry.registerEntityRenderingHandler(ModEntities.MINIGUN_SKIZZIE, MinigunSkizzieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PA_Entities.CORRUPTED_SKIZZIE, CorruptedSkizzieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PA_Entities.WITCH_SKIZZIE, WitchSkizzieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PA_Entities.SKIZZO, SkizzoRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PA_Entities.SKIZZIK_SKULL, SkizzikSkullRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(PA_Entities.SKIZZIK, SkizzikRenderer::new);
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
        registerAttributes();
    }

    public static void register() {}
}
