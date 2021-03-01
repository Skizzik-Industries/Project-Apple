
package net.uskizzik.skizzik.world.biome;

import net.uskizzik.skizzik.entity.BabyCosmicSkizzieEntity;
import net.uskizzik.skizzik.block.CosmicUnderrackBlock;
import net.uskizzik.skizzik.block.CosmicNyliumBlock;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.biome.ParticleEffectAmbience;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.Biome;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.entity.EntityClassification;

@SkizzikModElements.ModElement.Tag
public class CosmicPlainsBiome extends SkizzikModElements.ModElement {
	public static Biome biome;
	public CosmicPlainsBiome(SkizzikModElements instance) {
		super(instance, 300);
		FMLJavaModLoadingContext.get().getModEventBus().register(new BiomeRegisterHandler());
	}
	private static class BiomeRegisterHandler {
		@SubscribeEvent
		public void registerBiomes(RegistryEvent.Register<Biome> event) {
			if (biome == null) {
				BiomeAmbience effects = new BiomeAmbience.Builder().setFogColor(12638463).setWaterColor(4159204).setWaterFogColor(329011)
						.withSkyColor(7972607).withFoliageColor(10387789).withGrassColor(9470285)
						.setParticle(new ParticleEffectAmbience(ParticleTypes.WARPED_SPORE, 0.027f)).build();
				BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder()
						.withSurfaceBuilder(SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(CosmicNyliumBlock.block.getDefaultState(),
								CosmicUnderrackBlock.block.getDefaultState(), CosmicUnderrackBlock.block.getDefaultState())));
				MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder().isValidSpawnBiomeForPlayer();
				mobSpawnInfo.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(BabyCosmicSkizzieEntity.entity, 20, 3, 6));
				biome = new Biome.Builder().precipitation(Biome.RainType.RAIN).category(Biome.Category.NONE).depth(0.1f).scale(0.2f).temperature(0.5f)
						.downfall(0.5f).setEffects(effects).withMobSpawnSettings(mobSpawnInfo.copy())
						.withGenerationSettings(biomeGenerationSettings.build()).build();
				event.getRegistry().register(biome.setRegistryName("skizzik:cosmic_plains"));
			}
		}
	}
	@Override
	public void init(FMLCommonSetupEvent event) {
	}
}
