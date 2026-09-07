package com.ayutaki.chinjufumod.tileentity.render;

import com.ayutaki.chinjufumod.items.color.Chalk_Black;
import com.ayutaki.chinjufumod.items.color.Chalk_Blue;
import com.ayutaki.chinjufumod.items.color.Chalk_Brown;
import com.ayutaki.chinjufumod.items.color.Chalk_Cyan;
import com.ayutaki.chinjufumod.items.color.Chalk_Gray;
import com.ayutaki.chinjufumod.items.color.Chalk_Green;
import com.ayutaki.chinjufumod.items.color.Chalk_LightBlue;
import com.ayutaki.chinjufumod.items.color.Chalk_LightGray;
import com.ayutaki.chinjufumod.items.color.Chalk_Lime;
import com.ayutaki.chinjufumod.items.color.Chalk_Magenta;
import com.ayutaki.chinjufumod.items.color.Chalk_Orange;
import com.ayutaki.chinjufumod.items.color.Chalk_Pink;
import com.ayutaki.chinjufumod.items.color.Chalk_Purple;
import com.ayutaki.chinjufumod.items.color.Chalk_Red;
import com.ayutaki.chinjufumod.items.color.Chalk_Yellow;

import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;

public class TakeValue_CM {

	public static DyeColor chalkColor(Item item) {
		if (item instanceof Chalk_Orange) { return DyeColor.ORANGE; }
		if (item instanceof Chalk_Magenta) { return DyeColor.MAGENTA; }
		if (item instanceof Chalk_LightBlue) { return DyeColor.LIGHT_BLUE; }
		if (item instanceof Chalk_Yellow) { return DyeColor.YELLOW; }
		if (item instanceof Chalk_Lime) { return DyeColor.LIME; }
		if (item instanceof Chalk_Pink) { return DyeColor.PINK; }
		if (item instanceof Chalk_Gray) { return DyeColor.GRAY; }
		if (item instanceof Chalk_LightGray) { return DyeColor.LIGHT_GRAY; }
		if (item instanceof Chalk_Cyan) { return DyeColor.CYAN; }
		if (item instanceof Chalk_Purple) { return DyeColor.PURPLE; }
		if (item instanceof Chalk_Blue) { return DyeColor.BLUE; }
		if (item instanceof Chalk_Brown) { return DyeColor.BROWN; }
		if (item instanceof Chalk_Green) { return DyeColor.GREEN; }
		if (item instanceof Chalk_Red) { return DyeColor.RED; }
		if (item instanceof Chalk_Black) { return DyeColor.BLACK; }
		else { return DyeColor.WHITE; }
	}
	
	public static int txtColorValue(int meta) {
		if (meta == 1) { return 16756585; }//orange
		if (meta == 2) { return 15428075; }//magenta
		if (meta == 3) { return 11526655; }//lightblue
		if (meta == 4) { return 16777065; }//yellow
		if (meta == 5) { return 11534185; }//lime
		if (meta == 6) { return 16756705; }//pink
		if (meta == 7) { return 10197915; }//gray
		if (meta == 8) { return 13487565; }//lightgray
		if (meta == 9) { return 6941675; }//cyan
		if (meta == 10) { return 11495935; }//purple
		if (meta == 11) { return 6921195; }//blue
		if (meta == 12) { return 11500905; }//brown
		if (meta == 13) { return 6941545; }//green
		if (meta == 14) { return 15427955; }//red
		if (meta == 15) { return 6908265; }//black
		else { return 16777215; } //white
	}
}
