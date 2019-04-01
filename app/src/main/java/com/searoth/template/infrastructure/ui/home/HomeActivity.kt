package com.searoth.template.infrastructure.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.searoth.template.R
import com.searoth.template.databinding.ActivityHomeBinding
import com.searoth.template.infrastructure.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.bottom_nav.*
import kotlinx.android.synthetic.main.include_navigation_drawer.*

import timber.log.Timber

class HomeActivity : BaseActivity() {


    private val homeActivityViewModel: HomeActivityViewModel by lazy {
        ViewModelProviders.of(this).get(HomeActivityViewModel::class.java)
    }

    lateinit var layoutBinding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home).apply {
            setupBottomNav(bottomNavigationInc.bottomNavigation, R.id.action_home, R.color.blanched_almond)
            activityViewModel = homeActivityViewModel
        }
        setupNavDrawer(nav_recycler_view, toolbar, drawer_layout)
        setSupportActionBar(toolbar)
        setupHomeAsUp(ActionBarStyle.NAV_BUTTON, statusColor = R.color.tenx_red)
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
            return Intent(context, HomeActivity::class.java)
        }
    }
}