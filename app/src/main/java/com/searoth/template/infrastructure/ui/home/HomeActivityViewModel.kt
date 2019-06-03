package com.searoth.template.infrastructure.ui.home

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.searoth.template.domain.models.league.MatchSynopsis
import com.searoth.template.domain.models.league.Summoner
import com.searoth.template.infrastructure.di.SeaRothServiceLocator
import com.searoth.template.infrastructure.network.repository.MatchSynopsisSynopsisRepository
import com.searoth.template.infrastructure.network.repository.SummonerRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class HomeActivityViewModel(app: Application) : AndroidViewModel(app) {
    //repo
    private val summonerRepository = SeaRothServiceLocator.resolve(SummonerRepository::class.java)
    private val matchRepository = SeaRothServiceLocator.resolve(MatchSynopsisSynopsisRepository::class.java)
    // disposables
    lateinit var disposableSummoner: DisposableObserver<Summoner>
    lateinit var disposableMatchSynopsisList: DisposableObserver<List<MatchSynopsis>>
    private val disposables = CompositeDisposable()
    //coroutines

    //actions

    //observables
    val showLoadingIndicator    = ObservableBoolean(false)
    val showBottomNavigation     = ObservableBoolean(true)
    val summonerName = ObservableField<String>("imaqtpie")

    //data
    private lateinit var summoner: Summoner
    private lateinit var matchSynopses: List<MatchSynopsis>

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

            disposableSummoner = object : DisposableObserver<Summoner>() {
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
                .subscribe(disposableSummoner)
            }
    }

    fun searchMatchList() {
        showLoadingIndicator.set(true)
        disposableMatchSynopsisList = object : DisposableObserver<List<MatchSynopsis>>() {
            override fun onComplete() {}

            override fun onNext(t: List<MatchSynopsis>) {
                matchSynopses = t
            }

            override fun onError(e: Throwable) {

            }

        }
        matchRepository.getMatchesFromAPi(summoner.accountId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(disposableMatchSynopsisList)
    }

    fun setSummoner(){
        summonerName.set(summoner.name)
    }

    fun disposeElements(){
        try {
            if (disposableSummoner != null && !disposableSummoner.isDisposed) disposableSummoner.dispose()

        }catch(e: Exception){
            Timber.e("err")
        }
    }
}