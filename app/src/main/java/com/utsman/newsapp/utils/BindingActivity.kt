package com.utsman.newsapp.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BindingActivity<T: ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: T

    abstract fun inflateBinding(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateBinding()
        setContentView(binding.root)
        onCreateBinding(savedInstanceState)
    }

    abstract fun onCreateBinding(savedInstanceState: Bundle?)
}