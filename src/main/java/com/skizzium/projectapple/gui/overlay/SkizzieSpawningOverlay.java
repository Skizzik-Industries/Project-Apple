package com.skizzium.projectapple.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.FriendlySkizzik;
import com.skizzium.projectapple.init.PA_ClientHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;

public class SkizzieSpawningOverlay extends GuiComponent implements IIngameOverlay {
    private static final ResourceLocation SKIZZIE_HEAD_LOCATION = new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/friendly_skizzik/skizzie_head.png");
    private static final ResourceLocation WITCH_SKIZZIE_EYES_LOCATION = new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/friendly_skizzik/witch_skizzie_eyes.png");

    private static final ResourceLocation SPOOKZIE_HEAD_LOCATION = new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/holidays/spooktober/friendly_spookzik/spookzie_head.png");
    private static final ResourceLocation WITCH_SPOOKZIE_EYES_LOCATION = new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/holidays/spooktober/friendly_spookzik/witch_spookzie_eyes.png");

    private static final ResourceLocation DARKEN_OVERLAY_LOCATION = new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/friendly_skizzik/skizzie_head_darken_overlay.png");

    private void cooldown(PoseStack matrix, int x, int y, int cooldown) {
        RenderSystem.setShaderTexture(0, DARKEN_OVERLAY_LOCATION);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        GuiComponent.blit(matrix, x + 4, y + 4, this.getBlitOffset(), 0.0F, 0.0F, 16, 16, 16, 16);

        GuiComponent.drawString(matrix, Minecraft.getInstance().font, String.format("%.1f", cooldown / 20.0F), x + 5, y + 9, 0XFFFFFF);
    }
    
    @Override
    public void render(ForgeIngameGui gui, PoseStack matrix, float partialTicks, int width, int height) {
        Minecraft minecraft = PA_ClientHelper.getClient();
        Entity vehicle = minecraft.player.getVehicle();
        if (vehicle instanceof FriendlySkizzik) {
            for (int i = 0; i < 2 ; i++) {
                RenderSystem.enableBlend();
                RenderSystem.setShaderTexture(0, AbstractContainerScreen.INVENTORY_LOCATION);

                int x = 0;
                int y = i * 24 + 75;

                this.blit(matrix, x, y, 141, 166, 24, 24);

                RenderSystem.setShaderTexture(0, ProjectApple.holiday == 1 ? SPOOKZIE_HEAD_LOCATION : SKIZZIE_HEAD_LOCATION);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                GuiComponent.blit(matrix, x + 4, y + 4, this.getBlitOffset(), 0.0F, 0.0F, 16, 16, 16, 16);

                if (i == 1) {
                    RenderSystem.setShaderTexture(0, ProjectApple.holiday == 1 ? WITCH_SPOOKZIE_EYES_LOCATION : WITCH_SKIZZIE_EYES_LOCATION);
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    GuiComponent.blit(matrix, x + 4, y + 4, this.getBlitOffset(), 0.0F, 0.0F, 16, 16, 16, 16);
                }
                
//                if (i == 0 && ((FriendlySkizzik) vehicle).getSkizzieCooldown(FriendlySkizzik.Skizzies.values()[i]) > 0.0F) {
//                    this.cooldown(matrix, x, y, ((FriendlySkizzik) vehicle).getSkizzieCooldown(FriendlySkizzik.Skizzies.values()[i]));
//                }
                
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}
