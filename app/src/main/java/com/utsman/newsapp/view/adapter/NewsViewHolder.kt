package com.utsman.newsapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.utsman.newsapp.databinding.ItemNewsBinding
import com.utsman.newsapp.domain.entity.News

class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News, onItemClick: ((News) -> Unit)?) = binding.run {
        textItemNewsTitle.text = news.title
        textItemNewsDesc.text = news.description

        Glide.with(itemView.context)
            .load(news.imageUrl)
            .centerCrop()
            .into(imageItemNews)

        root.setOnClickListener {
            onItemClick?.invoke(news)
        }
    }
}