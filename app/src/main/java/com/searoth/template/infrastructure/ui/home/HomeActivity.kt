package com.searoth.template.infrastructure.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.searoth.template.infrastructure.ui.base.BaseActivity

class HomeActivity : BaseActivity() {


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}