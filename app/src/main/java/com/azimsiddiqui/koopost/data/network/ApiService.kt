package com.azimsiddiqui.koopost.data.network

import com.azimsiddiqui.koopost.data.model.PostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    suspend fun getPosts(
        @Query("page") page: Int
    ):Response<PostResponse>
}