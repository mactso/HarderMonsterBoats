package com.mactso.hardermonsterboats;

import com.mactso.hardermonsterboats.config.MyConfig;
import com.mactso.hardermonsterboats.events.EventHandler;
import com.mactso.hardermonsterboats.util.Utility;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("hardermonsterboats")
public class Main {

	    public static final String MODID = "hardermonsterboats"; 
	    
	    public Main(FMLJavaModLoadingContext context)
	    {
			context.getModEventBus().register(this);
			context.registerConfig(ModConfig.Type.COMMON, MyConfig.COMMON_SPEC);
			Utility.debugMsg (0, MODID + ": Registering Mod");
	    }
	    
		@SubscribeEvent 
		public void preInit (final FMLCommonSetupEvent event) {
			MinecraftForge.EVENT_BUS.register(new EventHandler());
		}  

}
