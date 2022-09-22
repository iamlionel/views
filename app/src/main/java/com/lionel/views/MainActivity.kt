package com.lionel.views

import android.os.Bundle
import android.view.LayoutInflater
import com.lionel.views.api.CanvasDrawBitmapActivity
import com.lionel.views.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goto(CanvasDrawBitmapActivity::class.java)
    }

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
}