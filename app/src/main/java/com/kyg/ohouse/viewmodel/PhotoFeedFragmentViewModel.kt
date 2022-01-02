package com.kyg.ohouse.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kyg.ohouse.api.RetrofitFactory
import com.kyg.ohouse.api.RetrofitService
import com.kyg.ohouse.model.repository.PhotoFeedPagingRepository
import com.kyg.ohouse.model.PopularCard
import kotlinx.coroutines.flow.Flow

class PhotoFeedFragmentViewModel : BaseViewModel() {
    private var service = RetrofitFactory.createRetrofit().create(RetrofitService::class.java)

    fun setPaging(): Flow<PagingData<PopularCard>> {
        return PhotoFeedPagingRepository(service).getPopularCardByPaging().cachedIn(viewModelScope)
    }
}