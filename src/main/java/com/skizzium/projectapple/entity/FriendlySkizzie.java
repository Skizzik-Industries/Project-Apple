package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.init.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class FriendlySkizzie extends CreatureEntity {
    public FriendlySkizzie(EntityType<? extends FriendlySkizzie> entity, World world) {
        super(entity, world);
        this.xpReward = 7;
        this.moveControl = new FlyingMovementController(this, 10, true);
        this.navigation = new FlyingPathNavigator(this, this.getCommandSenderWorld());
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return 1.30F;
    }

    @Override
    public Vector3d getLeashOffset() {
        return new Vector3d(0.0D, this.getEyeHeight() * 0.8F, this.getBbWidth() * 0.05F);
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.GENERIC_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_DEATH;
    }

    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEAD;
    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source == DamageSource.DRAGON_BREATH ||
                source == DamageSource.FALL ||
                source.isExplosion() ||
                source == DamageSource.LIGHTNING_BOLT ||
                source == DamageSource.IN_FIRE ||
                source == DamageSource.WITHER) {
            return false;
        }
        return super.hurt(source, amount);
    }

    public static AttributeModifierMap.MutableAttribute buildAttributes() {
        return MonsterEntity.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FLYING_SPEED, 0.3D);
    }

    protected void registerGoals() {
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Skizzie.class, true, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, true, true));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.setNoGravity(true);

        World world = this.level;
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        BlockPos pos = new BlockPos(x, y, z);

        this.clearFire();

        if (world.getBlockState(pos).getBlock() == Blocks.FIRE || world.getBlockState(pos).getBlock() == Blocks.SOUL_FIRE) {
            world.playSound(null, new BlockPos(x, y,z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundCategory.BLOCKS, (float) 1, (float) 1);
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            this.clearFire();
        }
        else if (world.getBlockState(pos).getBlock() == Blocks.LAVA) {
            world.setBlock(pos, ModBlocks.SKIZZIE_STATUE.get().defaultBlockState(), 3);
            world.playSound(null, new BlockPos(x, y,z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundCategory.BLOCKS, (float) 1, (float) 1);
            this.remove();
        }
    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        player.startRiding(this);
        return super.mobInteract(player, hand);
    }

    @Override
    public void playerTouch(PlayerEntity player) {
        super.playerTouch(player);

        World world = player.getCommandSenderWorld();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        if (player instanceof ServerPlayerEntity) {
            if (((ServerPlayerEntity) player).gameMode.isSurvival() && player.isOnFire()) {
                world.playSound(null, new BlockPos(x, y,z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundCategory.BLOCKS, (float) 1, (float) 1);
                player.clearFire();
            }
        }
        else if (player instanceof PlayerEntity && world.isClientSide()) {
            NetworkPlayerInfo network = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());

            if (network.getGameMode() == GameType.SURVIVAL || network.getGameMode() == GameType.ADVENTURE && player.isOnFire()) {
                world.playSound(null, new BlockPos(x, y,z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundCategory.BLOCKS, (float) 1, (float) 1);
                player.clearFire();
            }
        }
        else {
            if (!player.isOnFire()) {
                world.playSound(null, new BlockPos(x, y,z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundCategory.BLOCKS, (float) 1, (float) 1);
                player.clearFire();
            }
        }
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);

        World world = this.getCommandSenderWorld();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        LightningBoltEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
        lightning.moveTo(Vector3d.atCenterOf(new BlockPos(x, y, z)));
        world.addFreshEntity(lightning);
    }
}
