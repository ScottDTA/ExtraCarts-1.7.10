package com.dta.extracarts.mods.mfr.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * Created by Skylar on 3/23/2015.
 */
public class FakeDSUBlock extends Block {

	private IIcon[] textures = new IIcon[3];

	public FakeDSUBlock() {
		super(Material.iron);
		setHardness(1.0f);
		setBlockName("fakeDSUBlock");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iIconRegister) {
		getTextures()[0] = iIconRegister.registerIcon("minefactoryreloaded:machines/tile.mfr.machine.0.bottom");
		getTextures()[1] = iIconRegister.registerIcon("minefactoryreloaded:machines/tile.mfr.machine.deepstorageunit.active.top");
		getTextures()[2] = iIconRegister.registerIcon("minefactoryreloaded:machines/tile.mfr.machine.deepstorageunit.active.side");
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

	public IIcon[] getTextures() {
		return textures;
	}

	public void setTextures(IIcon[] textures) {
		this.textures = textures;
	}
}
