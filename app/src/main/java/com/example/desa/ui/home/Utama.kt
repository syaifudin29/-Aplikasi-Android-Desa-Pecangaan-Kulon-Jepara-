package com.example.desa.ui.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.view.isVisible
import com.example.desa.MainActivity
import com.example.desa.R
import com.example.desa.databinding.ActivityUtamaBinding
import com.example.desa.ui.login.LoginActivity

class Utama : AppCompatActivity() {

    private lateinit var binding: ActivityUtamaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUtamaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this@Utama, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSelanjutnya.setOnClickListener {
            val intent = Intent(this@Utama, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {

    }

    override fun onResume() {
        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val token = sharedPreference.getString("akses","kosong").toString()
        Log.d("ABCD", token)
        if (token != "kosong"){
            val intent = Intent(this@Utama, MainActivity::class.java)
            startActivity(intent)
        }
        super.onResume()
    }
}