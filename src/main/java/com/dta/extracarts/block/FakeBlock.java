package com.dta.extracarts.block;

/**
 * Created by Skylar on 3/27/2015.
 */
public class FakeBlock {
	private String blockName;
	private String[] icons;
	private int blockNumber;
	private int metaNumber;

	public FakeBlock(String blockName, String[] icons) {
		this.setBlockName(blockName);
		this.setIcons(icons);
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String[] getIcons() {
		return icons;
	}

	public void setIcons(String[] icons) {
		this.icons = icons;
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
