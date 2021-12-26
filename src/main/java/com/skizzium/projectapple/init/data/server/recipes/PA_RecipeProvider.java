package com.skizzium.projectapple.init.data.server.recipes;

import com.skizzium.projectapple.init.block.PA_Blocks;
import com.skizzium.projectapple.init.data.server.tags.PA_Tags;
import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class PA_RecipeProvider extends RecipeProvider {
    public PA_RecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipe) {
        ShapedRecipeBuilder.shaped(PA_Blocks.COMMAND_BLOCK.get()).define('#', PA_Blocks.DEACTIVATED_COMMAND_BLOCK.get()).define('S', Items.NETHER_STAR).define('R', Blocks.REDSTONE_BLOCK).pattern("SRS").pattern("R#R").pattern("SRS").group("skizzik_command_block").unlockedBy(PA_RecipeBuilders.getHasName(PA_Blocks.DEACTIVATED_COMMAND_BLOCK.get()), PA_RecipeBuilders.has(PA_Blocks.DEACTIVATED_COMMAND_BLOCK.get())).save(recipe);
        ShapedRecipeBuilder.shaped(PA_Blocks.DEACTIVATED_COMMAND_BLOCK.get()).define('#', PA_Blocks.BROKEN_COMMAND_BLOCK.get()).define('O', Blocks.OBSIDIAN).pattern("OOO").pattern("O#O").pattern("OOO").group("skizzik_command_block").unlockedBy(PA_RecipeBuilders.getHasName(PA_Blocks.BROKEN_COMMAND_BLOCK.get()), PA_RecipeBuilders.has(PA_Blocks.BROKEN_COMMAND_BLOCK.get())).save(recipe);

        UpgradeRecipeBuilder.smithing(Ingredient.of(Items.BONE), Ingredient.of(PA_Items.PLATINUM_NUGGET.get()), PA_Items.SKIZZIK_BONE.get()).unlocks(PA_RecipeBuilders.getHasName(Items.BONE), PA_RecipeBuilders.has(Items.BONE)).save(recipe, PA_RecipeBuilders.getFinalName("skizzik_bone"));
        ShapedRecipeBuilder.shaped(PA_Items.RAW_SKIZZIK_FLESH.get()).define('H', Items.RABBIT_HIDE).define('B', PA_Items.SKIZZIK_BONE.get()).define('L', Items.LEATHER).pattern("HBH").pattern("LBL").pattern("HBH").unlockedBy(PA_RecipeBuilders.getHasName(PA_Items.SKIZZIK_BONE.get()), PA_RecipeBuilders.has(PA_Items.SKIZZIK_BONE.get())).save(recipe);
        
        PA_RecipeBuilders.headRecipe(recipe, PA_Items.FRIENDLY_SKIZZIK_FLESH.get(), Items.SKELETON_SKULL, PA_Items.SMALL_FRIENDLY_SKIZZIK_HEAD.get());
        PA_RecipeBuilders.headRecipe(recipe, PA_Items.FRIENDLY_SKIZZIK_FLESH.get(), Items.WITHER_SKELETON_SKULL, PA_Items.FRIENDLY_SKIZZIK_HEAD.get());
        PA_RecipeBuilders.headRecipe(recipe, PA_Items.SKIZZIK_FLESH.get(), Items.SKELETON_SKULL, PA_Items.SMALL_SKIZZIK_HEAD.get());
        PA_RecipeBuilders.headRecipe(recipe, PA_Items.SKIZZIK_FLESH.get(), Items.WITHER_SKELETON_SKULL, PA_Items.SKIZZIK_HEAD.get());

        PA_RecipeBuilders.smallHeadWithGemsRecipe(recipe, PA_Items.SMALL_FRIENDLY_SKIZZIK_HEAD.get(), PA_Items.SMALL_FRIENDLY_SKIZZIK_HEAD_WITH_GEMS.get());
        PA_RecipeBuilders.smallHeadWithGemsRecipe(recipe, PA_Items.SMALL_SKIZZIK_HEAD.get(), PA_Items.SMALL_SKIZZIK_HEAD_WITH_GEMS.get());

        PA_RecipeBuilders.headWithGemsRecipe(recipe, PA_Items.FRIENDLY_SKIZZIK_HEAD.get(), PA_Items.FRIENDLY_SKIZZIK_HEAD_WITH_GEMS.get());
        PA_RecipeBuilders.headWithGemsRecipe(recipe, PA_Items.SKIZZIK_HEAD.get(), PA_Items.SKIZZIK_HEAD_WITH_GEMS.get());
        
        PA_RecipeBuilders.ribRecipes(recipe, PA_Items.FRIENDLY_SKIZZIK_FLESH.get(), PA_Items.FRIENDLY_SKIZZIK_RIB.get(), PA_Items.FRIENDLY_SKIZZIK_BOTTOM_RIB.get(), PA_Items.FRIENDLY_SKIZZIK_BIG_RIB.get(), "friendly_skizzik_rib");
        PA_RecipeBuilders.ribRecipes(recipe, PA_Items.SKIZZIK_FLESH.get(), PA_Items.SKIZZIK_RIB.get(), PA_Items.SKIZZIK_BOTTOM_RIB.get(), PA_Items.SKIZZIK_BIG_RIB.get(), "skizzik_rib");

        PA_RecipeBuilders.fleshBlockRecipes(recipe, PA_Items.RAW_SKIZZIK_FLESH.get(), PA_Blocks.RAW_SKIZZIK_FLESH_BLOCK.get());
        PA_RecipeBuilders.fleshBlockRecipes(recipe, PA_Items.FRIENDLY_SKIZZIK_FLESH.get(), PA_Blocks.FRIENDLY_SKIZZIK_FLESH_BLOCK.get());
        PA_RecipeBuilders.fleshBlockRecipes(recipe, PA_Items.SKIZZIK_FLESH.get(), PA_Blocks.SKIZZIK_FLESH_BLOCK.get());
        
        ShapelessRecipeBuilder.shapeless(PA_Items.RAINBOW_GEM.get()).requires(Ingredient.of(PA_Tags.Items.SKIZZIK_BASE_GEMS), 3).group("skizzik_gem")
                .unlockedBy(PA_RecipeBuilders.getHasName(PA_Items.BLACK_GEM.get()), PA_RecipeBuilders.has(PA_Items.BLACK_GEM.get()))
                .unlockedBy(PA_RecipeBuilders.getHasName(PA_Items.BLUE_GEM.get()), PA_RecipeBuilders.has(PA_Items.BLUE_GEM.get()))
                .unlockedBy(PA_RecipeBuilders.getHasName(PA_Items.BROWN_GEM.get()), PA_RecipeBuilders.has(PA_Items.BROWN_GEM.get()))
                .unlockedBy(PA_RecipeBuilders.getHasName(PA_Items.GREEN_GEM.get()), PA_RecipeBuilders.has(PA_Items.GREEN_GEM.get()))
                .unlockedBy(PA_RecipeBuilders.getHasName(PA_Items.ORANGE_GEM.get()), PA_RecipeBuilders.has(PA_Items.ORANGE_GEM.get()))
                .unlockedBy(PA_RecipeBuilders.getHasName(PA_Items.PINK_GEM.get()), PA_RecipeBuilders.has(PA_Items.PINK_GEM.get()))
                .unlockedBy(PA_RecipeBuilders.getHasName(PA_Items.YELLOW_GEM.get()), PA_RecipeBuilders.has(PA_Items.YELLOW_GEM.get()))
        .save(recipe);

        ShapedRecipeBuilder.shaped(PA_Items.RAINBOW_SWORD.get()).define('#', PA_Items.RAINBOW_GEM.get()).define('/', Items.STICK).pattern(" # ").pattern("###").pattern(" / ").unlockedBy(PA_RecipeBuilders.getHasName(PA_Items.RAINBOW_GEM.get()), PA_RecipeBuilders.has(PA_Items.RAINBOW_GEM.get())).save(recipe);

        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.BLACK_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.BLUE_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.BROWN_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.GREEN_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.ORANGE_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.PINK_GEM.get());
        PA_RecipeBuilders.gemRecipe(recipe, PA_Items.YELLOW_GEM.get());

        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.RAINBOW_GEM.get(), PA_Blocks.RAINBOW_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.BLACK_GEM.get(), PA_Blocks.BLACK_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.BLUE_GEM.get(), PA_Blocks.BLUE_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.BROWN_GEM.get(), PA_Blocks.BROWN_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.GREEN_GEM.get(), PA_Blocks.GREEN_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.ORANGE_GEM.get(), PA_Blocks.ORANGE_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.PINK_GEM.get(), PA_Blocks.PINK_GEM_BLOCK.get());
        PA_RecipeBuilders.gemBlockRecipes(recipe, PA_Items.YELLOW_GEM.get(), PA_Blocks.YELLOW_GEM_BLOCK.get());
    }
}
