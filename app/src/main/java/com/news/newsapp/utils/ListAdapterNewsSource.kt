package com.news.newsapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.news.newsapp.R
import com.news.newslibrary.NewsSource

class ListAdapterNewsSource(private val data: List<NewsSource>,val clickableItem: ClickableItem) : RecyclerView.Adapter<ListAdapterNewsSource.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_news_source, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleSourceTextView.text = "Name: ${data[position].name}"
        holder.categoryTextView.text = "Category: ${data[position].category}"
        holder.layout.setOnClickListener {
            clickableItem.onItemClicked(position)
        }
    }

    override fun getItemCount(): Int = data.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleSourceTextView: TextView = itemView.findViewById(R.id.tv_source_title)
        val categoryTextView: TextView = itemView.findViewById(R.id.tv_category)
        val layout: LinearLayout = itemView.findViewById(R.id.source_layout_item)
    }
}
