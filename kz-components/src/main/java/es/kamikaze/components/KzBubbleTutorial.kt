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
import es.kamikaze.components.databinding.KzBubbleTutorialBinding

class KzBubbleTutorial @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private val b: KzBubbleTutorialBinding = KzBubbleTutorialBinding.inflate(LayoutInflater.from(context), this, true)

    private var title = b.kzTitle.text ?: ""
    private var description = b.kzDescription.text ?: ""
    private var titleSize = b.kzTitle.textSize
    private var descriptionSize = b.kzDescription.textSize

    init {

        b.kzTitle.text = title
        b.kzTitle.textSize = titleSize
        b.kzDescription.text = description
        b.kzDescription.textSize = descriptionSize
    }

    fun setData(title: String, titleSize: Float, description: String, descriptionSize: Float) {
        this.title = title
        this.titleSize = titleSize
        this.description = title
        this.descriptionSize = descriptionSize
        b.kzTitle.text = title
        b.kzTitle.textSize = titleSize
        b.kzDescription.text = description
        b.kzDescription.textSize = descriptionSize
    }
}