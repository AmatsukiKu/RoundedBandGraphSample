package com.example.gonnokami.roundedbandgraphsample

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_rounded_band_graph1.view.*
import kotlin.properties.Delegates

/**
 * ShapeDrawableのrectangleを使った角丸帯グラフの実装（バグあり）
 *
 * @author gonnokami
 */
class RoundedBandGraph1 : LinearLayout {
    var positivePercentage: Int by Delegates.notNull()

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        LayoutInflater.from(context).inflate(R.layout.view_rounded_band_graph1, this, true)

        orientation = LinearLayout.HORIZONTAL

        positivePercentage = 98

        leftRect.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1 - (positivePercentage / 100f))
        rightRect.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, positivePercentage / 100f)

        val leftOriginalDrawable = ContextCompat.getDrawable(context, R.drawable.left_rounded_rectangle)
        val rightOriginalDrawable = ContextCompat.getDrawable(context, R.drawable.left_rounded_rectangle)

        val leftDrawable: Drawable = DrawableCompat.wrap(leftOriginalDrawable)
        val leftColor: Int = if (positivePercentage >= 50) {
            ContextCompat.getColor(context, R.color.minor)
        } else {
            ContextCompat.getColor(context, R.color.major)
        }
        DrawableCompat.setTint(leftDrawable, leftColor)
        DrawableCompat.setTintMode(leftDrawable, PorterDuff.Mode.SRC_IN)
        leftRect.background = leftDrawable

        val rightDrawable: Drawable = DrawableCompat.wrap(rightOriginalDrawable)
        val rightColor: Int = if (positivePercentage >= 50) {
            ContextCompat.getColor(context, R.color.major)
        } else {
            ContextCompat.getColor(context, R.color.minor)
        }
        DrawableCompat.setTint(rightDrawable, rightColor)
        DrawableCompat.setTintMode(rightDrawable, PorterDuff.Mode.SRC_IN)
        rightRect.background = rightDrawable
    }
}
