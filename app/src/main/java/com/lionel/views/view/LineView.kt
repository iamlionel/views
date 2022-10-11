package com.lionel.views.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * 利用双缓冲技术绘制线条
 */
class LineView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var bitmapBuffer: Bitmap? = null
    private lateinit var bitmapCanvas: Canvas
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var path: Path
    private var preX = 0f
    private var preY = 0f

    init {
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        paint.strokeWidth = 5f
        path = Path()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (bitmapBuffer == null) {
            bitmapBuffer = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            bitmapCanvas = Canvas(bitmapBuffer!!)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmapBuffer!!, 0f, 0f, null)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.reset()
                preX = x
                preY = y
                path.moveTo(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                val controlX = (x + preX) / 2
                val controlY = (y + preY) / 2
                path.quadTo(controlX, controlY, x, y)
                invalidate()
                preX = x
                preY = y
            }
            MotionEvent.ACTION_UP -> {
                bitmapCanvas.drawPath(path, paint)
            }
        }
        return true
    }
}