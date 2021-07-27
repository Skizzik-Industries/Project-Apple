package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.init.PA_Entities;
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
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.UUID;

public class Skizzo extends MonsterEntity {
    private UUID ownerUUID;
    private int ownerNetworkId;

    public Skizzo(EntityType<? extends Skizzo> entity, World world) {
        super(entity, world);
        this.xpReward = 25;
        this.moveControl = new FlyingMovementController(this, 10, true);
        this.navigation = new FlyingPathNavigator(this, this.getCommandSenderWorld());
        this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return 1.75F;
    }

    @Override
    public boolean canBeLeashed(PlayerEntity player) {
        return false;
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
    public boolean causeFallDamage(float f0, float f1) {
        return false;
    }

    @Override
    public boolean isSensitiveToWater() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source == DamageSource.ANVIL ||
                source.getDirectEntity() instanceof ArrowEntity ||
                source == DamageSource.CACTUS ||
                source == DamageSource.DRAGON_BREATH ||
                source == DamageSource.FALL ||
                source.isExplosion() ||
                source == DamageSource.LIGHTNING_BOLT ||
                source == DamageSource.HOT_FLOOR ||
                source == DamageSource.IN_FIRE ||
                source == DamageSource.LAVA ||
                source == DamageSource.ON_FIRE ||
                source.getDirectEntity() instanceof PotionEntity ||
                source == DamageSource.SWEET_BERRY_BUSH ||
                source.getDirectEntity() instanceof TridentEntity ||
                source == DamageSource.WITHER) {
            return false;
        }
        return super.hurt(source, amount);
    }

    public static AttributeModifierMap.MutableAttribute buildAttributes() {
        return MonsterEntity.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.FLYING_SPEED, 0.2D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, FriendlySkizzie.class, true, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 0, true, true, PA_Entities.SKIZZIK_SELECTOR));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.D, 0.0F));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
    }

    public void setOwner(@Nullable Entity entity) {
        if (entity != null) {
            this.ownerUUID = entity.getUUID();
            this.ownerNetworkId = entity.getId();
        }
    }

    @Nullable
    public Entity getOwner() {
        if (this.ownerUUID != null && this.level instanceof ServerWorld) {
            return ((ServerWorld)this.level).getEntity(this.ownerUUID);
        }
        else {
            return this.ownerNetworkId != 0 ? this.level.getEntity(this.ownerNetworkId) : null;
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        if (this.ownerUUID != null) {
            nbt.putUUID("Owner", this.ownerUUID);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        if (nbt.hasUUID("Owner")) {
            this.ownerUUID = nbt.getUUID("Owner");
        }
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.setNoGravity(true);

        /* double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        World world = this.getCommandSenderWorld();

        BlockPos[] blockPositions = {new BlockPos(x, y, z), new BlockPos(x + 1, y, z), new BlockPos(x - 1, y, z), new BlockPos(x, y, z + 1), new BlockPos(x, y, z - 1), new BlockPos(x + 1, y, z + 1), new BlockPos(x + 1, y, z - 1), new BlockPos(x - 1, y, z + 1), new BlockPos(x - 1, y, z - 1)};
        for (BlockPos pos : blockPositions) {
            Block blockBelow = world.getBlockState(pos.below()).getBlock();
            if (blockBelow != Blocks.FIRE &&
                blockBelow != Blocks.AIR &&
                blockBelow != Blocks.CAVE_AIR &&
                blockBelow != Blocks.VOID_AIR) {
                world.setBlock(pos, Blocks.FIRE.defaultBlockState(), 3);
            }
        } */
    }

    @Override
    public void playerTouch(PlayerEntity player) {
        super.playerTouch(player);
        World world = player.getCommandSenderWorld();

        if (player instanceof ServerPlayerEntity) {
            if (((ServerPlayerEntity) player).gameMode.isSurvival()) {
                player.setSecondsOnFire(10);
            }
        }
        else if (player instanceof PlayerEntity && world.isClientSide()) {
            NetworkPlayerInfo network = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());

            if (network.getGameMode() == GameType.SURVIVAL || network.getGameMode() == GameType.ADVENTURE) {
                player.setSecondsOnFire(10);
            }
        }
        else {
            if (!player.fireImmune()) {
                player.setSecondsOnFire(10);
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
