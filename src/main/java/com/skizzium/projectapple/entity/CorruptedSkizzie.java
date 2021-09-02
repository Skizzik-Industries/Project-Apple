package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.init.PA_Config;
import com.skizzium.projectapple.init.PA_Effects;
import com.skizzium.projectapple.init.PA_Tags;
import com.skizzium.projectapple.init.block.PA_Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;

public class CorruptedSkizzie extends Skizzie {
    public CorruptedSkizzie(EntityType<? extends Skizzie> entity, Level world) {
        super(entity, world);
    }

    public static AttributeSupplier.Builder buildAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FLYING_SPEED, 0.3D);
    }

    @Override
    public void baseTick() {
        super.baseTick();

        Level world = this.level;
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        BlockPos pos = new BlockPos(x, y-1, z);

        if (PA_Config.commonInstance.entities.allowCorruptedSkizzieAbility.get()) {
            if (!PA_Tags.Blocks.CORRUPTION_IMMUNE.contains(world.getBlockState(pos).getBlock())) {
                world.setBlock(pos, PA_Blocks.CORRUPTED_BLOCK.get().defaultBlockState(), 3);
            }
        }
    }

    @Override
    public void playerTouch(Player player) {
        Level world = player.getCommandSenderWorld();
        if (player instanceof LivingEntity && !player.hasEffect(PA_Effects.CORRUPTION.get())) {
            if (player instanceof ServerPlayer) {
                if (((ServerPlayer) player).gameMode.isSurvival()) {
                    player.addEffect(new MobEffectInstance(PA_Effects.CORRUPTION.get(), 1200, 1));
                }
            }
            else if (player instanceof Player && world.isClientSide()) {
                PlayerInfo network = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());

                if (network.getGameMode() == GameType.SURVIVAL || network.getGameMode() == GameType.ADVENTURE) {
                    player.addEffect(new MobEffectInstance(PA_Effects.CORRUPTION.get(), 1200, 1));
                }
            }
        }
    }
}
