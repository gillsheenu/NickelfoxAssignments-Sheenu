package com.example.nickelffoxassignments_sheenu.news.paging

import androidx.paging.*
import androidx.room.withTransaction
import com.example.nickelffoxassignments_sheenu.news.News
import com.example.nickelffoxassignments_sheenu.news.db.NewsDatabase
import com.example.nickelffoxassignments_sheenu.news.NewsService
import com.example.nickelffoxassignments_sheenu.news.db.Article
import com.example.nickelffoxassignments_sheenu.news.db.ArticleDao
import com.example.nickelffoxassignments_sheenu.news.db.RemoteKeys
import com.example.nickelffoxassignments_sheenu.news.db.RemoteKeysDao
import retrofit2.Response

@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(val newsService: NewsService, val newsDatabase: NewsDatabase, val funType:String, val query:String):RemoteMediator<Int, Article>(){

    lateinit var articleDao: ArticleDao
    lateinit var remoteKeysDao: RemoteKeysDao
    var response: Response<News>? =null

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Article>): MediatorResult {

       return try{
            var currentPage=when(loadType){
                LoadType.REFRESH->{
                    val remoteKeys=getRemoteKeyClosetToCurrentPosition(state)
                    remoteKeys?.nexKey?.minus(1)?:1
                }
                LoadType.PREPEND->{
                    val remoteKeys=getRemoteKeyForFirstItem(state)
                    val prevPage= remoteKeys?.prevKey
                        ?:return MediatorResult.Success(endOfPaginationReached = remoteKeys!=null)
                     prevPage
                }
                LoadType.APPEND->{
                    val remoteKeys=getRemoteKeyForLastItem(state)
                    val nextPage= remoteKeys?.nexKey
                        ?:return MediatorResult.Success(endOfPaginationReached = remoteKeys!=null)
                    nextPage
                }
            }
           when(funType){
               "everything"->{
                   response=newsService.getSearchNews(query,currentPage)
               }
               "TopHeadlines"->{
                   response=newsService.getTopHeadlines(query,currentPage)
               }
               "TopSourceHeadlines"->{
                   response=newsService.getSourceWiseHeadline(query,currentPage)
               }
               "TopCategoryHeadlines"->{
                   response=newsService.getCategoryWiseHeadline(query,currentPage)
               }
           }
//            var response=newsService.getTopHeadlines("in",currentPage)
            val endOfPagination= response?.body()?.articles!!.isEmpty()
            val prevPage=if(currentPage==1) null else currentPage-1
            val nextPage=if(endOfPagination) null else currentPage+1

            newsDatabase.withTransaction {

                if(loadType==LoadType.REFRESH){
                    articleDao.deleteArticles()
                    remoteKeysDao.deleteAllRemoteKeys()
                }

                articleDao.addArticles(response?.body()!!.articles)
                val keys= response?.body()!!.articles.map { articles->
                    RemoteKeys(
                        url=articles.url,
                        prevKey = prevPage,
                        nexKey = nextPage
                    )
                }
                remoteKeysDao.addRemoteKeys(keys)
            }
            MediatorResult.Success(endOfPagination)

        }catch (e:Exception){
             MediatorResult.Error(e)
        }

    }

    suspend fun getRemoteKeyClosetToCurrentPosition(state: PagingState<Int, Article>): RemoteKeys? {
         return state.anchorPosition?.let{ position->
             state.closestItemToPosition(position)?.url?.let { url->
                 remoteKeysDao.getRemoteKeys(url=url)

             }

         }
    }

    suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Article>): RemoteKeys? {
        return state.pages.firstOrNull{it.data.isEmpty()}?.data?.firstOrNull()
            ?.let {
                    article ->
                remoteKeysDao.getRemoteKeys(url=article.url)
            }
    }


    suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Article>): RemoteKeys? {
        return state.pages.lastOrNull{it.data.isEmpty()}?.data?.lastOrNull()
            ?.let {
                article ->
                remoteKeysDao.getRemoteKeys(url=article.url)
            }
    }

}