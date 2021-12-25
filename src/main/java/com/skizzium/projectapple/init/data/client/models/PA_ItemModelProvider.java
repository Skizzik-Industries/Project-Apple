package com.skizzium.projectapple.init.data.client.models;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PA_ItemModelProvider extends ItemModelProvider {
    public PA_ItemModelProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("command_block", modLoc("block/command_block"));
        withExistingParent("deactivated_command_block", modLoc("block/deactivated_command_block"));
        withExistingParent("broken_command_block", modLoc("block/broken_command_block"));

        withExistingParent("small_friendly_skizzik_head", mcLoc("item/template_skull"));
        withExistingParent("small_friendly_skizzik_head_with_gems", mcLoc("item/template_skull"));
        withExistingParent("friendly_skizzik_head", mcLoc("item/template_skull"));
        withExistingParent("friendly_skizzik_head_with_gems", mcLoc("item/template_skull"));
        
        withExistingParent("small_skizzik_head", mcLoc("item/template_skull"));
        withExistingParent("small_skizzik_head_with_gems", mcLoc("item/template_skull"));
        withExistingParent("skizzik_head", mcLoc("item/template_skull"));
        withExistingParent("skizzik_head_with_gems", mcLoc("item/template_skull"));

        withExistingParent("raw_skizzik_flesh_block", modLoc("block/raw_skizzik_flesh_block"));
        withExistingParent("friendly_skizzik_flesh_block", modLoc("block/friendly_skizzik_flesh_block"));
        withExistingParent("skizzik_flesh_block", modLoc("block/skizzik_flesh_block"));

        withExistingParent("corrupted_block", modLoc("block/corrupted_block"));

        withExistingParent("friendly_skizzie_spawn_egg", mcLoc("item/template_spawn_egg"));
        withExistingParent("friendly_witch_skizzie_spawn_egg", mcLoc("item/template_spawn_egg"));

        withExistingParent("friendly_skizzo_spawn_egg", mcLoc("item/template_spawn_egg"));
        
        withExistingParent("skizzie_spawn_egg", mcLoc("item/template_spawn_egg"));
        withExistingParent("kaboom_skizzie_spawn_egg", mcLoc("item/template_spawn_egg"));
        withExistingParent("minigun_skizzie_spawn_egg", mcLoc("item/template_spawn_egg"));
        withExistingParent("corrupted_skizzie_spawn_egg", mcLoc("item/template_spawn_egg"));
        withExistingParent("witch_skizzie_spawn_egg", mcLoc("item/template_spawn_egg"));

        withExistingParent("skizzo_spawn_egg", mcLoc("item/template_spawn_egg"));

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
        ModelFile handheld = getExistingFile(mcLoc("item/handheld"));
        ModelFile skizzikBottomRib = getExistingFile(modLoc("custom/skizzik_bottom_rib"));
        ModelFile skizzikRib = getExistingFile(modLoc("custom/skizzik_rib"));
        ModelFile skizzikBigRib = getExistingFile(modLoc("custom/skizzik_big_rib"));

        defaultBuilder(generated, "skizzik_bone");
        defaultBuilder(generated, "friendly_skizzik_flesh");
        defaultBuilder(generated, "raw_skizzik_flesh");
        defaultBuilder(generated, "skizzik_flesh");

        defaultBuilder(skizzikBottomRib, "friendly_skizzik_bottom_rib");
        defaultBuilder(skizzikRib, "friendly_skizzik_rib");
        defaultBuilder(skizzikBigRib, "friendly_skizzik_big_rib");
        defaultBuilder(skizzikBottomRib, "skizzik_bottom_rib");
        defaultBuilder(skizzikRib, "skizzik_rib");
        defaultBuilder(skizzikBigRib, "skizzik_big_rib");
        
        defaultBuilder(generated, "skizzik_seal");
        defaultBuilder(generated, "spookzik_seal");
        
        defaultBuilder(generated, "music_disc_skizzik");
        defaultBuilder(generated, "music_disc_spookzik");

        defaultBuilder(generated, "skizzik_flesh_cap");
        defaultBuilder(generated, "skizzik_flesh_tunic");
        defaultBuilder(generated, "skizzik_flesh_pants");
        defaultBuilder(generated, "skizzik_flesh_boots");

        defaultBuilder(generated, "platinum_nugget");
        defaultBuilder(generated, "platinum_ingot");

        defaultBuilder(handheld, "rainbow_sword");

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
