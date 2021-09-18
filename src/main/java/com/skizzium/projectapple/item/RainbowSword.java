package com.skizzium.projectapple.item;

import com.skizzium.projectapple.init.PA_Tags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class RainbowSword extends SwordItem {
    public RainbowSword(Tier tier, int p_i48460_2_, float p_i48460_3_, Properties properties) {
        super(tier, p_i48460_2_, p_i48460_3_, properties);
    }

    public float getDamage() {
        return 9.5F;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack item, @Nullable Level world, List<Component> text, TooltipFlag tooltip) {
        text.add(new TextComponent("Right-Click on a block to teleport it to another dimension."));
    }

    public InteractionResult useOn(UseOnContext context) {
        InteractionResult retrieval = super.useOn(context);
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        boolean isCreative = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId()).getGameMode().isCreative();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        ItemStack item = context.getItemInHand();
        
        if (isCreative || !PA_Tags.Blocks.RAINBOW_SWORD_IMMUNE.contains(world.getBlockState(pos).getBlock())) {
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

            if (world instanceof ServerLevel) {
                ((ServerLevel) world).sendParticles(ParticleTypes.PORTAL, x, y, z, 15, 1, 1, 1, 1);
            }

            world.playSound(null, new BlockPos(x, y,z), SoundEvents.PORTAL_TRAVEL, SoundSource.PLAYERS, (float) 1, (float) 1);
            if (!isCreative) {
                item.hurtAndBreak(1, player, (breakevent) -> breakevent.broadcastBreakEvent(context.getHand()));
            }
        }
        return retrieval;
    }
}
