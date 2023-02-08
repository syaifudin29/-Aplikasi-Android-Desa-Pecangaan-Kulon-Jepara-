package com.example.desa.ui.pesan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PesanViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is pesan Fragment"
    }
    val text: LiveData<String> = _text
}