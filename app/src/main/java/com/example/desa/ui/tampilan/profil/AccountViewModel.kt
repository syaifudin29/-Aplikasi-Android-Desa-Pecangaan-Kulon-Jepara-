package com.example.desa.ui.tampilan.profil

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desa.ApiConfig
import com.example.desa.MainActivity
import com.example.desa.response.DataItemNews
import com.example.desa.response.LayananResponse
import com.example.desa.response.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountViewModel : ViewModel(){

    private val _listReviewProfile = MutableLiveData<ProfileResponse>()
    val listReviewProfile: LiveData<ProfileResponse> = _listReviewProfile

    private val _listReviewLayanan = MutableLiveData<LayananResponse>()
    val listReviewLayanan: LiveData<LayananResponse> = _listReviewLayanan

    fun dataprofile(){
        val client = ApiConfig.getApiService().getDataProfile(token = "Bearer "+ MainActivity.TOKENKU)
        client.enqueue(object : Callback<ProfileResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listReviewProfile.value = responseBody as ProfileResponse
                    }
                } else {
                    Log.e("Eror", "onFailure: ${response.message()}")

                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.e("Eror", "onFailure: eror")
                Log.d("responseku", "respo: else2")
            }

        })
    }

    fun datalayanan(){
        val client = ApiConfig.getApiService().getLayanan(token = "Bearer "+ MainActivity.TOKENKU)
        client.enqueue(object : Callback<LayananResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<LayananResponse>,
                response: Response<LayananResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listReviewLayanan.value = responseBody as LayananResponse
                    }
                } else {
                    Log.e("Eror", "onFailure: ${response.message()}")

                }
            }

            override fun onFailure(call: Call<LayananResponse>, t: Throwable) {
                Log.e("Eror", "onFailure: eror")
                Log.d("responseku", "respo: else2")
            }

        })
    }
}