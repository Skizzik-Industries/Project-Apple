package com.skizzium.projectapple.init.data.server.recipes;

import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class PA_RecipeProvider extends RecipeProvider {
    public PA_RecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipe) {
        ShapelessRecipeBuilder.shapeless(PA_Items.BUTTER.get(), 3).requires(Items.SUGAR).requires(Items.MILK_BUCKET, 2).unlockedBy(PA_RecipeBuilders.getHasName(Items.SUGAR), PA_RecipeBuilders.has(Items.SUGAR)).unlockedBy(PA_RecipeBuilders.getHasName(Items.MILK_BUCKET), PA_RecipeBuilders.has(Items.MILK_BUCKET)).save(recipe);

        PA_RecipeBuilders.doorSet(recipe, PA_Blocks.CANDY_PLANKS.get(), PA_Blocks.CANDY_TRAPDOOR.get(), PA_Blocks.CANDY_DOOR.get());
        
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(PA_Items.CANDIANITE_PICKAXE.get(), PA_Items.CANDIANITE_SHOVEL.get(), PA_Items.CANDIANITE_AXE.get(), PA_Items.CANDIANITE_HOE.get(), PA_Items.CANDIANITE_SWORD.get(), PA_Items.CANDIANITE_HELMET.get(), PA_Items.CANDIANITE_CHESTPLATE.get(), PA_Items.CANDIANITE_LEGGINGS.get(), PA_Items.CANDIANITE_BOOTS.get(), PA_Items.CANDIANITE_HORSE_ARMOR.get()), PA_Items.CANDIANITE_NUGGET.get(), 0.1F, 200).unlockedBy("has_candianite_pickaxe", PA_RecipeBuilders.has(PA_Items.CANDIANITE_PICKAXE.get())).unlockedBy("has_candianite_shovel", PA_RecipeBuilders.has(PA_Items.CANDIANITE_SHOVEL.get())).unlockedBy("has_candianite_axe", PA_RecipeBuilders.has(PA_Items.CANDIANITE_AXE.get())).unlockedBy("has_candianite_hoe", PA_RecipeBuilders.has(PA_Items.CANDIANITE_HOE.get())).unlockedBy("has_candianite_sword", PA_RecipeBuilders.has(PA_Items.CANDIANITE_SWORD.get())).unlockedBy("has_candianite_helmet", PA_RecipeBuilders.has(PA_Items.CANDIANITE_HELMET.get())).unlockedBy("has_candianite_chestplate", PA_RecipeBuilders.has(PA_Items.CANDIANITE_CHESTPLATE.get())).unlockedBy("has_candianite_leggings", PA_RecipeBuilders.has(PA_Items.CANDIANITE_LEGGINGS.get())).unlockedBy("has_candianite_boots", PA_RecipeBuilders.has(PA_Items.CANDIANITE_BOOTS.get())).unlockedBy("has_candianite_horse_armor", PA_RecipeBuilders.has(PA_Items.CANDIANITE_HORSE_ARMOR.get())).save(recipe, PA_RecipeBuilders.getFinalName(PA_RecipeBuilders.getItemName(PA_Items.CANDIANITE_NUGGET.get()) + "_from_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(PA_Items.CANDIANITE_PICKAXE.get(), PA_Items.CANDIANITE_SHOVEL.get(), PA_Items.CANDIANITE_AXE.get(), PA_Items.CANDIANITE_HOE.get(), PA_Items.CANDIANITE_SWORD.get(), PA_Items.CANDIANITE_HELMET.get(), PA_Items.CANDIANITE_CHESTPLATE.get(), PA_Items.CANDIANITE_LEGGINGS.get(), PA_Items.CANDIANITE_BOOTS.get(), PA_Items.CANDIANITE_HORSE_ARMOR.get()), PA_Items.CANDIANITE_NUGGET.get(), 0.1F, 100).unlockedBy("has_candianite_pickaxe", PA_RecipeBuilders.has(PA_Items.CANDIANITE_PICKAXE.get())).unlockedBy("has_candianite_shovel", PA_RecipeBuilders.has(PA_Items.CANDIANITE_SHOVEL.get())).unlockedBy("has_candianite_axe", PA_RecipeBuilders.has(PA_Items.CANDIANITE_AXE.get())).unlockedBy("has_candianite_hoe", PA_RecipeBuilders.has(PA_Items.CANDIANITE_HOE.get())).unlockedBy("has_candianite_sword", PA_RecipeBuilders.has(PA_Items.CANDIANITE_SWORD.get())).unlockedBy("has_candianite_helmet", PA_RecipeBuilders.has(PA_Items.CANDIANITE_HELMET.get())).unlockedBy("has_candianite_chestplate", PA_RecipeBuilders.has(PA_Items.CANDIANITE_CHESTPLATE.get())).unlockedBy("has_candianite_leggings", PA_RecipeBuilders.has(PA_Items.CANDIANITE_LEGGINGS.get())).unlockedBy("has_candianite_boots", PA_RecipeBuilders.has(PA_Items.CANDIANITE_BOOTS.get())).unlockedBy("has_candianite_horse_armor", PA_RecipeBuilders.has(PA_Items.CANDIANITE_HORSE_ARMOR.get())).save(recipe, PA_RecipeBuilders.getFinalName(PA_RecipeBuilders.getItemName(PA_Items.CANDIANITE_NUGGET.get()) + "_from_blasting"));
        PA_RecipeBuilders.nuggetRecipes(recipe, PA_Items.CANDIANITE_NUGGET.get(), PA_Items.CANDIANITE_INGOT.get());
        PA_RecipeBuilders.ingotSmeltingRecipes(recipe, PA_Items.CANDIANITE_INGOT.get(), PA_Blocks.CANDIANITE_ORE.get());
        
        PA_RecipeBuilders.toolSet(recipe, PA_Items.CANDIANITE_INGOT.get(), PA_Items.CANDIANITE_AXE.get(), PA_Items.CANDIANITE_HOE.get(), PA_Items.CANDIANITE_PICKAXE.get(), PA_Items.CANDIANITE_SHOVEL.get(), PA_Items.CANDIANITE_SWORD.get());
        PA_RecipeBuilders.armorSet(recipe, PA_Items.CANDIANITE_INGOT.get(), PA_Items.CANDIANITE_HELMET.get(), PA_Items.CANDIANITE_CHESTPLATE.get(), PA_Items.CANDIANITE_LEGGINGS.get(), PA_Items.CANDIANITE_BOOTS.get());

        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.BLACK_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.BLUE_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.BROWN_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.GREEN_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.ORANGE_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.PINK_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.YELLOW_GEM.get());

        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.BLACK_GEM.get(), PA_Blocks.BLACK_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.BLUE_GEM.get(), PA_Blocks.BLUE_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.BROWN_GEM.get(), PA_Blocks.BROWN_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.GREEN_GEM.get(), PA_Blocks.GREEN_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.ORANGE_GEM.get(), PA_Blocks.ORANGE_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.PINK_GEM.get(), PA_Blocks.PINK_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.YELLOW_GEM.get(), PA_Blocks.YELLOW_GEM_BLOCK.get());
    }
}
