package com.searoth.template.infrastructure.ui.features.profile

import android.content.Context
import android.content.Intent
import com.searoth.template.infrastructure.ui.base.BaseActivity


class ProfileActivity : BaseActivity() {
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }
}