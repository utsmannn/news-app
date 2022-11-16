package com.utsman.newsapp.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.utsman.newsapp.databinding.ActivityMainBinding
import com.utsman.newsapp.event.StateEvent
import com.utsman.newsapp.utils.onSuccess
import com.utsman.newsapp.view.adapter.NewsAdapter
import com.utsman.newsapp.view.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: NewsViewModel by viewModel()
    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        newsAdapter.onItemClick = { news ->
            Toast.makeText(this, news.title, Toast.LENGTH_SHORT).show()
        }

        viewModel.getTopHeadline()
        viewModel.topHeadline.observe(this) { newsEvent ->
            binding.progressCircular.isVisible = newsEvent is StateEvent.Loading

            newsEvent.onSuccess {
                newsAdapter.news = it
            }
        }
    }

    private fun setupView() = binding.apply {
        recyclerViewNews.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerViewNews.adapter = newsAdapter
    }
}