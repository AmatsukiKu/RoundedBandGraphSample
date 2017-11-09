package com.example.gonnokami.roundedbandgraphsample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

/**
 * CanvasのClipPathを使った角丸帯グラフの実装
 *
 * @author gonnokami
 */
class RoundedBandGraph4 @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {
    private val paint = Paint().apply { isAntiAlias = true }
    private val leftRect = Rect()
    private val rightRect = Rect()
    private val path = Path()
    var positivePercentage: Int by Delegates.notNull()

    init {
        // Hardware accelerated drawing modelでClipPath()がサポートされていのはAPI Level 18以降
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val radius = height / 2
        val positiveRectangleWidth = width * positivePercentage / 100
        val negativeRectangleWidth = width - positiveRectangleWidth
        path.addCircle(radius.toFloat(), radius.toFloat(), radius.toFloat(), Path.Direction.CCW)
        path.addCircle(width - radius.toFloat(), radius.toFloat(), radius.toFloat(), Path.Direction.CCW)
        path.addRect(radius.toFloat(), 0f, width - radius.toFloat(), height.toFloat(), Path.Direction.CCW)
        canvas?.clipPath(path)
        if (positivePercentage >= 50) {
            paint.color = ContextCompat.getColor(context, R.color.major)
        } else {
            paint.color = ContextCompat.getColor(context, R.color.minor)
        }
        rightRect.set(width - positiveRectangleWidth, 0, width, height)
        canvas?.drawRect(rightRect, paint)
        if (positivePercentage >= 50) {
            paint.color = ContextCompat.getColor(context, R.color.minor)
        } else {
            paint.color = ContextCompat.getColor(context, R.color.major)
        }
        leftRect.set(0, 0, negativeRectangleWidth, height)
        canvas?.drawRect(leftRect, paint)
    }
}
