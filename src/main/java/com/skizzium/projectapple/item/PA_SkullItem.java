package com.skizzium.projectapple.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.function.Consumer;

public class PA_SkullItem extends StandingAndWallBlockItem {
    public PA_SkullItem(Block head, Block wallHead, Properties properties) {
        super(head, wallHead, properties);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(Renderer.INSTANCE);
    }
}

class Renderer implements IItemRenderProperties{
    public static Renderer INSTANCE = new Renderer();

    @Override
    public Font getFont(ItemStack stack) {
        return Minecraft.getInstance().font;
    }

    @Override
    public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
        return new PA_SkullItemISTER(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }
}
