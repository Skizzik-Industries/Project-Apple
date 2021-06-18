package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.init.ModEffects;
import com.skizzium.projectapple.init.ModTags;
import com.skizzium.projectapple.init.block.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraft.world.World;

public class CorruptedSkizzie extends Skizzie {
    public CorruptedSkizzie(EntityType<? extends Skizzie> entity, World world) {
        super(entity, world);
    }

    public static AttributeModifierMap.MutableAttribute buildAttributes() {
        return MonsterEntity.createMobAttributes()
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

        World world = this.level;
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        BlockPos pos = new BlockPos(x, y-1, z);

        if (!ModTags.Blocks.CORRUPTION_IMMUNE.contains(world.getBlockState(pos).getBlock())) {
            world.setBlock(pos, ModBlocks.CORRUPTED_BLOCK.get().defaultBlockState(), 3);
        }
    }

    @Override
    public void playerTouch(PlayerEntity player) {
        World world = player.getCommandSenderWorld();
        if (player instanceof LivingEntity && !player.hasEffect(ModEffects.CORRUPTION.get())) {
            if (player instanceof ServerPlayerEntity) {
                if (((ServerPlayerEntity) player).gameMode.isSurvival()) {
                    player.addEffect(new EffectInstance(ModEffects.CORRUPTION.get(), 1200, 1));
                }
            }
            else if (player instanceof PlayerEntity && world.isClientSide()) {
                NetworkPlayerInfo network = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());

                if (network.getGameMode() == GameType.SURVIVAL || network.getGameMode() == GameType.ADVENTURE) {
                    player.addEffect(new EffectInstance(ModEffects.CORRUPTION.get(), 1200, 1));
                }
            }
        }
    }
}
