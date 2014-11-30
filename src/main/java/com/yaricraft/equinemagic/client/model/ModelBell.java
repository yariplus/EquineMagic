package com.yaricraft.equinemagic.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * Created by Yari on 11/15/2014.
 */
public class ModelBell extends ModelBase
{
    ModelRenderer body;

    public ModelBell()
    {
        body = new ModelRenderer(this, 18, 4);
        body.addBox(-6F, -10F, -7F, 12, 18, 10, 0.0F);
        body.setRotationPoint(0.0F, 5F, 2.0F);
        body.rotateAngleX = -0.7854F;
    }
}
