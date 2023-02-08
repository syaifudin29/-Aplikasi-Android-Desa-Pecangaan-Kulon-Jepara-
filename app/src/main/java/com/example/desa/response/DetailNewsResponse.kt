package com.example.desa.response

import com.google.gson.annotations.SerializedName

data class DetailNewsResponse(

	@field:SerializedName("dokumen")
	val dokumen: Any? = null,

	@field:SerializedName("penulis")
	val penulis: String? = null,

	@field:SerializedName("gambar_utama")
	val gambarUtama: String? = null,

	@field:SerializedName("konten")
	val konten: String? = null,

	@field:SerializedName("tgl_upload")
	val tglUpload: String? = null,

	@field:SerializedName("gambar_1")
	val gambar1: String? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("gambar_2")
	val gambar2: String? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("gambar_3")
	val gambar3: String? = null
)
