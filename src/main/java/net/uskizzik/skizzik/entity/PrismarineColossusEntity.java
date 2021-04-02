
package net.uskizzik.skizzik.entity;

import net.uskizzik.skizzik.itemgroup.PrismarineSecretsTabItemGroup;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.BossInfo;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.controller.MovementController;
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

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@SkizzikModElements.ModElement.Tag
public class PrismarineColossusEntity extends SkizzikModElements.ModElement {
	public static EntityType entity = null;
	public PrismarineColossusEntity(SkizzikModElements instance) {
		super(instance, 288);
		FMLJavaModLoadingContext.get().getModEventBus().register(new ModelRegisterHandler());
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(5.5f, 12.200000000000001f))
						.build("prismarine_colossus").setRegistryName("prismarine_colossus");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -14335177, -11037300, new Item.Properties().group(PrismarineSecretsTabItemGroup.tab))
				.setRegistryName("prismarine_colossus_spawn_egg"));
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
				return new MobRenderer(renderManager, new Modelprismarine_colossus(), 2.9f) {
					{
						this.addLayer(new GlowingLayer<>(this));
					}
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("skizzik:textures/prismarine_colossus.png");
					}
				};
			});
		}
	}
	private void setupAttributes() {
		AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
		ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3);
		ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 300);
		ammma = ammma.createMutableAttribute(Attributes.ARMOR, 6);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3);
		ammma = ammma.createMutableAttribute(ForgeMod.SWIM_SPEED.get(), 0.3);
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
			this.moveController = new MovementController(this) {
				@Override
				public void tick() {
					if (CustomEntity.this.areEyesInFluid(FluidTags.WATER))
						CustomEntity.this.setMotion(CustomEntity.this.getMotion().add(0, 0.005, 0));
					if (this.action == MovementController.Action.MOVE_TO && !CustomEntity.this.getNavigator().noPath()) {
						double dx = this.posX - CustomEntity.this.getPosX();
						double dy = this.posY - CustomEntity.this.getPosY();
						double dz = this.posZ - CustomEntity.this.getPosZ();
						dy = dy / (double) MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
						CustomEntity.this.rotationYaw = this.limitAngle(CustomEntity.this.rotationYaw,
								(float) (MathHelper.atan2(dz, dx) * (double) (180 / (float) Math.PI)) - 90, 90);
						CustomEntity.this.renderYawOffset = CustomEntity.this.rotationYaw;
						CustomEntity.this.setAIMoveSpeed(MathHelper.lerp(0.125f, CustomEntity.this.getAIMoveSpeed(),
								(float) (this.speed * CustomEntity.this.getAttributeValue(Attributes.MOVEMENT_SPEED))));
						CustomEntity.this.setMotion(CustomEntity.this.getMotion().add(0, CustomEntity.this.getAIMoveSpeed() * dy * 0.1, 0));
					} else {
						CustomEntity.this.setAIMoveSpeed(0);
					}
				}
			};
			this.navigator = new SwimmerPathNavigator(this, this.world);
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
			return CreatureAttribute.WATER;
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
		public boolean canBreatheUnderwater() {
			return true;
		}

		@Override
		public boolean isNotColliding(IWorldReader worldreader) {
			return worldreader.checkNoEntityCollision(this, VoxelShapes.create(this.getBoundingBox()));
		}

		@Override
		public boolean isPushedByWater() {
			return false;
		}

		@Override
		public boolean isNonBoss() {
			return false;
		}
		private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS);
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

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			IVertexBuilder ivertexbuilder = bufferIn
					.getBuffer(RenderType.getEyes(new ResourceLocation("skizzik:textures/prismarine_colossus_glow.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}

	// Made with Blockbench 3.8.2
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelprismarine_colossus extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer head;
		private final ModelRenderer rightArm;
		private final ModelRenderer rightItem;
		private final ModelRenderer leftArm;
		private final ModelRenderer rightLeg;
		private final ModelRenderer leftLeg;
		public Modelprismarine_colossus() {
			textureWidth = 384;
			textureHeight = 384;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 24.0F, 0.0F);
			body.setTextureOffset(96, 96).addBox(-24.0F, -144.0F, -12.0F, 48.0F, 72.0F, 24.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(-0.5238F, -118.2262F, 0.5238F);
			head.setTextureOffset(0, 0).addBox(-25.4762F, -49.7738F, -14.5238F, 51.0F, 48.0F, 38.0F, 0.01F, false);
			head.setTextureOffset(140, 0).addBox(-25.4762F, -49.7738F, -27.5238F, 51.0F, 15.0F, 13.0F, 0.01F, false);
			head.setTextureOffset(178, 44).addBox(-23.4762F, -34.7738F, -22.5238F, 8.0F, 44.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(178, 44).addBox(-15.4762F, -34.7738F, -22.5238F, 8.0F, 28.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(178, 44).addBox(0.0238F, -26.7738F, -22.5238F, 8.0F, 28.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(178, 44).addBox(-7.9762F, -26.7738F, -22.5238F, 8.0F, 28.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(178, 44).addBox(7.5238F, -34.7738F, -22.5238F, 8.0F, 28.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(210, 57).addBox(-5.9762F, -34.7738F, -28.5238F, 12.0F, 13.0F, 12.0F, 0.01F, false);
			head.setTextureOffset(210, 82).addBox(-22.9762F, -33.7738F, -28.5238F, 17.0F, 7.0F, 1.0F, 0.01F, false);
			head.setTextureOffset(216, 90).addBox(6.0238F, -33.7738F, -28.5238F, 17.0F, 7.0F, 1.0F, 0.01F, false);
			head.setTextureOffset(210, 44).addBox(-24.4762F, -34.7738F, -27.5238F, 49.0F, 8.0F, 5.0F, 0.01F, false);
			head.setTextureOffset(258, 57).addBox(-24.4762F, -26.7738F, -27.5238F, 6.0F, 8.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(317, 112).addBox(-18.4762F, -26.7738F, -26.5238F, 13.0F, 1.0F, 6.0F, 0.01F, false);
			head.setTextureOffset(317, 112).addBox(5.5238F, -26.7738F, -26.5238F, 13.0F, 1.0F, 6.0F, 0.01F, false);
			head.setTextureOffset(316, 111).addBox(-18.4762F, -19.7738F, -26.5238F, 13.0F, 1.0F, 6.0F, 0.01F, false);
			head.setTextureOffset(316, 111).addBox(5.5238F, -19.7738F, -26.5238F, 13.0F, 1.0F, 6.0F, 0.01F, false);
			head.setTextureOffset(286, 57).addBox(-17.4762F, -25.7738F, -24.5238F, 11.0F, 6.0F, 0.0F, 0.01F, false);
			head.setTextureOffset(286, 57).addBox(6.5238F, -25.7738F, -24.5238F, 11.0F, 6.0F, 0.0F, 0.01F, false);
			head.setTextureOffset(328, 105).addBox(-18.4762F, -26.7738F, -26.5238F, 1.0F, 8.0F, 6.0F, 0.01F, false);
			head.setTextureOffset(328, 105).addBox(5.5238F, -26.7738F, -26.5238F, 1.0F, 8.0F, 6.0F, 0.01F, false);
			head.setTextureOffset(329, 98).addBox(-6.4762F, -26.7738F, -26.5238F, 1.0F, 8.0F, 6.0F, 0.01F, false);
			head.setTextureOffset(329, 98).addBox(17.5238F, -26.7738F, -26.5238F, 1.0F, 8.0F, 6.0F, 0.01F, false);
			head.setTextureOffset(268, 0).addBox(-24.4762F, -18.7738F, -27.5238F, 49.0F, 4.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(350, 12).addBox(-13.4762F, -10.7738F, -27.5238F, 9.0F, 28.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(323, 107).addBox(-4.4762F, -10.7738F, -22.5238F, 9.0F, 4.0F, 1.0F, 0.02F, false);
			head.setTextureOffset(323, 107).addBox(-4.4762F, -6.7738F, -22.5238F, 1.0F, 8.0F, 1.0F, 0.02F, false);
			head.setTextureOffset(323, 107).addBox(3.5238F, -6.7738F, -22.5238F, 1.0F, 8.0F, 1.0F, 0.02F, false);
			head.setTextureOffset(323, 101).addBox(-15.4762F, -6.7738F, -22.5238F, 7.0F, 1.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(322, 95).addBox(7.5238F, -6.7738F, -22.5238F, 7.0F, 1.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(327, 95).addBox(-15.4762F, -6.7738F, -22.5238F, 1.0F, 16.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(322, 95).addBox(14.5238F, -6.7738F, -22.5238F, 1.0F, 16.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(350, 48).addBox(4.5238F, -10.7738F, -27.5238F, 9.0F, 28.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(268, 12).addBox(-13.4762F, -14.7738F, -27.5238F, 27.0F, 4.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(258, 57).addBox(18.5238F, -26.7738F, -27.5238F, 6.0F, 8.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(258, 73).addBox(-5.4762F, -26.7738F, -27.5238F, 11.0F, 8.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(178, 44).addBox(15.5238F, -34.7738F, -22.5238F, 8.0F, 44.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(178, 44).addBox(15.5238F, -34.7738F, 13.4762F, 8.0F, 44.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(178, 44).addBox(-3.9762F, -34.7738F, 13.4762F, 8.0F, 44.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(178, 44).addBox(-23.4762F, -34.7738F, 13.4762F, 8.0F, 44.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(178, 44).addBox(-13.4762F, -26.7738F, 13.4762F, 8.0F, 44.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(178, 44).addBox(5.5238F, -26.7738F, 13.4762F, 8.0F, 44.0F, 8.0F, 0.01F, false);
			head.setTextureOffset(178, 28).addBox(-23.4762F, -49.7738F, -28.5238F, 47.0F, 15.0F, 1.0F, 0.01F, false);
			rightArm = new ModelRenderer(this);
			rightArm.setRotationPoint(-25.5F, -110.0F, 0.0F);
			rightArm.setTextureOffset(240, 96).addBox(-22.5F, -10.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
			rightArm.setTextureOffset(288, 192).addBox(-24.0F, -11.5F, -13.5F, 18.0F, 27.0F, 27.0F, 0.0F, false);
			rightItem = new ModelRenderer(this);
			rightItem.setRotationPoint(19.0F, 119.75F, 1.0F);
			rightArm.addChild(rightItem);
			leftArm = new ModelRenderer(this);
			leftArm.setRotationPoint(26.25F, -110.0F, 0.0F);
			leftArm.setTextureOffset(192, 288).addBox(-2.25F, -10.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
			leftArm.setTextureOffset(288, 285).addBox(5.25F, -11.5F, -13.5F, 18.0F, 27.0F, 27.0F, 0.0F, false);
			rightLeg = new ModelRenderer(this);
			rightLeg.setRotationPoint(-12.15F, -48.0F, 0.0F);
			rightLeg.setTextureOffset(0, 96).addBox(-11.9F, 0.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
			rightLeg.setTextureOffset(0, 228).addBox(-12.1F, 60.0F, -12.0F, 24.0F, 12.0F, 24.0F, 1.01F, false);
			leftLeg = new ModelRenderer(this);
			leftLeg.setRotationPoint(12.15F, -48.0F, 0.0F);
			leftLeg.setTextureOffset(96, 288).addBox(-12.1F, 0.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
			leftLeg.setTextureOffset(0, 192).addBox(-11.9F, 60.0F, -12.0F, 24.0F, 12.0F, 24.0F, 1.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
			leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
			rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
			leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.rightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.rightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.leftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.leftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		}
	}
}
