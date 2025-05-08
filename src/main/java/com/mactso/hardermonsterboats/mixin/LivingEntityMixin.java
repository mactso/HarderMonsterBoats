package com.mactso.hardermonsterboats.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mactso.hardermonsterboats.events.EventHandler;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

@Mixin(LivingEntity.class)
abstract class LivingEntityMixin {

	@Inject(method = "actuallyHurt", at = @At("HEAD"))
	private void onEntityActuallyHurt(ServerLevel serverLevel, DamageSource source, float amount, CallbackInfo info) {
		EventHandler.onEntityActuallyHurt((LivingEntity) (Object) this);
	}
}
