package com.searoth.template.infrastructure.ui.youtube

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import com.searoth.template.infrastructure.common.helpers.LiveDataAction
import com.searoth.template.infrastructure.ui.base.BaseActivityViewModel

class YoutubeActivityViewModel(app: Application) : BaseActivityViewModel(app){

    //repo

    //coroutines

    //actions
    val showLogin = LiveDataAction()

    //observables
    val showLoadingIndicator    = ObservableBoolean(false)
    val showBottomNavigation     = ObservableBoolean(true)

    //data

    init {

    }

    fun loginYoutube(){

    }

}
