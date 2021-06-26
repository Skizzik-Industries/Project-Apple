package com.skizzium.projectapple.item;

import com.skizzium.projectapple.init.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

public class RainbowSword extends SwordItem {
    public RainbowSword(IItemTier iitemtier, int p_i48460_2_, float p_i48460_3_, Properties properties) {
        super(iitemtier, p_i48460_2_, p_i48460_3_, properties);
    }

    public float getDamage() {
        return 9.5F;
    }

    public ActionResultType useOn(ItemUseContext context) {
        ActionResultType retrieval = super.useOn(context);
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        PlayerEntity entity = context.getPlayer();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        ItemStack item = context.getItemInHand();
        
        if (!ModTags.Blocks.RAINBOW_SWORD_IMMUNE.contains(world.getBlockState(pos).getBlock())) {
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

            if (world instanceof ServerWorld) {
                ((ServerWorld) world).sendParticles(ParticleTypes.PORTAL, x, y, z, 15, 1, 1, 1, 1);
            }

            world.playSound(null, new BlockPos(x, y,z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.portal.travel")), SoundCategory.PLAYERS, (float) 1, (float) 1);
            item.hurtAndBreak(1, entity, (breakevent) -> {breakevent.broadcastBreakEvent(context.getHand());});
        }
        return retrieval;
    }
}
