package com.example.nickelffoxassignments_sheenu.news

import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nickelffoxassignments_sheenu.R
import com.example.nickelffoxassignments_sheenu.news.db.Bookmark
import com.example.nickelffoxassignments_sheenu.news.models.RecyclerListener

class NewsAdapter:ListAdapter<Bookmark,NewsAdapter.ViewHolder>(NewsDiffUtilCallback()) {
    var recyclerListener: RecyclerListener? =null

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        private var newsImage=itemView.findViewById<ImageView>(R.id.ivNewsImage)
        var newsTitle= itemView.findViewById<TextView>(R.id.newTitle)!!
        private var newsAuthor=itemView.findViewById<TextView>(R.id.newAuthor)
        private var newsSource=itemView.findViewById<TextView>(R.id.newsCategory)
        var newsMenu= itemView.findViewById<TextView>(R.id.contextMenu)!!
        fun bind(item: Bookmark){
            Glide.with(itemView).load(item.urlToImage).into(newsImage)
            newsTitle.text=item.title
            newsAuthor.text=item.author
            newsSource.text=item.source

        }
    }
    fun setListeners(recyclerListener: RecyclerListener){
        this.recyclerListener=recyclerListener

    }
    class NewsDiffUtilCallback:DiffUtil.ItemCallback<Bookmark>(){
        override fun areItemsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
            return oldItem==newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.news_item_container,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item =getItem(position)
        holder.bind(item)
        holder.newsTitle.setOnClickListener {
            recyclerListener?.toDetailsPage(item!!.url)
        }
        holder.newsMenu.setOnCreateContextMenuListener(object : View.OnCreateContextMenuListener {
            override fun onCreateContextMenu(p0: ContextMenu?, p1: View?, p2: ContextMenu.ContextMenuInfo?) {
                p0!!.add(0, 122,0,"UnMark").setOnMenuItemClickListener {
                    recyclerListener?.onContextMenuClick(
                        item!!.title,
                        item.author,
                        item.source,
                        item.urlToImage,
                        item.url
                    )
                    true
                }
                p0.add(0,123,0,"Share").setOnMenuItemClickListener {
                    recyclerListener?.onShare(item!!.url)
                    return@setOnMenuItemClickListener true
                }
            }

        })



    }
}