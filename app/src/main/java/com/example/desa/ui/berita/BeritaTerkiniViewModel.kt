package com.example.desa.ui.berita

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desa.ApiConfig
import com.example.desa.response.DataItemNews
import com.example.desa.response.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeritaTerkiniViewModel: ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listReviewBerita = MutableLiveData<ArrayList<DataItemNews>>()
    val listReviewBerita: LiveData<ArrayList<DataItemNews>> = _listReviewBerita


    init {
        dataku()
    }
    fun dataku(bol:Boolean = true){
        _isLoading.value = bol
        val client = ApiConfig.getApiService().getBerita()
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d("responseku", "Konek")
                        _listReviewBerita.value = responseBody.data as ArrayList<DataItemNews>
                        _isLoading.value = false
                    }
                } else {
                    Log.d("responseku", "respo: else1")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("Eror", "onFailure: eror")
                Log.d("responseku", "respo: else2")

            }
        })
    }
}