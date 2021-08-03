package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.util.SkizzieConversion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class FriendlySkizzie extends PathfinderMob {
    public FriendlySkizzie(EntityType<? extends FriendlySkizzie> entity, Level world) {
        super(entity, world);
        this.xpReward = 7;
        this.moveControl = new FlyingMoveControl(this, 10, true);
        this.navigation = new FlyingPathNavigation(this, this.getCommandSenderWorld());
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return 1.30F;
    }

    @Override
    public Vec3 getLeashOffset() {
        return new Vec3(0.0D, this.getEyeHeight() * 0.8F, this.getBbWidth() * 0.05F);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.GENERIC_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_DEATH;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
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

    public static AttributeSupplier.Builder buildAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FLYING_SPEED, 0.3D);
    }

    protected void registerGoals() {
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Skizzie.class, true, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Monster.class, true, true));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.setNoGravity(true);

        Level world = this.level;
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        BlockPos pos = new BlockPos(x, y, z);

        this.clearFire();

        if (world.getBlockState(pos).getBlock() == Blocks.FIRE || world.getBlockState(pos).getBlock() == Blocks.SOUL_FIRE) {
            world.playSound(null, new BlockPos(x, y,z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.BLOCKS, (float) 1, (float) 1);
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            this.clearFire();
        }
        else if (world.getBlockState(pos).getBlock() == Blocks.LAVA) {
            world.setBlock(pos, PA_Blocks.SKIZZIE_STATUE.get().defaultBlockState(), 3);
            world.playSound(null, new BlockPos(x, y,z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.BLOCKS, (float) 1, (float) 1);
            this.remove(false);
        }
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        player.startRiding(this);
        return SkizzieConversion.conversion(this, player);
    }

    @Override
    public void playerTouch(Player player) {
        super.playerTouch(player);

        Level world = player.getCommandSenderWorld();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        if (player instanceof ServerPlayer) {
            if (((ServerPlayer) player).gameMode.isSurvival() && player.isOnFire()) {
                world.playSound(null, new BlockPos(x, y,z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.BLOCKS, (float) 1, (float) 1);
                player.clearFire();
            }
        }
        else if (player instanceof Player && world.isClientSide()) {
            PlayerInfo network = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());

            if (network.getGameMode() == GameType.SURVIVAL || network.getGameMode() == GameType.ADVENTURE && player.isOnFire()) {
                world.playSound(null, new BlockPos(x, y,z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.BLOCKS, (float) 1, (float) 1);
                player.clearFire();
            }
        }
        else {
            if (!player.isOnFire()) {
                world.playSound(null, new BlockPos(x, y,z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundSource.BLOCKS, (float) 1, (float) 1);
                player.clearFire();
            }
        }
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);

        Level world = this.getCommandSenderWorld();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(world);
        lightning.moveTo(Vec3.atCenterOf(new BlockPos(x, y, z)));
        world.addFreshEntity(lightning);
    }
}
