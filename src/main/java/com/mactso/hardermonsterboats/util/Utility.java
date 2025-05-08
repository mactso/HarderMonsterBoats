package com.mactso.hardermonsterboats.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

public class Utility {
	public final static int FOUR_SECONDS = 80;
	public final static int TWO_SECONDS = 40;


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
					+ le.blockPosition().getX() + "," 
					+ le.blockPosition().getY() + ","
					+ le.blockPosition().getZ() + "): " + dMsg);
//		}

	}

	public static void sendBoldChat(ServerPlayer p, String chatMessage, ChatFormatting textColor) {

		MutableComponent component = Component.literal(chatMessage);
		component.setStyle(component.getStyle().withBold(true));
		component.setStyle(component.getStyle().withColor(textColor));
		p.sendSystemMessage(component);

	}

	public static void sendChat(ServerPlayer p, String chatMessage, ChatFormatting textColor) {

		MutableComponent component = Component.literal(chatMessage);
		component.setStyle(component.getStyle().withColor(textColor));
		p.sendSystemMessage(component);

	}


}
