package com.example.desa.response

import com.google.gson.annotations.SerializedName

data class LayananResponse(

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("layanan")
	val layanan: Layanan? = null,

	@field:SerializedName("nama")
	val nama: String? = null
)

data class Interdesa(

	@field:SerializedName("harga_paket")
	val hargaPaket: String? = null,

	@field:SerializedName("jatuh_tempo")
	val jatuhTempo: String? = null,

	@field:SerializedName("nama_paket")
	val namaPaket: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class Layanan(

	@field:SerializedName("jemput_sampah")
	val jemputSampah: JemputSampah? = null,

	@field:SerializedName("interdesa")
	val interdesa: Interdesa? = null
)

data class JemputSampah(

	@field:SerializedName("jatuh_tempo")
	val jatuhTempo: String? = null,

	@field:SerializedName("tagihan_per_bulan")
	val tagihanPerBulan: String? = null,

	@field:SerializedName("paket_pelanggan")
	val paketPelanggan: String? = null
)
