package com.skizzium.projectapple.entity;

import com.skizzium.projectapple.init.Register;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import com.skizzium.projectapple.init.ModEntities;
import com.skizzium.projectapple.init.block.ModBlocks;
import com.skizzium.projectapple.init.item.ModItems;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class CandyPig extends PigEntity {
    private static final Ingredient FOOD_ITEMS = Ingredient.of(ModItems.CANDY_CANE.get());
    public static final Item SPAWN_EGG = new SpawnEggItem(ModEntities.CANDY_PIG, 0XFF638C, 0XC92B60, (new Item.Properties()).tab(Register.LIVING_CANDY_TAB)).setRegistryName("skizzik:candy_pig_spawn_egg");

    @Override
    public boolean isFood(ItemStack item) {
        return FOOD_ITEMS.test(item);
    }

    public CandyPig(EntityType<? extends PigEntity> entity, World world) {
        super(entity, world);
    }

    public CandyPig getBreedOffspring(ServerWorld world, AgeableEntity entity) {
        return ModEntities.CANDY_PIG.create(this.level);
    }

    public static AttributeModifierMap.MutableAttribute buildAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public static boolean canEntitySpawn(EntityType<? extends CandyPig> animal, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getBlockState(pos.below()).is(ModBlocks.CANDY_NYLIUM.get()) && world.getRawBrightness(pos, 0) > 8 && world.canSeeSky(pos);
    }
}
