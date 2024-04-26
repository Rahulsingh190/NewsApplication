package com.news.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.news.newsapp.databinding.ActivityMainBinding
import com.news.newsapp.utils.ClickableItem
import com.news.newsapp.viewmodel.MainViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.newsapp.utils.ListAdapterNewsSource
import com.news.newslibrary.NewsSource
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ClickableItem {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel : MainViewModel
    lateinit var newsSourceList : List<NewsSource>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = MainViewModel(this)
        lifecycleScope.launch {
            mainViewModel.fetchAllNewsSource()
        }

        setUpObserver()
    }

    fun setUpObserver(){
        mainViewModel.liveNewsSource.observe(this){
            newsSourceList = it.sources
            val adapter = ListAdapterNewsSource(newsSourceList, this)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = adapter
        }
    }



    override fun onItemClicked(position: Int) {
        val intent = Intent(this, HeadlineActivity::class.java)
        intent.putExtra("SOURCE", newsSourceList[position].id)
        startActivity(intent)
    }
}