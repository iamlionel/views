package com.lionel.views.api

import android.os.Bundle
import android.view.LayoutInflater
import com.lionel.views.BaseActivity
import com.lionel.views.databinding.ActivityBitmapBufferBinding

class BitmapBufferActivity : BaseActivity<ActivityBitmapBufferBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityBitmapBufferBinding
        get() = ActivityBitmapBufferBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnRedo.setOnClickListener {
            binding.whiteBoardView.redo()
        }
    }
}