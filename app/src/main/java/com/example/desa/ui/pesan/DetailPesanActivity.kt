package com.example.desa.ui.pesan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desa.R

class DetailPesanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pesan)
        supportActionBar?.hide()
    }
}