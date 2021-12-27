package com.skizzium.projectapple.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.item.Seal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.awt.*;

// Based on SSKirillSS's relics mod.
// GitHub: https://github.com/SSKirillSS/relics

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class TooltipHandler {
    @SubscribeEvent
    public static void tooltipColor(RenderTooltipEvent.Color event) {
        ItemStack stack = event.getItemStack();

        if (!(stack.getItem() instanceof Seal))
            return;

        Color top = ((Seal) stack.getItem()).getPrimaryColor();
        Color bottom = ((Seal) stack.getItem()).getSecondaryColor() == null ? top.darker().darker() : ((Seal) stack.getItem()).getSecondaryColor();

        event.setBorderStart(top.getRGB());
        event.setBorderEnd(bottom.getRGB());
    }

    @SubscribeEvent
    public static void onPostTooltipEvent(RenderTooltipEvent.Pre event) {
        ItemStack stack = event.getItemStack();

        int x = event.getX();
        int y = event.getY();
        int width = event.getScreenWidth();
        int height = event.getScreenHeight();
        PoseStack matrix = event.getPoseStack();

        Minecraft.getInstance().getTextureManager().bindForSetup(new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/tooltip/" + stack.getItem().getRegistryName().getPath() + ".png"));

        int texWidth = GlStateManager._getTexLevelParameter(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);
        int texHeight = GlStateManager._getTexLevelParameter(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);

        if (texHeight == 0 || texWidth == 0)
            return;

        matrix.pushPose();

        RenderSystem.enableBlend();

        matrix.translate(0, 0, 410.0);

        Gui.blit(matrix, x - 8 - 6, y - 8 - 6, 1, 1 % texHeight, 16, 16, texWidth, texHeight);
        Gui.blit(matrix, x + width - 8 + 6, y - 8 - 6, texWidth - 16 - 1, 1 % texHeight, 16, 16, texWidth, texHeight);

        Gui.blit(matrix, x - 8 - 6, y + height - 8 + 6, 1, 1 % texHeight + 16, 16, 16, texWidth, texHeight);
        Gui.blit(matrix, x + width - 8 + 6, y + height - 8 + 6, texWidth - 16 - 1, 1 % texHeight + 16, 16, 16, texWidth, texHeight);

        if (width >= 94) {
            Gui.blit(matrix, x + (width / 2) - 47, y - 16, 16 + 2 * texWidth + 1, 1 % texHeight, 94, 16, texWidth, texHeight);
            Gui.blit(matrix, x + (width / 2) - 47, y + height, 16 + 2 * texWidth + 1, 1 % texHeight + 16, 94, 16, texWidth, texHeight);
        }

        RenderSystem.disableBlend();

        matrix.popPose();
    }
}
