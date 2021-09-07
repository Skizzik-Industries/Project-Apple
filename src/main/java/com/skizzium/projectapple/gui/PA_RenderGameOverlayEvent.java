package com.skizzium.projectapple.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.network.PA_BossEventPacket;
import com.skizzium.projectapple.util.PA_BossEvent;
import com.skizzium.projectapple.util.PA_LerpingBossEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = ProjectApple.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PA_RenderGameOverlayEvent {
    private static final ResourceLocation GUI_BARS_LOCATION = new ResourceLocation(/*ProjectApple.MOD_ID, */"textures/gui/bars.png");
    private static final Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent
    public static void registerBossBarRendering(RenderGameOverlayEvent.Pre event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            Map<UUID, PA_LerpingBossEvent> events = PA_BossEventPacket.getEvents();
            if (!events.isEmpty()) {
                int i = minecraft.getWindow().getGuiScaledWidth();
                int j = 12;

                for (PA_LerpingBossEvent lerpingEvent : events.values()) {
                    int k = i / 2 - 91;
                    if (!event.isCanceled()) {
                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                        RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);
                        drawBar(event.getMatrixStack(), k, j, lerpingEvent);
                        Component component = lerpingEvent.getName();
                        int l = minecraft.font.width(component);
                        int i1 = i / 2 - l / 2;
                        int j1 = j - 9;
                        minecraft.font.drawShadow(event.getMatrixStack(), component, (float) i1, (float) j1, 16777215);
                    }

                    if (j >= minecraft.getWindow().getGuiScaledHeight() / 3) {
                        break;
                    }
                }
            }
        }
    }

    private static void drawBar(PoseStack pose, int windowWidth, int windowHeight, PA_BossEvent bossEvent) {
        GuiComponent.blit(pose, windowWidth, windowHeight, 0, 0, bossEvent.getColor().ordinal() * 5 * 2, 182, 5, 256, 256);
        if (bossEvent.getOverlay() != BossEvent.BossBarOverlay.PROGRESS) {
            GuiComponent.blit(pose, windowWidth, windowHeight, 0, 0, 80 + (bossEvent.getOverlay().ordinal() - 1) * 5 * 2, 182, 5, 256, 256);
        }

        int i = (int)(bossEvent.getProgress() * 183.0F);
        if (i > 0) {
            GuiComponent.blit(pose, windowWidth, windowHeight, 0, 0, bossEvent.getColor().ordinal() * 5 * 2 + 5, i, 5, 256, 256);
            if (bossEvent.getOverlay() != BossEvent.BossBarOverlay.PROGRESS) {
                GuiComponent.blit(pose, windowWidth, windowHeight, 0, 0, 80 + (bossEvent.getOverlay().ordinal() - 1) * 5 * 2 + 5, i, 5, 256, 256);
            }
        }
    }
}
