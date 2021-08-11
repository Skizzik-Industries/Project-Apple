package com.skizzium.projectapple.data.tags;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.PA_Tags;
import com.skizzium.projectapple.init.entity.PA_Entities;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import com.skizzium.projectapple.init.block.PA_Fluids;

public class PA_EntityTypeTagsProvider extends EntityTypeTagsProvider {
    public PA_EntityTypeTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(PA_Tags.EntityTypes.SKIZZIES).add(PA_Entities.SKIZZIE, PA_Entities.KABOOM_SKIZZIE, PA_Entities.WITCH_SKIZZIE, PA_Entities.CORRUPTED_SKIZZIE);
        tag(PA_Tags.EntityTypes.FRIENDLY_SKIZZIES).add(PA_Entities.FRIENDLY_SKIZZIE, PA_Entities.FRIENDLY_WITCH_SKIZZIE);
    }
}