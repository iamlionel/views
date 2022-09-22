package com.lionel.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: VB
    abstract val bindingInflater: (LayoutInflater) -> VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater.invoke(layoutInflater).apply {
            setContentView(root)
        }
    }

    fun goto(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }
}