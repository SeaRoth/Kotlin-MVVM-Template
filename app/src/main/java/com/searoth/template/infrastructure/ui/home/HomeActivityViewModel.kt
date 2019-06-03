package com.searoth.template.infrastructure.ui.home

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.searoth.template.R
import com.searoth.template.domain.models.data.LeagueApiService
import com.searoth.template.domain.models.data.NewsApiService
import com.searoth.template.domain.models.league.Summoner
import com.searoth.template.infrastructure.common.utils.getString
import com.searoth.template.infrastructure.di.SeaRothServiceLocator
import com.searoth.template.infrastructure.network.repository.SummonerRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

class HomeActivityViewModel(app: Application) : AndroidViewModel(app) {
    //repo
    private val summonerRepository = SeaRothServiceLocator.resolve(SummonerRepository::class.java)
    lateinit var disposable: DisposableObserver<Summoner>
    private val disposables = CompositeDisposable()
    //coroutines

    //actions

    //observables
    val showLoadingIndicator    = ObservableBoolean(false)
    val showBottomNavigation     = ObservableBoolean(true)
    val summonerName = ObservableField<String>("imaqtpie")

    //data
    private lateinit var summoner: Summoner

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    init {

    }

    fun searchSummoner() {
        val name = summonerName.get()
        if(name != null) {
            showLoadingIndicator.set(true)

            disposable = object : DisposableObserver<Summoner>() {
                override fun onComplete() {}

                override fun onNext(t: Summoner) {
                    summoner = t
                }

                override fun onError(e: Throwable) {
                    Timber.e("error")
                }
            }

            summonerRepository.getSummonerFromApi(name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribe(disposable)


//            disposable = SeaRothServiceLocator.resolve(LeagueApiService::class.java).getSummoner(getString(R.string.summoner_by_name, name, getString(R.string.league_key)))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    { result ->
//                        summoner = result
//                        setSummoner()
//                        showLoadingIndicator.set(false)
//                    },
//                    { error ->
//                        showLoadingIndicator.set(false)
//                        println("error")
//                    }
//                )
            }
    }

    fun setSummoner(){
        summonerName.set(summoner.name)
    }

    fun disposeElements(){
        try {
            if (disposable != null && !disposable.isDisposed) disposable.dispose()

        }catch(e: Exception){
            Timber.e("err")
        }
    }
}