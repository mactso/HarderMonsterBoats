package com.mactso.hardermonsterboats.config;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mactso.hardermonsterboats.Main;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MyConfig {

	static {

		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	private static final Logger LOGGER = LogManager.getLogger();
	public static final Common COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;


	public static String[] willMountBoat;
	public static String[] willNotHitBoat;

	public static boolean isWillMountBoat(String classname)
	{	
		for (String mod : willNotHitBoat) {
			if (classname.contains(mod)) {
				return true;
			}
		}
		return false;
	}
	public static boolean isWillNotHitBoat(String classname)
	{	
		for (String mod : willNotHitBoat) {
			if (classname.contains(mod)) {
				return true;
			}
		}
		return false;
	}
	
	
	@SubscribeEvent
	public static void onModConfigEvent(final ModConfigEvent configEvent) {
		if (configEvent.getConfig().getSpec() == MyConfig.COMMON_SPEC) {
			bakeConfig();
		}
	}

	public static void bakeConfig() {
		willMountBoat  = extract(COMMON.willMountBoat.get());
		willNotHitBoat = extract(COMMON.willNotHitBoat.get());
	}

	private static String[] extract(List<? extends String> value)
	{
		return value.toArray(new String[value.size()]);
	}

	
	public static class Common {
		List<String> willMountBoatList = Arrays.asList("minecraft","ironchest");
		List<String> willNotHitBoatList = Arrays.asList("minecraft","ironchest");
		public final ConfigValue<List<? extends String>> willMountBoat;
		public final ConfigValue<List<? extends String>> willNotHitBoat;

		public Common(ForgeConfigSpec.Builder builder) {
			String baseTrans = Main.MODID + ".config.";

			willMountBoat = builder
					.comment("Checked Mods Name List")
					.translation(Main.MODID + ".config." + "willMountBoat")
					.defineList("willMountBoat", willMountBoatList, Common::isString);

			willNotHitBoat = builder
					.comment("Checked Mods Name List")
					.translation(Main.MODID + ".config." + "willNotHitBoat")
					.defineList("willNotHitBoat", willNotHitBoatList, Common::isString);

		}
		public static boolean isString(Object o)
		{
			return (o instanceof String);
		}
	}

}
