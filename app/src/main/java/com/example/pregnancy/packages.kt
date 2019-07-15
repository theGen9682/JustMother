package com.example.pregnancy

import android.annotation.SuppressLint
import android.graphics.Color.BLACK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.app_bar_main.*

class packages : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_packages)


        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)

    }
}
