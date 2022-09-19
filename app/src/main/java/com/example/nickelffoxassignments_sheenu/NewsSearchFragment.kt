package com.example.nickelffoxassignments_sheenu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nickelffoxassignments_sheenu.news.paging.NewsLoaderAdapter
import com.example.nickelffoxassignments_sheenu.news.adapter.NewsPagingAdapter
import com.example.nickelffoxassignments_sheenu.news.NewsViewModel
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@ExperimentalPagingApi
@AndroidEntryPoint
class NewsSearchFragment : Fragment() {

    lateinit var newsRecycler: RecyclerView
//    lateinit var newsadapter: NewsAdapter
     lateinit var newsadapter: NewsPagingAdapter
    lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.fragment_news_search, container, false)
        newsViewModel= ViewModelProvider(this@NewsSearchFragment)[NewsViewModel::class.java]

        val chipGroup=view.findViewById<ChipGroup>(R.id.chipGroup)
        chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            when(group.checkedChipId){
                R.id.chipForYou->{
//                            var country=TelephonyManager.NetworkC
                          var locale=Locale.getDefault().country
                        Log.d("COUNR", "onCreateView: $locale")
                          newsViewModel.TopNews(locale).observe(viewLifecycleOwner, Observer {
                              newsadapter.submitData(lifecycle,it)
                          })

                     }
                R.id.chipTop->{
                        newsViewModel.TopSourceNews("bbc-news").observe(viewLifecycleOwner, Observer {
                            newsadapter.submitData(lifecycle,it)
                        })

                }
                R.id.chipWorld->{
                        newsViewModel.TopCategoryNews("general").observe(viewLifecycleOwner, Observer {
                            newsadapter.submitData(lifecycle,it)
                        })
//                        newsViewModel.TopCategoryNews("general")
                }
                R.id.chipSports->{
                        newsViewModel.TopCategoryNews("sports").observe(viewLifecycleOwner, Observer {
                            newsadapter.submitData(lifecycle,it)
                        })

//                        newsViewModel.TopCategoryNews("sports")
                }
                R.id.chipEntertainment->{

                        newsViewModel.TopCategoryNews("entertainment").observe(viewLifecycleOwner, Observer {
                            newsadapter.submitData(lifecycle,it)
                        })
//                        newsViewModel.TopCategoryNews("entertainment")

                }

            }
        }

//        newsadapter=NewsAdapter()
        newsadapter= NewsPagingAdapter()
        newsRecycler=view.findViewById(R.id.NewsRoot)
//        newsViewModel..observe(viewLifecycleOwner, Observer {
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