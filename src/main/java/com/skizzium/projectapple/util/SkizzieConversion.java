package com.skizzium.projectapple.util;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.block.SkizzieStatue;
import com.skizzium.projectapple.entity.*;
import com.skizzium.projectapple.init.PA_Config;
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
        SkizzieStatue statue = ProjectApple.holiday == 1 ? PA_Blocks.SPOOKZIE_STATUE.get() : PA_Blocks.SKIZZIE_STATUE.get();

        double rotation = entity.yBodyRot;
        Direction statueFacing = rotation <= 45 ? Direction.SOUTH :
                                 rotation <= 135 ? Direction.WEST :
                                 rotation <= 225 ? Direction.NORTH :
                                 rotation <= 315 ? Direction.EAST :
                                 Direction.SOUTH;

        FluidState fluidstate = world.getFluidState(pos);
        return statue.defaultBlockState().setValue(SkizzieStatue.FACING, statueFacing).setValue(SkizzieStatue.WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    // Required since the way I randomize the statue spawnment requires a float between 0.0 and 1.0
    private static float getChanceFromConfig() {
        int configValue = PA_Config.commonInstance.entities.skizzieStatueChances.get();
        String valueString = Integer.toString(configValue);
        int digitCount = valueString.length();

        if (digitCount == 1) {
            return Float.parseFloat("0.0" + valueString);
        }
        else if (digitCount == 2) {
            return Float.parseFloat("0." + valueString);
        }
        else if (digitCount == 3) {
            return 1.0F;
        }

        return 0.3F;
    }

    private static InteractionResult spawn(LivingEntity from, LivingEntity to, ItemStack item, Player player, Level world, boolean canBeStatue) {
        double fromX = from.getX();
        double fromY = from.getY();
        double fromZ = from.getZ();
        BlockPos fromPos = new BlockPos(fromX, fromY, fromZ);
        
        if (world instanceof ServerLevel) {
            if (!((ServerPlayer) player).gameMode.isCreative() && Math.random() < getChanceFromConfig() && canBeStatue) {
                world.setBlock(fromPos, skizzieStatueState(from, fromPos, world), 3);
                from.discard();
                return InteractionResult.SUCCESS;
            }

            if (to instanceof FriendlySkizzie && from instanceof Skizzie) {
                ((FriendlySkizzie) to).setHolidayVariation(((Skizzie) from).getHolidayVariation());
            }
            else if (to instanceof FriendlySkizzie) {
                ((FriendlySkizzie) to).setHolidayVariation(((FriendlySkizzie) from).getHolidayVariation());
            }
            else if (to instanceof Skizzie && from instanceof FriendlySkizzie) {
                ((Skizzie) to).setHolidayVariation(((FriendlySkizzie) from).getHolidayVariation());
            }
            else if (to instanceof Skizzie) {
                ((Skizzie) to).setHolidayVariation(((Skizzie) from).getHolidayVariation());
            }
            
            to.moveTo(fromX, fromY, fromZ, from.xRotO, from.yRotO);
            to.setYBodyRot(from.yBodyRot);
            to.setYHeadRot(from.yHeadRot);

            ((Mob) to).finalizeSpawn((ServerLevel) world, world.getCurrentDifficultyAt(to.blockPosition()), MobSpawnType.CONVERSION, null, null);
            world.addFreshEntity(to);

            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(world);
            lightning.moveTo(Vec3.atCenterOf(fromPos));
            world.addFreshEntity(lightning);

            from.discard();
        }
        return InteractionResult.SUCCESS;
    }

    public static InteractionResult convert(LivingEntity skizzie, Player player) {
        Item item = player.getMainHandItem().getItem();
        ItemStack itemStack = player.getMainHandItem();
        Level world = player.level;

        if (!world.isClientSide) {
            if (!(skizzie instanceof FriendlySkizzie) && item == Items.WATER_BUCKET) {
                if (skizzie instanceof WitchSkizzie) {
                    return spawn(skizzie, new FriendlyWitchSkizzie(PA_Entities.FRIENDLY_WITCH_SKIZZIE.get(), world), itemStack, player, world, true);
                }
                else if (skizzie instanceof Skizzie) {
                    return spawn(skizzie, new FriendlySkizzie(PA_Entities.FRIENDLY_SKIZZIE.get(), world), itemStack, player, world, true);
                }
            }
            else if (skizzie instanceof FriendlySkizzie && !(skizzie instanceof FriendlyWitchSkizzie) && item == Items.POTION) {
                return spawn(skizzie, new FriendlyWitchSkizzie(PA_Entities.FRIENDLY_WITCH_SKIZZIE.get(), world), itemStack, player, world, false);
            }
            /*else if (skizzie instanceof FriendlySkizzie && item == PA_Blocks.SMALL_SKIZZIK_HEAD.get().asItem()) {
                return convert(skizzie, new FriendlySkizzie(PA_Entities.FRIENDLY_SKIZZIE, world), itemStack, player, world, false);
            }*/
            else if (!(skizzie instanceof Skizzie) && item == Items.LAVA_BUCKET) {
                if (skizzie instanceof FriendlyWitchSkizzie) {
                    return spawn(skizzie, new WitchSkizzie(PA_Entities.WITCH_SKIZZIE.get(), world), itemStack, player, world, true);
                }
                else if (skizzie instanceof FriendlySkizzie) {
                    return spawn(skizzie, new Skizzie(PA_Entities.SKIZZIE.get(), world), itemStack, player, world, true);
                }
            }
            else if (!(skizzie instanceof KaboomSkizzie) && !(skizzie instanceof FriendlySkizzie) && item == Items.GUNPOWDER) {
                return spawn(skizzie, new KaboomSkizzie(PA_Entities.KABOOM_SKIZZIE.get(), world), itemStack, player, world, false);
            }
            else if (!(skizzie instanceof CorruptedSkizzie) && !(skizzie instanceof FriendlySkizzie) && item == PA_Blocks.CORRUPTED_BLOCK.get().asItem()) {
                return spawn(skizzie, new CorruptedSkizzie(PA_Entities.CORRUPTED_SKIZZIE.get(), world), itemStack, player, world, false);
            }
            else if (!(skizzie instanceof WitchSkizzie) && !(skizzie instanceof FriendlySkizzie) && item == Items.POTION) {
                return spawn(skizzie, new WitchSkizzie(PA_Entities.WITCH_SKIZZIE.get(), world), itemStack, player, world, false);
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
