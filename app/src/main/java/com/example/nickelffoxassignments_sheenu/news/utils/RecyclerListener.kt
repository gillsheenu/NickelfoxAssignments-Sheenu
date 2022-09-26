package com.example.nickelffoxassignments_sheenu.news.utils


interface RecyclerListener {
  fun toDetailsPage(url:String)
  fun onContextMenuClick(title:String, author: String?, source:String, image:String,url:String)
  fun onShare(url:String)



}