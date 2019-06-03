package com.searoth.template.infrastructure.ui.base

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import com.searoth.template.infrastructure.common.helpers.LiveDataActionWithData

open class BaseActivityViewModel(app: Application) : AndroidViewModel(app) {

    val showProgressBar = ObservableBoolean(false)
    val disableBackButton = LiveDataActionWithData<Boolean>()

    fun showProgress(showProgress: Boolean, disableBack: Boolean) {
        showProgressBar.set(showProgress)
        disableBackButton.actionOccurred(disableBack)
    }
}