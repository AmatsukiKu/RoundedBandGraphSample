package com.example.gonnokami.roundedbandgraphsample

/**
 * @author gonnokami
 */

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

/**
 * Canvasによる描画を使った角丸帯グラフの実装
 *
 * @author gonnokami
 */
class RoundedBandGraph3 @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply { isAntiAlias = true }
    private val leftRect = Rect()
    private val rightRect = Rect()
    private val leftArcArea = RectF()
    private val rightArcArea = RectF()

    var step: Int = 0
    var positivePercentage: Int by Delegates.notNull()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val radius = height / 2
        val positiveRectangleWidth = width * positivePercentage / 100
        val negativeRectangleWidth = width - positiveRectangleWidth

        if (step >= 1) {
            paint.color = ContextCompat.getColor(context, R.color.major)
            canvas?.drawCircle(radius.toFloat(), radius.toFloat(), radius.toFloat(), paint)
            canvas?.drawCircle((width - radius).toFloat(), radius.toFloat(), radius.toFloat(), paint)
            if (step >= 2) {
                if (positivePercentage >= 50) {
                    paint.color = ContextCompat.getColor(context, R.color.major)
                } else {
                    paint.color = ContextCompat.getColor(context, R.color.minor)
                }
                if (positiveRectangleWidth > radius) {
                    rightRect.set(Math.max(width - positiveRectangleWidth, radius), 0, width - radius, height)
                    canvas?.drawRect(rightRect, paint)
                }
                if (step >= 3) {
                    var x = Math.max(0, radius - positiveRectangleWidth)
                    var angle = Math.toDegrees(Math.acos(x.toDouble() / radius.toDouble())).toFloat()
                    rightArcArea.set((width - height).toFloat(), 0f, width.toFloat(), height.toFloat())
                    canvas?.drawArc(rightArcArea, 0f - angle, 2f * angle, false, paint)
                    if (step >= 4) {
                        if (positivePercentage >= 50) {
                            paint.color = ContextCompat.getColor(context, R.color.minor)
                        } else {
                            paint.color = ContextCompat.getColor(context, R.color.major)
                        }
                        if (negativeRectangleWidth > radius) {
                            leftRect.set(radius, 0, Math.min(negativeRectangleWidth, width - radius), height)
                            canvas?.drawRect(leftRect, paint)
                        }

                        if (step >= 5) {
                            x = Math.max(0, radius - negativeRectangleWidth)
                            angle = Math.toDegrees(Math.acos(x.toDouble() / radius.toDouble())).toFloat()
                            leftArcArea.set(0f, 0f, height.toFloat(), height.toFloat())
                            canvas?.drawArc(leftArcArea, 180f - angle, 2f * angle, false, paint)
                        }
                    }
                }
            }
        }
    }
}
