package com.example.desa.response

import com.google.gson.annotations.SerializedName

data class PesanResponse(

	@field:SerializedName("status")
	val status: Int,

	@field:SerializedName("data_pesan")
	val dataPesan: List<DataPesanItem>
)

data class DataPesanItem(

	@field:SerializedName("waktu")
	val waktu: String,

	@field:SerializedName("jenis")
	val jenis: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("judul")
	val judul: String
)
