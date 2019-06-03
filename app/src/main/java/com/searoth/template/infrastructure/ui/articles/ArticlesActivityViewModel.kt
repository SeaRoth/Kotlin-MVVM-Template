package com.searoth.template.infrastructure.ui.articles

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import com.searoth.template.R
import com.searoth.template.domain.models.news.BBCTopHeadline
import com.searoth.template.domain.models.data.NewsApiService
import com.searoth.template.infrastructure.common.utils.getString
import com.searoth.template.infrastructure.di.SeaRothServiceLocator
import com.xwray.groupie.Section
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArticlesActivityViewModel(app: Application) : AndroidViewModel(app) {
    // Repo

    // Coroutines

    // Actions

    // Observables
    val showLoadingIndicator    = ObservableBoolean(false)
    val showBottomNavigation     = ObservableBoolean(true)

    // Data
    private var disposable: Disposable? = null
    private val articles = mutableListOf<ArticleViewModel>()
    lateinit var topHeadlines: BBCTopHeadline

    // Groupie
    val mainGroup = Section()

    init {
        loadData()
    }

    private fun loadData() {
        GlobalScope.launch(Dispatchers.Main) {
            showLoadingIndicator.set(true)
            disposable = SeaRothServiceLocator.resolve(NewsApiService::class.java).getNews("bbc-news", getString(R.string.news_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        topHeadlines = result
                        setArticles()
                        showLoadingIndicator.set(false)
                    },
                    { error ->
                        showLoadingIndicator.set(false)
                        println("error")
                    }
                )
        }
    }
    private fun setArticles() {
        topHeadlines.articles.forEach {
            mainGroup.add(ArticleViewModel(it, this))
        }
    }
}