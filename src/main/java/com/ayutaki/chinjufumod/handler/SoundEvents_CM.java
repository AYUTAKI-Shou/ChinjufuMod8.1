package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class SoundEvents_CM {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, ChinjufuMod.MOD_ID);

	public static final DeferredHolder<SoundEvent, SoundEvent> AM_CARTRIDGE = register("am_cartridge");
	public static final DeferredHolder<SoundEvent, SoundEvent> AM_CARTRIDGE_K = register("am_cartridge_k");
	public static final DeferredHolder<SoundEvent, SoundEvent> AM_FIRE = register("am_fire");
	public static final DeferredHolder<SoundEvent, SoundEvent> AM_IMPACT = register("am_impact");
	public static final DeferredHolder<SoundEvent, SoundEvent> AM_HIT = register("am_hit");
	
	public static final DeferredHolder<SoundEvent, SoundEvent> KK_ATACK = register("kk_atack");
	public static final DeferredHolder<SoundEvent, SoundEvent> KK_ATACK2 = register("kk_atack2");
	public static final DeferredHolder<SoundEvent, SoundEvent> KK_START = register("kk_starting");
	public static final DeferredHolder<SoundEvent, SoundEvent> KK_BREAK = register("kk_break");
	public static final DeferredHolder<SoundEvent, SoundEvent> KK_IMPACT = register("kk_impact");
	public static final DeferredHolder<SoundEvent, SoundEvent> KK_PROPELLER = register("kk_propeller");
	public static final DeferredHolder<SoundEvent, SoundEvent> KK_PROPELLER12 = register("kk_propeller_12");
	public static final DeferredHolder<SoundEvent, SoundEvent> KK_STOP = register("kk_stop");
	public static final DeferredHolder<SoundEvent, SoundEvent> GYORAI = register("gyorai");
	public static final DeferredHolder<SoundEvent, SoundEvent> SET_GUN = register("set_gun");
	public static final DeferredHolder<SoundEvent, SoundEvent> EMPTY_AMMO = register("empty_ammo");
	
	public static final DeferredHolder<SoundEvent, SoundEvent> WATER_WAKE = register("wake_water");
	public static final DeferredHolder<SoundEvent, SoundEvent> WATER_SPLASH = register("water_splash");

	public static final DeferredHolder<SoundEvent, SoundEvent> JUU = register("juu");
	public static final DeferredHolder<SoundEvent, SoundEvent> GUTSUGUTSU = register("gutsugutsu");
	public static final DeferredHolder<SoundEvent, SoundEvent> TEA = register("tea");
	public static final DeferredHolder<SoundEvent, SoundEvent> SAKE = register("sake");
	public static final DeferredHolder<SoundEvent, SoundEvent> SHOUYU = register("shouyu");
	public static final DeferredHolder<SoundEvent, SoundEvent> PAKU = register("paku");
	public static final DeferredHolder<SoundEvent, SoundEvent> GOKU = register("goku");
	public static final DeferredHolder<SoundEvent, SoundEvent> WATER_START = register("water_start");
	public static final DeferredHolder<SoundEvent, SoundEvent> WATER_STOP = register("water_stop");
	public static final DeferredHolder<SoundEvent, SoundEvent> KITCHEN_CUT = register("kitchen_cut");

	public static final DeferredHolder<SoundEvent, SoundEvent> TANSU_OPEN = register("tansu_open");
	public static final DeferredHolder<SoundEvent, SoundEvent> TANSU_CLOSE = register("tansu_close");

	public static final DeferredHolder<SoundEvent, SoundEvent> WINDOW_OPEN = register("window_open");
	public static final DeferredHolder<SoundEvent, SoundEvent> WINDOW_CLOSE = register("window_close");
	public static final DeferredHolder<SoundEvent, SoundEvent> WINDOW_UD = register("window_updown");
	public static final DeferredHolder<SoundEvent, SoundEvent> FUSUMA = register("fusuma");
	public static final DeferredHolder<SoundEvent, SoundEvent> FUSUMA_SHORT = register("fusuma_short");
	public static final DeferredHolder<SoundEvent, SoundEvent> AMADO_CANCEL = register("amado_cancel");

	public static final DeferredHolder<SoundEvent, SoundEvent> HIKIDO = register("hikido");
	public static final DeferredHolder<SoundEvent, SoundEvent> HIKIDO_SHORT = register("hikido_short");
	public static final DeferredHolder<SoundEvent, SoundEvent> KINUZURE = register("kinuzure");
	public static final DeferredHolder<SoundEvent, SoundEvent> SIT_CHAIR = register("sit_chair");

	public static final DeferredHolder<SoundEvent, SoundEvent> SHISHIODOSHI = register("shishiodoshi");

	public static final DeferredHolder<SoundEvent, SoundEvent> GATE_WOOD = register("gate_wood");
	public static final DeferredHolder<SoundEvent, SoundEvent> GATE_IRON_OPEN = register("gate_iron_open");
	public static final DeferredHolder<SoundEvent, SoundEvent> GATE_IRON_CLOSE = register("gate_iron_close");
	public static final DeferredHolder<SoundEvent, SoundEvent> PAINT = register("paint");

	public static final DeferredHolder<SoundEvent, SoundEvent> WADAIKO_TOP = register("wadaiko_top");
	public static final DeferredHolder<SoundEvent, SoundEvent> WADAIKO_SIDE = register("wadaiko_side");
	public static final DeferredHolder<SoundEvent, SoundEvent> CURTAIN = register("curtain");
	
	public static final DeferredHolder<SoundEvent, SoundEvent> WRITE_REPORT = register("write_report");
	public static final DeferredHolder<SoundEvent, SoundEvent> WRITE_CHALK = register("write_chalk");
	public static final DeferredHolder<SoundEvent, SoundEvent> USE_ERASER = register("use_eraser");
	public static final DeferredHolder<SoundEvent, SoundEvent> CLEAN_ERASER = register("clean_eraser");
	public static final DeferredHolder<SoundEvent, SoundEvent> OPEN = register("open");
	public static final DeferredHolder<SoundEvent, SoundEvent> OPEN_OVEN = register("open_oven");
	public static final DeferredHolder<SoundEvent, SoundEvent> REIZOU_OPEN = register("reizou_open");
	public static final DeferredHolder<SoundEvent, SoundEvent> REIZOU_CLOSE = register("reizou_close");
	public static final DeferredHolder<SoundEvent, SoundEvent> TOUCH_BLOCK = register("touch_block");
	public static final DeferredHolder<SoundEvent, SoundEvent> THROW = register("throw");
	public static final DeferredHolder<SoundEvent, SoundEvent> SWING = register("swing");
	public static final DeferredHolder<SoundEvent, SoundEvent> ERROR = register("error");
	public static final DeferredHolder<SoundEvent, SoundEvent> NOISE = register("basalt_deltas_click2");
	public static final DeferredHolder<SoundEvent, SoundEvent> SONAR = register("sonar");
	public static final DeferredHolder<SoundEvent, SoundEvent> PAGE_TURN = register("page_turn");
	
	///* Register *///
	private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(ChinjufuMod.id(name)));
	}
}
