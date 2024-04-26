package com.news.newsapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.news.newsapp.utils.Constants
import com.news.newslibrary.ApiCaller
import com.news.newslibrary.NewsResponse
import com.news.newslibrary.NewsSourceResponse

class MainViewModel(context: Context): ViewModel() {

    val apiCaller = ApiCaller(Constants.NEWS_API_BASEURL)

    val liveNewsSource = MutableLiveData<NewsSourceResponse>()
    val liveNewHeadlines = MutableLiveData<NewsResponse>()


    suspend fun fetchAllNewsSource(){
        var newsSources = apiCaller.getNewsSources(Constants.API_KEY)
        liveNewsSource.postValue(newsSources)
    }

    suspend fun fetchAllNews(source: String){
        var newsHeadline = apiCaller.getNewsHeadlines(Constants.API_KEY, source)
        liveNewHeadlines.postValue(newsHeadline)
    }

}