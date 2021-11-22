package com.skizzium.projectapple.init.entity;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.*;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzik;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzikSkull;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzo;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.client.renderer.FriendlySkizzikRenderer;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.client.renderer.FriendlySkizzoRenderer;
import com.skizzium.projectapple.entity.boss.skizzik.*;
import com.skizzium.projectapple.entity.boss.skizzik.client.renderer.*;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.client.renderer.FriendlySkizzikSkullRenderer;
import com.skizzium.projectapple.entity.boss.skizzik.client.renderer.SkizzikSkullRenderer;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.*;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.SkizzieModel;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.SkizzoModel;
import com.skizzium.projectapple.entity.boss.skizzik.client.model.WitchSkizzieModel;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.skizzie.FriendlySkizzie;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.skizzie.FriendlyWitchSkizzie;
import com.skizzium.projectapple.entity.client.renderer.*;
import com.skizzium.projectapple.init.PA_Registry;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_Entities {
    public static final Predicate<LivingEntity> SKIZZIE_SELECTOR = (entity) -> (entity.getMobType() != MobType.UNDEAD || entity instanceof FriendlySkizzie || entity instanceof Skizzie) &&
                                                                                                    !(entity instanceof Skizzo) &&
                                                                                                    !(entity instanceof Skizzik) &&
                                                                                                    entity.attackable();
    public static final Predicate<LivingEntity> SKIZZIK_SELECTOR = (entity) -> (entity.getMobType() != MobType.UNDEAD || entity instanceof FriendlySkizzie) &&
                                                                                !(entity instanceof Skizzie) &&
                                                                                !(entity instanceof Skizzo) &&
                                                                                !(entity instanceof Skizzik) &&
                                                                                entity.attackable();

    public static final Predicate<LivingEntity> FRIENDLY_SKIZZIK_SELECTOR = (entity) -> entity instanceof Monster &&
                                                                                        !(entity instanceof FriendlySkizzie) &&
                                                                                        !(entity instanceof FriendlySkizzo) &&
                                                                                        !(entity instanceof FriendlySkizzik) &&
                                                                                        entity.attackable();

    public static final RegistryObject<EntityType<CandyPig>> CANDY_PIG = PA_Registry.ENTITIES.register("candy_pig", () -> EntityType.Builder.of(CandyPig::new, MobCategory.CREATURE).sized(0.9F, 0.9F).clientTrackingRange(10).build("candy_pig"));
    
    public static final RegistryObject<EntityType<FriendlySkizzie>> FRIENDLY_SKIZZIE = PA_Registry.ENTITIES.register("friendly_skizzie", () -> EntityType.Builder.of(FriendlySkizzie::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).updateInterval(3).sized(0.6F, 1.6F).clientTrackingRange(10).build("friendly_skizzie"));
    public static final RegistryObject<EntityType<FriendlyWitchSkizzie>> FRIENDLY_WITCH_SKIZZIE = PA_Registry.ENTITIES.register("friendly_witch_skizzie", () -> EntityType.Builder.of(FriendlyWitchSkizzie::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).updateInterval(3).sized(0.6F, 1.6F).clientTrackingRange(10).build("friendly_witch_skizzie"));

    public static final RegistryObject<EntityType<FriendlySkizzikSkull>> FRIENDLY_SKIZZIK_SKULL = PA_Registry.ENTITIES.register("friendly_skizzik_skull", () -> EntityType.Builder.<FriendlySkizzikSkull>of(FriendlySkizzikSkull::new, MobCategory.MISC).updateInterval(10).sized(0.3125F, 0.3125F).clientTrackingRange(4).setCustomClientFactory(((spawnEntity, world) -> new FriendlySkizzikSkull(world))).build("friendly_skizzik_skull"));
    public static final RegistryObject<EntityType<FriendlySkizzo>> FRIENDLY_SKIZZO = PA_Registry.ENTITIES.register("friendly_skizzo", () -> EntityType.Builder.of(FriendlySkizzo::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(0.8F, 1.7F).clientTrackingRange(10).build("friendly_skizzo"));
    public static final RegistryObject<EntityType<FriendlySkizzik>> FRIENDLY_SKIZZIK = PA_Registry.ENTITIES.register("friendly_skizzik", () -> EntityType.Builder.of(FriendlySkizzik::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(2.5F, 4.0F).clientTrackingRange(10).build("friendly_skizzik"));
    
    public static final RegistryObject<EntityType<Skizzie>> SKIZZIE = PA_Registry.ENTITIES.register("skizzie", () -> EntityType.Builder.of(Skizzie::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(0.6F, 1.6F).clientTrackingRange(10).build("skizzie"));
    public static final RegistryObject<EntityType<KaboomSkizzie>> KABOOM_SKIZZIE = PA_Registry.ENTITIES.register("kaboom_skizzie", () -> EntityType.Builder.of(KaboomSkizzie::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(0.6F, 1.6F).clientTrackingRange(10).build("kaboom_skizzie"));
    public static final RegistryObject<EntityType<WitchSkizzie>> WITCH_SKIZZIE = PA_Registry.ENTITIES.register("witch_skizzie", () -> EntityType.Builder.of(WitchSkizzie::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(0.6F, 1.6F).clientTrackingRange(10).build("witch_skizzie"));
    public static final RegistryObject<EntityType<CorruptedSkizzie>> CORRUPTED_SKIZZIE = PA_Registry.ENTITIES.register("corrupted_skizzie", () -> EntityType.Builder.of(CorruptedSkizzie::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(0.6F, 1.6F).clientTrackingRange(10).build("corrupted_skizzie"));
    
    public static final RegistryObject<EntityType<SkizzikSkull>> SKIZZIK_SKULL = PA_Registry.ENTITIES.register("skizzik_skull", () -> EntityType.Builder.<SkizzikSkull>of(SkizzikSkull::new, MobCategory.MISC).updateInterval(10).sized(0.3125F, 0.3125F).clientTrackingRange(4).setCustomClientFactory(((spawnEntity, world) -> new SkizzikSkull(world))).build("skizzik_skull"));
    public static final RegistryObject<EntityType<Skizzo>> SKIZZO = PA_Registry.ENTITIES.register("skizzo", () -> EntityType.Builder.of(Skizzo::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(0.8F, 1.8F).clientTrackingRange(10).build("skizzo"));
    public static final RegistryObject<EntityType<Skizzik>> SKIZZIK = PA_Registry.ENTITIES.register("skizzik", () -> EntityType.Builder.of(Skizzik::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).updateInterval(3).fireImmune().sized(2.5F, 4.0F).clientTrackingRange(10).build("skizzik"));

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(CANDY_PIG.get(), CandyPig.buildAttributes().build());

        event.put(FRIENDLY_SKIZZIE.get(), FriendlySkizzie.buildAttributes().build());
        event.put(FRIENDLY_WITCH_SKIZZIE.get(), FriendlyWitchSkizzie.buildAttributes().build());

        event.put(FRIENDLY_SKIZZO.get(), FriendlySkizzo.buildAttributes().build());
        event.put(FRIENDLY_SKIZZIK.get(), FriendlySkizzik.buildAttributes().build());
        
        event.put(SKIZZIE.get(), Skizzie.buildAttributes().build());
        event.put(KABOOM_SKIZZIE.get(), KaboomSkizzie.buildAttributes().build());
        event.put(WITCH_SKIZZIE.get(), WitchSkizzie.buildAttributes().build());
        event.put(CORRUPTED_SKIZZIE.get(), CorruptedSkizzie.buildAttributes().build());

        event.put(SKIZZO.get(), Skizzo.buildAttributes().build());
        event.put(SKIZZIK.get(), Skizzik.buildAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawns(FMLCommonSetupEvent event) {
        SpawnPlacements.register(CANDY_PIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CandyPig::canEntitySpawn);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PA_ModelLayers.SKIZZIE_LAYER, SkizzieModel::createBodyLayer);
        event.registerLayerDefinition(PA_ModelLayers.WITCH_SKIZZIE_LAYER, WitchSkizzieModel::createBodyLayer);
        event.registerLayerDefinition(PA_ModelLayers.SKIZZO_LAYER, SkizzoModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(PA_Entities.CANDY_PIG.get(), CandyPigRenderer::new);

        event.registerEntityRenderer(PA_Entities.FRIENDLY_SKIZZIE.get(), SkizzieRenderer::new);
        event.registerEntityRenderer(PA_Entities.FRIENDLY_WITCH_SKIZZIE.get(), WitchSkizzieRenderer::new);

        event.registerEntityRenderer(PA_Entities.FRIENDLY_SKIZZIK_SKULL.get(), FriendlySkizzikSkullRenderer::new);
        event.registerEntityRenderer(PA_Entities.FRIENDLY_SKIZZO.get(), FriendlySkizzoRenderer::new);
        event.registerEntityRenderer(PA_Entities.FRIENDLY_SKIZZIK.get(), FriendlySkizzikRenderer::new);

        event.registerEntityRenderer(PA_Entities.SKIZZIE.get(), SkizzieRenderer::new);
        event.registerEntityRenderer(PA_Entities.KABOOM_SKIZZIE.get(), SkizzieRenderer::new);
        event.registerEntityRenderer(PA_Entities.WITCH_SKIZZIE.get(), WitchSkizzieRenderer::new);
        event.registerEntityRenderer(PA_Entities.CORRUPTED_SKIZZIE.get(), SkizzieRenderer::new);

        event.registerEntityRenderer(PA_Entities.SKIZZIK_SKULL.get(), SkizzikSkullRenderer::new);
        event.registerEntityRenderer(PA_Entities.SKIZZO.get(), SkizzoRenderer::new);
        event.registerEntityRenderer(PA_Entities.SKIZZIK.get(), SkizzikRenderer::new);
    }

    public static void register() {}
}
