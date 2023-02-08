package com.example.desa.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.desa.MainActivity
import com.example.desa.R
import com.example.desa.databinding.ActivityLoginBinding
import com.example.desa.databinding.ActivityUbahpasswordBinding
import com.example.desa.ui.home.HomeFragment
import com.example.desa.ui.tampilan.profil.AccountFragment

class UbahpasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUbahpasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUbahpasswordBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        supportActionBar?.hide()
        binding.actionbar.namaActionbar.setText("Akun Desa")
        binding.actionbar.backBar.setOnClickListener{
            onBackPressed()
        }
        binding.actionbar.profileBar.setOnClickListener{
            onBackPressed()
        }
    }
}