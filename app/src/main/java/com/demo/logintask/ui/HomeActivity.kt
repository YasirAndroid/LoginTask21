package com.demo.logintask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.logintask.R
import com.demo.logintask.databinding.ActivityHomeBinding
import com.demo.logintask.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        var name = intent.getStringExtra("name")
        var email = intent.getStringExtra("name")

        binding.tvEmail.text = email
        binding.tvName.text = name

    }
}