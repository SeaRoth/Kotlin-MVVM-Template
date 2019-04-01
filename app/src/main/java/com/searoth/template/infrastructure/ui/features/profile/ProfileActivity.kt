package com.searoth.template.infrastructure.ui.features.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.searoth.template.R
import com.searoth.template.databinding.ActivityProfileBinding
import com.searoth.template.infrastructure.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_profile.*
import timber.log.Timber

class ProfileActivity : BaseActivity() {
    private val profileActivityViewModel: ProfileActivityViewModel by lazy {
        ViewModelProviders.of(this).get(ProfileActivityViewModel::class.java)
    }

    lateinit var layoutBinding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = DataBindingUtil.setContentView<ActivityProfileBinding>(this, R.layout.activity_profile).apply {
            activityViewModel = profileActivityViewModel

        }
        setSupportActionBar(toolbar)
        setupHomeAsUp(ActionBarStyle.UP_BUTTON, statusColor = R.color.tenx_red)
        supportActionBar?.setTitle("")
    }

    override fun inflateOptionsMenu(menu: Menu){
        return menuInflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_date -> {
                Timber.d("Ok")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }
}