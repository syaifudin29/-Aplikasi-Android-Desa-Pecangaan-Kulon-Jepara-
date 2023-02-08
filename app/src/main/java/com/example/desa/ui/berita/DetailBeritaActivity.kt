package com.example.desa.ui.berita

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.desa.ApiConfig
import com.example.desa.databinding.ActivityDetrailBeritaBinding
import com.example.desa.response.DetailNewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailBeritaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetrailBeritaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetrailBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val st : String = "<h2>PEMBAGIAN SERTIFIKAT DESA PECANGAAN KULON</h2><br>\n" +
                "<p>Pecangaan Kulon, Sebanyak 304 sertfikat dari program PTSL sudah mulai dibagian dalam tahapan pertama dari jumlah total pendaftar 1190. Program PTSL dipecangaan kulon dimulai pada bulan februari 2021 ini baru digagas dan sekaligus segera dilakukan pembentukan panitia melalui Musdes dan Bpk Miftah Arifin terpilih Sebagai Ketua Panitia pelaksana PTSL desa pecangaan Kulon.</p>"

        if (Build.VERSION.SDK_INT >= 24)
        {
            binding.deskripsiDetailBerita.setText(Html.fromHtml(st, Html.FROM_HTML_MODE_LEGACY));

        }
        else
        {
            binding.deskripsiDetailBerita.setText(Html.fromHtml(st));
        }

    }

    fun getBerita(){
        binding.progressBar.visibility =  View.VISIBLE
        binding.tampilanView.visibility =  View.GONE
        val slug = intent.getStringExtra("slug")
        val client = ApiConfig.getApiService().getDetailberita(slug)
        client.enqueue(object : Callback<DetailNewsResponse> {
            override fun onResponse(
                call: Call<DetailNewsResponse>,
                response: Response<DetailNewsResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d("DATAKU", responseBody.judul.toString())
                        binding.judulDetailBerita.text = responseBody.judul
                        binding.tanggalDetailBerita.text = responseBody.tglUpload


                        Glide.with(this@DetailBeritaActivity)
                            .load(responseBody.gambarUtama)
                            .skipMemoryCache(true)//for caching the image url in case phone is offline
                            .into(binding.imgDetailBerita)
                        Glide.with(this@DetailBeritaActivity)
                            .load(responseBody.gambar1)
                            .skipMemoryCache(true)//for caching the image url in case phone is offline
                            .into(binding.gambarsatu)
                        Glide.with(this@DetailBeritaActivity)
                            .load(responseBody.gambar2)
                            .skipMemoryCache(true)//for caching the image url in case phone is offline
                            .into(binding.gambardua)
                        Glide.with(this@DetailBeritaActivity)
                            .load(responseBody.gambar3)
                            .skipMemoryCache(true)//for caching the image url in case phone is offline
                            .into(binding.gambartiga)

                        Html.FROM_HTML_MODE_LEGACY
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                        {
                            binding.deskripsiDetailBerita.setText(Html.fromHtml(responseBody.konten, Html.FROM_HTML_MODE_LEGACY));
                        }
                        else
                        {
                            binding.deskripsiDetailBerita.setText(Html.fromHtml(responseBody.konten));
                        }
                    }
                } else {
                    Log.e("Eror", "onFailure: ${response.message()}")
                    Log.d("responseku", "respo: else1")
                    val toast =
                        Toast.makeText(applicationContext, "NIK dan PIN Salah", Toast.LENGTH_SHORT)
                    toast.show()
//                    showLoading(false)
                }
                binding.progressBar.visibility =  View.GONE
                binding.tampilanView.visibility =  View.VISIBLE
            }

            override fun onFailure(call: Call<DetailNewsResponse>, t: Throwable) {
                Log.e("Eror", "onFailure: eror")
                Log.d("responseku", "respo: else2")
                val toast =
                    Toast.makeText(applicationContext, "periksa koneksi kamu", Toast.LENGTH_SHORT)
                toast.show()
//                showLoading(false)
            }
        })
    }

    override fun onResume() {
        getBerita()
        super.onResume()
    }
}