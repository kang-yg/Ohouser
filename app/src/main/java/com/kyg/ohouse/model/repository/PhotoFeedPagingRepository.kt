package com.kyg.ohouse.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kyg.ohouse.api.RetrofitService
import com.kyg.ohouse.model.PopularCard
import kotlinx.coroutines.flow.Flow

class PhotoFeedPagingRepository(private val service: RetrofitService) {
    fun getPopularCardByPaging(): Flow<PagingData<PopularCard>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PhotoFeedPagingSource(service) }
        ).flow
    }
}