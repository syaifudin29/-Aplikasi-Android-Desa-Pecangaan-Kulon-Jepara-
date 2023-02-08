package com.example.desa.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("tempat_lahir")
	val tempatLahir: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("nama_ibu")
	val namaIbu: String? = null,

	@field:SerializedName("nama_ayah")
	val namaAyah: String? = null,

	@field:SerializedName("tanggal_lahir")
	val tanggalLahir: String? = null,

	@field:SerializedName("alamat")
	val alamat: Alamat? = null
)

data class Alamat(

	@field:SerializedName("rt")
	val rt: String? = null,

	@field:SerializedName("dusun")
	val dusun: String? = null,

	@field:SerializedName("rw")
	val rw: String? = null
)
