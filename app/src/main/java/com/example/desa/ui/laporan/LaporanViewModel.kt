package com.example.desa.ui.laporan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LaporanViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is laporan Fragment"
    }
    val text: LiveData<String> = _text
}