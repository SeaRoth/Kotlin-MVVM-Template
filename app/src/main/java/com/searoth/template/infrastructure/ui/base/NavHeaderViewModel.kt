package com.searoth.template.infrastructure.ui.base

import android.app.Application
import android.os.Handler
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.searoth.template.R
import com.searoth.template.domain.models.NavHeaderTopItem
import com.searoth.template.infrastructure.common.helpers.LiveDataAction
import com.searoth.template.infrastructure.common.helpers.LiveDataActionWithData
import com.searoth.template.infrastructure.common.helpers.getDrawablePath
import com.searoth.template.infrastructure.common.utils.getString

class NavHeaderViewModel(app: Application) : AndroidViewModel(app) {

    // Observables
    val text        = ObservableField<String>("10X or go home")
    val imageUrl    = ObservableField<String>(R.drawable.ic_404.getDrawablePath())
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
        NavHeaderTopItem(text = "The G&E Show", imgUrl = R.drawable.ic_404.getDrawablePath(),
            link = getString(R.string.nav_planets)),
        NavHeaderTopItem(text = "The Cardone Zone", imgUrl = R.drawable.ic_404.getDrawablePath(),
            link = getString(R.string.nav_home))
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

