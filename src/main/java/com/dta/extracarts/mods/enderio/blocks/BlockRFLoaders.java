package com.dta.extracarts.mods.enderio.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by Skylar on 11/9/2014.
 */
public class BlockRFLoaders extends Block {

	IIcon blockRFLoader;
	IIcon blockRFUnloader;

	public BlockRFLoaders() {
		super(Material.iron);
		setHardness(1.0F);
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int blockSide, int blockMeta) {
		if(blockMeta == 0) {
			return blockRFLoader;
		}
		return blockRFUnloader;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iIconRegister) {
		blockRFLoader = iIconRegister.registerIcon("enderio:capacitorBankOutput");
		blockRFUnloader  = iIconRegister.registerIcon("enderio:capacitorBankInput");
	}

	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List subItems) {
		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
	}
}
