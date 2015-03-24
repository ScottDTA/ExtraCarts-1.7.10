package com.dta.extracarts.mods.mfr.block;

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
 * Created by Skylar on 3/23/2015.
 */
public class FakeDSUBlock extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon[] textures = new IIcon[3];

	public FakeDSUBlock() {
		super(Material.iron);
		setHardness(1.0f);
		setBlockName("fakeDSUBlock");
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iIconRegister) {
		getTextures()[0] = iIconRegister.registerIcon("minefactoryreloaded:tile.mfr.machine.0.bottom.png");
		getTextures()[1] = iIconRegister.registerIcon("minefactoryreloaded:tile.mfr.machine.deepstorageunit.active.top.png");
		getTextures()[2] = iIconRegister.registerIcon("minefactoryreloaded:tile.mfr.machine.deepstorageunit.active.side.png");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		switch (side) {
			case 0:
				return getTextures()[0];
			case 1:
				return getTextures()[1];
			default:
				return getTextures()[2];
		}
	}

	public String getUnlocalizedName() {
		return "fakeDSUBlock";
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < 1; i++)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	public IIcon[] getTextures() {
		return textures;
	}

	public void setTextures(IIcon[] textures) {
		this.textures = textures;
	}
}
