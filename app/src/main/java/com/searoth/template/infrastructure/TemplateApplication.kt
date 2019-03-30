package com.searoth.template.infrastructure

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.searoth.template.infrastructure.di.ServiceInitializer
import timber.log.Timber

class TemplateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AndroidThreeTen.init(this)
        Stetho.initializeWithDefaults(this)
        ServiceInitializer.initServices(this)
    }
}