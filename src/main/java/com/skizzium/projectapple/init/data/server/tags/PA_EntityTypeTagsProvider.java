package com.skizzium.projectapple.init.data.server.tags;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.init.entity.PA_Entities;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PA_EntityTypeTagsProvider extends EntityTypeTagsProvider {
    public PA_EntityTypeTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, ProjectApple.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(PA_Tags.EntityTypes.SKIZZIES).add(PA_Entities.SKIZZIE.get(), PA_Entities.KABOOM_SKIZZIE.get(), PA_Entities.WITCH_SKIZZIE.get(), PA_Entities.CORRUPTED_SKIZZIE.get());
        tag(PA_Tags.EntityTypes.FRIENDLY_SKIZZIES).add(PA_Entities.FRIENDLY_SKIZZIE.get(), PA_Entities.FRIENDLY_WITCH_SKIZZIE.get());
    }
}