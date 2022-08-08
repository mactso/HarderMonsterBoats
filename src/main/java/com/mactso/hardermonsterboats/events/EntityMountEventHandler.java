package com.mactso.hardermonsterboats.events;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mactso.hardermonsterboats.Main;
import com.mactso.hardermonsterboats.config.MyConfig;
import com.mactso.hardermonsterboats.util.Utility;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(bus = Bus.FORGE, modid = Main.MODID)
public class EntityMountEventHandler {
	private static final Logger LOGGER = LogManager.getLogger();

	@SubscribeEvent(priority = EventPriority.LOW)
	public void onSpawnEvent(EntityMountEvent event) {

		if (event.getEntityBeingMounted() instanceof Boat boat) {
			if (event.getEntity() instanceof Monster me) {

				String meRN = me.getType().getRegistryName().toString();

				if (!MyConfig.isWillMonsterNotHitBoat(meRN)) {
					boat.hurt(DamageSource.GENERIC, 6.0f);
				}

				if (!MyConfig.isWillMonsterMountBoat(meRN)) {
					if (event.isCancelable()) {
						event.setCanceled(true);
						return;
					}
				}
			}
		}
	}
}
