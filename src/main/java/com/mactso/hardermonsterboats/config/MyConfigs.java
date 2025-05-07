package com.mactso.hardermonsterboats.config;
import java.util.HashSet;

import com.mactso.hardermonsterboats.Main;
import com.mojang.datafixers.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyConfigs {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

	public static int debugLevel;
	public static String[] willMonsterMountBoat;
	public static String[] willMonsterNotHitBoat;
	public static String[] willMonsterNotLeaveBoat;	
	
	public static int getDebugLevel() {
		return debugLevel;
	}

	public static void setDebugLevel(int debugLevel) {
		MyConfigs.debugLevel = debugLevel;
	}



	
	public static boolean isWillMonsterMountBoat(String classname)
	{	
		for (String mod : willMonsterMountBoat) {
			if (classname.contains(mod)) {
				return true;
			}
		}
		return false;
	}

	
	
	public static boolean isWillMonsterNotHitBoat(String classname)
	{	
		for (String mod : willMonsterNotHitBoat) {
			if (classname.contains(mod)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isWillMonsterNotLeaveBoat(String classname)
	{	
		for (String mod : willMonsterNotLeaveBoat) {
			if (classname.contains(mod)) {
				return true;
			}
		}
		return false;
	}

	public static HashSet<String> getModStringSet (String[] values) {
		HashSet<String> returnset = new HashSet<>();
		// Collection<ModContainer> loadedMods= FabricLoader.getAllMods();  error static calling non-static.
		HashSet<String> loadedset = new HashSet<>();
		loadedset.add("hardermonsterboats");
		loadedset.add("test");

		for (String s : loadedset) {
			String s2 = s.trim().toLowerCase();
			if (!s2.isEmpty()) {
				if (!returnset.contains(s2)) {
					returnset.add(s2);
				} else {
					LOGGER.warn("spawnbalanceutility includedReportModsSet entry : " +s2 + " is not a valid current loaded forge mod.");
				} 
			}
		}
		return returnset;
	}
	
	public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(Main.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("key.debugLevel", 0), "int");	
        configs.addKeyValuePair(new Pair<>("key.willMonsterMountBoat", "minecraft:zombie_villager,minecraft:vex" ), "String");	
        configs.addKeyValuePair(new Pair<>("key.willMonsterNotHitBoat", "minecraft:zombie_villager,minecraft:zombie,minecraft:creeper,minecraft:skeleton,nasty:skeleton,minecraft:vex"), "String");
        configs.addKeyValuePair(new Pair<>("key.willMonsterNotLeaveBoat", "minecraft:zombie_villager"), "String");
    }

    private static void assignConfigs() {
    	debugLevel = CONFIG.getOrDefault("key.debugLevel", 0);
    	willMonsterMountBoat = extractStrings(CONFIG.getOrDefault("key.willMonsterMountBoat", "minecraft:zombie_villager,minecraft:vex"));	
    	willMonsterNotHitBoat = extractStrings(CONFIG.getOrDefault("key.willMonsterNotHitBoat", "minecraft:zombie_villager,minecraft:zombie,minecraft:creeper,minecraft:skeleton,nasty:skeleton,minecraft:vex"));
    	willMonsterNotLeaveBoat = extractStrings(CONFIG.getOrDefault("key.willMonsterNotLeaveBoat",  "minecraft:zombie_villager"));
        LOGGER.info("All " + configs.getConfigsList().size() + " have been set properly");
    }
    
    private static String[] extractStrings(String input) {
        if (input == null || input.isEmpty()) {
            return new String[0]; // Return an empty array if input is null or empty
        }
        return input.split("\\s*,\\s*"); // Splits by commas, trimming spaces
    }
}
