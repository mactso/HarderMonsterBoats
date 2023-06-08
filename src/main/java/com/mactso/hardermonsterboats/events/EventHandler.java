package com.mactso.hardermonsterboats.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mactso.hardermonsterboats.Main;
import com.mactso.hardermonsterboats.config.MyConfig;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(bus = Bus.FORGE, modid = Main.MODID)
public class EventHandler {
	private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onTarget(LivingDamageEvent event)
    {
    	
        LivingEntity e = event.getEntityLiving();
        if (!(e instanceof ServerPlayerEntity)) {
            if (e.getRidingEntity() instanceof BoatEntity) {
    			String meRN = e.getType().getRegistryName().toString();
    			if (!MyConfig.isWillMonsterNotLeaveBoat(meRN)) {
    	        	e.stopRiding();
    			}

            }
        }
        
    }
    
    
	
	@SubscribeEvent(priority = EventPriority.LOW)
	public void onMountEvent(EntityMountEvent event) {
		
		if (event.getEntityBeingMounted() instanceof BoatEntity) {
			BoatEntity boat = (BoatEntity) event.getEntityBeingMounted();
			if (event.getEntity() instanceof MonsterEntity) {
				MobEntity me = (MobEntity) event.getEntity();
				String meRN = me.getType().getRegistryName().toString();

				if (!MyConfig.isWillMonsterNotHitBoat(meRN)) {
					boat.attackEntityFrom(DamageSource.GENERIC, 6.0f);
				}

				if (MyConfig.isWillMonsterMountBoat(meRN)) {
					return;
				} else {
					if (event.isCancelable()) {
						event.setCanceled(true);
						return;
					}
				}
			}
		}
	}
}
