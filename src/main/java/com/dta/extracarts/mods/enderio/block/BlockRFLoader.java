package com.dta.extracarts.mods.enderio.block;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.mods.enderio.tileentity.TileEntityRFLoader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

/**
 * Created by Skylar on 5/27/2015.
 */
public class BlockRFLoader extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon[] textures = new IIcon[4];
	private ForgeDirection facing = ForgeDirection.UP;

	public BlockRFLoader() {
		super(Material.iron);
		setHardness(1.0F);
		setCreativeTab(CreativeTabs.tabBlock);
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

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iIconRegister) {
		getTextures()[0] = iIconRegister.registerIcon("enderio:machineSide");
		getTextures()[1] = iIconRegister.registerIcon("enderio:machineTop");
		getTextures()[2] = iIconRegister.registerIcon("enderio:capacitorBankInput");
		getTextures()[3] = iIconRegister.registerIcon("enderio:capacitorBankOutput");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
									EntityPlayer player, int metadata, float fx, float fy, float fz) {
		player.openGui(ExtraCarts.instance, 0, world, x, y, z);
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
		int orientation = BlockPistonBase.determineOrientation(world, x, y, z, entityLivingBase);
		int metadata = world.getBlockMetadata(x, y, z);
		facing = ForgeDirection.getOrientation(orientation);
		getTileEntity(world, x, y, z, metadata).setFacing(facing);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int blockSide) {
		int metadata = world.getBlockMetadata(x, y, z);
		TileEntity tileEntityRFLoader = world.getTileEntity(x, y, z);
		if(tileEntityRFLoader instanceof TileEntityRFLoader) {
			facing = ((TileEntityRFLoader) tileEntityRFLoader).getFacing();
			System.out.println(facing);
		}
		return getIcon(blockSide, metadata);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if(side == facing.ordinal()) {
			return getTextures()[3];
		} else {
			return getTextures()[0];
		}
	}

	public TileEntityRFLoader getTileEntity(World world, int x, int y, int z, int metadata) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityRFLoader) {
			return (TileEntityRFLoader)tileEntity;
		}
		return new TileEntityRFLoader(metadata);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityRFLoader(metadata);
	}

	public IIcon[] getTextures() {
		return textures;
	}

	public void setTextures(IIcon[] textures) {
		this.textures = textures;
	}
}
