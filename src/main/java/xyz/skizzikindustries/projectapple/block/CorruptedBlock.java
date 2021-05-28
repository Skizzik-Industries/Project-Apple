package xyz.skizzikindustries.projectapple.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import xyz.skizzikindustries.projectapple.init.ModEffects;

public class CorruptedBlock extends Block {
    public CorruptedBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && /*((ServerPlayerEntity) entity).gameMode.isSurvival() &&*/ !((LivingEntity) entity).hasEffect(ModEffects.CORRUPTION.get())) {
            if (entity instanceof ServerPlayerEntity) {
                if (((ServerPlayerEntity) entity).gameMode.isSurvival()) {
                    ((ServerPlayerEntity) entity).addEffect(new EffectInstance(ModEffects.CORRUPTION.get(), 1200, 1));
                }
            }
            else if (entity instanceof PlayerEntity && world.isClientSide()) {
                NetworkPlayerInfo network = Minecraft.getInstance().getConnection().getPlayerInfo(((AbstractClientPlayerEntity) entity).getGameProfile().getId());

                if (network.getGameMode() == GameType.SURVIVAL || network.getGameMode() == GameType.ADVENTURE) {
                    ((PlayerEntity) entity).addEffect(new EffectInstance(ModEffects.CORRUPTION.get(), 1200, 1));
                }
            }
            else {
                ((LivingEntity) entity).addEffect(new EffectInstance(ModEffects.CORRUPTION.get(), 1200, 1));
            }
        }
    }
}
