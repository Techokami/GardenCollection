package com.jaquadro.minecraft.gardencontainers.core;

import com.jaquadro.minecraft.gardencontainers.GardenContainers;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModRecipes
{
    public void init () {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.largePot, 3, 1), "x x", "x x", "xxx",
            'x', Blocks.clay);
        GameRegistry.addRecipe(new ItemStack(ModBlocks.largePot, 3), "x x", "x x", "xxx",
            'x', Blocks.hardened_clay);

        for (int i = 0; i < 16; i++) {
            GameRegistry.addRecipe(new ItemStack(ModBlocks.largePotColored, 3, i), "x x", "x x", "xxx",
                'x', new ItemStack(Blocks.stained_hardened_clay, 1, 15 - i));

            GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.largePotColored, 1, i),
                ModBlocks.largePot, new ItemStack(Items.dye, 1, i));
        }

        GameRegistry.addRecipe(new ItemStack(ModBlocks.potteryTable), "x", "y",
            'x', Items.clay_ball, 'y', Blocks.crafting_table);

        GameRegistry.addSmelting(new ItemStack(ModBlocks.largePot, 1, 1), new ItemStack(ModBlocks.largePot, 1, 0), 0);

        for (int i = 1; i < 256; i++) {
            if (GardenContainers.config.hasPattern(i))
                GameRegistry.addSmelting(new ItemStack(ModBlocks.largePot, 1, 1 | (i << 8)), new ItemStack(ModBlocks.largePot, 1, (i << 8)), 0);
        }

        GameRegistry.addRecipe(new ItemStack(ModBlocks.decorativePot, 3), "x x", "xxx", " x ",
            'x', Blocks.quartz_block);
    }
}
