package es.kamikaze.components.util

import android.content.Context
import android.graphics.Typeface

class FontUtils {
    object FontConstantHolder {
        const val REGULAR_KZ_FONT = "font/kamikazeRegularFont.ttf"
        const val BOLD_KZ_FONT = "font/kamikazeBoldFont.ttf"
        const val ITALIC_KZ_FONT = "font/kamikazeItalicFont.ttf"
        const val ITALIC_BOLD_KZ_FONT = "font/kamikazeBoldItalicFont.ttf"
    }

    companion object {
        fun getTypeFont(fontValue: Int, context: Context): Typeface = Typeface.createFromAsset(
            context.assets, when (fontValue) {
                1 -> FontConstantHolder.BOLD_KZ_FONT
                2 -> FontConstantHolder.ITALIC_KZ_FONT
                3 -> FontConstantHolder.ITALIC_BOLD_KZ_FONT
                else -> FontConstantHolder.REGULAR_KZ_FONT
            }
        )
    }
}