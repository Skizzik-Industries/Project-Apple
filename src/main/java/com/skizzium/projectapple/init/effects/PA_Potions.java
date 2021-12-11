package com.skizzium.projectapple.init.effects;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.PA_Registry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PA_Potions {
    public static final RegistryObject<Potion> CONVERSION = PA_Registry.POTIONS.register("conversion", () -> new Potion(new MobEffectInstance(PA_Effects.CONVERSION.get(), 12000)));

    @SubscribeEvent
    public static void registerRecipes(FMLCommonSetupEvent event) {
        BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_REGENERATION)), Ingredient.of(Items.ENCHANTED_GOLDEN_APPLE), PotionUtils.setPotion(new ItemStack(Items.POTION), PA_Potions.CONVERSION.get()));
    }
    
    public static void register() {}
}
