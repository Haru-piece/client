package com.brudenell.harupiece

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.brudenell.harupiece.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("brudenell", "test")
    }
}