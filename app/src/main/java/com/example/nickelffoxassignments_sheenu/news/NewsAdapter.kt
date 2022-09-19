package com.example.nickelffoxassignments_sheenu.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nickelffoxassignments_sheenu.R
import com.example.nickelffoxassignments_sheenu.news.db.Article

class NewsAdapter:ListAdapter<Article,NewsAdapter.viewHolder>(NewsDiffUtilCallback()) {

    class viewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var newsImage=itemView.findViewById<ImageView>(R.id.ivNewsImage)
        var newsTitle=itemView.findViewById<TextView>(R.id.newTitle)
        var newsAuthor=itemView.findViewById<TextView>(R.id.newAuthor)
        var newsSource=itemView.findViewById<TextView>(R.id.newsCategory)
        var newTime=itemView.findViewById<TextView>(R.id.contextMenu)
        fun bind(item: Article){
            Glide.with(itemView).load(item.urlToImage).into(newsImage)
            newsTitle.text=item.title
            newsAuthor.text=item.author
            newsSource.text=item.source.name

        }
    }
    class NewsDiffUtilCallback:DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.news_item_container,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       var item =getItem(position)
        holder.bind(item)
//        holder.itemView.setOnClickListener {
//
//        }
    }
}