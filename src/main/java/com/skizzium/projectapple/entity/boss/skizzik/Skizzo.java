package com.skizzium.projectapple.entity.boss.skizzik;

import com.skizzium.projectapple.entity.boss.AbstractSkizzo;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.skizzie.FriendlySkizzie;
import com.skizzium.projectapple.init.network.PA_PacketRegistry;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.network.SkizzoConnectionParticlesPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Skizzo extends AbstractSkizzo {
    private AnimationFactory factory = new AnimationFactory(this);

    public Skizzo(EntityType<? extends Skizzo> entity, Level world) {
        super(entity, world);
    }

    @Override
    public boolean isSensitiveToWater() {
        return true;
    }

    public static AttributeSupplier.Builder buildAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.FLYING_SPEED, 0.2D);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 0, true, true, PA_Entities.SKIZZIK_SELECTOR));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, FriendlySkizzie.class, true, true));
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
}
