package com.skizzium.projectapple.gui;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.FriendlySkizzik;
import com.skizzium.projectapple.entity.boss.skizzik.FriendlySkizzikHeadPart;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.ArrayList;
import java.util.List;

public class FriendlySkizzikScreen extends AbstractContainerScreen<FriendlySkizzikMenu> {
    private static final ResourceLocation SKIZZIK_INVENTORY_LOCATION = new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/friendly_skizzik/friendly_skizzik_container.png");
    private static final ResourceLocation HEAD_INVENTORY_LOCATION = new ResourceLocation(ProjectApple.MOD_ID, "textures/gui/friendly_skizzik/friendly_skizzik_head_container.png");

    private final FriendlySkizzik skizzik;
    private float xMouse;
    private float yMouse;
    
    public FriendlySkizzikHeadTab currentTab;
    public List<FriendlySkizzikHeadTab> tabs = new ArrayList<>();

    public FriendlySkizzikScreen(FriendlySkizzikMenu menu, Inventory inventory, FriendlySkizzik skizzik) {
        super(menu, inventory, skizzik.getDisplayName());
        this.skizzik = skizzik;
        this.passEvents = false;
        for (int k = 0; k < 4; k++) {
            tabs.add(new FriendlySkizzikHeadTab(328, 49 + k * 18, this, (FriendlySkizzikHeadPart) skizzik.getParts()[k], skizzik));
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            for(FriendlySkizzikHeadTab tab : this.tabs) {
                if (tab.isMouseOver(mouseX, mouseY) && skizzik.getAddedHeads().contains(tab.head.headType)) {
                    if (tab == currentTab) {
                        this.currentTab = null;    
                    }
                    else {
                        this.currentTab = tab;
                    }
                    break;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    @Override
    protected void renderBg(PoseStack pose, float partialTick, int x, int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, this.currentTab == null ? SKIZZIK_INVENTORY_LOCATION : HEAD_INVENTORY_LOCATION);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(pose, i, j, 0, 0, this.imageWidth, this.imageHeight);

        this.blit(pose, i + 7, j + 35 - 18, 18, this.imageHeight + 54, 18, 18);
        this.blit(pose, i + 7, j + 35, 0, this.imageHeight + 54, 18, 18);

        for (FriendlySkizzikHeadTab tab : this.tabs) {
            tab.render(pose);
        }
        
        if (currentTab == null) {
            renderSkizzikInInventory(i + 87, j + 73, 15, (float) (i + 85) - this.xMouse, (float) (j + 79 - 50) - this.yMouse, this.skizzik);
        }
        else {
            renderHeadInInventory(i + 87, j + 73, 15, (float) (i + 85) - this.xMouse, (float) (j + 79 - 50) - this.yMouse, this.currentTab.head.headType, this.skizzik);
        }
    }

    @Override
    protected void renderLabels(PoseStack pose, int mouseX, int mouseY) {
        this.font.draw(pose, this.playerInventoryTitle, (float)this.inventoryLabelX, (float)this.inventoryLabelY, 4210752);
    }

    @Override
    public void render(PoseStack pose, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(pose);
        this.xMouse = (float)mouseX;
        this.yMouse = (float)mouseY;
        
        super.render(pose, mouseX, mouseY, partialTick);
        this.renderTooltip(pose, mouseX, mouseY);
    }

    public static void renderHeadInInventory(int x, int y, int scale, float mouseX, float mouseY, FriendlySkizzik.Heads head, FriendlySkizzik parent) {
        float f = (float)Math.atan(mouseX / 40.0F);
        float f1 = (float)Math.atan(mouseY / 40.0F);
        PoseStack pose = RenderSystem.getModelViewStack();
        pose.pushPose();
        pose.translate(x, y, 1050.0D);
        pose.scale(1.0F, 1.0F, -1.0F);

        RenderSystem.applyModelViewMatrix();
        PoseStack posestack1 = new PoseStack();
        posestack1.translate(0.0D, 0.0D, 1000.0D);
        posestack1.scale((float)scale, (float)scale, (float)scale);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.mul(quaternion1);
        posestack1.mulPose(quaternion);

        float oldYRot = parent.getHeadYRot(head.ordinal() + 1);
        float oldXRot = parent.getHeadXRot(head.ordinal() + 1);

        parent.setHeadYRot(head, 180.0F + f * 40.0F);
        parent.setHeadXRot(head, -f1 * 20.0F);

        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();
        
        entityrenderdispatcher.overrideCameraOrientation(quaternion1);
        entityrenderdispatcher.setRenderShadow(false);
        
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
        //RenderSystem.runAsFancy(() -> entityrenderdispatcher.render(parent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, posestack1, multibuffersource$buffersource, 15728880));
        
        multibuffersource$buffersource.endBatch();
        entityrenderdispatcher.setRenderShadow(true);

        parent.setHeadYRot(head, oldYRot);
        parent.setHeadXRot(head, oldXRot);

        pose.popPose();
        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();
    }

    public static void renderSkizzikInInventory(int x, int y, int scale, float mouseX, float mouseY, FriendlySkizzik entity) {
        float f = (float)Math.atan(mouseX / 40.0F);
        float f1 = (float)Math.atan(mouseY / 40.0F);
        PoseStack posestack = RenderSystem.getModelViewStack();
        posestack.pushPose();
        posestack.translate(x, y, 1050.0D);
        posestack.scale(1.0F, 1.0F, -1.0F);
        
        RenderSystem.applyModelViewMatrix();
        PoseStack posestack1 = new PoseStack();
        posestack1.translate(0.0D, 0.0D, 1000.0D);
        posestack1.scale((float)scale, (float)scale, (float)scale);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.mul(quaternion1);
        posestack1.mulPose(quaternion);
        
        float oldBodyRot = entity.yBodyRot;
        float oldYRot = entity.getYRot();
        float oldXRot = entity.getXRot();
        float oldHeadRot0 = entity.yHeadRotO;
        float oldHeadRot = entity.yHeadRot;
        
        List<Float> headXRotations = new ArrayList<>();
        List<Float> headYRotations = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            headXRotations.add(entity.getHeadXRot(i));
            headYRotations.add(entity.getHeadYRot(i));
        }
        
        entity.yBodyRot = 180.0F + f * 20.0F;
        entity.setYRot(180.0F + f * 40.0F);
        entity.setXRot(-f1 * 20.0F);
        entity.yHeadRot = entity.getYRot();
        entity.yHeadRotO = entity.getYRot();
        
        for (int i = 0; i < 4; i++) {
            entity.setHeadXRot(FriendlySkizzik.Heads.values()[i], -f1 * 20.0F);
            entity.setHeadYRot(FriendlySkizzik.Heads.values()[i], 180.0F + f * 40.0F);
        }
        
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();
        entityrenderdispatcher.overrideCameraOrientation(quaternion1);
        entityrenderdispatcher.setRenderShadow(false);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> entityrenderdispatcher.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, posestack1, multibuffersource$buffersource, 15728880));
        multibuffersource$buffersource.endBatch();
        entityrenderdispatcher.setRenderShadow(true);
        
        entity.yBodyRot = oldBodyRot;
        entity.setYRot(oldYRot);
        entity.setXRot(oldXRot);
        entity.yHeadRotO = oldHeadRot0;
        entity.yHeadRot = oldHeadRot;

        for (int i = 0; i < 4; i++) {
            entity.setHeadXRot(FriendlySkizzik.Heads.values()[i], headXRotations.get(i));
            entity.setHeadYRot(FriendlySkizzik.Heads.values()[i], headYRotations.get(i));
        }
        
        posestack.popPose();
        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();
    }
}
