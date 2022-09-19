package com.example.nickelffoxassignments_sheenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nickelffoxassignments_sheenu.news.paging.NewsLoaderAdapter
import com.example.nickelffoxassignments_sheenu.news.adapter.NewsPagingAdapter
import com.example.nickelffoxassignments_sheenu.news.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class NewsHomeFragment : Fragment() {

    lateinit var newsRecycler: RecyclerView
//    lateinit var newsadapter: NewsAdapter
    lateinit var newsadapter: NewsPagingAdapter

    lateinit var searchView: SearchView
    lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view=inflater.inflate(R.layout.fragment_news_home, container, false)

        searchView=view.findViewById(R.id.svNews)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override  fun onQueryTextChange(newText: String?): Boolean {

                    if (newText != null) {
//                        newsViewModel.search(newText)
                        newsViewModel.SearchNews(newText).observe(viewLifecycleOwner, Observer {
                            newsadapter.submitData(lifecycle,it)
                        })

                    }


                return true
            }

        })

//        newsadapter=NewsAdapter()
        newsadapter= NewsPagingAdapter()
        newsRecycler=view.findViewById(R.id.NewsRoot)
        newsViewModel= ViewModelProvider(this@NewsHomeFragment)[NewsViewModel::class.java]
//        newsViewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
//            newsadapter.submitList(it.articles)
//        })
        newsRecycler.adapter=newsadapter.withLoadStateHeaderAndFooter(
            header = NewsLoaderAdapter(),
            footer = NewsLoaderAdapter()
        )
        newsRecycler.layoutManager= LinearLayoutManager(activity)


        return view
    }


}