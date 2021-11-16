package com.azimsiddiqui.koopost.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.azimsiddiqui.koopost.data.model.Data
import com.azimsiddiqui.koopost.data.model.PostResponse
import com.azimsiddiqui.koopost.data.network.PostRepository
import com.azimsiddiqui.koopost.paging.PagingDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val repository: PostRepository):ViewModel() {

    val posts = Pager(PagingConfig(pageSize = 20)) {
        PagingDataSource(repository)
    }.flow.cachedIn(viewModelScope)

}