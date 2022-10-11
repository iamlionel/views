package com.lionel.views.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class WhiteBoardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var bitmapBuffer: Bitmap? = null
    private var bitmapCanvas: Canvas? = null
    private var path = Path()
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var preX = 0f
    private var preY = 0f
    private var lines = arrayListOf<Line>()
    private var line: Line? = null

    init {
        paint.color = Color.BLACK
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
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
        if (line == null) {
            line = Line()
        }
        line?.add(PointF(x, y))
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
                line?.let {
                    lines.add(it)
                }
                line = null
                bitmapCanvas?.drawPath(path, paint)
            }
        }
        return true
    }

    fun redo() {
        path.reset()
        lines.removeFirstOrNull()
        bitmapCanvas?.drawColor(0, PorterDuff.Mode.CLEAR)
        val path = Path()
        var preX = 0f
        var preY = 0f
        for (line in lines) {
            line.points.forEachIndexed { index, pointF ->
                if (index == 0) {
                    path.moveTo(pointF.x, pointF.y)
                } else {
                    val controlX = (preX + pointF.x) / 2
                    val controlY = (preY + pointF.y) / 2
                    path.quadTo(controlX, controlY, pointF.x, pointF.y)
                }
                preX = pointF.x
                preY = pointF.y
            }
        }
        bitmapCanvas?.drawPath(path, paint)
        invalidate()
    }
}

class Line {
    val points = ArrayList<PointF>()

    fun add(point: PointF) {
        points.add(point)
    }
}

class ShapeDrawer {
    private val history = History()

    fun draw(canvas: Canvas) {

    }

    fun onTouchEvent(event: MotionEvent): Boolean {
        TODO("Not yet implemented")
    }

    fun redo() {
        history.redo()
    }
}

class History {
    private val shapes = ArrayDeque<ShapeDrawer>()

    fun add(shapeDrawer: ShapeDrawer) {
        shapes.addFirst(shapeDrawer)
    }

    fun redo() {
        shapes.removeFirstOrNull()
    }
}