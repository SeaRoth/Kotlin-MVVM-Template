package com.searoth.template.infrastructure.ui.features.profile

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel

class ProfileActivityViewModel(app: Application) : AndroidViewModel(app) {
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