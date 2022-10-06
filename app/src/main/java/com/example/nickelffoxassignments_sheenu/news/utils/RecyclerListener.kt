package com.example.nickelffoxassignments_sheenu.news.utils


interface RecyclerListener {
  fun toDetailsPage(url:String)
  fun onContextMenuClick(title:String, author: String?, source:String, image:String,url:String)
  fun onContextMenuClickDelete(title:String, author: String?, source:String, image:String,url:String)
  fun onShare(url:String)

//
//  fun setArticleStatus(url: String,status:String)
//
//  fun getArticleStatus(url: String):String



}