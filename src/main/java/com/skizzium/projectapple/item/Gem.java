package com.skizzium.projectapple.item;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.SkizzikGem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class Gem extends Item {
    private final GemType type;
    
    public Gem(GemType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    public GemType getType() {
        return type;
    }

    public enum GemType {
        BLACK("black"),
        BLUE("blue"),
        GREEN("green"),
        ORANGE("orange"),
        PINK("pink"),
        YELLOW("yellow");
        
        private final String name;
        private final ResourceLocation location;

        GemType(String name) {
            this.name = name;
            this.location = new ResourceLocation(ProjectApple.MOD_ID, String.format("textures/entity/friendly_skizzik/gems/%s_gem.png", name));
        }

        public String getName() {
            return this.name;
        }
        
        public ResourceLocation getLocation() {
            return this.location;
        }

        public static Gem.GemType byName(String name) {
            Gem.GemType[] values = values();
            int length = values.length;

            for (GemType gemTypes : values) {
                if (gemTypes.name.equals(name)) {
                    return gemTypes;
                }
            }

            return BLACK;
        }
    }
}
