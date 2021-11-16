package com.azimsiddiqui.koopost.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.azimsiddiqui.koopost.data.model.Data
import com.azimsiddiqui.koopost.data.model.PostResponse
import com.azimsiddiqui.koopost.data.network.PostRepository
import javax.inject.Inject

class PagingDataSource @Inject constructor(private val repository: PostRepository) :
    PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val currentPageNumber = params.key ?: 1
            val response = repository.getPosts(currentPageNumber)
            val responseList= mutableListOf<Data>()
            val data=response.body()?.data?: emptyList()
            responseList.addAll(data)


                LoadResult.Page(
                    data = responseList,
                    prevKey = if (currentPageNumber > 0) currentPageNumber - 1 else null,
                    //nextKey = if (nextPageNumber < response.meta.pagination.pages) nextPageNumber + 1 else null
                    nextKey = currentPageNumber+1
                )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state?.anchorPosition
    }
}