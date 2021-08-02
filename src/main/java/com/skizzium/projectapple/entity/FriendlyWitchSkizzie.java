package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.init.PA_Effects;
import com.skizzium.projectapple.init.PA_SoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class FriendlyWitchSkizzie extends FriendlySkizzie {
    public FriendlyWitchSkizzie(EntityType<? extends FriendlyWitchSkizzie> entity, Level world) {
        super(entity, world);
    }

    public static AttributeSupplier.Builder buildAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FLYING_SPEED, 0.3D);
    }

    @Override
    public void playerTouch(Player player) {
        Level world = player.getCommandSenderWorld();
        if (player instanceof LivingEntity && !player.hasEffect(PA_Effects.CORRUPTION.get())) {
            if (player instanceof ServerPlayer) {
                if (((ServerPlayer) player).gameMode.isSurvival() && Math.random() < 0.05) {
                    if (Math.random() < 0.1) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 2));
                    }
                    else {
                        player.addEffect(new MobEffectInstance(MobEffects.HEAL, 3));
                    }
                }
            }
            else if (player instanceof Player && world.isClientSide()) {
                PlayerInfo network = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());

                if ((network.getGameMode() == GameType.SURVIVAL || network.getGameMode() == GameType.ADVENTURE) && Math.random() < 0.05) {
                    if (Math.random() < 0.1) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 2));
                    }
                    else {
                        player.addEffect(new MobEffectInstance(MobEffects.HEAL, 3));
                    }
                }
            }
        }
    }
}
