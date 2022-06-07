/*
 * Copyright (c) 2022. Operación Kami - Kaze.
 *
 * Licensed under the GNU General Public License v3.0
 *
 * https://www.gnu.org/licenses/gpl-3.0.html
 *
 * Permissions of this strong copyleft license are conditioned on making available complete
 * source code of licensed works and modifications, which include larger works using a licensed
 * work, under the same license. Copyright and license notices must be preserved. Contributors
 * provide an express grant of patent rights.
 */

package es.kamikaze.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import es.kamikaze.components.util.FontUtils.Companion.getTypeFont

open class KzTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private val paint = Paint()
    private var strikeThroughEnabled: Boolean = false

    private fun init(attrs: AttributeSet?) {
        var fontValue = 0
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.kz_style, 0, 0)
        if (typedArray.hasValue(R.styleable.kz_style_kzFont)) {
            fontValue = typedArray.getInt(R.styleable.kz_style_kzFont, 0)
        }
        typedArray.recycle()
        if (typeface != null)
            this.setTypeface(getTypeFont(fontValue, context), typeface.style)
        else
            this.typeface = getTypeFont(fontValue, context)
    }

    /**
     * Allows to set a coloured strikethrough line in the [KzTextView] instance.
     * Because of Android limitations, a strikethrough character is part of the font used to display
     * the text (same happens for underlined), so the character and the line must have the same colour.
     * This method draws a line on top of the text displayed, setting the line in the middle of the [KzTextView].
     * Because of this implementation, this usage restricts the [KzTextView] to be single line only, so
     * the line is always displayed on the correct position.
     */
    fun setStrikeThroughEnabled(enabled: Boolean, @ColorRes color: Int = R.color.black) {
        strikeThroughEnabled = enabled
        paint.color = ContextCompat.getColor(this.context, color)
        paint.strokeWidth = this.context.resources.displayMetrics.density
        if (enabled) this.isSingleLine = true
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (strikeThroughEnabled) {
            val deviation = this.textSize / STRIKETHROUGH_VALUE
            canvas?.drawLine(
                0F,
                ((height / 2) + deviation),
                width.toFloat(),
                ((height / 2) + deviation),
                paint
            )
        }
    }

    companion object {
        private const val STRIKETHROUGH_VALUE = 8
    }
}