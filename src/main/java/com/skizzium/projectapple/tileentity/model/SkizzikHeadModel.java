package com.skizzium.projectapple.tileentity.model;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SkizzikHeadModel extends GenericHeadModel {
    protected final ModelRenderer head;

    public SkizzikHeadModel(int i1, int i2, int texWidth, int texHeight) {
        this.texWidth = texWidth;
        this.texHeight = texHeight;
        this.head = new ModelRenderer(this, i1, i2);
        this.head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F);
        this.head.setPos(0.0F, 0.0F, 0.0F);
    }
}
