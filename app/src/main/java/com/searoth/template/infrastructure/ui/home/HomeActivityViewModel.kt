package com.searoth.template.infrastructure.ui.home

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.searoth.template.domain.models.league.Match
import com.searoth.template.domain.models.league.MatchListResponse
import com.searoth.template.domain.models.league.MatchSynopsis
import com.searoth.template.domain.models.league.Summoner
import com.searoth.template.infrastructure.di.SeaRothServiceLocator
import com.searoth.template.infrastructure.network.repository.MatchRepository
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
    private val synopsisRepository = SeaRothServiceLocator.resolve(MatchSynopsisSynopsisRepository::class.java)
    private val matchRepository    = SeaRothServiceLocator.resolve(MatchRepository::class.java)
    // disposables
    lateinit var disposableSummoner: DisposableObserver<Summoner>
    lateinit var disposableMatchSynopsisList: DisposableObserver<MatchListResponse>
    lateinit var disposableMatches : DisposableObserver<Match>
    private val disposables = CompositeDisposable()
    //coroutines

    //actions

    //observables
    val showLoadingIndicator    = ObservableBoolean(false)
    val showBottomNavigation     = ObservableBoolean(true)
    val summonerName = ObservableField<String>("imaqtpie")
    val numberOfGames = ObservableField<String>()

    //data
    private lateinit var summoner: Summoner
    private lateinit var matchSynopses: List<MatchSynopsis>
    private val matches = mutableListOf<Match>()

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
                    searchMatchList()
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
        disposableMatchSynopsisList = object : DisposableObserver<MatchListResponse>() {
            override fun onComplete() {}

            override fun onNext(t: MatchListResponse) {
                matchSynopses = t.matches
                buildMatchListUI()
            }

            override fun onError(e: Throwable) {
                Timber.e("error")
            }

        }
        synopsisRepository.getMatchesFromAPi(summoner.accountId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(disposableMatchSynopsisList)
    }

    fun buildMatchListUI(){
        val queueMap = mutableMapOf<Int, Int>()
        val roleMap  = mutableMapOf<String, Int>()

        numberOfGames.set("${matchSynopses.size} games")

        matchSynopses.forEach {
            //favorite queue
            val q = it.queue
            if(queueMap.containsKey(q)) {
                val num = queueMap.getValue(q)
                queueMap[q] = num + 1
            }else
                queueMap[q] = 1

            //favorite role
            val r = it.role
            if(roleMap.containsKey(r)){
                val num = roleMap.getValue(r)
                roleMap[r] = num + 1
            }else
                roleMap[r] = 1
        }
        Timber.d("sdf")
    }

    fun getMatchInformation(){
        showLoadingIndicator.set(true)
        disposableMatches = object : DisposableObserver<Match>() {
            override fun onComplete() {}

            override fun onNext(t: Match) {
                matches.add(t)
            }

            override fun onError(e: Throwable) {
                Timber.d("error")
            }
        }
        matchRepository.getMatchFromApi("3055960811")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(disposableMatches)
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

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}