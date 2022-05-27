package com.dwicandra.githubuserapi.API

import com.dwicandra.githubuserapi.Model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<ResponseUser>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUsers(
        @Path("username") username: String
    ): Call<ResponseDetailUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowersUsers(
        @Path("username") username: String
    ): Call<ArrayList<ItemsItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowingUsers(
        @Path("username") username: String
    ): Call<ArrayList<ItemsItem>>
}