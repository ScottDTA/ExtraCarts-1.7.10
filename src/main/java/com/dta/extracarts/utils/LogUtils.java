package com.dta.extracarts.utils;

import com.dta.extracarts.ModInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 * Created by Skylar on 10/22/2014.
 */
public class LogUtils {
	public static void log(Level level, String msg) {
		LogManager.getLogger(ModInfo.NAME).log(level, msg);
	}
}
