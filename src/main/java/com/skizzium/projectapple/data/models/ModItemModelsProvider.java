package com.skizzium.projectapple.data.models;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelsProvider extends ItemModelProvider {
    public ModItemModelsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("command_block", modLoc("block/command_block"));
        withExistingParent("deactivated_command_block", modLoc("block/deactivated_command_block"));
        withExistingParent("broken_command_block", modLoc("block/broken_command_block"));

        withExistingParent("skizzik_flesh_block", modLoc("block/skizzik_flesh_block"));
        withExistingParent("corrupted_block", modLoc("block/corrupted_block"));

        withExistingParent("skizzie_spawn_egg", mcLoc("item/template_spawn_egg"));
        withExistingParent("kaboom_skizzie_spawn_egg", mcLoc("item/template_spawn_egg"));
        withExistingParent("minigun_skizzie_spawn_egg", mcLoc("item/template_spawn_egg"));
        withExistingParent("corrupted_skizzie_spawn_egg", mcLoc("item/template_spawn_egg"));
        withExistingParent("witch_skizzie_spawn_egg", mcLoc("item/template_spawn_egg"));

        withExistingParent("candy_pig_spawn_egg", mcLoc("item/template_spawn_egg"));

        withExistingParent("white_chocolate_block", modLoc("block/white_chocolate_block"));
        withExistingParent("chocolate_block", modLoc("block/chocolate_block"));
        withExistingParent("dark_chocolate_block", modLoc("block/dark_chocolate_block"));
        withExistingParent("waffle_block", modLoc("block/waffle_block"));

        withExistingParent("candianite_ore", modLoc("block/candianite_ore"));

        withExistingParent("candy_pressure_plate", modLoc("block/candy_pressure_plate"));
        withExistingParent("candy_button", modLoc("block/candy_button_inventory"));

        withExistingParent("candy_planks", modLoc("block/candy_planks"));
        withExistingParent("candy_slab", modLoc("block/candy_slab"));
        withExistingParent("candy_stairs", modLoc("block/candy_stairs"));

        withExistingParent("candy_trapdoor", modLoc("block/candy_trapdoor_bottom"));
        withExistingParent("candy_fence", modLoc("block/candy_fence_inventory"));
        withExistingParent("candy_fence_gate", modLoc("block/candy_fence_gate"));

        withExistingParent("candy_log", modLoc("block/candy_log"));
        withExistingParent("stripped_candy_log", modLoc("block/stripped_candy_log"));
        withExistingParent("candy_wood", modLoc("block/candy_wood"));
        withExistingParent("stripped_candy_wood", modLoc("block/stripped_candy_wood"));

        withExistingParent("candy_leaves", modLoc("block/candy_leaves"));
        withExistingParent("candy_nylium", modLoc("block/candy_nylium"));
        withExistingParent("candyrack", modLoc("block/candyrack"));

        withExistingParent("rainbow_ore", modLoc("block/rainbow_ore"));

        withExistingParent("rainbow_gem_block", modLoc("block/rainbow_gem_block"));
        withExistingParent("black_gem_block", modLoc("block/black_gem_block"));
        withExistingParent("blue_gem_block", modLoc("block/blue_gem_block"));
        withExistingParent("brown_gem_block", modLoc("block/brown_gem_block"));
        withExistingParent("yellow_gem_block", modLoc("block/yellow_gem_block"));
        withExistingParent("orange_gem_block", modLoc("block/orange_gem_block"));
        withExistingParent("green_gem_block", modLoc("block/green_gem_block"));
        withExistingParent("pink_gem_block", modLoc("block/pink_gem_block"));

        ModelFile generated = getExistingFile(mcLoc("item/generated"));

        defaultBuilder(generated, "skizzik_bone");
        defaultBuilder(generated, "raw_skizzik_flesh");
        defaultBuilder(generated, "skizzik_flesh");

        defaultBuilder(generated, "skizzik_flesh_cap");
        defaultBuilder(generated, "skizzik_flesh_tunic");
        defaultBuilder(generated, "skizzik_flesh_pants");
        defaultBuilder(generated, "skizzik_flesh_boots");

        defaultBuilder(generated, "salt");
        defaultBuilder(generated, "butter");

        defaultBuilder(generated, "candy_cane");
        defaultBuilder(generated, "maple_syrup_bucket");
        defaultBuilder(generated, "waffle");

        defaultBuilder(generated, "pancakes");
        defaultBuilder(generated, "syrup_pancakes");

        defaultBuilder(generated, "chocolate_bar_wrap");
        defaultBuilder(generated, "white_chocolate_bar");
        defaultBuilder(generated, "chocolate_bar");
        defaultBuilder(generated, "dark_chocolate_bar");

        defaultBuilder(generated, "candy_door");

        defaultBuilder(generated, "candianite_nugget");
        defaultBuilder(generated, "candianite_ingot");

        defaultBuilder(generated, "candianite_sword");
        defaultBuilder(generated, "candianite_pickaxe");
        defaultBuilder(generated, "candianite_axe");
        defaultBuilder(generated, "candianite_shovel");
        defaultBuilder(generated, "candianite_hoe");

        defaultBuilder(generated, "candianite_horse_armor");
        defaultBuilder(generated, "candianite_helmet");
        defaultBuilder(generated, "candianite_chestplate");
        defaultBuilder(generated, "candianite_leggings");
        defaultBuilder(generated, "candianite_boots");

        defaultBuilder(generated, "platinum_nugget");
        defaultBuilder(generated, "platinum_ingot");

        defaultBuilder(generated, "rainbow_sword");

        defaultBuilder(generated, "rainbow_gem");
        defaultBuilder(generated, "black_gem");
        defaultBuilder(generated, "blue_gem");
        defaultBuilder(generated, "brown_gem");
        defaultBuilder(generated, "yellow_gem");
        defaultBuilder(generated, "orange_gem");
        defaultBuilder(generated, "green_gem");
        defaultBuilder(generated, "pink_gem");
    }

    private ItemModelBuilder defaultBuilder(ModelFile generated, String name) {
        return getBuilder(name).parent(generated).texture("layer0", "item/" + name);
    }
}
