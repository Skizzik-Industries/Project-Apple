package com.skizzium.projectapple.util;

import com.skizzium.projectapple.block.SkizzieStatue;
import com.skizzium.projectapple.entity.*;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.entity.PA_Entities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;

public class SkizzieConversion {
    private static BlockState skizzieStatueState(LivingEntity entity, BlockPos pos, Level world) {
        SkizzieStatue statue = PA_Blocks.SKIZZIE_STATUE.get();

        double rotation = entity.yBodyRot;
        Direction statueFacing = rotation <= 45 ? Direction.SOUTH :
                                 rotation <= 135 ? Direction.WEST :
                                 rotation <= 225 ? Direction.NORTH :
                                 rotation <= 315 ? Direction.EAST :
                                 Direction.SOUTH;

        FluidState fluidstate = world.getFluidState(pos);
        return statue.defaultBlockState().setValue(statue.FACING, statueFacing).setValue(statue.WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    private static InteractionResult convert(LivingEntity from, LivingEntity to, ItemStack item, Player player, Level world, boolean canTurnStone) {
        double fromX = from.getX();
        double fromY = from.getY();
        double fromZ = from.getZ();
        BlockPos fromPos = new BlockPos(fromX, fromY, fromZ);

        if (((ServerPlayer) player).gameMode.isCreative()) {
            if (world instanceof ServerLevel) {
                to.moveTo(fromX, fromY, fromZ, from.xRotO, from.yRotO);
                to.setYBodyRot(from.yBodyRot);
                to.setYHeadRot(from.yHeadRot);

                ((Mob) to).finalizeSpawn((ServerLevel) world, world.getCurrentDifficultyAt(to.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                world.addFreshEntity(to);

                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(world);
                lightning.moveTo(Vec3.atCenterOf(fromPos));
                world.addFreshEntity(lightning);

                from.discard();
            }
        }
        else {
            if (Math.random() < 0.3 && canTurnStone) {
                world.setBlock(fromPos, skizzieStatueState(from, fromPos, world), 3);
                from.discard();
            }
            else {
                to.moveTo(fromX, fromY, fromZ, from.xRotO, from.yRotO);
                to.setYBodyRot(from.yBodyRot);
                to.setYHeadRot(from.yHeadRot);

                ((Mob) to).finalizeSpawn((ServerLevel) world, world.getCurrentDifficultyAt(to.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                world.addFreshEntity(to);

                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(world);
                lightning.moveTo(Vec3.atCenterOf(fromPos));
                world.addFreshEntity(lightning);

                from.discard();
                item.shrink(1);
            }
        }
        return InteractionResult.SUCCESS;
    }

    public static InteractionResult conversion(LivingEntity skizzie, Player player) {
        Item item = player.getMainHandItem().getItem();
        ItemStack itemStack = player.getMainHandItem();
        Level world = player.level;

        if (!world.isClientSide) {
            if (!(skizzie instanceof FriendlySkizzie) && item == Items.WATER_BUCKET) {
                if (skizzie instanceof WitchSkizzie) {
                    return convert(skizzie, new FriendlyWitchSkizzie(PA_Entities.FRIENDLY_WITCH_SKIZZIE, world), itemStack, player, world, true);
                }
                else if (skizzie instanceof Skizzie) {
                    return convert(skizzie, new FriendlySkizzie(PA_Entities.FRIENDLY_SKIZZIE, world), itemStack, player, world, true);
                }
            }
            else if (skizzie instanceof FriendlySkizzie && !(skizzie instanceof FriendlyWitchSkizzie) && item == Items.POTION) {
                return convert(skizzie, new FriendlyWitchSkizzie(PA_Entities.FRIENDLY_WITCH_SKIZZIE, world), itemStack, player, world, false);
            }
            /*else if (skizzie instanceof FriendlySkizzie && item == PA_Blocks.SMALL_SKIZZIK_HEAD.get().asItem()) {
                return convert(skizzie, new FriendlySkizzie(PA_Entities.FRIENDLY_SKIZZIE, world), itemStack, player, world, false);
            }*/
            else if (!(skizzie instanceof Skizzie) && item == Items.LAVA_BUCKET) {
                if (skizzie instanceof FriendlyWitchSkizzie) {
                    return convert(skizzie, new WitchSkizzie(PA_Entities.WITCH_SKIZZIE, world), itemStack, player, world, true);
                }
                else if (skizzie instanceof FriendlySkizzie) {
                    return convert(skizzie, new Skizzie(PA_Entities.SKIZZIE, world), itemStack, player, world, true);
                }
            }
            else if (!(skizzie instanceof KaboomSkizzie) && !(skizzie instanceof FriendlySkizzie) && item == Items.GUNPOWDER) {
                return convert(skizzie, new KaboomSkizzie(PA_Entities.KABOOM_SKIZZIE, world), itemStack, player, world, false);
            }
            else if (!(skizzie instanceof CorruptedSkizzie) && !(skizzie instanceof FriendlySkizzie) && item == PA_Blocks.CORRUPTED_BLOCK.get().asItem()) {
                return convert(skizzie, new CorruptedSkizzie(PA_Entities.CORRUPTED_SKIZZIE, world), itemStack, player, world, false);
            }
            else if (!(skizzie instanceof WitchSkizzie) && !(skizzie instanceof FriendlySkizzie) && item == Items.POTION) {
                return convert(skizzie, new WitchSkizzie(PA_Entities.WITCH_SKIZZIE, world), itemStack, player, world, false);
            }
            /*else if (!(skizzie instanceof FriendlySkizzie) && item == PA_Blocks.SMALL_SKIZZIK_HEAD.get().asItem()) {
                return convert(skizzie, new Skizzie(PA_Entities.SKIZZIE, world), itemStack, player, world, false);
            }*/
            return InteractionResult.PASS;
        }
        else {
            return InteractionResult.PASS;
        }
    }
}
