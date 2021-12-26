package com.skizzium.projectapple.item;

import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.world.PA_PoiTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.Optional;

public class LootBagLocator extends Item {
    public LootBagLocator(Properties properties) {
        super(properties);
    }

    public static boolean isTrackingLootBag(ItemStack stack) {
        CompoundTag compoundtag = stack.getTag();
        return compoundtag != null && (compoundtag.contains("LootBagDimension") || compoundtag.contains("LootBagPos"));
    }

    public static Optional<ResourceKey<Level>> getLootBagDimension(CompoundTag tag) {
        return Level.RESOURCE_KEY_CODEC.parse(NbtOps.INSTANCE, tag.get("LootBagDimension")).result();
    }
    
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (!level.isClientSide) {
            if (isTrackingLootBag(stack)) {
                CompoundTag tag = stack.getOrCreateTag();
                if (tag.contains("LootBagTracked") && !tag.getBoolean("LootBagTracked")) {
                    return;
                }

                Optional<ResourceKey<Level>> optional = getLootBagDimension(tag);
                if (optional.isPresent() && optional.get() == level.dimension() && tag.contains("LootBagPos")) {
                    BlockPos pos = NbtUtils.readBlockPos(tag.getCompound("LootBagPos"));
                    if (!level.isInWorldBounds(pos) || !((ServerLevel)level).getPoiManager().existsAtPosition(PA_PoiTypes.SKIZZIK_LOOT_BAG.get(), pos)) {
                        tag.remove("LootBagPos");
                    }
                }
            }
        }
    }

    public void bindLootBag(ResourceKey<Level> dimension, BlockPos pos, CompoundTag nbt) {
        nbt.put("LootBagPos", NbtUtils.writeBlockPos(pos));
        Level.RESOURCE_KEY_CODEC.encodeStart(NbtOps.INSTANCE, dimension).result().ifPresent((result) -> nbt.put("LootBagDimension", result));
        nbt.putBoolean("LootBagTracked", true);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos blockpos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        if (level.getBlockState(blockpos).is(PA_Blocks.SKIZZIK_LOOT_BAG.get()) && pContext.getPlayer().getAbilities().instabuild) {
            bindLootBag(pContext.getLevel().dimension(), pContext.getClickedPos(), pContext.getItemInHand().getOrCreateTag());
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
