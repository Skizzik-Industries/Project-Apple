
package net.uskizzik.skizzik.entity;

import net.uskizzik.skizzik.procedures.SkizzieRightClickedOnEntityProcedure;
import net.uskizzik.skizzik.procedures.SkizzieEntityDiesProcedure;
import net.uskizzik.skizzik.procedures.FriendlyWitchSkizziePlayerCollidesWithThisEntityProcedure;
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
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ActionResultType;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.BlockState;

import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@SkizzikModElements.ModElement.Tag
public class FriendlyWitchSkizzie3Entity extends SkizzikModElements.ModElement {
	public static EntityType entity = null;
	public FriendlyWitchSkizzie3Entity(SkizzikModElements instance) {
		super(instance, 243);
		FMLJavaModLoadingContext.get().getModEventBus().register(new ModelRegisterHandler());
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.6f, 1.5999999999999999f))
						.build("friendly_witch_skizzie_3").setRegistryName("friendly_witch_skizzie_3");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -16711681, -16711885, new Item.Properties().group(TemplateTabItemGroup.tab))
				.setRegistryName("friendly_witch_skizzie_3_spawn_egg"));
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
				return new MobRenderer(renderManager, new Modelwitch_skizzie(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("skizzik:textures/friendly_witch_skizzie.png");
					}
				};
			});
		}
	}
	private void setupAttributes() {
		AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
		ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3);
		ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 20);
		ammma = ammma.createMutableAttribute(Attributes.ARMOR, 4);
		ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 8);
		ammma = ammma.createMutableAttribute(Attributes.FLYING_SPEED, 0.3);
		GlobalEntityTypeAttributes.put(entity, ammma.create());
	}
	public static class CustomEntity extends CreatureEntity {
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
			this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true));
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, MonsterEntity.class, false, true));
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
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		public boolean onLivingFall(float l, float d) {
			return false;
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
				SkizzieEntityDiesProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public ActionResultType func_230254_b_(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			ActionResultType retval = ActionResultType.func_233537_a_(this.world.isRemote());
			super.func_230254_b_(sourceentity, hand);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("sourceentity", sourceentity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				SkizzieRightClickedOnEntityProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}

		@Override
		public void onCollideWithPlayer(PlayerEntity sourceentity) {
			super.onCollideWithPlayer(sourceentity);
			Entity entity = this;
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("sourceentity", sourceentity);
				FriendlyWitchSkizziePlayerCollidesWithThisEntityProcedure.executeProcedure($_dependencies);
			}
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

	// Made with Blockbench 3.7.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelwitch_skizzie extends EntityModel<Entity> {
		private final ModelRenderer body1;
		private final ModelRenderer body2;
		private final ModelRenderer head1;
		public Modelwitch_skizzie() {
			textureWidth = 64;
			textureHeight = 64;
			body1 = new ModelRenderer(this);
			body1.setRotationPoint(2.0F, 6.9F, -0.5F);
			body1.setTextureOffset(0, 28).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, 0.0F, false);
			body1.setTextureOffset(0, 24).addBox(-8.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, false);
			body1.setTextureOffset(0, 20).addBox(-8.0F, 4.0F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, false);
			body1.setTextureOffset(0, 16).addBox(-8.0F, 6.5F, 0.5F, 11.0F, 2.0F, 2.0F, 0.0F, false);
			body2 = new ModelRenderer(this);
			body2.setRotationPoint(2.0F, 16.9F, -0.5F);
			setRotationAngle(body2, 0.6109F, 0.0F, 0.0F);
			body2.setTextureOffset(12, 29).addBox(-4.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);
			head1 = new ModelRenderer(this);
			head1.setRotationPoint(0.0F, 3.0F, 0.0F);
			head1.setTextureOffset(0, 0).addBox(-4.5F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
			head1.setTextureOffset(30, 19).addBox(-1.5F, -9.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
			head1.setTextureOffset(24, 0).addBox(-1.5F, -7.0F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
			head1.setTextureOffset(24, 16).addBox(-1.5F, -12.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			head1.setTextureOffset(0, 0).addBox(-1.5F, -11.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head1.setTextureOffset(32, 8).addBox(-2.5F, -9.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
			head1.setTextureOffset(24, 29).addBox(-3.5F, -7.0F, -1.0F, 6.0F, 2.0F, 2.0F, 0.0F, false);
			head1.setTextureOffset(26, 16).addBox(-4.5F, -5.0F, -1.0F, 8.0F, 1.0F, 2.0F, 0.0F, false);
			head1.setTextureOffset(30, 25).addBox(-3.5F, -5.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			head1.setTextureOffset(32, 12).addBox(0.5F, -5.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			head1.setTextureOffset(0, 4).addBox(0.5F, -5.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			head1.setTextureOffset(24, 0).addBox(-2.5F, -7.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			head1.setTextureOffset(24, 3).addBox(0.5F, -7.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			head1.setTextureOffset(9, 28).addBox(0.5F, -7.0F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			head1.setTextureOffset(30, 19).addBox(-2.5F, -7.0F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			head1.setTextureOffset(24, 33).addBox(-3.5F, -5.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
			head1.setTextureOffset(18, 20).addBox(-1.5F, -5.0F, -4.0F, 2.0F, 1.0F, 8.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body1.render(matrixStack, buffer, packedLight, packedOverlay);
			body2.render(matrixStack, buffer, packedLight, packedOverlay);
			head1.render(matrixStack, buffer, packedLight, packedOverlay);
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
