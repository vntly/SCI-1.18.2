package com.vntly.sci.core.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.vntly.sci.SCIMod;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MixingScreen extends AbstractContainerScreen<MixingMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(SCIMod.MODID, "textures/gui/mixing_gui.png");

    public MixingScreen(MixingMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack p_97853_, float p_97854_, int p_97855_, int p_97856_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, this.TEXTURE);
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(p_97853_, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if (this.menu.isLit()) {
            int k = this.menu.getLitProgress();
            //System.out.println('Q');

            this.blit(p_97853_, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.menu.getBurnProgress();
        this.blit(p_97853_, i + 79, j + 34, 176, 14, l + 1, 16);
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}