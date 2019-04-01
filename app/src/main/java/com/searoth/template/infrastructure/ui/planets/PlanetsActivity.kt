package com.searoth.template.infrastructure.ui.planets

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.searoth.template.R
import com.searoth.template.databinding.ActivityPlanetsBinding
import com.searoth.template.infrastructure.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_planets.*
import kotlinx.android.synthetic.main.bottom_nav.*
import kotlinx.android.synthetic.main.include_navigation_drawer.*
import timber.log.Timber

class PlanetsActivity : BaseActivity() {
    private val planetsActivityViewModel: PlanetsActivityViewModel by lazy {
        ViewModelProviders.of(this).get(PlanetsActivityViewModel::class.java)
    }

    lateinit var layoutBinding: ActivityPlanetsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = DataBindingUtil.setContentView<ActivityPlanetsBinding>(this, R.layout.activity_planets).apply {
            setupBottomNav(bottomNavigationInc.bottomNavigation, R.id.action_planets, R.color.blanched_almond)
            activityViewModel = planetsActivityViewModel
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
            return Intent(context, PlanetsActivity::class.java)
        }
    }
}