package com.example.desa.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.desa.ApiConfig
import com.example.desa.MainActivity
import com.example.desa.R
import com.example.desa.databinding.ActivityLoginBinding
import com.example.desa.databinding.ActivityUtamaBinding
import com.example.desa.response.LoginResponse
import com.example.desa.response.MasukResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        binding.btnSebelumnya.setOnClickListener{
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
        var dataAkses = sharedPreference.getString("akses","kosong")
        Log.d("Aksesku", dataAkses.toString())
        binding.btnLogin.setOnClickListener{
            val username = binding.inputusername.text
            val pasword = binding.inputPassword.text
            dataku(username.toString(), pasword.toString())
        }

        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(LoginViewModel::class.java)
        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    fun dataku(u : String, p : String){
        showLoading(true)
        val client = ApiConfig.getApiService().getLogin(u,p)
        client.enqueue(object : Callback<MasukResponse> {
            override fun onResponse(
                call: Call<MasukResponse>,
                response: Response<MasukResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                            Log.d("DATAKU", "Berhasil token"+responseBody.token)
                            Toast.makeText(applicationContext, "Berhasil login", Toast.LENGTH_SHORT).show()
                            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
                            var editor = sharedPreference.edit()
                            editor.putString("akses","1")
                            editor.putString("token", responseBody.token)
                            editor.commit()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            showLoading(false)
                    }
                } else {
                    Log.e("Eror", "onFailure: ${response.message()}")
                    Log.d("responseku", "respo: else1")
                    val toast = Toast.makeText(applicationContext, "NIK dan PIN Salah", Toast.LENGTH_SHORT)
                    toast.show()
                    showLoading(false)
                }
            }

            override fun onFailure(call: Call<MasukResponse>, t: Throwable) {
                Log.e("Eror", "onFailure: eror")
                Log.d("responseku", "respo: else2")
                val toast = Toast.makeText(applicationContext, "periksa koneksi kamu", Toast.LENGTH_SHORT)
                toast.show()
                showLoading(false)
            }

        })
    }
    override fun onResume() {
        if (MainActivity.AKSESKU == "1"){
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
        super.onResume()
    }
}