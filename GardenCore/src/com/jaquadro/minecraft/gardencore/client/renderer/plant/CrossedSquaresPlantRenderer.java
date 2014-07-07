package com.jaquadro.minecraft.gardencore.client.renderer.plant;

import com.jaquadro.minecraft.gardencore.api.IPlantMetaResolver;
import com.jaquadro.minecraft.gardencore.api.IPlantRenderer;
import com.jaquadro.minecraft.gardencore.api.PlantRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CrossedSquaresPlantRenderer implements IPlantRenderer
{
    @Override
    public void render (IBlockAccess world, int x, int y, int z, RenderBlocks renderer, Block block, int meta, int height) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));

        int colorMult = block.colorMultiplier(renderer.blockAccess, x, y, z);
        //if (l == world.getBiomeGenForCoords(x, z).getBiomeGrassColor(x, y, z))
        //    l = ColorizerGrass.getGrassColor(te.getBiomeTemperature(), te.getBiomeHumidity());

        float r = (float)(colorMult >> 16 & 255) / 255.0F;
        float g = (float)(colorMult >> 8 & 255) / 255.0F;
        float b = (float)(colorMult & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float gray = (r * 30.0F + g * 59.0F + b * 11.0F) / 100.0F;
            float ra = (r * 30.0F + g * 70.0F) / 100.0F;
            float ba = (r * 30.0F + b * 70.0F) / 100.0F;
            r = gray;
            g = ra;
            b = ba;
        }

        tessellator.setColorOpaque_F(r, g, b);

        IPlantMetaResolver resolver = PlantRegistry.instance().getPlantMetaResolver(block, meta);
        if (resolver != null)
            meta = resolver.getPlantSectionMeta(block, meta, height);

        IIcon iicon = renderer.getBlockIconFromSideAndMetadata(block, 0, meta);
        renderer.drawCrossedSquares(iicon, x, y, z, 1.0F);
    }
}