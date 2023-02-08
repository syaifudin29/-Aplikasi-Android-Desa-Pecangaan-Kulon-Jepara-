package com.example.desa.response

import com.google.gson.annotations.SerializedName

data class MasukResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)

