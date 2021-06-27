package com.skizzium.projectapple.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import com.skizzium.projectapple.init.PA_Effects;

public class CorruptedBlock extends Block {
    public CorruptedBlock(Properties properties) {
        super(properties);
    }

    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, MobEntity entity) {
        return PathNodeType.DAMAGE_OTHER;
    }

    @Override
    public void stepOn(World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && !((LivingEntity) entity).hasEffect(PA_Effects.CORRUPTION.get())) {
            if (entity instanceof ServerPlayerEntity) {
                if (((ServerPlayerEntity) entity).gameMode.isSurvival()) {
                    ((ServerPlayerEntity) entity).addEffect(new EffectInstance(PA_Effects.CORRUPTION.get(), 1200, 1));
                }
            }
            else if (entity instanceof PlayerEntity && world.isClientSide()) {
                NetworkPlayerInfo network = Minecraft.getInstance().getConnection().getPlayerInfo(((AbstractClientPlayerEntity) entity).getGameProfile().getId());

                if (network.getGameMode() == GameType.SURVIVAL || network.getGameMode() == GameType.ADVENTURE) {
                    ((PlayerEntity) entity).addEffect(new EffectInstance(PA_Effects.CORRUPTION.get(), 1200, 1));
                }
            }
            else {
                ((LivingEntity) entity).addEffect(new EffectInstance(PA_Effects.CORRUPTION.get(), 1200, 1));
            }
        }
    }
}
