
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
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
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
		ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 500);
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
			this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(5, new SwimGoal(this));
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

	// Made with Blockbench 3.6.6
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelprismarine_colossus extends EntityModel<Entity> {
		private final ModelRenderer head;
		private final ModelRenderer rightArm;
		private final ModelRenderer leftArm;
		private final ModelRenderer rightLeg;
		private final ModelRenderer leftLeg;
		private final ModelRenderer body;
		public Modelprismarine_colossus() {
			textureWidth = 512;
			textureHeight = 256;
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, -120.0F, 0.0F);
			head.setTextureOffset(0, 168).addBox(-24.0F, -46.0F, -16.0F, 48.0F, 48.0F, 40.0F, 0.0F, false);
			head.setTextureOffset(0, 30).addBox(-20.0F, -48.0F, -22.0F, 40.0F, 2.0F, 40.0F, 0.0F, false);
			head.setTextureOffset(144, 116).addBox(-22.0F, -33.5F, -24.0F, 8.0F, 44.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(464, 184).addBox(-14.0F, -9.5F, -28.0F, 8.0F, 26.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(464, 184).addBox(6.0F, -9.5F, -28.0F, 8.0F, 26.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(144, 116).addBox(14.0F, -33.5F, -24.0F, 8.0F, 44.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(144, 80).addBox(6.0F, -29.5F, -24.0F, 8.0F, 28.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(464, 218).addBox(-6.0F, -29.5F, -24.0F, 12.0F, 30.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(308, 51).addBox(-6.0F, -17.5F, -28.0F, 12.0F, 7.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(272, 16).addBox(-14.0F, -17.5F, -28.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(272, 16).addBox(6.0F, -17.5F, -28.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(120, 1).addBox(-26.0F, -17.5F, -28.0F, 12.0F, 4.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(120, 1).addBox(14.0F, -17.5F, -28.0F, 12.0F, 4.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(272, 16).addBox(-26.0F, -25.5F, -28.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(0, 14).addBox(-26.0F, -33.5F, -29.0F, 52.0F, 8.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(0, 6).addBox(-24.0F, -32.5F, -30.0F, 48.0F, 7.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(272, 16).addBox(18.0F, -25.5F, -28.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(272, 32).addBox(-5.0F, -25.5F, -28.0F, 10.0F, 8.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(336, 92).addBox(-5.0F, -32.5F, -31.0F, 10.0F, 11.0F, 3.0F, 0.0F, false);
			head.setTextureOffset(272, 48).addBox(-7.0F, -46.5F, -32.0F, 14.0F, 14.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(272, 48).addBox(-7.0F, -46.5F, -28.0F, 14.0F, 14.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(272, 48).addBox(-7.0F, -46.5F, -24.0F, 14.0F, 14.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(272, 48).addBox(-7.0F, -46.5F, -20.0F, 14.0F, 14.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(120, 13).addBox(-24.0F, -46.0F, -32.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(120, 13).addBox(7.0F, -46.0F, -32.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(120, 13).addBox(-24.0F, -46.0F, -28.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(120, 13).addBox(7.0F, -46.0F, -28.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(120, 13).addBox(-24.0F, -46.0F, -24.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(120, 13).addBox(7.0F, -46.0F, -24.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(120, 13).addBox(-24.0F, -46.0F, -20.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(120, 13).addBox(7.0F, -46.0F, -20.0F, 17.0F, 13.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(464, 168).addBox(-18.0F, -25.5F, -25.0F, 13.0F, 8.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(464, 168).addBox(5.0F, -25.5F, -25.0F, 13.0F, 8.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(144, 80).addBox(-14.0F, -29.5F, -24.0F, 8.0F, 28.0F, 8.0F, 0.0F, false);
			rightArm = new ModelRenderer(this);
			rightArm.setRotationPoint(-4.0F, -112.0F, 0.0F);
			rightArm.setTextureOffset(176, 160).addBox(-44.0F, -8.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
			rightArm.setTextureOffset(362, 118).addBox(-45.0F, 32.0F, -13.0F, 26.0F, 16.0F, 26.0F, 0.0F, false);
			rightArm.setTextureOffset(176, 10).addBox(-46.0F, -9.0F, -13.5F, 18.0F, 27.0F, 27.0F, 0.0F, false);
			leftArm = new ModelRenderer(this);
			leftArm.setRotationPoint(4.0F, -112.0F, 0.0F);
			leftArm.setTextureOffset(176, 64).addBox(20.0F, -8.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
			leftArm.setTextureOffset(362, 76).addBox(19.0F, 32.0F, -13.0F, 26.0F, 16.0F, 26.0F, 0.0F, false);
			leftArm.setTextureOffset(272, 106).addBox(28.0F, -9.0F, -13.5F, 18.0F, 27.0F, 27.0F, 0.0F, false);
			rightLeg = new ModelRenderer(this);
			rightLeg.setRotationPoint(0.0F, -48.0F, 0.0F);
			rightLeg.setTextureOffset(272, 160).addBox(-24.0F, 0.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
			rightLeg.setTextureOffset(272, 86).addBox(-24.0F, 60.0F, -20.0F, 24.0F, 12.0F, 8.0F, 0.0F, false);
			leftLeg = new ModelRenderer(this);
			leftLeg.setRotationPoint(0.0F, -48.0F, 0.0F);
			leftLeg.setTextureOffset(368, 160).addBox(0.0F, 0.0F, -12.0F, 24.0F, 72.0F, 24.0F, 0.0F, false);
			leftLeg.setTextureOffset(272, 66).addBox(0.0F, 60.0F, -20.0F, 24.0F, 12.0F, 8.0F, 0.0F, false);
			body = new ModelRenderer(this);
			body.setRotationPoint(-24.0F, -119.5F, 2.0F);
			body.setTextureOffset(0, 72).addBox(0.0F, -0.5F, -14.0F, 48.0F, 72.0F, 24.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
			leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
			rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
			leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
			body.render(matrixStack, buffer, packedLight, packedOverlay);
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
