package com.example.nickelffoxassignments_sheenu.news.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nickelffoxassignments_sheenu.news.data.dao.ArticleDao
import com.example.nickelffoxassignments_sheenu.news.data.dao.BookmarkDAO
import com.example.nickelffoxassignments_sheenu.news.data.dao.RemoteKeysDao
import com.example.nickelffoxassignments_sheenu.news.data.local.Article
import com.example.nickelffoxassignments_sheenu.news.data.local.Bookmark
import com.example.nickelffoxassignments_sheenu.news.data.local.RemoteKeys
import com.example.nickelffoxassignments_sheenu.news.utils.SourceTypeConverter

@Database(entities = [Article::class, RemoteKeys::class, Bookmark::class],version=1
    ,exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
abstract class NewsDatabase:RoomDatabase() {

    abstract fun getArticleDAO(): ArticleDao
    abstract fun getRemoteKeysDAO(): RemoteKeysDao
    abstract fun getBookmarkDAO(): BookmarkDAO
}