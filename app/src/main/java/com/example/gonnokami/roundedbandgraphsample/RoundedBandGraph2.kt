package com.example.gonnokami.roundedbandgraphsample

import android.content.Context
import android.graphics.drawable.ClipDrawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout

import kotlinx.android.synthetic.main.view_rounded_band_graph2.view.*

/**
 * ShapeDrawableのringとClipDrawableを使った角丸帯グラフの実装
 *
 * @author gonnokami
 */
class RoundedBandGraph2 : RelativeLayout {
    var positivePercentage: Int = 0
        set(value) {
            (leftRect.layoutParams as LinearLayout.LayoutParams).weight = (100 - value).toFloat()
            (rightRect.layoutParams as LinearLayout.LayoutParams).weight = value.toFloat()

            val leftColor: Int
            val rightColor: Int
            if (value >= 50) {
                leftColor = ContextCompat.getColor(context, R.color.minor)
                rightColor = ContextCompat.getColor(context, R.color.major)
            } else {
                leftColor = ContextCompat.getColor(context, R.color.major)
                rightColor = ContextCompat.getColor(context, R.color.minor)
            }
            leftRect.setBackgroundColor(leftColor)
            rightRect.setBackgroundColor(rightColor)
        }

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
        LayoutInflater.from(context).inflate(R.layout.view_rounded_band_graph2, this, true)

        (leftCutter.drawable as ClipDrawable).level = 5000
        (rightCutter.drawable as ClipDrawable).level = 5000
    }
}
