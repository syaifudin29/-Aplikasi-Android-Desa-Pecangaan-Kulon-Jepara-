package com.example.desa.ui.pasar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PasarViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is pasar Fragment"
    }
    val text: LiveData<String> = _text
}