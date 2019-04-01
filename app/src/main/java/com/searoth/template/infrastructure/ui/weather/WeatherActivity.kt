package com.searoth.template.infrastructure.ui.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.searoth.template.R
import com.searoth.template.databinding.ActivityWeatherBinding
import com.searoth.template.infrastructure.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.bottom_nav.*
import kotlinx.android.synthetic.main.include_navigation_drawer.*
import timber.log.Timber

class WeatherActivity : BaseActivity() {
    private val weatherActivityViewModel: WeatherActivityViewModel by lazy {
        ViewModelProviders.of(this).get(WeatherActivityViewModel::class.java)
    }

    lateinit var layoutBinding: ActivityWeatherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = DataBindingUtil.setContentView<ActivityWeatherBinding>(this, R.layout.activity_weather).apply {
            setupBottomNav(bottomNavigationInc.bottomNavigation, R.id.action_weather, R.color.blanched_almond)
            activityViewModel = weatherActivityViewModel
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
            return Intent(context, WeatherActivity::class.java)
        }
    }
}