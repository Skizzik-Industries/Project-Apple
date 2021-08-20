package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.item.PA_Items;
import com.skizzium.projectapple.init.world.PA_Biomes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import java.util.Random;

public class CandyPig extends Pig {
    private static final Ingredient FOOD_ITEMS = Ingredient.of(PA_Items.CANDY_CANE.get());

    @Override
    public boolean isFood(ItemStack item) {
        return FOOD_ITEMS.test(item);
    }

    public CandyPig(EntityType<? extends Pig> entity, Level world) {
        super(entity, world);
    }

    @Override
    public CandyPig getBreedOffspring(ServerLevel world, AgeableMob entity) {
        return PA_Entities.CANDY_PIG.create(this.level);
    }

    public static AttributeSupplier.Builder buildAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public static boolean canEntitySpawn(EntityType<? extends CandyPig> animal, LevelAccessor world, MobSpawnType reason, BlockPos pos, Random random) {
        return world.getBlockState(pos.below()).is(PA_Blocks.CANDY_NYLIUM.get()) && world.getRawBrightness(pos, 0) > 8 && world.canSeeSky(pos) && world.getBiome(pos).equals(PA_Biomes.CANDY_PLAINS.get());
    }
}
