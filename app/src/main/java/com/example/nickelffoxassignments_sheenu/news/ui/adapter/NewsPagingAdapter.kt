package com.example.nickelffoxassignments_sheenu.news.ui.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.nickelffoxassignments_sheenu.R
import com.example.nickelffoxassignments_sheenu.news.utils.RecyclerListener
import com.example.nickelffoxassignments_sheenu.news.data.local.Article
import kotlinx.coroutines.*

class NewsPagingAdapter:PagingDataAdapter<Article, NewsPagingAdapter.NewsViewHolder>(COMPARATOR){

      var recyclerListener: RecyclerListener? =null
    var bookmark:Boolean=false
     var title:String="Bookmark"
    var result:String=""


    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var newsImage=itemView.findViewById<ImageView>(R.id.ivNewsImage)
        var newsTitle = itemView.findViewById<TextView>(R.id.newTitle)!!
        private var newsAuthor=itemView.findViewById<TextView>(R.id.newAuthor)
        private var newsSource=itemView.findViewById<TextView>(R.id.newsCategory)
        private var progressbar=itemView.findViewById<ProgressBar>(R.id.newsProgressBar)
        var newsMenu= itemView.findViewById<TextView>(R.id.contextMenu)!!


        fun bind(item: Article){

            progressbar.visibility=View.VISIBLE
            Glide.with(itemView).load(item.urlToImage)
                .error(R.drawable.ic_baseline_error_outline_24)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        progressbar.visibility=View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        progressbar.visibility=View.GONE

                        return false
                    }

                }).into(newsImage)
            newsTitle.text=item.title
            newsAuthor.text=item.author
            newsSource.text=item.source.name



        }
    }

    companion object{
        private val COMPARATOR= object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url==newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem==newItem
            }


        }
        var urlList= mutableListOf<String>()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setUrlList(urlList:List<String>){
        NewsPagingAdapter.urlList= urlList.toMutableList()
        notifyDataSetChanged()
    }

    fun setListeners(recyclerListener: RecyclerListener){
        this.recyclerListener=recyclerListener

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item =getItem(position)
        if (item != null) {
            holder.bind(item)
        }


        holder.newsTitle.setOnClickListener {
           recyclerListener?.toDetailsPage(item!!.url)
        }




        holder.newsMenu.setOnCreateContextMenuListener(object : View.OnCreateContextMenuListener {
            override fun onCreateContextMenu(p0: ContextMenu?, p1: View?, p2: ContextMenu.ContextMenuInfo?) {
                if(!urlList.contains(item!!.url)) {
                    p0?.add("Bookmark")?.setOnMenuItemClickListener {
                        recyclerListener?.onContextMenuClick(item.title, item.author, item.source.name, item.urlToImage, item.url)
                        return@setOnMenuItemClickListener true
                    }
                }else{
                    p0?.add("UnMark")?.setOnMenuItemClickListener {

                      recyclerListener?.onContextMenuClickDelete(item.title, item.author, item.source.name, item.urlToImage, item.url)
                       return@setOnMenuItemClickListener true
                    }
                }
                p0?.add("Share")?.setOnMenuItemClickListener {
                    recyclerListener?.onShare(item.url)
                    return@setOnMenuItemClickListener true
                }

            }
        })
}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.news_item_container,parent,false)
        return NewsViewHolder(view)
    }


}