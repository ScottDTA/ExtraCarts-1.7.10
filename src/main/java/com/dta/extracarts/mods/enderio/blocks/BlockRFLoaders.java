package com.dta.extracarts.mods.enderio.blocks;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.mods.enderio.client.ContainerRFLoaders;
import com.dta.extracarts.mods.enderio.client.GuiRFLoaders;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import crazypants.enderio.ModObject;
import crazypants.enderio.machine.AbstractMachineBlock;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Skylar on 11/9/2014.
 */
public class BlockRFLoaders extends AbstractMachineBlock<TileEntityRFLoaders> implements OpenableGUI {

	public BlockRFLoaders() {
		super(ModObject.blockBuffer, TileEntityRFLoaders.class, Material.iron);
		setHardness(1.0F);
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	protected void init() {

	}

	@Override
	protected int getGuiId() {
		return 0;
	}

	@Override
	protected String getMachineFrontIconKey(boolean active) {
		return "enderio:capacitorBankInput";
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
	public boolean onBlockActivated(World world, int x, int y, int z,
									EntityPlayer player, int metadata, float what, float these, float are) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		player.openGui(ExtraCarts.instance, 0, world, x, y, z);
		return true;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		if(te instanceof TileEntityRFLoaders) {
			return new ContainerRFLoaders(player.inventory, (TileEntityRFLoaders) te);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		if(te instanceof TileEntityRFLoaders) {
			return new GuiRFLoaders(player.inventory, (TileEntityRFLoaders) te);
		}
		return null;
	}
}
