package com.skizzium.projectapple.item;

import com.skizzium.projectapple.ProjectApple;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.SpawnEggItem;

public class ThemeableSpawnEgg extends SpawnEggItem {
    public ThemeableSpawnEgg(EntityType<? extends Mob> entity, int defaultPrimaryColor, int defaultSecondaryColor, Properties itemProperties) {
        super(entity, defaultPrimaryColor, defaultSecondaryColor, itemProperties);
    }

    @Override
    public String getDescriptionId() {
        return ProjectApple.getThemedDescriptionId(super.getDescriptionId());
    }
}
