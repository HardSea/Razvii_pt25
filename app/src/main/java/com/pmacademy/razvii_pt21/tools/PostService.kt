package com.pmacademy.razvii_pt21.tools

import com.pmacademy.razvii_pt21.data.UserPostResponse
import retrofit2.Call
import retrofit2.http.GET

interface PostService {
    @GET("/posts")
    fun getPostsList(): Call<List<UserPostResponse>>
}