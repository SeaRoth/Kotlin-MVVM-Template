package com.searoth.planner.infrastructure.ui.base

import android.app.Application
import android.os.Handler
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.searoth.planner.R
import com.searoth.planner.domain.models.NavHeaderTopItem
import com.searoth.planner.infrastructure.common.helpers.LiveDataAction
import com.searoth.planner.infrastructure.common.helpers.LiveDataActionWithData
import com.searoth.planner.infrastructure.common.helpers.getDrawablePath
import com.searoth.planner.infrastructure.common.utils.getString

class NavHeaderViewModel(app: Application) : AndroidViewModel(app) {

    // Observables
    val text        = ObservableField<String>("10X or go home")
    val imageUrl    = ObservableField<String>(R.drawable.growth_con.getDrawablePath())
    val link        = ObservableField<String>()
    val drawerOpen  = ObservableBoolean(false)

    // Actions
    val actionOpenYoutubeIntent = LiveDataActionWithData<String>()
    val actionAnimate           = LiveDataAction()

    // Data
    var currentIndex = 0
    var handler = Handler()
    val navHeaderTopItems= mutableListOf<NavHeaderTopItem>()
    val navHeaderTopItemsFresh = mutableListOf<NavHeaderTopItem>(
        NavHeaderTopItem(text = "The G&E Show", imgUrl = R.drawable.ge_show.getDrawablePath(),
            link = getString(R.string.link_playlist_ge_show)),
        NavHeaderTopItem(text = "The Cardone Zone", imgUrl = R.drawable.cardone_zone.getDrawablePath(),
            link = getString(R.string.link_playlist_cardone_zone)),
        NavHeaderTopItem(text = "Young Hustlers", imgUrl = R.drawable.young_hustlers.getDrawablePath(),
            link = getString(R.string.link_playlist_young_hustlers)),
        NavHeaderTopItem(text = "Real Estate Investing Made Simple", imgUrl = R.drawable.real_estate_investing.getDrawablePath(),
            link = getString(R.string.link_playlist_real_estate)),
        NavHeaderTopItem(text = "Power Players", imgUrl = R.drawable.power_players.getDrawablePath(),
            link = getString(R.string.link_playlist_power_players)),
        NavHeaderTopItem(text = "How to invest millions in real estate", imgUrl = R.drawable.buying_big.getDrawablePath(),
            link = getString(R.string.link_playlist_invest_millions)),
        NavHeaderTopItem(text = "Best of Grant Cardone", imgUrl = R.drawable.how_to_change.getDrawablePath(),
            link = getString(R.string.link_playlist_best_of)),
        NavHeaderTopItem(text = "10x Business Boot Camp", imgUrl = R.drawable.boot_camp.getDrawablePath(),
            link = getString(R.string.link_playlist_boot_camp)),
        NavHeaderTopItem(text = "10X Growth Con", imgUrl = R.drawable.growth_con.getDrawablePath(),
            link = getString(R.string.link_playlist_growth_con)),
        NavHeaderTopItem(text = "10X Growth Con", imgUrl = R.drawable.growth_con_3.getDrawablePath(),
            link = getString(R.string.link_playlist_growth_con))
    )

    private val runnableCode = object : Runnable {
        override fun run() {
            if(drawerOpen.get()) {
                currentIndex++
                setPhoto()
                handler.postDelayed(this, 10000)
            }
        }
    }

    init {
        navHeaderTopItems.addAll(navHeaderTopItemsFresh)
    }

    fun openIntent() {
        actionOpenYoutubeIntent.actionOccurred(link.get())
    }

    fun clickedMoveRight() {
        currentIndex++
        if(currentIndex > 8)
            currentIndex = 0

    }

    fun clickedMoveLeft() {
        currentIndex--
        if(currentIndex < 0)
            currentIndex = 8
    }

    fun startTimer() {
        drawerOpen.set(true)
        handler.post(runnableCode)
    }

    private fun setPhoto() {
        val item = navHeaderTopItems[currentIndex]
        text.set(item.text)
        imageUrl.set(item.imgUrl)
        link.set(item.link)
        actionAnimate.actionOccurred()
    }

    fun stopTimer() {
        drawerOpen.set(false)
    }
}

