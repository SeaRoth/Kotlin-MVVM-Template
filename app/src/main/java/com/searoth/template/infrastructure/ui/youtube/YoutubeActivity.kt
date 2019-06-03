package com.searoth.template.infrastructure.ui.youtube

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.searoth.template.R
import com.searoth.template.databinding.ActivityNotificationBinding
import com.searoth.template.infrastructure.ui.base.BaseActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.include_navigation_drawer.*
import timber.log.Timber

class YoutubeActivity : BaseActivity() {
    private val youtubeActivityViewModel: YoutubeActivityViewModel by lazy {
        ViewModelProviders.of(this).get(YoutubeActivityViewModel::class.java)
    }

    private val listsGroup = Section()
    private val groupAdapter = GroupAdapter<ViewHolder>()
    private val masterGroup = Section()

    lateinit var layoutBinding: ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = DataBindingUtil.setContentView<ActivityNotificationBinding>(this, R.layout.activity_notification).apply {
            setupBottomNav(bottomNavigationInc.bottomNavigation, R.id.action_notifications, R.color.blanched_almond)
            activityViewModel = youtubeActivityViewModel
        }

        youtubeActivityViewModel.showLogin.observe(this){
            masterGroup.setPlaceholder(LoginYoutubeItem(youtubeActivityViewModel))
        }

        setupNavDrawer(nav_recycler_view, toolbar, drawer_layout)
        setSupportActionBar(toolbar)
        setupHomeAsUp(ActionBarStyle.NAV_BUTTON, statusColor = R.color.tenx_red)
        supportActionBar?.setTitle("")

        setupAdapter()
    }

    private fun setupAdapter() {
        rv_youtube.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_youtube.adapter = groupAdapter
        groupAdapter.add(masterGroup)
    }

    override fun inflateOptionsMenu(menu: Menu) {
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
            return Intent(context, YoutubeActivity::class.java)
        }
    }
}