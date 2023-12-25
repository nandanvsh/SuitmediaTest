package com.example.suitmediatest.data.response

import com.google.gson.annotations.SerializedName

data class UserAccountResponse(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("list_data")
	var listData: ArrayList<DataItem> = arrayListOf(),

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("totalPages")
	val totalPages: Int? = null,

	@field:SerializedName("support")
	val support: Support? = null
)

data class DataItem(

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class Support(

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

