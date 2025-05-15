package com.mactso.hardermonsterboats;

import com.mactso.hardermonsterboats.config.MyConfig;
import com.mactso.hardermonsterboats.events.EventHandler;
import com.mactso.hardermonsterboats.util.Utility;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod("hardermonsterboats")
public class Main {

	    public static final String MODID = "hardermonsterboats"; 
	    
	    public Main(IEventBus modEventBus, ModContainer modContainer)
	    {
	        modEventBus.register(this);
			modContainer.registerConfig(ModConfig.Type.COMMON, MyConfig.COMMON_SPEC);
			Utility.debugMsg (0, MODID + ": Registering Mod");
	    }
	    
		@SubscribeEvent 
		public void preInit (final FMLCommonSetupEvent event) {
//			NeoForge.EVENT_BUS.register(new EventHandler());
		}  

}
