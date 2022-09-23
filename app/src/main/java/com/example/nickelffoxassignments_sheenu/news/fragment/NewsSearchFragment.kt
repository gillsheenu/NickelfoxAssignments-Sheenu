package com.example.nickelffoxassignments_sheenu.news.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nickelffoxassignments_sheenu.R
import com.example.nickelffoxassignments_sheenu.news.paging.NewsLoaderAdapter
import com.example.nickelffoxassignments_sheenu.news.paging.NewsPagingAdapter
import com.example.nickelffoxassignments_sheenu.news.models.NewsViewModel
import com.example.nickelffoxassignments_sheenu.news.models.RecyclerListener
import com.example.nickelffoxassignments_sheenu.news.db.Bookmark
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@AndroidEntryPoint
class NewsSearchFragment : Fragment(), RecyclerListener {

    private lateinit var newsRecycler: RecyclerView
//    lateinit var newsAdapter: NewsAdapter
    lateinit var newsAdapter: NewsPagingAdapter

    lateinit var searchView: SearchView
    lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_news_search, container, false)

        searchView=view.findViewById(R.id.svNews)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override  fun onQueryTextChange(newText: String?): Boolean {

                    if (newText != null) {
//                        newsViewModel.search(newText)
                        newsViewModel.searchNews(newText).observe(viewLifecycleOwner) {
                            newsAdapter.submitData(lifecycle, it)
                        }

                    }


                return true
            }

        })

//        newsAdapter=NewsAdapter()
        newsAdapter= NewsPagingAdapter()
        newsAdapter.setListeners(this)
        newsRecycler=view.findViewById(R.id.NewsRoot)
        newsViewModel= ViewModelProvider(this@NewsSearchFragment)[NewsViewModel::class.java]
//        newsViewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
//            newsAdapter.submitList(it.articles)
//        })
        newsRecycler.adapter=newsAdapter.withLoadStateHeaderAndFooter(
            header = NewsLoaderAdapter(),
            footer = NewsLoaderAdapter()
        )

        newsRecycler.layoutManager= LinearLayoutManager(activity)
        registerForContextMenu(newsRecycler)


        return view
    }

    override fun toDetailsPage(url: String) {
        val bundle =  Bundle()
        bundle.putString("url", url)
        NavHostFragment.findNavController(this).navigate(R.id.detailFragment,bundle)
    }

    override fun onContextMenuClick(
        title: String,
        author: String?,
        source: String,
        image: String,
        url: String
    ) {
        CoroutineScope(Dispatchers.IO).launch{

            newsViewModel.repository.newsDatabase.getBookmarkDAO().insertArticles(Bookmark(source,author,title,image,url))
        }


    }

    override fun onShare(url: String) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type = "text/Plain"
        intent.putExtra(Intent.EXTRA_TEXT,url)
        startActivity(Intent.createChooser(intent,"Share Link"))
    }


}