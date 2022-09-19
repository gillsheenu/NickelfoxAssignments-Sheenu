package com.example.nickelffoxassignments_sheenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar


class DetailFragment : Fragment() {

    lateinit var webView:WebView
    lateinit var detailProgressBar:ProgressBar
    lateinit var url:String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view=inflater.inflate(R.layout.fragment_detail, container, false)
        webView=view.findViewById(R.id.webView)
        detailProgressBar=view.findViewById(R.id.detailProgressBar)
         url= arguments?.get("url") as String
        if(url !=null){
            webView.settings.javaScriptEnabled=true
            webView.webViewClient= object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    detailProgressBar.visibility=View.GONE
                    webView.visibility=View.VISIBLE
                }

            }
            webView.loadUrl(url)
        }
        return view
    }


}