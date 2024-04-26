package com.news.newsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.news.newsapp.databinding.ActivityMainBinding
import com.news.newsapp.utils.ClickableItem
import com.news.newsapp.viewmodel.MainViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.newsapp.databinding.ActivityHeadlineBinding
import com.news.newsapp.utils.ListAdapterNewsArticle
import com.news.newsapp.utils.ListAdapterNewsSource
import com.news.newslibrary.Article
import com.news.newslibrary.NewsSource
import kotlinx.coroutines.launch

class HeadlineActivity : AppCompatActivity(), ClickableItem {
    private lateinit var binding: ActivityHeadlineBinding
    lateinit var mainViewModel : MainViewModel
    lateinit var articleList : List<Article>
    var source : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeadlineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        source = intent.getStringExtra("SOURCE")

        mainViewModel = MainViewModel(this)
        lifecycleScope.launch {
            source?.let {
                mainViewModel.fetchAllNews(it)
            }
        }

        setUpObserver()
    }

    fun setUpObserver(){
        mainViewModel.liveNewHeadlines.observe(this){
            articleList = it.articles
            val adapter = ListAdapterNewsArticle(articleList, this)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = adapter
        }
    }



    override fun onItemClicked(position: Int) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(articleList[position].url))
        startActivity(intent)
    }
}