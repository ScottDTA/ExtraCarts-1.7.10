package com.dta.extracarts.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * Created by Skylar on 3/27/2015.
 */
abstract public class FakeSubBlock {
	private String blockName;
	private int blockNumber;
	private int metaNumber;

	public FakeSubBlock(String blockName) {
		this.setBlockName(blockName);
	}

	@SideOnly(Side.CLIENT)
	abstract public void registerBlockIcons(IIconRegister iIconRegister);

	@SideOnly(Side.CLIENT)
	abstract public IIcon getIcon(int side, int metadata);

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public int getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}

	public int getMetaNumber() {
		return metaNumber;
	}

	public void setMetaNumber(int metaNumber) {
		this.metaNumber = metaNumber;
	}
}
