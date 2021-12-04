package com.skizzium.projectapple.entity.boss.skizzik;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.skizzie.FriendlySkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.base.SkizzikFinishHim;
import com.skizzium.projectapple.init.PA_PacketRegistry;
import com.skizzium.projectapple.init.effects.PA_Effects;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.network.SkizzoConnectionParticlesPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.UUID;

public class Skizzo extends Monster implements IAnimatable {
    private UUID ownerUUID;
    private int ownerNetworkId;
    private AnimationFactory factory = new AnimationFactory(this);

    public Skizzo(EntityType<? extends Skizzo> entity, Level world) {
        super(entity, world);
        this.xpReward = 25;
        this.moveControl = new FlyingMoveControl(this, 10, true);
        this.navigation = new FlyingPathNavigation(this, this.getCommandSenderWorld());
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    }

    @Override
    protected Component getTypeName() {
        return new TranslatableComponent(ProjectApple.getThemedDescriptionId(super.getType().getDescriptionId()));
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return 1.38F;
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.GENERIC_HURT;
    }

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
    public boolean isSensitiveToWater() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return !(source.getDirectEntity() instanceof Player) || super.isInvulnerableTo(source);
    }

    public static AttributeSupplier.Builder buildAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.FLYING_SPEED, 0.2D);
    }

    protected void registerGoals() {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 0, true, true, PA_Entities.SKIZZIK_SELECTOR));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, FriendlySkizzie.class, true, true));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.D, 0.0F));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public void setOwner(@Nullable Entity entity) {
        if (entity != null) {
            this.ownerUUID = entity.getUUID();
            this.ownerNetworkId = entity.getId();
        }
    }

    @Nullable
    public Entity getOwner() {
        if (this.ownerUUID != null && this.level instanceof ServerLevel) {
            return ((ServerLevel)this.level).getEntity(this.ownerUUID);
        }
        else {
            return this.ownerNetworkId != 0 ? this.level.getEntity(this.ownerNetworkId) : null;
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        if (this.ownerUUID != null) {
            nbt.putUUID("Owner", this.ownerUUID);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        if (nbt.hasUUID("Owner")) {
            this.ownerUUID = nbt.getUUID("Owner");
        }
    }

    private <E extends IAnimatable> PlayState ambient(AnimationEvent<E> event) {
        if (this.attackAnim > 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.skizzo.attack"));
        }
        else if (event.getController().getAnimationState() == AnimationState.Stopped) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.skizzo.ambient"));
        }
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState attack(AnimationEvent<E> event) {
        
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "ambient", 0, this::ambient));
        data.addAnimationController(new AnimationController(this, "attack", 0, this::attack));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
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
    public void aiStep() {
        super.aiStep();
        this.level.addParticle(ParticleTypes.DRIPPING_LAVA, this.getX() + this.random.nextGaussian() * (double)0.2F, this.getY() + this.getEyeHeight() + this.random.nextGaussian() * (double)0.2F, this.getZ() + this.random.nextGaussian() * (double)0.2F, 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected void customServerAiStep() {
        if (this.getOwner() != null && this.getOwner() instanceof Skizzik && this.distanceTo(this.getOwner()) < 25) {
            PA_PacketRegistry.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new SkizzoConnectionParticlesPacket(this.getOwner().getId(), this.getId()));
        }
    }

    @Override
    public void playerTouch(Player player) {
        super.playerTouch(player);
        Level world = player.getCommandSenderWorld();

        if (player instanceof ServerPlayer) {
            if (((ServerPlayer) player).gameMode.isSurvival()) {
                player.setSecondsOnFire(10);
            }
        }
        else if (player instanceof Player && world.isClientSide()) {
            PlayerInfo network = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());

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

        Level world = this.getCommandSenderWorld();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(world);
        lightning.moveTo(Vec3.atCenterOf(new BlockPos(x, y, z)));
        world.addFreshEntity(lightning);
    }
}
