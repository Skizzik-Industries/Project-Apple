package com.skizzium.projectapple.item;

import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;

public class Seal extends Item {
    @Nonnull
    private final Color primaryColor;
    @Nullable
    private final Color secondaryColor;

    public Seal(Color primaryColor, Properties properties) {
        this(primaryColor, null, properties);
    }
    
    public Seal(@NotNull Color primaryColor, @Nullable Color secondaryColor, Properties properties) {
        super(properties);
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    @Nonnull
    public Color getPrimaryColor() {
        return this.primaryColor;
    }

    @Nullable
    public Color getSecondaryColor() {
        return this.secondaryColor;
    }
}
