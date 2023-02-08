package com.example.desa


import com.example.desa.response.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("auth/login")
    fun getLogin(@Field("nik") nik:String, @Field("pin") pin:String): Call<MasukResponse>

    @GET("public/artikel")
    fun getBerita(): Call<NewsResponse>

    @GET("pesan")
    fun getPesan(): Call<PesanResponse>

    @GET("public/artikel/{slug}")
    fun getDetailberita(@Path("slug") slug: String?): Call<DetailNewsResponse>

    @GET("penduduk/data")
    fun getDataProfile(@Header("Authorization") token: String): Call<ProfileResponse>

    @GET("penduduk/layanan")
    fun getLayanan(@Header("Authorization") token: String): Call<LayananResponse>
}