package com.azimsiddiqui.koopost.data.network

import javax.inject.Inject

class PostRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getPosts(page:Int)=apiService.getPosts(page)
}