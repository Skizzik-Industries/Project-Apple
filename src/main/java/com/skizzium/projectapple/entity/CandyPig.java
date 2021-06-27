package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.init.PA_Entities;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class CandyPig extends PigEntity {
    private static final Ingredient FOOD_ITEMS = Ingredient.of(PA_Items.CANDY_CANE.get());

    @Override
    public boolean isFood(ItemStack item) {
        return FOOD_ITEMS.test(item);
    }

    public CandyPig(EntityType<? extends PigEntity> entity, World world) {
        super(entity, world);
    }

    public CandyPig getBreedOffspring(ServerWorld world, AgeableEntity entity) {
        return PA_Entities.CANDY_PIG.create(this.level);
    }

    public static AttributeModifierMap.MutableAttribute buildAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public static boolean canEntitySpawn(EntityType<? extends CandyPig> animal, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getBlockState(pos.below()).is(PA_Blocks.CANDY_NYLIUM.get()) && world.getRawBrightness(pos, 0) > 8 && world.canSeeSky(pos);
    }
}
