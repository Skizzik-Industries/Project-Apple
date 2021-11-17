package com.skizzium.projectapple.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.skizzium.projectapple.entity.boss.skizzik.FriendlySkizzik;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FriendlySkizzikScreen extends AbstractContainerScreen<FriendlySkizzikMenu> {
    private static final ResourceLocation HORSE_INVENTORY_LOCATION = new ResourceLocation("textures/gui/container/horse.png");
    private final FriendlySkizzik skizzik;
    private float xMouse;
    private float yMouse;

    public FriendlySkizzikScreen(FriendlySkizzikMenu menu, Inventory inventory, FriendlySkizzik skizzik) {
        super(menu, inventory, skizzik.getDisplayName());
        this.skizzik = skizzik;
        this.passEvents = false;
    }

    protected void renderBg(PoseStack pose, float partialTick, int x, int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, HORSE_INVENTORY_LOCATION);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(pose, i, j, 0, 0, this.imageWidth, this.imageHeight);

        this.blit(pose, i + 7, j + 35 - 18, 18, this.imageHeight + 54, 18, 18);
        this.blit(pose, i + 7, j + 35, 0, this.imageHeight + 54, 18, 18);

        InventoryScreen.renderEntityInInventory(i + 51, j + 60, 17, (float)(i + 51) - this.xMouse, (float)(j + 75 - 50) - this.yMouse, this.skizzik);
    }

    public void render(PoseStack pose, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(pose);
        this.xMouse = (float)mouseX;
        this.yMouse = (float)mouseY;
        
        super.render(pose, mouseX, mouseY, partialTick);
        this.renderTooltip(pose, mouseX, mouseY);
    }
}
