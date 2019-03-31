package com.searoth.template.infrastructure.ui.weather

import android.content.Context
import android.content.Intent
import com.searoth.template.infrastructure.ui.base.BaseActivity
import com.searoth.template.infrastructure.ui.home.HomeActivity

class WeatherActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, WeatherActivity::class.java)
        }
    }
}