package com.searoth.template.infrastructure.ui.articles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.searoth.template.R
import com.searoth.template.databinding.ActivityPlanetsBinding
import com.searoth.template.infrastructure.ui.base.BaseActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_planets.*
import kotlinx.android.synthetic.main.include_navigation_drawer.*
import timber.log.Timber

class ArticlesActivity : BaseActivity() {
    private val articlesActivityViewModel: ArticlesActivityViewModel by lazy {
        ViewModelProviders.of(this).get(ArticlesActivityViewModel::class.java)
    }

    lateinit var layoutBinding: ActivityPlanetsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = DataBindingUtil.setContentView<ActivityPlanetsBinding>(this, R.layout.activity_planets).apply {
            setupBottomNav(bottomNavigationInc.bottomNavigation, R.id.action_planets, R.color.blanched_almond)
            activityViewModel = articlesActivityViewModel
        }
        setupNavDrawer(nav_recycler_view, toolbar, drawer_layout)
        setSupportActionBar(toolbar)
        setupHomeAsUp(ActionBarStyle.NAV_BUTTON, statusColor = R.color.tenx_red)
        supportActionBar?.setTitle("")

        setupAdapter()
    }

    private fun setupAdapter(){
        val adapterPlanner = GroupAdapter<ViewHolder>()
        rv_articles.layoutManager = LinearLayoutManager(this@ArticlesActivity)
        rv_articles.itemAnimator = DefaultItemAnimator()
        rv_articles.adapter = adapterPlanner
        adapterPlanner.add(articlesActivityViewModel.mainGroup)
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
            return Intent(context, ArticlesActivity::class.java)
        }
    }
}