package com.dta.extracarts.othermodcode.minechem.utils;

import com.dta.extracarts.utils.LogUtils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.StatCollector;

import java.net.URI;
import java.util.List;

/**
 * Created by Skylar on 4/4/2015.
 */
public class MinechemUtil {

	public static String getLocalString(String key)
	{
		return getLocalString(key, false);
	}

	public static String getLocalString(String key, boolean capitalize)
	{
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			String localString = StatCollector.translateToLocal(key);
			return capitalize ? capitalizeFully(localString.replaceAll("molecule\\.", "")) : localString;
		}
		return key;
	}

	public static String capitalizeFully(String input)
	{
		String[] splitString = input.split(" ");
		String result = "";
		for (int i = 0; i < splitString.length; i++)
		{
			char[] digit = splitString[i].toCharArray();
			if (digit.length<1) continue;
			digit[0] = Character.toUpperCase(digit[0]);
			for (int j = 1; j < digit.length; j++)
			{
				digit[j] = Character.toLowerCase(digit[j]);
			}
			result += new String(digit) + (i < splitString.length - 1 ? " " : "");
		}
		return result;
	}

	public static int getSplitStringHeight(FontRenderer fontRenderer, String string, int width)
	{
		List<?> stringRows = fontRenderer.listFormattedStringToWidth(string, width);
		return stringRows.size() * fontRenderer.FONT_HEIGHT;
	}

	/*
	 * Opens passed in URL, MUST check
	 * FMLClientHandler.instance().getClient(),mc.gameSettings.chatLinksPrompt
	 * before using.
	 */
	public static void openURL(String url)
	{
		try
		{
			Class oclass = Class.forName("java.awt.Desktop");
			Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object) null, new Object[0]);
			oclass.getMethod("browse", new Class[]
					{
							URI.class
					}).invoke(object, new Object[]
					{
							new URI(url)
					});
		} catch (Throwable throwable)
		{
			LogUtils.debug("Couldn\'t open link: " + url);
		}
	}
}
