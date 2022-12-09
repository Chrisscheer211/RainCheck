package com.example.raincheck

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.raincheck.databinding.NavHeaderBinding

class NavHeader: AppCompatActivity() {

    lateinit var binding: NavHeaderBinding
    lateinit var preferences: SharedPreferences

    override fun onCreate(SavedInstanceState: Bundle?) {
        super.onCreate(SavedInstanceState)
        binding = NavHeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        binding.apply {
            val email = preferences.getString("EMAIL", "")
            emailName.text = email
        }

    }
}


