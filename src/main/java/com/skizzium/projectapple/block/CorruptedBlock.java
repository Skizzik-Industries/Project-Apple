package com.skizzium.projectapple.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import com.skizzium.projectapple.init.PA_Effects;

public class CorruptedBlock extends Block {
    public CorruptedBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockPathTypes getAiPathNodeType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
        return BlockPathTypes.DAMAGE_OTHER;
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity && !((LivingEntity) entity).hasEffect(PA_Effects.CORRUPTION.get())) {
            if (entity instanceof ServerPlayer) {
                if (((ServerPlayer) entity).gameMode.isSurvival()) {
                    ((ServerPlayer) entity).addEffect(new MobEffectInstance(PA_Effects.CORRUPTION.get(), 1200, 1));
                }
            }
            else if (entity instanceof Player && world.isClientSide()) {
                PlayerInfo network = Minecraft.getInstance().getConnection().getPlayerInfo(((AbstractClientPlayer) entity).getGameProfile().getId());

                if (network.getGameMode() == GameType.SURVIVAL || network.getGameMode() == GameType.ADVENTURE) {
                    ((Player) entity).addEffect(new MobEffectInstance(PA_Effects.CORRUPTION.get(), 1200, 1));
                }
            }
            else {
                ((LivingEntity) entity).addEffect(new MobEffectInstance(PA_Effects.CORRUPTION.get(), 1200, 1));
            }
        }
    }
}
