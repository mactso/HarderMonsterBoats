package com.mactso.hardermonsterboats;

import com.mactso.hardermonsterboats.config.MyConfigs;

import net.fabricmc.api.ModInitializer;


public class Main implements ModInitializer {

    public static final String MOD_ID = "villagersrespawn"; 
    
	@Override
	public void onInitialize() {

		MyConfigs.registerConfigs();


	}
}
