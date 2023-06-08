package com.mactso.hardermonsterboats.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;


public class Utility {
	public final static int FOUR_SECONDS = 80;
	public final static int TWO_SECONDS = 40;
	public final static float Pct00 = 0.00f;
	public final static float Pct02 = 0.02f;
	public final static float Pct05 = 0.05f;
	public final static float Pct09 = 0.09f;
	public final static float Pct16 = 0.16f;
	public final static float Pct25 = 0.25f;
	public final static float Pct50 = 0.50f;
	public final static float Pct75 = 0.75f;
	public final static float Pct84 = 0.84f;
	public final static float Pct89 = 0.89f;
	public final static float Pct91 = 0.91f;
	public final static float Pct95 = 0.95f;
	public final static float Pct99 = 0.99f;
	public final static float Pct100 = 1.0f;

	private static final Logger LOGGER = LogManager.getLogger();

	public static void debugMsg(int level, String dMsg) {

//		if (MyConfig.getDebugLevel() > level - 1) {
			LOGGER.info("L" + level + ":" + dMsg);
//		}

	}

	public static void debugMsg(int level, BlockPos pos, String dMsg) {

//		if (MyConfig.getDebugLevel() > level - 1) {
			LOGGER.info("L" + level + " (" + pos.getX() + "," + pos.getY() + "," + pos.getZ() + "): " + dMsg);
//		}

	}

	public static void debugMsg(int level, LivingEntity le, String dMsg) {

//		if (MyConfig.getDebugLevel() > level - 1) {
			LOGGER.info("L" + level + " (" 
					+ le.getPosition().getX() + "," 
					+ le.getPosition().getY() + ","
					+ le.getPosition().getZ() + "): " + dMsg);
//		}

	}


}
