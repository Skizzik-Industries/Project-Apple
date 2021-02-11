
package net.uskizzik.skizzik.entity;

import net.uskizzik.skizzik.procedures.InactiveSkizzikEntityDiesProcedure;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.World;
import net.minecraft.world.BossInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@SkizzikModElements.ModElement.Tag
public class InactiveSkizzikEntity extends SkizzikModElements.ModElement {
	public static EntityType entity = null;
	public InactiveSkizzikEntity(SkizzikModElements instance) {
		super(instance, 184);
		FMLJavaModLoadingContext.get().getModEventBus().register(new ModelRegisterHandler());
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(3f, 4f))
						.build("inactive_skizzik").setRegistryName("inactive_skizzik");
		elements.entities.add(() -> entity);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(this::setupAttributes);
	}
	private static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelinactive_skizzik(), 1f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("skizzik:textures/inactive_skizzik.png");
					}
				};
			});
		}
	}
	private void setupAttributes() {
		AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
		ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3);
		ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 1);
		ammma = ammma.createMutableAttribute(Attributes.ARMOR, 0);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 0);
		GlobalEntityTypeAttributes.put(entity, ammma.create());
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			enablePersistence();
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			if (source == DamageSource.LIGHTNING_BOLT)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public void onDeath(DamageSource source) {
			super.onDeath(source);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity sourceentity = source.getTrueSource();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				InactiveSkizzikEntityDiesProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public boolean isNonBoss() {
			return false;
		}
		private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.WHITE, BossInfo.Overlay.PROGRESS);
		@Override
		public void addTrackingPlayer(ServerPlayerEntity player) {
			super.addTrackingPlayer(player);
			this.bossInfo.addPlayer(player);
		}

		@Override
		public void removeTrackingPlayer(ServerPlayerEntity player) {
			super.removeTrackingPlayer(player);
			this.bossInfo.removePlayer(player);
		}

		@Override
		public void updateAITasks() {
			super.updateAITasks();
			this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		}
	}

	// Made with Blockbench 3.6.6
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelinactive_skizzik extends EntityModel<Entity> {
		private final ModelRenderer Skizzik;
		private final ModelRenderer body;
		private final ModelRenderer head1;
		private final ModelRenderer head2;
		private final ModelRenderer head3;
		private final ModelRenderer head4;
		private final ModelRenderer head5;
		public Modelinactive_skizzik() {
			textureWidth = 256;
			textureHeight = 256;
			Skizzik = new ModelRenderer(this);
			Skizzik.setRotationPoint(0.0F, 24.0F, 0.0F);
			body = new ModelRenderer(this);
			body.setRotationPoint(0.5F, -7.3895F, -10.9211F);
			Skizzik.addChild(body);
			body.setTextureOffset(73, 100).addBox(-3.5F, -9.7105F, 8.4211F, 7.0F, 19.0F, 7.0F, 0.0F, false);
			body.setTextureOffset(84, 46).addBox(-11.5F, -8.2105F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
			body.setTextureOffset(0, 86).addBox(7.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
			body.setTextureOffset(80, 24).addBox(-10.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
			body.setTextureOffset(60, 44).addBox(-7.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
			body.setTextureOffset(0, 42).addBox(4.5F, -7.2105F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
			body.setTextureOffset(0, 37).addBox(4.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
			body.setTextureOffset(0, 32).addBox(-7.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 3.0F, 0.0F, false);
			body.setTextureOffset(64, 78).addBox(-10.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
			body.setTextureOffset(90, 76).addBox(7.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 19.0F, 0.0F, false);
			body.setTextureOffset(88, 0).addBox(-10.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 19.0F, 0.0F, false);
			body.setTextureOffset(76, 54).addBox(7.5F, 3.7895F, -9.0789F, 3.0F, 2.0F, 20.0F, 0.0F, false);
			body.setTextureOffset(0, 10).addBox(-7.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 3.0F, 0.0F, false);
			body.setTextureOffset(0, 4).addBox(4.5F, -2.2105F, -9.0789F, 3.0F, 3.0F, 3.0F, 0.0F, false);
			body.setTextureOffset(48, 8).addBox(-11.5F, -2.7105F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
			body.setTextureOffset(48, 0).addBox(-11.5F, 2.7895F, 9.9211F, 23.0F, 4.0F, 4.0F, 0.0F, false);
			body.setTextureOffset(0, 32).addBox(-7.5F, -8.7105F, -6.5789F, 15.0F, 15.0F, 15.0F, 0.0F, false);
			body.setTextureOffset(29, 98).addBox(-2.5F, 6.2895F, -8.5789F, 5.0F, 1.0F, 17.0F, 0.0F, false);
			body.setTextureOffset(0, 0).addBox(-2.5F, 4.2895F, -8.5789F, 5.0F, 2.0F, 2.0F, 0.0F, false);
			head1 = new ModelRenderer(this);
			head1.setRotationPoint(1.0F, -40.0F, 0.0F);
			Skizzik.addChild(head1);
			head1.setTextureOffset(0, 0).addBox(-9.0F, 7.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
			head2 = new ModelRenderer(this);
			head2.setRotationPoint(19.0F, -33.0F, 0.0F);
			Skizzik.addChild(head2);
			head2.setTextureOffset(0, 62).addBox(-6.0F, 21.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
			head3 = new ModelRenderer(this);
			head3.setRotationPoint(-18.0F, -35.0F, 1.0F);
			Skizzik.addChild(head3);
			head3.setTextureOffset(36, 74).addBox(-6.0F, 23.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
			head4 = new ModelRenderer(this);
			head4.setRotationPoint(-17.0F, -19.0F, 1.0F);
			Skizzik.addChild(head4);
			head4.setTextureOffset(52, 20).addBox(-6.0F, -5.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
			head5 = new ModelRenderer(this);
			head5.setRotationPoint(17.0F, -55.0F, 0.0F);
			Skizzik.addChild(head5);
			head5.setTextureOffset(48, 50).addBox(-5.0F, 31.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			Skizzik.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
		}
	}
}
