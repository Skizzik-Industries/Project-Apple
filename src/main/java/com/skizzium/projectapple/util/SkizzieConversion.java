package com.skizzium.projectapple.util;

import com.skizzium.projectapple.block.SkizzieStatue;
import com.skizzium.projectapple.entity.*;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.entity.PA_Entities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SkizzieConversion {
    private static BlockState skizzieStatueState(LivingEntity entity, BlockPos pos, World world) {
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

    private static ActionResultType convert(LivingEntity from, LivingEntity to, ItemStack item, PlayerEntity player, World world, boolean canTurnStone) {
        double fromX = from.getX();
        double fromY = from.getY();
        double fromZ = from.getZ();
        BlockPos fromPos = new BlockPos(fromX, fromY, fromZ);

        if (((ServerPlayerEntity) player).gameMode.isCreative()) {
            if (world instanceof ServerWorld) {
                to.moveTo(fromX, fromY, fromZ, from.xRotO, from.yRotO);
                to.setYBodyRot(from.yBodyRot);
                to.setYHeadRot(from.yHeadRot);

                ((MobEntity) to).finalizeSpawn((ServerWorld) world, world.getCurrentDifficultyAt(to.blockPosition()), SpawnReason.MOB_SUMMONED, null, null);
                world.addFreshEntity(to);

                LightningBoltEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
                lightning.moveTo(Vector3d.atCenterOf(fromPos));
                world.addFreshEntity(lightning);

                from.remove();
            }
        }
        else {
            if (Math.random() < 0.3 && canTurnStone) {
                world.setBlock(fromPos, skizzieStatueState(from, fromPos, world), 3);
                from.remove();
            }
            else {
                to.moveTo(fromX, fromY, fromZ, from.xRotO, from.yRotO);
                to.setYBodyRot(from.yBodyRot);
                to.setYHeadRot(from.yHeadRot);

                ((MobEntity) to).finalizeSpawn((ServerWorld) world, world.getCurrentDifficultyAt(to.blockPosition()), SpawnReason.MOB_SUMMONED, null, null);
                world.addFreshEntity(to);

                LightningBoltEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
                lightning.moveTo(Vector3d.atCenterOf(fromPos));
                world.addFreshEntity(lightning);

                from.remove();
                item.shrink(1);
            }
        }
        return ActionResultType.SUCCESS;
    }

    public static ActionResultType conversion(LivingEntity skizzie, PlayerEntity player) {
        Item item = player.getMainHandItem().getItem();
        ItemStack itemStack = player.getMainHandItem();
        World world = player.level;

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
            return ActionResultType.PASS;
        }
        else {
            return ActionResultType.PASS;
        }
    }
}
