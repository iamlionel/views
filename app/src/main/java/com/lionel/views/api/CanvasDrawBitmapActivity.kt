package com.lionel.views.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import com.lionel.views.BaseActivity
import com.lionel.views.R
import com.lionel.views.databinding.ActivityCanvasDrawBitmapBinding

class CanvasDrawBitmapActivity : BaseActivity<ActivityCanvasDrawBitmapBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawBitmap()
    }

    private fun drawBitmap() {
        val bmpBuffer = Bitmap.createBitmap(400, 500, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmpBuffer)
        val bmp = BitmapFactory.decodeResource(
            resources,
            R.mipmap.ic_launcher
        )
        canvas.drawBitmap(bmp, 0f, 0f, null)
        binding.ivContent.setImageBitmap(bmpBuffer)
    }

    override val bindingInflater: (LayoutInflater) -> ActivityCanvasDrawBitmapBinding
        get() = ActivityCanvasDrawBitmapBinding::inflate

}