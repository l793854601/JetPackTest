package com.tkm.paging3_basic

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import kotlin.math.max

private const val STARTING_KEY = 0

private const val LOAD_DELAY_MILLIS = 3_000L

@RequiresApi(Build.VERSION_CODES.O)
private val firstArticleCreatedTime = LocalDateTime.now()

class ArticlePagingSource : PagingSource<Int, Article>() {

    companion object {
        private const val TAG = "ArticlePagingSource"
    }

    /**
     * 当PagingSource中的数据发生改变而需要重新加载界面时，则会调用此方法
     * 1.对PagingAdapter调用refresh()
     * 2.对PagingAdapter调用invalidate()
     */
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(article.id - (state.config.pageSize / 2))
    }

    /**
     * LoadParams：
     *  key：要加载的界面的key，如果是第一次加载，则为null
     *  loadSize：请求加载内容的数量
     *
     * LoadResult：
     *  LoadResult.Page：结果返回成功
     *  LoadResult.Error：发生错误
     *  LoadResult.Invalid：无法保证其结果的完整性而应失效
     *
     * LoadResult.Page必须包含三个必选参数：
     *  data：数据List
     *  prevKey：上一页，如果为首页的上一页，则为null
     *  nextKey：下一页，如果已加载全部，则为null
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        Log.d(TAG, "load - key: ${params.key}, loadSize: ${params.loadSize}")
        //  首次加载为STARTING_KEY，其次为params.key
        val start = params.key ?: STARTING_KEY
        //  确定加载的范围
        val range = start.until(start + params.loadSize)

        delay(LOAD_DELAY_MILLIS)

        return LoadResult.Page(
            data = range.map { number ->
                Article(
                    id = number,
                    title = "Article $number",
                    description = "This describes article $number",
                    created = firstArticleCreatedTime.minusDays(number.toLong())
                )
            },
            prevKey = when (start) {
                STARTING_KEY -> null
                else ->ensureValidKey(range.first - params.loadSize)
            },
            nextKey = range.last + 1
        )
    }

    /**
     * 保证传入的key>=STARTING_KEY
     */
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}


























