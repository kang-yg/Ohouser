package com.kyg.ohouse.model.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kyg.ohouse.api.RetrofitService
import com.kyg.ohouse.model.Card
import com.kyg.ohouse.model.PopularCard
import retrofit2.awaitResponse

class PhotoFeedPagingSource(
    private val service: RetrofitService
) : PagingSource<Int, PopularCard>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularCard> {
        return try {
            val pageIndex = params.key ?: 1
            var result: Card? = null
            result = service.getPhotoFeedData(page = pageIndex, per = 20).awaitResponse().body()

            result?.let {
                LoadResult.Page(
                    data = it.cards,
                    prevKey = if (pageIndex == 1) null else pageIndex - 1,
                    nextKey = if (it.cards.isEmpty()) null else pageIndex + 1
                )
            }!!
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PopularCard>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}