package com.searoth.template.infrastructure.ui.planets

import android.content.Context
import android.content.Intent
import com.searoth.template.infrastructure.ui.base.BaseActivity
import com.searoth.template.infrastructure.ui.home.HomeActivity

class PlanetsActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, PlanetsActivity::class.java)
        }
    }
}