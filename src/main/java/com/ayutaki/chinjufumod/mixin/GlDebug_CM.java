package com.ayutaki.chinjufumod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.mojang.blaze3d.platform.GlDebug;

@Mixin(GlDebug.class)
public class GlDebug_CM {
	//Stop halfway through. [mojang/GlDebug]: OpenGL debug message: id=1282, source=API, type=ERROR
	private static int loggedNum = 0;
	
	@Inject(method = "printDebugLog", at = @At(value = "HEAD"), cancellable = true)
	private static void halfwayThrough(int source, int type, int id, int severity, int messageLength, long message, long userParam, CallbackInfo callback) {
		if (loggedNum <= 9) { 
			if (loggedNum == 0) { ChinjufuMod.LOGGER.info("Stop the log by GlDebug after 10 times."); }
			loggedNum++; }
		
		else { callback.cancel(); }
	}
}
