package com.example.nickelffoxassignments_sheenu.news.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nickelffoxassignments_sheenu.R
import com.example.nickelffoxassignments_sheenu.news.ui.adapter.NewsLoaderAdapter
import com.example.nickelffoxassignments_sheenu.news.ui.adapter.NewsPagingAdapter
import com.example.nickelffoxassignments_sheenu.news.ui.viewmodel.NewsViewModel
import com.example.nickelffoxassignments_sheenu.news.utils.RecyclerListener
import com.example.nickelffoxassignments_sheenu.news.data.local.Bookmark
import com.example.nickelffoxassignments_sheenu.news.utils.ConnectionLiveData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@AndroidEntryPoint
class NewsSearchFragment : Fragment(), RecyclerListener {

    private lateinit var newsRecycler: RecyclerView

    lateinit var newsAdapter: NewsPagingAdapter
    private lateinit var emptyTextView:ImageView
    lateinit var searchView: SearchView
    lateinit var newsViewModel: NewsViewModel
    lateinit var searchImage:ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_search, container, false)

        val connectionLiveData= ConnectionLiveData(requireActivity().applicationContext )

        emptyTextView=view.findViewById(R.id.empty_view)
        newsViewModel = ViewModelProvider(this@NewsSearchFragment)[NewsViewModel::class.java]
        searchView = view.findViewById(R.id.svNews)
        searchImage=view.findViewById(R.id.ivSearchNews)

        newsAdapter = NewsPagingAdapter()
        newsAdapter.setListeners(this)
        newsRecycler = view.findViewById(R.id.rvNewsRoot)
        newsRecycler.layoutManager = LinearLayoutManager(activity)



        connectionLiveData.observe(viewLifecycleOwner) { availableInfo ->
            if (!availableInfo) {
                newsRecycler.visibility = View.GONE
                emptyTextView.visibility = View.VISIBLE

            } else {

                emptyTextView.visibility = View.GONE
                newsRecycler.visibility = View.VISIBLE
                newsRecycler.adapter = newsAdapter.withLoadStateHeaderAndFooter(
                    header = NewsLoaderAdapter(),
                    footer = NewsLoaderAdapter()
                )
            }

        }
        registerForContextMenu(newsRecycler)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsRecycler.visibility=View.GONE
        searchImage.visibility=View.VISIBLE


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != null) {
                    searchImage.visibility=View.GONE
                    newsRecycler.visibility=View.VISIBLE
////                        newsViewModel.search(newText)
                    newsViewModel.searchNews(newText).observe(viewLifecycleOwner) {
                        newsAdapter.submitData(lifecycle, it)
                    }
                }
                return true
            }

        })
    }



    override fun toDetailsPage(url: String) {
            val bundle = Bundle()
            bundle.putString("url", url)
            NavHostFragment.findNavController(this).navigate(R.id.detailFragment, bundle)
        }

        override fun onContextMenuClick(
            title: String,
            author: String?,
            source: String,
            image: String,
            url: String
        ) {
            CoroutineScope(Dispatchers.IO).launch {

                newsViewModel.repository.newsDatabase.getBookmarkDAO()
                    .insertArticles(Bookmark(source, author, title, image, url))
            }


        }

        override fun onShare(url: String) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/Plain"
            intent.putExtra(Intent.EXTRA_TEXT, url)
            startActivity(Intent.createChooser(intent, "Share Link"))
        }

//
//        fun isConnected(): Boolean {
//            val connectivityManager =
//                activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                var activeNetwork = connectivityManager.activeNetwork ?: return false
//                var networkCapabilities =
//                    connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
//                return when {
//                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//                    else -> false
//                }
//            } else {
//                val networkInfo = connectivityManager.activeNetworkInfo ?: return false
//                return networkInfo.isConnected
//            }
//        }

}

