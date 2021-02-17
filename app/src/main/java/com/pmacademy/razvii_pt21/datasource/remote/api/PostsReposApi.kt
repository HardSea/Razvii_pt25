package com.pmacademy.razvii_pt21.datasource.remote.api

import com.pmacademy.razvii_pt21.datasource.remote.model.UserPostResponse
import retrofit2.Call
import retrofit2.http.GET

interface PostsReposApi {
    @GET("/posts")
    fun getPostsList(): Call<List<UserPostResponse>>
}