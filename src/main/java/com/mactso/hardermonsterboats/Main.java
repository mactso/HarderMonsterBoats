package com.mactso.hardermonsterboats;

import com.mactso.hardermonsterboats.config.MyConfig;
import com.mactso.hardermonsterboats.events.EventHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("hardermonsterboats")
public class Main {

	    public static final String MODID = "hardermonsterboats"; 
	    
	    public Main()
	    {
	  		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	  		
	    	System.out.println(MODID + ": Registering Mod.");
 	        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON,MyConfig.COMMON_SPEC );
	    }
	    
		@SubscribeEvent 
		public void preInit (final FMLCommonSetupEvent event) {
			MinecraftForge.EVENT_BUS.register(new EventHandler());
		}  

}
