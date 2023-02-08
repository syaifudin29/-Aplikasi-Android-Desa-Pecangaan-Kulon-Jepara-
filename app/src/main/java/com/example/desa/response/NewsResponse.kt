package com.example.desa.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(

	@field:SerializedName("data")
	val data: List<DataItemNews?>? = null,

	@field:SerializedName("thumbPath")
	val thumbPath: String? = null
)

data class Kategori(

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class DataItemNews(

	@field:SerializedName("id_kategori")
	val idKategori: String? = null,

	@field:SerializedName("kategori")
	val kategori: Kategori? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)
