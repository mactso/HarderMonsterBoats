package com.mactso.hardermonsterboats.modloader.main;

import com.mactso.hardermonsterboats.common.util.MyUtility;
import com.mactso.hardermonsterboats.modloader.config.MyConfig;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod("hardermonsterboats")
public class Main {

	    public static final String MODID = "hardermonsterboats"; 
	    
	    public Main(IEventBus modEventBus, ModContainer modContainer)
	    {
	        modEventBus.register(this);
			modContainer.registerConfig(ModConfig.Type.COMMON, MyConfig.COMMON_SPEC);
			MyUtility.debugMsg (0, MODID + ": Registering Mod");
	    }
	    
		@SubscribeEvent 
		public void preInit (final FMLCommonSetupEvent event) {
//			NeoForge.EVENT_BUS.register(new EventHandler());
		}  

}
