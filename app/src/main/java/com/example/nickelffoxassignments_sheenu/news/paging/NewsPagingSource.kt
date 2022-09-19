package com.example.nickelffoxassignments_sheenu.news.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nickelffoxassignments_sheenu.news.News
import com.example.nickelffoxassignments_sheenu.news.NewsService
import com.example.nickelffoxassignments_sheenu.news.db.Article
import retrofit2.Response
import javax.inject.Inject

class NewsPagingSource @Inject constructor(val newsService: NewsService, val funType:String, val query:String): PagingSource<Int, Article>(){
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
            state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        var response: Response<News>? =null
        return try{
            var position=params.key?:1
            when(funType){
                "everything"->{
                     response=newsService.getSearchNews(query,position)
                }
                "TopHeadlines"->{
                     response=newsService.getTopHeadlines(query,position)
                }
                "TopSourceHeadlines"->{
                     response=newsService.getSourceWiseHeadline(query,position)
                }
                "TopCategoryHeadlines"->{
                     response=newsService.getCategoryWiseHeadline(query,position)
                }
            }
//            val response=newsService.getTopHeadlines("in",position)
            LoadResult.Page(
                data = response!!.body()!!.articles,
                prevKey = if(position==1)null else position -1,
                nextKey = if(response.body()!!.articles.isEmpty()) null else position + 1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }


    }

}

