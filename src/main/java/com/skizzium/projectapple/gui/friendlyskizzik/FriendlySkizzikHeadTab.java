package com.skizzium.projectapple.gui.friendlyskizzik;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzik;
import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzikHeadPart;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

public class FriendlySkizzikHeadTab {
    private static final ResourceLocation SKIZZIK_INVENTORY_LOCATION = new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/friendly_skizzik/friendly_skizzik_container.png");
    private static final ResourceLocation HEAD_LOCATION = new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/friendly_skizzik/small_head.png");

    public final int x;
    public final int y;
    public final int height = 18;
    public final int width = 19;
    public final FriendlySkizzikScreen screen;
    public final FriendlySkizzikHeadPart head;
    public final FriendlySkizzik skizzik;
    
    public FriendlySkizzikHeadTab(int x, int y, FriendlySkizzikScreen screen, FriendlySkizzikHeadPart head, FriendlySkizzik skizzik) {
        this.x = x;
        this.y = y;
        this.screen = screen;
        this.head = head;
        this.skizzik = skizzik;
    }

    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX > (double)this.x && mouseX < (double)(this.x + this.width) && mouseY > (double)this.y && mouseY < (double)(this.y + this.height);
    }
    
    public void render(PoseStack pose) {
        if (skizzik.getAddedHeads().contains(head.headType)) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, SKIZZIK_INVENTORY_LOCATION);

            if (this == screen.currentTab) {
                screen.blit(pose, this.x - 3, this.y, 176, screen.tabs.indexOf(this) == 0 ? 19 : 37, 24, 19);
            }
            else {
                screen.blit(pose, this.x, this.y, 176, 0, 18, 19);
            }
            
            RenderSystem.setShaderTexture(0, HEAD_LOCATION);
            GuiComponent.blit(pose, this.x, this.y + 1, 0, 0, 0, 16, 16, 16, 16);
        }
    }
}
