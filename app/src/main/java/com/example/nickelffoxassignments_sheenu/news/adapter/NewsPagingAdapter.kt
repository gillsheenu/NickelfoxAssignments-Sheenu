package com.example.nickelffoxassignments_sheenu.news.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nickelffoxassignments_sheenu.R
import com.example.nickelffoxassignments_sheenu.news.db.Article

class NewsPagingAdapter:PagingDataAdapter<Article, NewsPagingAdapter.viewHolder>(COMPARATOR) {

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
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

    companion object{
        private val COMPARATOR= object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url==newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem==newItem
            }


        }
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var item =getItem(position)
        if (item != null) {
            holder.bind(item)
        }
        holder.itemView.setOnClickListener {
            var bundle =  Bundle();
            bundle.putString("url", item!!.url);
            Navigation.findNavController(it).navigate(R.id.detailFragment,bundle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.news_item_container,parent,false)
        return viewHolder(view)
    }
}