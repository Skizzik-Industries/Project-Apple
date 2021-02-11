
package net.uskizzik.skizzik.entity;

import net.uskizzik.skizzik.itemgroup.TemplateTabItemGroup;
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

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.block.BlockState;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@SkizzikModElements.ModElement.Tag
public class CosmicSkizzieEntity extends SkizzikModElements.ModElement {
	public static EntityType entity = null;
	public CosmicSkizzieEntity(SkizzikModElements instance) {
		super(instance, 267);
		FMLJavaModLoadingContext.get().getModEventBus().register(new ModelRegisterHandler());
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(1.1f, 3.1f))
						.build("cosmic_skizzie").setRegistryName("cosmic_skizzie");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -6750055, -10092442, new Item.Properties().group(TemplateTabItemGroup.tab))
				.setRegistryName("cosmic_skizzie_spawn_egg"));
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
				return new MobRenderer(renderManager, new Modelcosmic_skizzie(), 0.5f) {
					{
						this.addLayer(new GlowingLayer<>(this));
					}
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("skizzik:textures/cosmic_skizzie.png");
					}
				};
			});
		}
	}
	private void setupAttributes() {
		AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
		ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3);
		ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 35);
		ammma = ammma.createMutableAttribute(Attributes.ARMOR, 10);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3);
		ammma = ammma.createMutableAttribute(Attributes.FLYING_SPEED, 0.3);
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
			this.moveController = new FlyingMovementController(this, 10, true);
			this.navigator = new FlyingPathNavigator(this, this.world);
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
			return CreatureAttribute.UNDEAD;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("skizzik:entity.cosmic_skizzie.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("skizzik:entity.cosmic_skizzie.death"));
		}

		@Override
		public boolean onLivingFall(float l, float d) {
			return false;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
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
		protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		}

		@Override
		public void setNoGravity(boolean ignored) {
			super.setNoGravity(true);
		}

		public void livingTick() {
			super.livingTick();
			this.setNoGravity(true);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(new ResourceLocation("skizzik:textures/cosmic_skizzie_glow.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}

	// Made with Blockbench 3.7.5
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelcosmic_skizzie extends EntityModel<Entity> {
		private final ModelRenderer cosmic_skizzie;
		private final ModelRenderer body;
		private final ModelRenderer body2;
		private final ModelRenderer head1;
		public Modelcosmic_skizzie() {
			textureWidth = 128;
			textureHeight = 128;
			cosmic_skizzie = new ModelRenderer(this);
			cosmic_skizzie.setRotationPoint(0.0F, 17.0F, 0.0F);
			body = new ModelRenderer(this);
			body.setRotationPoint(-0.5F, -6.0F, 1.0F);
			cosmic_skizzie.addChild(body);
			setRotationAngle(body, 0.4363F, 0.0F, 0.0F);
			body.setTextureOffset(46, 19).addBox(-2.5F, 1.2901F, -2.4515F, 5.0F, 8.0F, 5.0F, 0.0F, false);
			body.setTextureOffset(32, 48).addBox(-1.5F, 0.2901F, -1.4515F, 3.0F, 11.0F, 3.0F, 0.0F, false);
			body.setTextureOffset(0, 0).addBox(-0.5F, 10.2901F, -0.4515F, 1.0F, 4.0F, 1.0F, 0.0F, false);
			body2 = new ModelRenderer(this);
			body2.setRotationPoint(-0.5F, -17.1F, 1.0F);
			cosmic_skizzie.addChild(body2);
			body2.setTextureOffset(0, 44).addBox(-2.5F, -7.0F, -2.5F, 5.0F, 14.0F, 5.0F, 0.0F, false);
			body2.setTextureOffset(20, 44).addBox(-1.5F, -9.0F, -1.5F, 3.0F, 18.0F, 3.0F, 0.0F, false);
			body2.setTextureOffset(44, 48).addBox(-0.5F, -10.0F, -0.5F, 1.0F, 20.0F, 1.0F, 0.0F, false);
			body2.setTextureOffset(38, 40).addBox(-8.5F, -6.5F, -2.0F, 17.0F, 4.0F, 4.0F, 0.0F, false);
			body2.setTextureOffset(36, 0).addBox(-8.5F, -2.0F, -2.0F, 17.0F, 4.0F, 4.0F, 0.0F, false);
			body2.setTextureOffset(0, 36).addBox(-8.5F, 2.5F, -2.0F, 17.0F, 4.0F, 4.0F, 0.0F, false);
			body2.setTextureOffset(0, 32).addBox(-10.5F, 3.5F, -1.0F, 21.0F, 2.0F, 2.0F, 0.0F, false);
			body2.setTextureOffset(0, 28).addBox(-10.5F, -1.0F, -1.0F, 21.0F, 2.0F, 2.0F, 0.0F, false);
			body2.setTextureOffset(0, 24).addBox(-10.5F, -5.5F, -1.0F, 21.0F, 2.0F, 2.0F, 0.0F, false);
			head1 = new ModelRenderer(this);
			head1.setRotationPoint(-0.5F, -34.0F, 0.0F);
			cosmic_skizzie.addChild(head1);
			head1.setTextureOffset(0, 0).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
			head1.setTextureOffset(48, 48).addBox(-5.0F, -8.0F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
			head1.setTextureOffset(48, 56).addBox(-5.0F, -9.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
			head1.setTextureOffset(40, 32).addBox(3.0F, -8.0F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
			head1.setTextureOffset(48, 8).addBox(4.0F, -9.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			cosmic_skizzie.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head1.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head1.rotateAngleX = f4 / (180F / (float) Math.PI);
		}
	}
}
