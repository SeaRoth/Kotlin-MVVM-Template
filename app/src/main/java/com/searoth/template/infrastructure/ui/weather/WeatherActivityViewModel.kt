package com.searoth.template.infrastructure.ui.weather

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel

class WeatherActivityViewModel(app: Application) : AndroidViewModel(app) {
    //repo

    //coroutines

    //actions

    //observables
    val showLoadingIndicator    = ObservableBoolean(false)
    val showBottomNavigation     = ObservableBoolean(true)

    //data

    init {

    }
}