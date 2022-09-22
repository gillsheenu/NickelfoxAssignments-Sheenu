package com.example.nickelffoxassignments_sheenu.news.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Article::class, RemoteKeys::class,Bookmark::class],version=1
    ,exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
abstract class NewsDatabase:RoomDatabase() {

    abstract fun getArticleDAO(): ArticleDao
    abstract fun getRemoteKeysDAO(): RemoteKeysDao
    abstract fun getBookmarkDAO(): BookmarkDAO
}