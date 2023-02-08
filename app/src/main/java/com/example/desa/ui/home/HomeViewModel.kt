package com.example.desa.ui.home

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.*
import com.example.desa.ApiConfig
import com.example.desa.response.BeritaResponse
import com.example.desa.response.DataItem
import com.example.desa.response.DataItemNews
import com.example.desa.response.NewsResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class HomeViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listReviewBerita = MutableLiveData<ArrayList<DataItemNews>>()
    val listReviewBerita: LiveData<ArrayList<DataItemNews>> = _listReviewBerita


    init {
        dataku()
    }
    fun dataku(bol:Boolean = true) {
        Log.d("sesi", "viewmodel")
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
                            Log.d("responseku", "Sukses")
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