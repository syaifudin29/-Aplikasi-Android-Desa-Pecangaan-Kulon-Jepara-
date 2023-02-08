package com.example.desa.response

import com.google.gson.annotations.SerializedName

data class BeritaResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("status")
	val status: Int
)

data class DataItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("judul")
	val judul: String,

	@field:SerializedName("gambar")
	val gambar: String
)
