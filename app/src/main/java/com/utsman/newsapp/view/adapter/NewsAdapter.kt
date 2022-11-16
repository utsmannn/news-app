package com.utsman.newsapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.utsman.newsapp.R
import com.utsman.newsapp.databinding.ItemNewsBinding
import com.utsman.newsapp.domain.entity.News
import kotlin.properties.Delegates

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    var news: List<News> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n ->
            o.id == n.id
        }
    }

    var onItemClick: ((News) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        val binding = ItemNewsBinding.bind(view)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = news[position]
        holder.bind(item, onItemClick)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    private fun autoNotify(old: List<News>, new: List<News>, compare: (News, News) -> Boolean) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return old.size
            }

            override fun getNewListSize(): Int {
                return new.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = old[oldItemPosition]
                val newItem = new[newItemPosition]
                return compare.invoke(oldItem, newItem)
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = old[oldItemPosition]
                val newItem = new[newItemPosition]
                return compare.invoke(oldItem, newItem)
            }
        }

        val diff = DiffUtil.calculateDiff(diffCallback)
        diff.dispatchUpdatesTo(this)
    }
}