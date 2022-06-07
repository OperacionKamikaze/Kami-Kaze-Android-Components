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
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import es.kamikaze.components.databinding.KzStatusFieldBinding
import es.kamikaze.components.util.KzStatsFieldDC
import es.kamikaze.components.util.match

class KzStatsField @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private val b: KzStatusFieldBinding = KzStatusFieldBinding.inflate(LayoutInflater.from(context), this, true)

    private var data = KzStatsFieldDC("", "")

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.KzStatsField, defStyleAttr, 0)

        data = KzStatsFieldDC(
            name = a.getString(R.styleable.KzStatsField_kztvFieldName) ?: "",
            value = a.getString(R.styleable.KzStatsField_kztvFieldValue) ?: ""
        )

        b.kztvFieldName.text = data.name
        b.kztvFieldValue.text = data.value
        a.recycle()
    }

    fun setData(data: KzStatsFieldDC) {
        this.data = data
        b.kztvFieldName.text = data.name
        b.kztvFieldValue.text = data.value
    }
}