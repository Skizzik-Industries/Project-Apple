package com.skizzium.projectapple.item;

import com.skizzium.projectapple.init.PA_Tags;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

public class RainbowSword extends SwordItem {
    public RainbowSword(IItemTier tier, int p_i48460_2_, float p_i48460_3_, Properties properties) {
        super(tier, p_i48460_2_, p_i48460_3_, properties);
    }

    public float getDamage() {
        return 9.5F;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack item, @Nullable World world, List<ITextComponent> text, ITooltipFlag tooltip) {
        text.add(new StringTextComponent("Right-Click on a block to teleport it to another dimension."));
    }

    public ActionResultType useOn(ItemUseContext context) {
        ItemStack item = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        PlayerEntity player = context.getPlayer();
        World world = context.getLevel();

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        
        if (!PA_Tags.Blocks.RAINBOW_SWORD_IMMUNE.contains(world.getBlockState(pos).getBlock())) {
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

            if (world instanceof ServerWorld) {
                ((ServerWorld) world).sendParticles(ParticleTypes.PORTAL, x, y, z, 15, 1, 1, 1, 1);
            }

            world.playSound(null, new BlockPos(x, y, z), SoundEvents.PORTAL_TRAVEL, SoundCategory.PLAYERS, (float) 1, (float) 1);
            item.hurtAndBreak(1, player, (breakevent) -> breakevent.broadcastBreakEvent(context.getHand()));
        }
        return super.useOn(context);
    }
}
