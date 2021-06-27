package com.skizzium.projectapple.block;

import net.minecraft.block.SkullBlock;

public class PA_SkullBlock extends SkullBlock {
    public PA_SkullBlock(ISkullType skull, Properties properties) {
        super(skull, properties);
    }

    public static enum CustomTypes implements SkullBlock.ISkullType {
        SKIZZIK;
    }
}
