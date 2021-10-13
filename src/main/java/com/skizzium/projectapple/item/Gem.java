package com.skizzium.projectapple.item;

import com.skizzium.projectapple.entity.boss.skizzik.SkizzikGem;
import net.minecraft.world.item.Item;

public class Gem extends Item {
    private final SkizzikGem.GemType type;
    
    public Gem(SkizzikGem.GemType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    public SkizzikGem.GemType getType() {
        return type;
    }
}
