package com.dwicandra.githubuserapi.Model

import com.google.gson.annotations.SerializedName

data class ResponseUser(

	@field:SerializedName("items")
	val items: ArrayList<ItemsItem>
)

data class ItemsItem(

	@field:SerializedName("following_url")
	val followingUrl: String,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("followers_url")
	val followersUrl: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("id")
	val id: Int,

)
