package com.utsman.newsapp.data.repository

import com.utsman.newsapp.data.Mapper
import com.utsman.newsapp.data.network.WebServices
import com.utsman.newsapp.domain.entity.News
import com.utsman.newsapp.domain.repository.NewsRepository
import com.utsman.newsapp.event.StateEvent
import com.utsman.newsapp.utils.asFlowEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsRepositoryImpl(private val webServices: WebServices) : NewsRepository {

    private val _topHeadline: MutableStateFlow<StateEvent<List<News>>> =
        MutableStateFlow(StateEvent.Idle())

    override val topHeadline: Flow<StateEvent<List<News>>>
        get() = _topHeadline

    override suspend fun getTopHeadline() {
        val loadingEvent = StateEvent.Loading<List<News>>()
        _topHeadline.value = loadingEvent

        webServices.getTopHeadline(
            category = "technology"
        ).asFlowEvent {
            Mapper.mapResponseToNews(it)
        }.collect(_topHeadline)
    }

    override suspend fun invalidate() {
        _topHeadline.value = StateEvent.Idle()
    }
}