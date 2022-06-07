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

@file:JvmName("Extensions")

package es.kamikaze.components.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintSet

/**
 * Pone la vista en como GONE.
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * Pone la vista en como VISIBLE.
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Pone la vista en como INVISIBLE.
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * Para saber si la vista esta en GONE.
 */
fun View.isHidden() = visibility == View.GONE

/**
 * Para saber si la vista esta en VISIBLE.
 */
fun View.isVisible() = visibility == View.VISIBLE

/**
 * Para saber si la vista esta en INVISIBLE.
 */
fun View.isInvisible() = visibility == View.INVISIBLE

/**
 * Ocultar la vista, en caso de que el texto sea nulo.
 */
fun TextView.setTextOrHideIfNull(textValue: String?) {
    textValue?.let { text = textValue } ?: gone()
}

fun ViewGroup.inflateLayout(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(this.context).inflate(layoutRes, this, false)
}

fun View.bindData(data: String?) {
    if (data.isNullOrEmpty() || data.isBlank()) return gone() else visible()
    when (this) {
        is TextView -> this.text = data
        is ImageView -> ImageLoadingUtil.setImage(this.context, data, 0, this)
    }
}

fun View.setMargins(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams ?: return
    lp.setMargins(
        left ?: lp.leftMargin,
        top ?: lp.topMargin,
        right ?: lp.rightMargin,
        bottom ?: lp.bottomMargin
    )
    layoutParams = lp
}

fun ConstraintSet.match(view: View, parentView: View) {
    this.connect(view.id, ConstraintSet.TOP, parentView.id, ConstraintSet.TOP)
    this.connect(view.id, ConstraintSet.START, parentView.id, ConstraintSet.START)
    this.connect(view.id, ConstraintSet.END, parentView.id, ConstraintSet.END)
    this.connect(view.id, ConstraintSet.BOTTOM, parentView.id, ConstraintSet.BOTTOM)
}

/**
 * Añadir una vista al componente como un [LinearLayoutCompat] y lo retorna.
 *
 * @param context contexto de la vista.
 * @param layout id del recursos al que se le quiere añadir la vista.
 * @param rootView la vista que lo llama.
 * @return [View] la nueva vista.
 */
fun LinearLayoutCompat.addView(context: Context, layout: Int, rootView: ViewGroup): View =
    LayoutInflater.from(context).inflate(layout, rootView, false).apply { addView(this) }