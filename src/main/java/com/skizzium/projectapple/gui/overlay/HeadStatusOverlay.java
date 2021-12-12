package com.skizzium.projectapple.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzik;
import com.skizzium.projectapple.init.events.PA_ModClientEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;

public class HeadStatusOverlay extends GuiComponent implements IIngameOverlay {
    private static final ResourceLocation HEAD_LOCATION = new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/friendly_skizzik/small_head.png");
    private static final ResourceLocation RIDDEN_LOCATION = new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/friendly_skizzik/small_head_ridden.png");
    private static final ResourceLocation DETACHED_LOCATION = new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/friendly_skizzik/small_head_detached.png");
    private static final ResourceLocation ELIMINATED_LOCATION = new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/friendly_skizzik/small_head_eliminated.png");

    private static final ResourceLocation SPOOKTOBER_HEAD_LOCATION = new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/holidays/spooktober/friendly_spookzik/small_head.png");

    @Override
    public void render(ForgeIngameGui gui, PoseStack matrix, float partialTicks, int width, int height) {
        Minecraft minecraft = PA_ModClientEvents.getClient();
        Entity vehicle = minecraft.player.getVehicle();
        if (vehicle instanceof FriendlySkizzik) {
            for (int i = 0; i < 4 ; i++) {
                RenderSystem.enableBlend();
                RenderSystem.setShaderTexture(0, AbstractContainerScreen.INVENTORY_LOCATION);

                int x = i * 24;
                int y = 1;

                this.blit(matrix, x, y, 141, 166, 24, 24);

                RenderSystem.setShaderTexture(0, ProjectApple.holiday == 1 ? SPOOKTOBER_HEAD_LOCATION : HEAD_LOCATION);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                GuiComponent.blit(matrix, x + 4, y + 4, this.getBlitOffset(), 0.0F, 0.0F, 16, 16, 16, 16);

                if (!((FriendlySkizzik) vehicle).getAddedHeads().contains(FriendlySkizzik.Heads.values()[i])) {
                    RenderSystem.setShaderTexture(0, ELIMINATED_LOCATION);
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    GuiComponent.blit(matrix, x + 4, y + 4, this.getBlitOffset(), 0.0F, 0.0F, 16, 16, 16, 16);
                }
                else {
                    if (((FriendlySkizzik) vehicle).getDetachedHeads().contains(FriendlySkizzik.Heads.values()[i])) {
                        RenderSystem.setShaderTexture(0, DETACHED_LOCATION);
                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                        GuiComponent.blit(matrix, x + 4, y + 4, this.getBlitOffset(), 0.0F, 0.0F, 16, 16, 16, 16);
                    }
                }
                
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}
