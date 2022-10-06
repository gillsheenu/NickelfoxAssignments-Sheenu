package com.example.nickelffoxassignments_sheenu.news.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nickelffoxassignments_sheenu.R
import com.example.nickelffoxassignments_sheenu.news.data.local.Article
import com.example.nickelffoxassignments_sheenu.news.ui.adapter.NewsLoaderAdapter
import com.example.nickelffoxassignments_sheenu.news.ui.adapter.NewsPagingAdapter
import com.example.nickelffoxassignments_sheenu.news.ui.viewmodel.NewsViewModel
import com.example.nickelffoxassignments_sheenu.news.utils.RecyclerListener
import com.example.nickelffoxassignments_sheenu.news.data.local.Bookmark
import com.example.nickelffoxassignments_sheenu.news.utils.ConnectionLiveData
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.*

@ExperimentalPagingApi
@AndroidEntryPoint
class NewsHomeFragment : Fragment(), RecyclerListener {

    private lateinit var newsRecycler: RecyclerView
//    lateinit var newsAdapter: NewsAdapter
     private lateinit var newsAdapter: NewsPagingAdapter
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsHomeProgressBar:ProgressBar
    private lateinit var emptyTextView: ImageView
    var title:String=""
    var ismatched:String=""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.fragment_news_home, container, false)

        val connectionLiveData= ConnectionLiveData(requireActivity().application )

        emptyTextView=view.findViewById(R.id.emptyView)
        newsHomeProgressBar=view.findViewById(R.id.pgNewHome)
        newsRecycler=view.findViewById(R.id.NewsRoot)
        newsHomeProgressBar.visibility=View.VISIBLE
        newsRecycler.visibility=View.GONE


        newsViewModel= ViewModelProvider(this@NewsHomeFragment)[NewsViewModel::class.java]

        newsViewModel.topNews(Locale.getDefault().country).observe(viewLifecycleOwner){
            newsRecycler.visibility=View.VISIBLE
            newsAdapter.submitData(lifecycle,it)

        }

        val chipGroup=view.findViewById<ChipGroup>(R.id.chipGroup)
        chipGroup.setOnCheckedStateChangeListener { group, _ ->
            when(group.checkedChipId){
                R.id.chipForYou ->{
//                            var country=TelephonyManager.NetworkC
                          val locale=Locale.getDefault().country
                          newsViewModel.topNews(locale).observe(viewLifecycleOwner) {
                              newsAdapter.submitData(lifecycle, it)
                          }

                }
                R.id.chipTop ->{
                        newsViewModel.topSourceNews("bbc-news").observe(viewLifecycleOwner) {
                            newsAdapter.submitData(lifecycle, it)
                        }

                }
                R.id.chipWorld ->{
                        newsViewModel.topCategoryNews("general").observe(viewLifecycleOwner) {
                            newsAdapter.submitData(lifecycle, it)
                        }
//                        newsViewModel.topCategoryNews("general")
                }
                R.id.chipSports ->{
                        newsViewModel.topCategoryNews("sports").observe(viewLifecycleOwner) {
                            newsAdapter.submitData(lifecycle, it)
                        }

//                        newsViewModel.topCategoryNews("sports")
                }
                R.id.chipEntertainment ->{

                        newsViewModel.topCategoryNews("entertainment").observe(viewLifecycleOwner) {
                            newsAdapter.submitData(lifecycle, it)
                        }
//                        newsViewModel.topCategoryNews("entertainment")

                }

            }
        }
        newsViewModel.repository.newsDatabase.getBookmarkDAO().getArticlesUrl().observe(viewLifecycleOwner
        ) {
            newsAdapter.setUrlList(it)
        }

//        newsAdapter=NewsAdapter()
        newsAdapter= NewsPagingAdapter()

        newsAdapter.setListeners(this)
//        newsViewModel..observe(viewLifecycleOwner, Observer {
//            newsAdapter.submitList(it.articles)
//        })

        connectionLiveData.observe(viewLifecycleOwner) { availableInfo ->
            if (!availableInfo) {
                newsRecycler.visibility = View.GONE
                newsHomeProgressBar.visibility = View.GONE
                emptyTextView.visibility = View.VISIBLE
                Toast.makeText(activity, "NO INTERNET ", Toast.LENGTH_SHORT).show()

            } else {
                emptyTextView.visibility = View.GONE
                newsRecycler.visibility = View.VISIBLE
                newsRecycler.adapter = newsAdapter.withLoadStateHeaderAndFooter(
                    header = NewsLoaderAdapter(),
                    footer = NewsLoaderAdapter()
                )

            }

        }

        newsRecycler.layoutManager= LinearLayoutManager(activity)
        registerForContextMenu(newsRecycler)



        return view
    }

    override fun toDetailsPage(url: String) {
        val bundle =  Bundle()
        bundle.putString("url", url)
        findNavController(this).navigate(R.id.detailFragment,bundle)
    }

    override fun onContextMenuClick(title: String, author: String?, source: String, image: String,url:String) {
        CoroutineScope(Dispatchers.IO).launch{
            newsViewModel.repository.newsDatabase.getBookmarkDAO().insertArticles(Bookmark(source,author,title,image,url))
        }

    }

    override fun onContextMenuClickDelete(title: String, author: String?, source: String, image: String, url: String) {
        CoroutineScope(Dispatchers.IO).launch{
            newsViewModel.repository.newsDatabase.getBookmarkDAO().deleteArticles(Bookmark(title,author,source,image,url))
        }

    }

    override fun onShare(url: String) {
        val intent= Intent(Intent.ACTION_SEND)
        intent.type = "text/Plain"
        intent.putExtra(Intent.EXTRA_TEXT,url)
        startActivity(Intent.createChooser(intent,"Share Link"))
    }




}











