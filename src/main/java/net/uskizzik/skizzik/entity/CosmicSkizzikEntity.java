
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
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.BlockState;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@SkizzikModElements.ModElement.Tag
public class CosmicSkizzikEntity extends SkizzikModElements.ModElement {
	public static EntityType entity = null;
	public CosmicSkizzikEntity(SkizzikModElements instance) {
		super(instance, 264);
		FMLJavaModLoadingContext.get().getModEventBus().register(new ModelRegisterHandler());
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("cosmic_skizzik")
						.setRegistryName("cosmic_skizzik");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -1, -1, new Item.Properties().group(TemplateTabItemGroup.tab))
				.setRegistryName("cosmic_skizzik_spawn_egg"));
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
				return new MobRenderer(renderManager, new Modelcosmic_skizzik(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("skizzik:textures/cosmic_skizzik.png");
					}
				};
			});
		}
	}
	private void setupAttributes() {
		AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
		ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3);
		ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 10);
		ammma = ammma.createMutableAttribute(Attributes.ARMOR, 0);
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
		public boolean onLivingFall(float l, float d) {
			return false;
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

	// Made with Blockbench 3.7.5
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelcosmic_skizzik extends EntityModel<Entity> {
		private final ModelRenderer cosmic_skizzik;
		private final ModelRenderer body;
		private final ModelRenderer tentacles;
		private final ModelRenderer cube_r1;
		private final ModelRenderer cube_r2;
		private final ModelRenderer body_r1;
		private final ModelRenderer tentacles2;
		private final ModelRenderer cube_r3;
		private final ModelRenderer body_r2;
		private final ModelRenderer cube_r4;
		private final ModelRenderer tentacles3;
		private final ModelRenderer cube_r5;
		private final ModelRenderer body_r3;
		private final ModelRenderer cube_r6;
		private final ModelRenderer tentacles4;
		private final ModelRenderer cube_r7;
		private final ModelRenderer body_r4;
		private final ModelRenderer cube_r8;
		private final ModelRenderer tentacles5;
		private final ModelRenderer cube_r9;
		private final ModelRenderer body_r5;
		private final ModelRenderer cube_r10;
		private final ModelRenderer body2;
		private final ModelRenderer head1;
		private final ModelRenderer sphere;
		private final ModelRenderer cube_r11;
		private final ModelRenderer cube_r12;
		private final ModelRenderer cube_r13;
		private final ModelRenderer cube_r14;
		private final ModelRenderer cube_r15;
		private final ModelRenderer cube_r16;
		private final ModelRenderer cube_r17;
		private final ModelRenderer cube_r18;
		private final ModelRenderer cube_r19;
		private final ModelRenderer cube_r20;
		private final ModelRenderer cube_r21;
		private final ModelRenderer cube_r22;
		private final ModelRenderer cube_r23;
		private final ModelRenderer cube_r24;
		private final ModelRenderer cube_r25;
		private final ModelRenderer cube_r26;
		private final ModelRenderer cube_r27;
		private final ModelRenderer cube_r28;
		private final ModelRenderer cube_r29;
		private final ModelRenderer cube_r30;
		private final ModelRenderer cube_r31;
		private final ModelRenderer cube_r32;
		private final ModelRenderer cube_r33;
		private final ModelRenderer cube_r34;
		private final ModelRenderer cube_r35;
		private final ModelRenderer cube_r36;
		private final ModelRenderer cube_r37;
		private final ModelRenderer cube_r38;
		private final ModelRenderer cube_r39;
		private final ModelRenderer cube_r40;
		private final ModelRenderer cube_r41;
		private final ModelRenderer cube_r42;
		private final ModelRenderer cube_r43;
		private final ModelRenderer cube_r44;
		private final ModelRenderer cube_r45;
		private final ModelRenderer cube_r46;
		private final ModelRenderer cube_r47;
		private final ModelRenderer cube_r48;
		private final ModelRenderer cube_r49;
		private final ModelRenderer cube_r50;
		private final ModelRenderer cube_r51;
		private final ModelRenderer cube_r52;
		private final ModelRenderer cube_r53;
		private final ModelRenderer cube_r54;
		private final ModelRenderer cube_r55;
		private final ModelRenderer cube_r56;
		private final ModelRenderer cube_r57;
		private final ModelRenderer cube_r58;
		private final ModelRenderer cube_r59;
		private final ModelRenderer cube_r60;
		private final ModelRenderer cube_r61;
		private final ModelRenderer cube_r62;
		private final ModelRenderer cube_r63;
		private final ModelRenderer cube_r64;
		private final ModelRenderer cube_r65;
		private final ModelRenderer cube_r66;
		private final ModelRenderer cube_r67;
		private final ModelRenderer cube_r68;
		private final ModelRenderer cube_r69;
		private final ModelRenderer cube_r70;
		private final ModelRenderer cube_r71;
		private final ModelRenderer cube_r72;
		private final ModelRenderer cube_r73;
		private final ModelRenderer cube_r74;
		private final ModelRenderer cube_r75;
		private final ModelRenderer cube_r76;
		private final ModelRenderer cube_r77;
		private final ModelRenderer cube_r78;
		private final ModelRenderer cube_r79;
		private final ModelRenderer cube_r80;
		private final ModelRenderer cube_r81;
		private final ModelRenderer cube_r82;
		private final ModelRenderer cube_r83;
		private final ModelRenderer cube_r84;
		private final ModelRenderer cube_r85;
		private final ModelRenderer cube_r86;
		private final ModelRenderer cube_r87;
		private final ModelRenderer cube_r88;
		private final ModelRenderer cube_r89;
		private final ModelRenderer cube_r90;
		private final ModelRenderer cube_r91;
		private final ModelRenderer cube_r92;
		private final ModelRenderer cube_r93;
		private final ModelRenderer cube_r94;
		private final ModelRenderer cube_r95;
		private final ModelRenderer cube_r96;
		private final ModelRenderer cube_r97;
		private final ModelRenderer cube_r98;
		private final ModelRenderer cube_r99;
		private final ModelRenderer head2;
		private final ModelRenderer head3;
		private final ModelRenderer head4;
		private final ModelRenderer head5;
		public Modelcosmic_skizzik() {
			textureWidth = 512;
			textureHeight = 512;
			cosmic_skizzik = new ModelRenderer(this);
			cosmic_skizzik.setRotationPoint(0.0F, 5.0F, 0.0F);
			body = new ModelRenderer(this);
			body.setRotationPoint(0.5F, -20.975F, 1.0F);
			cosmic_skizzik.addChild(body);
			body.setTextureOffset(47, 106).addBox(-4.5F, -10.125F, -4.5F, 9.0F, 22.0F, 9.0F, 0.0F, false);
			body.setTextureOffset(158, 68).addBox(-3.5F, -11.125F, -3.5F, 7.0F, 24.0F, 7.0F, 0.0F, false);
			body.setTextureOffset(100, 56).addBox(-13.5F, -9.625F, -3.0F, 27.0F, 6.0F, 6.0F, 0.0F, false);
			body.setTextureOffset(56, 94).addBox(-13.5F, -2.125F, -3.0F, 27.0F, 6.0F, 6.0F, 0.0F, false);
			body.setTextureOffset(90, 36).addBox(-13.5F, 5.375F, -3.0F, 27.0F, 6.0F, 6.0F, 0.0F, false);
			body.setTextureOffset(86, 48).addBox(-16.5F, -8.625F, -2.0F, 33.0F, 4.0F, 4.0F, 0.0F, false);
			body.setTextureOffset(0, 48).addBox(-21.5F, -7.625F, -1.0F, 43.0F, 2.0F, 2.0F, 0.0F, false);
			body.setTextureOffset(42, 86).addBox(-16.5F, -1.125F, -2.0F, 33.0F, 4.0F, 4.0F, 0.0F, false);
			body.setTextureOffset(0, 44).addBox(-21.5F, -0.125F, -1.0F, 43.0F, 2.0F, 2.0F, 0.0F, false);
			body.setTextureOffset(80, 28).addBox(-16.5F, 6.375F, -2.0F, 33.0F, 4.0F, 4.0F, 0.0F, false);
			body.setTextureOffset(0, 40).addBox(-21.5F, 7.375F, -1.0F, 43.0F, 2.0F, 2.0F, 0.0F, false);
			tentacles = new ModelRenderer(this);
			tentacles.setRotationPoint(0.0F, 0.0F, 0.0F);
			body.addChild(tentacles);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(62.0F, -39.0F, 4.0F);
			tentacles.addChild(cube_r1);
			setRotationAngle(cube_r1, 1.1345F, 0.0F, 0.9599F);
			cube_r1.setTextureOffset(160, 232).addBox(-7.6F, -60.025F, 0.5F, 5.0F, 20.0F, 1.0F, 0.0F, false);
			cube_r1.setTextureOffset(154, 0).addBox(-8.6F, -40.025F, -0.5F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(28.0F, -25.0F, 20.0F);
			tentacles.addChild(cube_r2);
			setRotationAngle(cube_r2, 0.4363F, 0.0F, 0.9599F);
			cube_r2.setTextureOffset(155, 155).addBox(-0.6F, -40.325F, -0.2F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			body_r1 = new ModelRenderer(this);
			body_r1.setRotationPoint(9.0F, -8.125F, 9.5F);
			tentacles.addChild(body_r1);
			setRotationAngle(body_r1, -0.4363F, 0.0F, 0.9599F);
			body_r1.setTextureOffset(155, 110).addBox(-3.5F, -28.9F, -2.3F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			tentacles2 = new ModelRenderer(this);
			tentacles2.setRotationPoint(14.0F, 9.0F, -1.0F);
			body.addChild(tentacles2);
			setRotationAngle(tentacles2, 0.0F, 0.0F, -1.8675F);
			cube_r3 = new ModelRenderer(this);
			cube_r3.setRotationPoint(41.0F, -32.0F, 20.0F);
			tentacles2.addChild(cube_r3);
			setRotationAngle(cube_r3, 0.4363F, 0.0F, 0.9599F);
			cube_r3.setTextureOffset(140, 68).addBox(-0.6F, -40.325F, -0.2F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			body_r2 = new ModelRenderer(this);
			body_r2.setRotationPoint(22.0F, -15.125F, 9.5F);
			tentacles2.addChild(body_r2);
			setRotationAngle(body_r2, -0.4363F, 0.0F, 0.9599F);
			body_r2.setTextureOffset(0, 151).addBox(-3.5F, -28.9F, -2.3F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			cube_r4 = new ModelRenderer(this);
			cube_r4.setRotationPoint(75.0F, -46.0F, 4.0F);
			tentacles2.addChild(cube_r4);
			setRotationAngle(cube_r4, 1.1345F, 0.0F, 0.9599F);
			cube_r4.setTextureOffset(148, 232).addBox(-7.6F, -60.025F, 0.5F, 5.0F, 20.0F, 1.0F, 0.0F, false);
			cube_r4.setTextureOffset(18, 151).addBox(-8.6F, -40.025F, -0.5F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			tentacles3 = new ModelRenderer(this);
			tentacles3.setRotationPoint(15.0F, -13.0F, -1.0F);
			body.addChild(tentacles3);
			setRotationAngle(tentacles3, 0.0F, 0.0F, -3.1416F);
			cube_r5 = new ModelRenderer(this);
			cube_r5.setRotationPoint(41.0F, -32.0F, 20.0F);
			tentacles3.addChild(cube_r5);
			setRotationAngle(cube_r5, 0.4363F, 0.0F, 0.9599F);
			cube_r5.setTextureOffset(36, 137).addBox(-0.6F, -40.325F, -0.2F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			body_r3 = new ModelRenderer(this);
			body_r3.setRotationPoint(22.0F, -15.125F, 9.5F);
			tentacles3.addChild(body_r3);
			setRotationAngle(body_r3, -0.4363F, 0.0F, 0.9599F);
			body_r3.setTextureOffset(54, 137).addBox(-3.5F, -28.9F, -2.3F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			cube_r6 = new ModelRenderer(this);
			cube_r6.setRotationPoint(75.0F, -46.0F, 4.0F);
			tentacles3.addChild(cube_r6);
			setRotationAngle(cube_r6, 1.1345F, 0.0F, 0.9599F);
			cube_r6.setTextureOffset(136, 232).addBox(-7.6F, -60.025F, 0.5F, 5.0F, 20.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(137, 137).addBox(-8.6F, -40.025F, -0.5F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			tentacles4 = new ModelRenderer(this);
			tentacles4.setRotationPoint(-14.0F, -13.0F, -1.0F);
			body.addChild(tentacles4);
			setRotationAngle(tentacles4, 0.0F, 0.0F, 1.2217F);
			cube_r7 = new ModelRenderer(this);
			cube_r7.setRotationPoint(41.0F, -32.0F, 20.0F);
			tentacles4.addChild(cube_r7);
			setRotationAngle(cube_r7, 0.4363F, 0.0F, 0.9599F);
			cube_r7.setTextureOffset(122, 68).addBox(-0.6F, -40.325F, -0.2F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			body_r4 = new ModelRenderer(this);
			body_r4.setRotationPoint(22.0F, -15.125F, 9.5F);
			tentacles4.addChild(body_r4);
			setRotationAngle(body_r4, -0.4363F, 0.0F, 0.9599F);
			body_r4.setTextureOffset(83, 130).addBox(-3.5F, -28.9F, -2.3F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			cube_r8 = new ModelRenderer(this);
			cube_r8.setRotationPoint(75.0F, -46.0F, 4.0F);
			tentacles4.addChild(cube_r8);
			setRotationAngle(cube_r8, 1.1345F, 0.0F, 0.9599F);
			cube_r8.setTextureOffset(227, 231).addBox(-7.6F, -60.025F, 0.5F, 5.0F, 20.0F, 1.0F, 0.0F, false);
			cube_r8.setTextureOffset(101, 130).addBox(-8.6F, -40.025F, -0.5F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			tentacles5 = new ModelRenderer(this);
			tentacles5.setRotationPoint(0.0F, 17.0F, -1.0F);
			body.addChild(tentacles5);
			setRotationAngle(tentacles5, 0.0F, 0.0F, -0.9599F);
			cube_r9 = new ModelRenderer(this);
			cube_r9.setRotationPoint(41.0F, -32.0F, 20.0F);
			tentacles5.addChild(cube_r9);
			setRotationAngle(cube_r9, 0.4363F, 0.0F, 0.9599F);
			cube_r9.setTextureOffset(0, 109).addBox(-0.6F, -40.325F, -0.2F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			body_r5 = new ModelRenderer(this);
			body_r5.setRotationPoint(22.0F, -15.125F, 9.5F);
			tentacles5.addChild(body_r5);
			setRotationAngle(body_r5, -0.4363F, 0.0F, 0.9599F);
			body_r5.setTextureOffset(18, 109).addBox(-3.5F, -28.9F, -2.3F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			cube_r10 = new ModelRenderer(this);
			cube_r10.setRotationPoint(75.0F, -46.0F, 4.0F);
			tentacles5.addChild(cube_r10);
			setRotationAngle(cube_r10, 1.1345F, 0.0F, 0.9599F);
			cube_r10.setTextureOffset(137, 110).addBox(-7.6F, -60.025F, 0.5F, 5.0F, 20.0F, 1.0F, 0.0F, false);
			cube_r10.setTextureOffset(119, 119).addBox(-8.6F, -40.025F, -0.5F, 7.0F, 40.0F, 2.0F, 0.0F, false);
			body2 = new ModelRenderer(this);
			body2.setRotationPoint(0.5F, -8.6026F, 4.6414F);
			cosmic_skizzik.addChild(body2);
			setRotationAngle(body2, 0.6981F, 0.0F, 0.0F);
			body2.setTextureOffset(83, 106).addBox(-4.5F, 2.9783F, -10.681F, 9.0F, 15.0F, 9.0F, 0.0F, false);
			body2.setTextureOffset(165, 35).addBox(-3.5F, 1.9783F, -9.681F, 7.0F, 19.0F, 7.0F, 0.0F, false);
			body2.setTextureOffset(0, 11).addBox(-2.5F, 20.9783F, -8.681F, 5.0F, 2.0F, 5.0F, 0.0F, false);
			body2.setTextureOffset(0, 52).addBox(-1.5F, 21.9783F, -7.681F, 3.0F, 7.0F, 3.0F, 0.0F, false);
			head1 = new ModelRenderer(this);
			head1.setRotationPoint(0.875F, -59.125F, 0.0F);
			cosmic_skizzik.addChild(head1);
			head1.setTextureOffset(0, 0).addBox(-9.875F, -1.875F, -10.0F, 20.0F, 20.0F, 20.0F, 0.0F, false);
			head1.setTextureOffset(76, 0).addBox(-10.875F, -8.875F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
			head1.setTextureOffset(60, 0).addBox(-12.875F, -15.875F, -1.0F, 4.0F, 7.0F, 2.0F, 0.0F, false);
			head1.setTextureOffset(36, 111).addBox(-9.875F, -25.875F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
			head1.setTextureOffset(0, 0).addBox(7.125F, -8.875F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
			head1.setTextureOffset(43, 52).addBox(9.125F, -15.875F, -1.0F, 4.0F, 7.0F, 2.0F, 0.0F, false);
			head1.setTextureOffset(0, 81).addBox(8.125F, -25.875F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
			sphere = new ModelRenderer(this);
			sphere.setRotationPoint(0.125F, -12.875F, 0.0F);
			head1.addChild(sphere);
			sphere.setTextureOffset(56, 228).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			sphere.setTextureOffset(237, 85).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			sphere.setTextureOffset(68, 231).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			sphere.setTextureOffset(150, 199).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r11 = new ModelRenderer(this);
			cube_r11.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r11);
			setRotationAngle(cube_r11, 1.1832F, 0.3614F, 0.7137F);
			cube_r11.setTextureOffset(158, 99).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r12 = new ModelRenderer(this);
			cube_r12.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r12);
			setRotationAngle(cube_r12, 1.0808F, 0.1925F, 0.3444F);
			cube_r12.setTextureOffset(193, 125).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r12.setTextureOffset(59, 52).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r13 = new ModelRenderer(this);
			cube_r13.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r13);
			setRotationAngle(cube_r13, 1.0472F, 0.0F, 0.0F);
			cube_r13.setTextureOffset(34, 195).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r13.setTextureOffset(34, 109).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r14 = new ModelRenderer(this);
			cube_r14.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r14);
			setRotationAngle(cube_r14, 1.0808F, -0.1925F, -0.3444F);
			cube_r14.setTextureOffset(56, 195).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r14.setTextureOffset(173, 171).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r15 = new ModelRenderer(this);
			cube_r15.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r15);
			setRotationAngle(cube_r15, 1.1832F, -0.3614F, -0.7137F);
			cube_r15.setTextureOffset(195, 52).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r16 = new ModelRenderer(this);
			cube_r16.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r16);
			setRotationAngle(cube_r16, 1.0353F, 0.4718F, 0.6537F);
			cube_r16.setTextureOffset(68, 198).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r17 = new ModelRenderer(this);
			cube_r17.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r17);
			setRotationAngle(cube_r17, 0.9113F, 0.2485F, 0.3073F);
			cube_r17.setTextureOffset(198, 198).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r17.setTextureOffset(116, 189).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r18 = new ModelRenderer(this);
			cube_r18.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r18);
			setRotationAngle(cube_r18, 0.8727F, 0.0F, 0.0F);
			cube_r18.setTextureOffset(198, 26).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r18.setTextureOffset(195, 63).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r19 = new ModelRenderer(this);
			cube_r19.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r19);
			setRotationAngle(cube_r19, 0.9113F, -0.2485F, -0.3073F);
			cube_r19.setTextureOffset(198, 65).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r19.setTextureOffset(198, 195).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r20 = new ModelRenderer(this);
			cube_r20.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r20);
			setRotationAngle(cube_r20, 1.0353F, -0.4718F, -0.6537F);
			cube_r20.setTextureOffset(198, 77).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r21 = new ModelRenderer(this);
			cube_r21.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r21);
			setRotationAngle(cube_r21, 0.0F, 0.7854F, 0.0F);
			cube_r21.setTextureOffset(198, 180).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r21.setTextureOffset(229, 112).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r22 = new ModelRenderer(this);
			cube_r22.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r22);
			setRotationAngle(cube_r22, 0.0F, 0.3927F, 0.0F);
			cube_r22.setTextureOffset(128, 199).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r22.setTextureOffset(200, 0).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r22.setTextureOffset(237, 87).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r22.setTextureOffset(229, 33).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r23 = new ModelRenderer(this);
			cube_r23.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r23);
			setRotationAngle(cube_r23, 0.0F, -0.3927F, 0.0F);
			cube_r23.setTextureOffset(172, 199).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r23.setTextureOffset(231, 54).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r23.setTextureOffset(237, 83).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r23.setTextureOffset(34, 228).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r24 = new ModelRenderer(this);
			cube_r24.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r24);
			setRotationAngle(cube_r24, 0.0F, -0.7854F, 0.0F);
			cube_r24.setTextureOffset(184, 200).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r24.setTextureOffset(22, 227).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r25 = new ModelRenderer(this);
			cube_r25.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r25);
			setRotationAngle(cube_r25, 0.8706F, 0.5724F, 0.5713F);
			cube_r25.setTextureOffset(200, 2).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r26 = new ModelRenderer(this);
			cube_r26.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r26);
			setRotationAngle(cube_r26, 0.7373F, 0.2975F, 0.2602F);
			cube_r26.setTextureOffset(200, 13).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r26.setTextureOffset(232, 228).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r27 = new ModelRenderer(this);
			cube_r27.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r27);
			setRotationAngle(cube_r27, 0.6981F, 0.0F, 0.0F);
			cube_r27.setTextureOffset(80, 201).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r27.setTextureOffset(90, 234).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r28 = new ModelRenderer(this);
			cube_r28.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r28);
			setRotationAngle(cube_r28, 0.7373F, -0.2975F, -0.2602F);
			cube_r28.setTextureOffset(102, 201).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r28.setTextureOffset(234, 20).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r29 = new ModelRenderer(this);
			cube_r29.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r29);
			setRotationAngle(cube_r29, 0.8706F, -0.5724F, -0.5713F);
			cube_r29.setTextureOffset(201, 89).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r30 = new ModelRenderer(this);
			cube_r30.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r30);
			setRotationAngle(cube_r30, 0.6847F, 0.6591F, 0.4636F);
			cube_r30.setTextureOffset(201, 101).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r31 = new ModelRenderer(this);
			cube_r31.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r31);
			setRotationAngle(cube_r31, 0.5585F, 0.3378F, 0.2042F);
			cube_r31.setTextureOffset(201, 112).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r31.setTextureOffset(234, 22).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r32 = new ModelRenderer(this);
			cube_r32.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r32);
			setRotationAngle(cube_r32, 0.5236F, 0.0F, 0.0F);
			cube_r32.setTextureOffset(201, 136).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r32.setTextureOffset(234, 24).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r33 = new ModelRenderer(this);
			cube_r33.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r33);
			setRotationAngle(cube_r33, 0.5585F, -0.3378F, -0.2042F);
			cube_r33.setTextureOffset(201, 147).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r33.setTextureOffset(234, 26).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r34 = new ModelRenderer(this);
			cube_r34.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r34);
			setRotationAngle(cube_r34, 0.6847F, -0.6591F, -0.4636F);
			cube_r34.setTextureOffset(201, 158).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r35 = new ModelRenderer(this);
			cube_r35.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r35);
			setRotationAngle(cube_r35, 0.4754F, 0.7268F, 0.3295F);
			cube_r35.setTextureOffset(114, 203).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r36 = new ModelRenderer(this);
			cube_r36.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r36);
			setRotationAngle(cube_r36, 0.3753F, 0.3678F, 0.1407F);
			cube_r36.setTextureOffset(0, 205).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r36.setTextureOffset(234, 28).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r37 = new ModelRenderer(this);
			cube_r37.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r37);
			setRotationAngle(cube_r37, 0.3491F, 0.0F, 0.0F);
			cube_r37.setTextureOffset(22, 205).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r37.setTextureOffset(234, 30).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r38 = new ModelRenderer(this);
			cube_r38.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r38);
			setRotationAngle(cube_r38, 0.3753F, -0.3678F, -0.1407F);
			cube_r38.setTextureOffset(205, 37).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r38.setTextureOffset(234, 57).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r39 = new ModelRenderer(this);
			cube_r39.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r39);
			setRotationAngle(cube_r39, 0.4754F, -0.7268F, -0.3295F);
			cube_r39.setTextureOffset(205, 123).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r40 = new ModelRenderer(this);
			cube_r40.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r40);
			setRotationAngle(cube_r40, 0.2444F, 0.7703F, 0.1719F);
			cube_r40.setTextureOffset(34, 206).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r41 = new ModelRenderer(this);
			cube_r41.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r41);
			setRotationAngle(cube_r41, 0.1886F, 0.3864F, 0.0718F);
			cube_r41.setTextureOffset(56, 206).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r41.setTextureOffset(234, 59).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r42 = new ModelRenderer(this);
			cube_r42.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r42);
			setRotationAngle(cube_r42, 0.1745F, 0.0F, 0.0F);
			cube_r42.setTextureOffset(207, 48).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r42.setTextureOffset(234, 61).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r43 = new ModelRenderer(this);
			cube_r43.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r43);
			setRotationAngle(cube_r43, 0.1886F, -0.3864F, -0.0718F);
			cube_r43.setTextureOffset(68, 209).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r43.setTextureOffset(234, 63).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r44 = new ModelRenderer(this);
			cube_r44.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r44);
			setRotationAngle(cube_r44, 0.2444F, -0.7703F, -0.1719F);
			cube_r44.setTextureOffset(196, 209).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r45 = new ModelRenderer(this);
			cube_r45.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r45);
			setRotationAngle(cube_r45, -1.8227F, -0.2443F, -0.7543F);
			cube_r45.setTextureOffset(126, 210).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r46 = new ModelRenderer(this);
			cube_r46.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r46);
			setRotationAngle(cube_r46, -1.8952F, -0.1313F, -0.3712F);
			cube_r46.setTextureOffset(148, 210).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r46.setTextureOffset(234, 65).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r47 = new ModelRenderer(this);
			cube_r47.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r47);
			setRotationAngle(cube_r47, -1.9199F, 0.0F, 0.0F);
			cube_r47.setTextureOffset(170, 210).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r47.setTextureOffset(234, 68).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r48 = new ModelRenderer(this);
			cube_r48.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r48);
			setRotationAngle(cube_r48, -1.8952F, 0.1313F, 0.3712F);
			cube_r48.setTextureOffset(208, 210).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r48.setTextureOffset(234, 70).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r49 = new ModelRenderer(this);
			cube_r49.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r49);
			setRotationAngle(cube_r49, -1.8227F, 0.2443F, 0.7543F);
			cube_r49.setTextureOffset(210, 24).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r50 = new ModelRenderer(this);
			cube_r50.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r50);
			setRotationAngle(cube_r50, -1.6948F, -0.1231F, -0.7777F);
			cube_r50.setTextureOffset(210, 59).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r51 = new ModelRenderer(this);
			cube_r51.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r51);
			setRotationAngle(cube_r51, -1.7323F, -0.0665F, -0.3873F);
			cube_r51.setTextureOffset(210, 70).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r51.setTextureOffset(234, 72).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r52 = new ModelRenderer(this);
			cube_r52.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r52);
			setRotationAngle(cube_r52, -1.7453F, 0.0F, 0.0F);
			cube_r52.setTextureOffset(210, 170).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r52.setTextureOffset(234, 74).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r53 = new ModelRenderer(this);
			cube_r53.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r53);
			setRotationAngle(cube_r53, -1.7323F, 0.0665F, 0.3873F);
			cube_r53.setTextureOffset(210, 181).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r53.setTextureOffset(234, 76).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r54 = new ModelRenderer(this);
			cube_r54.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r54);
			setRotationAngle(cube_r54, -1.6948F, 0.1231F, 0.7777F);
			cube_r54.setTextureOffset(210, 192).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r55 = new ModelRenderer(this);
			cube_r55.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r55);
			setRotationAngle(cube_r55, -1.5708F, 0.0F, -0.7854F);
			cube_r55.setTextureOffset(182, 211).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r56 = new ModelRenderer(this);
			cube_r56.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r56);
			setRotationAngle(cube_r56, -1.5708F, 0.0F, -0.3927F);
			cube_r56.setTextureOffset(80, 212).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r56.setTextureOffset(234, 166).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r57 = new ModelRenderer(this);
			cube_r57.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r57);
			setRotationAngle(cube_r57, -1.5708F, 0.0F, 0.0F);
			cube_r57.setTextureOffset(102, 212).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r57.setTextureOffset(234, 168).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r58 = new ModelRenderer(this);
			cube_r58.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r58);
			setRotationAngle(cube_r58, -1.5708F, 0.0F, 0.3927F);
			cube_r58.setTextureOffset(212, 0).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r58.setTextureOffset(234, 170).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r59 = new ModelRenderer(this);
			cube_r59.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r59);
			setRotationAngle(cube_r59, -1.5708F, 0.0F, 0.7854F);
			cube_r59.setTextureOffset(212, 11).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r60 = new ModelRenderer(this);
			cube_r60.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r60);
			setRotationAngle(cube_r60, -1.4468F, 0.1231F, -0.7777F);
			cube_r60.setTextureOffset(213, 81).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r61 = new ModelRenderer(this);
			cube_r61.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r61);
			setRotationAngle(cube_r61, -1.4093F, 0.0665F, -0.3873F);
			cube_r61.setTextureOffset(213, 92).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r61.setTextureOffset(234, 172).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r62 = new ModelRenderer(this);
			cube_r62.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r62);
			setRotationAngle(cube_r62, -1.3963F, 0.0F, 0.0F);
			cube_r62.setTextureOffset(213, 103).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r62.setTextureOffset(234, 174).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r63 = new ModelRenderer(this);
			cube_r63.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r63);
			setRotationAngle(cube_r63, -1.4093F, -0.0665F, 0.3873F);
			cube_r63.setTextureOffset(213, 134).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r63.setTextureOffset(234, 178).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r64 = new ModelRenderer(this);
			cube_r64.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r64);
			setRotationAngle(cube_r64, -1.4468F, -0.1231F, 0.7777F);
			cube_r64.setTextureOffset(213, 145).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r65 = new ModelRenderer(this);
			cube_r65.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r65);
			setRotationAngle(cube_r65, -1.3189F, 0.2443F, -0.7543F);
			cube_r65.setTextureOffset(213, 156).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r66 = new ModelRenderer(this);
			cube_r66.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r66);
			setRotationAngle(cube_r66, -1.2464F, 0.1313F, -0.3712F);
			cube_r66.setTextureOffset(114, 214).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r66.setTextureOffset(234, 180).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r67 = new ModelRenderer(this);
			cube_r67.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r67);
			setRotationAngle(cube_r67, -1.2217F, 0.0F, 0.0F);
			cube_r67.setTextureOffset(0, 216).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r67.setTextureOffset(234, 182).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r68 = new ModelRenderer(this);
			cube_r68.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r68);
			setRotationAngle(cube_r68, -1.2464F, -0.1313F, 0.3712F);
			cube_r68.setTextureOffset(22, 216).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r68.setTextureOffset(234, 184).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r69 = new ModelRenderer(this);
			cube_r69.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r69);
			setRotationAngle(cube_r69, -1.3189F, -0.2443F, 0.7543F);
			cube_r69.setTextureOffset(34, 217).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r70 = new ModelRenderer(this);
			cube_r70.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r70);
			setRotationAngle(cube_r70, -1.1832F, 0.3614F, -0.7137F);
			cube_r70.setTextureOffset(56, 217).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r71 = new ModelRenderer(this);
			cube_r71.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r71);
			setRotationAngle(cube_r71, -1.0808F, 0.1925F, -0.3444F);
			cube_r71.setTextureOffset(217, 35).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r71.setTextureOffset(234, 186).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r72 = new ModelRenderer(this);
			cube_r72.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r72);
			setRotationAngle(cube_r72, -1.0472F, 0.0F, 0.0F);
			cube_r72.setTextureOffset(217, 114).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r72.setTextureOffset(234, 189).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r73 = new ModelRenderer(this);
			cube_r73.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r73);
			setRotationAngle(cube_r73, -1.0808F, -0.1925F, 0.3444F);
			cube_r73.setTextureOffset(219, 46).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r73.setTextureOffset(234, 191).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r74 = new ModelRenderer(this);
			cube_r74.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r74);
			setRotationAngle(cube_r74, -1.1832F, -0.3614F, 0.7137F);
			cube_r74.setTextureOffset(68, 220).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r75 = new ModelRenderer(this);
			cube_r75.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r75);
			setRotationAngle(cube_r75, -1.0353F, 0.4718F, -0.6537F);
			cube_r75.setTextureOffset(194, 220).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r76 = new ModelRenderer(this);
			cube_r76.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r76);
			setRotationAngle(cube_r76, -0.9113F, 0.2485F, -0.3073F);
			cube_r76.setTextureOffset(220, 220).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r76.setTextureOffset(234, 193).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r77 = new ModelRenderer(this);
			cube_r77.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r77);
			setRotationAngle(cube_r77, -0.8727F, 0.0F, 0.0F);
			cube_r77.setTextureOffset(220, 203).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r77.setTextureOffset(234, 195).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r78 = new ModelRenderer(this);
			cube_r78.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r78);
			setRotationAngle(cube_r78, -0.9113F, -0.2485F, 0.3073F);
			cube_r78.setTextureOffset(126, 221).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r78.setTextureOffset(234, 197).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r79 = new ModelRenderer(this);
			cube_r79.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r79);
			setRotationAngle(cube_r79, -1.0353F, -0.4718F, 0.6537F);
			cube_r79.setTextureOffset(148, 221).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r80 = new ModelRenderer(this);
			cube_r80.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r80);
			setRotationAngle(cube_r80, -0.8706F, 0.5724F, -0.5713F);
			cube_r80.setTextureOffset(170, 221).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r81 = new ModelRenderer(this);
			cube_r81.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r81);
			setRotationAngle(cube_r81, -0.7373F, 0.2975F, -0.2602F);
			cube_r81.setTextureOffset(206, 221).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r81.setTextureOffset(193, 235).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r82 = new ModelRenderer(this);
			cube_r82.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r82);
			setRotationAngle(cube_r82, -0.6981F, 0.0F, 0.0F);
			cube_r82.setTextureOffset(182, 222).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r82.setTextureOffset(89, 236).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r83 = new ModelRenderer(this);
			cube_r83.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r83);
			setRotationAngle(cube_r83, -0.7373F, -0.2975F, 0.2602F);
			cube_r83.setTextureOffset(222, 22).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r83.setTextureOffset(111, 236).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r84 = new ModelRenderer(this);
			cube_r84.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r84);
			setRotationAngle(cube_r84, -0.8706F, -0.5724F, 0.5713F);
			cube_r84.setTextureOffset(222, 57).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r85 = new ModelRenderer(this);
			cube_r85.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r85);
			setRotationAngle(cube_r85, -0.6847F, 0.6591F, -0.4636F);
			cube_r85.setTextureOffset(222, 68).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r86 = new ModelRenderer(this);
			cube_r86.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r86);
			setRotationAngle(cube_r86, -0.5585F, 0.3378F, -0.2042F);
			cube_r86.setTextureOffset(222, 167).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r86.setTextureOffset(172, 236).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r87 = new ModelRenderer(this);
			cube_r87.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r87);
			setRotationAngle(cube_r87, -0.5236F, 0.0F, 0.0F);
			cube_r87.setTextureOffset(222, 178).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r87.setTextureOffset(236, 0).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r88 = new ModelRenderer(this);
			cube_r88.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r88);
			setRotationAngle(cube_r88, -0.5585F, -0.3378F, 0.2042F);
			cube_r88.setTextureOffset(222, 189).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r88.setTextureOffset(236, 2).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r89 = new ModelRenderer(this);
			cube_r89.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r89);
			setRotationAngle(cube_r89, -0.6847F, -0.6591F, 0.4636F);
			cube_r89.setTextureOffset(80, 223).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r90 = new ModelRenderer(this);
			cube_r90.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r90);
			setRotationAngle(cube_r90, -0.4754F, 0.7268F, -0.3295F);
			cube_r90.setTextureOffset(102, 223).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r91 = new ModelRenderer(this);
			cube_r91.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r91);
			setRotationAngle(cube_r91, -0.3753F, 0.3678F, -0.1407F);
			cube_r91.setTextureOffset(224, 1).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r91.setTextureOffset(236, 4).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r92 = new ModelRenderer(this);
			cube_r92.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r92);
			setRotationAngle(cube_r92, -0.3491F, 0.0F, 0.0F);
			cube_r92.setTextureOffset(114, 225).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r92.setTextureOffset(236, 6).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r93 = new ModelRenderer(this);
			cube_r93.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r93);
			setRotationAngle(cube_r93, -0.3753F, -0.3678F, 0.1407F);
			cube_r93.setTextureOffset(225, 79).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r93.setTextureOffset(236, 8).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r94 = new ModelRenderer(this);
			cube_r94.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r94);
			setRotationAngle(cube_r94, -0.4754F, -0.7268F, 0.3295F);
			cube_r94.setTextureOffset(225, 90).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r95 = new ModelRenderer(this);
			cube_r95.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r95);
			setRotationAngle(cube_r95, -0.2444F, 0.7703F, -0.1719F);
			cube_r95.setTextureOffset(225, 101).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r96 = new ModelRenderer(this);
			cube_r96.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r96);
			setRotationAngle(cube_r96, -0.1886F, 0.3864F, -0.0718F);
			cube_r96.setTextureOffset(225, 125).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r96.setTextureOffset(193, 237).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r97 = new ModelRenderer(this);
			cube_r97.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r97);
			setRotationAngle(cube_r97, -0.1745F, 0.0F, 0.0F);
			cube_r97.setTextureOffset(225, 136).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r97.setTextureOffset(237, 79).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r98 = new ModelRenderer(this);
			cube_r98.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r98);
			setRotationAngle(cube_r98, -0.1886F, -0.3864F, 0.0718F);
			cube_r98.setTextureOffset(225, 147).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			cube_r98.setTextureOffset(237, 81).addBox(-5.0F, -0.5F, -0.9946F, 10.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r99 = new ModelRenderer(this);
			cube_r99.setRotationPoint(0.0F, 0.0F, 0.0F);
			sphere.addChild(cube_r99);
			setRotationAngle(cube_r99, -0.2444F, -0.7703F, 0.1719F);
			cube_r99.setTextureOffset(0, 227).addBox(-0.9946F, -0.5F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
			head2 = new ModelRenderer(this);
			head2.setRotationPoint(19.0F, -33.0F, 0.0F);
			cosmic_skizzik.addChild(head2);
			head2.setTextureOffset(0, 81).addBox(5.0F, -8.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
			head2.setTextureOffset(122, 5).addBox(17.0F, -15.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
			head2.setTextureOffset(122, 0).addBox(16.0F, -11.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
			head2.setTextureOffset(116, 89).addBox(6.0F, -15.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
			head2.setTextureOffset(114, 76).addBox(6.0F, -11.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
			head3 = new ModelRenderer(this);
			head3.setRotationPoint(-30.0F, -35.0F, 1.0F);
			cosmic_skizzik.addChild(head3);
			head3.setTextureOffset(80, 0).addBox(-7.0F, -8.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
			head3.setTextureOffset(110, 106).addBox(5.0F, -15.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
			head3.setTextureOffset(114, 71).addBox(4.0F, -11.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
			head3.setTextureOffset(74, 106).addBox(-6.0F, -15.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
			head3.setTextureOffset(112, 84).addBox(-6.0F, -11.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
			head4 = new ModelRenderer(this);
			head4.setRotationPoint(-27.0F, -72.0F, 1.0F);
			cosmic_skizzik.addChild(head4);
			head4.setTextureOffset(58, 58).addBox(-7.0F, -8.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
			head4.setTextureOffset(83, 106).addBox(-6.0F, -11.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
			head4.setTextureOffset(100, 56).addBox(-6.0F, -15.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
			head4.setTextureOffset(58, 66).addBox(4.0F, -11.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
			head4.setTextureOffset(56, 94).addBox(5.0F, -15.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
			head5 = new ModelRenderer(this);
			head5.setRotationPoint(29.0F, -67.0F, 0.0F);
			cosmic_skizzik.addChild(head5);
			head5.setTextureOffset(1, 53).addBox(-7.0F, -8.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
			head5.setTextureOffset(0, 62).addBox(4.0F, -11.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
			head5.setTextureOffset(8, 88).addBox(-6.0F, -15.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
			head5.setTextureOffset(59, 59).addBox(-6.0F, -11.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
			head5.setTextureOffset(8, 82).addBox(5.0F, -15.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
		}

		@Override
		public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			// previously the render function, render code was moved to a method below
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			cosmic_skizzik.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
