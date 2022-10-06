package com.example.nickelffoxassignments_sheenu.news.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nickelffoxassignments_sheenu.R
import com.example.nickelffoxassignments_sheenu.news.ui.adapter.NewsAdapter
import com.example.nickelffoxassignments_sheenu.news.ui.viewmodel.NewsViewModel
import com.example.nickelffoxassignments_sheenu.news.utils.RecyclerListener
import com.example.nickelffoxassignments_sheenu.news.data.local.Bookmark
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
@AndroidEntryPoint
class NewsBookmarkFragment : Fragment(), RecyclerListener {
    private lateinit var bookmarkRecycler:RecyclerView
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.fragment_news_bookmark, container, false)
        bookmarkRecycler=view.findViewById(R.id.rvBookmark)

        newsViewModel= ViewModelProvider(this@NewsBookmarkFragment)[NewsViewModel::class.java]
         newsAdapter= NewsAdapter()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel.repository.newsDatabase.getBookmarkDAO().getArticles().observe(viewLifecycleOwner
        ) {
            newsAdapter.submitList(it)
        }
        newsAdapter.setListeners(this)
        bookmarkRecycler.adapter=newsAdapter
        bookmarkRecycler.layoutManager=LinearLayoutManager(activity)

    }

    override fun toDetailsPage(url: String) {
        val bundle =  Bundle()
        bundle.putString("url", url)
        NavHostFragment.findNavController(this).navigate(R.id.detailFragment,bundle)
    }

    override fun onContextMenuClick(title: String, author: String?, source: String, image: String,url:String) {
        CoroutineScope(Dispatchers.IO).launch{
            newsViewModel.repository.newsDatabase.getBookmarkDAO().deleteArticles(Bookmark(title,author,source,image,url))
        }

    }

    override fun onContextMenuClickDelete(
        title: String,
        author: String?,
        source: String,
        image: String,
        url: String
    ) {

    }

    override fun onShare(url: String) {
        val intent= Intent(Intent.ACTION_SEND)
        intent.type = "text/Plain"
        intent.putExtra(Intent.EXTRA_TEXT,url)
        startActivity(Intent.createChooser(intent,"Share Link"))
    }

//    override fun isUrlMatched(url: String): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun setArticleStatus(url: String, status: String) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getArticleStatus(url: String): String {
//        return url
//    }


}