package com.searoth.template.infrastructure.ui.planets

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import com.searoth.template.R
import com.searoth.template.domain.models.data.NewsApiService
import com.searoth.template.infrastructure.common.utils.getString
import com.searoth.template.infrastructure.di.SeaRothServiceLocator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlanetsActivityViewModel(app: Application) : AndroidViewModel(app) {
    //repo

    //coroutines

    //actions

    //observables
    val showLoadingIndicator    = ObservableBoolean(false)
    val showBottomNavigation     = ObservableBoolean(true)

    //data
    private var disposable: Disposable? = null

    init {
        loadData()
    }

    fun loadData() {
        GlobalScope.launch(Dispatchers.Main) {
            showLoadingIndicator.set(true)
            disposable = SeaRothServiceLocator.resolve(NewsApiService::class.java).getNews("bbc-news", getString(R.string.news_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        showLoadingIndicator.set(false)
                    },
                    { error ->
                        showLoadingIndicator.set(false)
                        println("error")
                    }
                )
        }
    }
}