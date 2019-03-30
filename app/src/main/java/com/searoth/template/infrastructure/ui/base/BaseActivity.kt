package com.searoth.template.infrastructure.ui.base

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.ViewAnimator
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.searoth.planner.infrastructure.ui.base.NavHeaderViewModel
import com.searoth.template.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.ViewHolder

import timber.log.Timber

abstract class BaseActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    DrawerLayout.DrawerListener {

    lateinit var drawerToggle: ActionBarDrawerToggle

    inline fun <reified T : Activity> createNavIntent(): Intent =
        Intent(this, T::class.java).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)

    private val navHeaderViewModel: NavHeaderViewModel by lazy {
        ViewModelProviders.of(this).get(NavHeaderViewModel::class.java)
    }

    final override fun onCreateOptionsMenu(menu: Menu): Boolean {
        inflateOptionsMenu(menu)
        return true
    }

    /***
     * To change from the full menu override this
     */
    open fun inflateOptionsMenu(menu: Menu) {
        return menuInflater.inflate(R.menu.menu_planner, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_date -> {
                Timber.d("wtf"); return true
            }
            android.R.id.home -> {
                onBackPressed()
                finish(); return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == selectedItem) {
            return true
        }
        when (item.itemId) {
            R.id.action_planner -> {
                startActivity(createNavIntent<PlannerActivity>())
            }
            R.id.action_network -> {
                startActivity(createNavIntent<NetworkActivity>())
            }
            R.id.action_goals -> {
                startActivity(createNavIntent<GoalsListActivity>())
            }
            R.id.action_work -> {
                startActivity(createNavIntent<WorkActivity>())
            }
        }
        return false
    }

    private fun figureHowToSlideActivityLeftOrRight() {
        if(selectedItem == R.id.action_network){

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navHeaderViewModel.actionOpenYoutubeIntent.observe(this) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }

        navHeaderViewModel.actionAnimate.observe(this) {
            ViewAnimator
                .animate(iv_nav_header, tv_nav_title)
                .dp().translationX(-200f, 0f)
                .duration(500)
                .start()

//            val matrix = iv_nav_header.imageMatrix
//            val imageW = iv_nav_header.drawable.intrinsicWidth.toFloat()
//            val screenW = resources.displayMetrics.widthPixels.toFloat()
//            val ratio = screenW.div(imageW)
//                //(screenW / imageW).toFloat()
//            matrix.postScale(ratio, ratio)
//            iv_nav_header.imageMatrix = matrix
        }
    }

    private var selectedItem = R.id.action_planner
    fun setupBottomNav(bn: BottomNavigationView, selected: Int, iconColor: Int) {
        bn.setOnNavigationItemSelectedListener(this)
        bn.itemIconTintList = ColorStateList.valueOf(ContextCompat.getColor(this, iconColor))
        bn.itemTextAppearanceInactive = R.style.NavTextColorInActive
        when (selected) {
            R.id.action_planner -> bn.itemTextAppearanceActive = R.style.NavTextColorActivePlanner
            R.id.action_network -> bn.itemTextAppearanceActive = R.style.NavTextColorActiveCalendar
            R.id.action_work -> bn.itemTextAppearanceActive = R.style.NavTextColorActiveWork
            R.id.action_goals -> bn.itemTextAppearanceActive = R.style.NavTextColorActiveGoals
        }
        // this resets the  other icons to black, hacky but only way
        bn.itemIconTintList = null
        bn.itemTextColor = null

        selectedItem = selected
        bn.selectedItemId = selected
    }

    enum class ActionBarStyle {
        NO_BUTTON, UP_BUTTON, UP_BUTTON_ACTUAL, NAV_BUTTON, CLOSE_BUTTON
    }

    fun setupHomeAsUp(actionBarStyle: ActionBarStyle, iconOverride: Int? = null, statusColor: Int? = null) {
        supportActionBar?.let {
            when (actionBarStyle) {
                ActionBarStyle.UP_BUTTON -> {
                    it.setDisplayHomeAsUpEnabled(true)
                    it.setHomeAsUpIndicator(iconOverride ?: R.drawable.ic_back_white_normal)
                }
                ActionBarStyle.UP_BUTTON_ACTUAL -> {
                    it.setDisplayHomeAsUpEnabled(true)
                    it.setHomeAsUpIndicator(iconOverride ?: R.drawable.ic_arrow_upward_black)
                }
                ActionBarStyle.CLOSE_BUTTON -> {
                    it.setDisplayHomeAsUpEnabled(true)
                    it.setHomeAsUpIndicator(iconOverride ?: R.drawable.ic_close_white_normal)
                }
                ActionBarStyle.NAV_BUTTON -> {
                    it.setDisplayHomeAsUpEnabled(true)
                    it.setHomeAsUpIndicator(iconOverride ?: R.drawable.ic_menu_normal)
                }
                ActionBarStyle.NO_BUTTON -> {
                    it.setDisplayHomeAsUpEnabled(false)
                    // If we use this, need to scale the margin
                }

            }
        }
        changeStatusBarColor(statusColor)
    }

    fun saveUpdateView(view: TextView, label: String) {
        view.text = label
        view.visibility = View.VISIBLE
        val animPos = dpToPx(21).toFloat()
        val animationMs: Long = 800
        val startDelay: Long = 1000
        val propertyPositionForward = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, animPos)
        val propertyAlphaForward = PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f)

        val forward = ObjectAnimator.ofPropertyValuesHolder(view, propertyPositionForward, propertyAlphaForward)
        forward.duration = animationMs
        forward.start()
        forward.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                animation?.removeListener(this)
                val propertyPositionBack = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, -animPos)
                val propertyAlphaBack = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0f)
                val backward = ObjectAnimator.ofPropertyValuesHolder(view, propertyPositionBack, propertyAlphaBack)
                backward.duration = animationMs
                backward.startDelay = startDelay
                backward.start()
                backward.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        animation?.removeListener(this)
                        view.visibility = View.GONE
                    }
                })
            }
        })
    }

    private val groupAdapter = GroupAdapter<ViewHolder>()
    fun setupNavDrawer(rv: RecyclerView, toolbar: Toolbar, drawerLayout: DrawerLayout) {
        drawerLayout.addDrawerListener(this)
        setSupportActionBar(toolbar)
        drawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.common_open_on_phone,
            R.string.abc_action_bar_home_description
        )
        groupAdapter.apply {
            setOnItemClickListener(onItemClickListener)
            withNewSection {
                add(NavHeader(navHeaderViewModel))
            }
            withNewSection {
                add(
                    NavItem(
                        label = R.string.nav_planner,
                        newIntent = PlannerActivity.Companion::newIntent,
                        iconResource = R.drawable.ic_planner_black_two
                    )
                )
                add(
                    NavItem(
                        label = R.string.nav_network,
                        newIntent = NetworkActivity.Companion::newIntent,
                        iconResource = R.drawable.ic_network_black
                    )
                )
                //<div>Icons made by <a href="https://www.flaticon.com/authors/surang" title="surang">surang</a> from <a href="https://www.flaticon.com/" 			    title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" 			    title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
                add(
                    NavItem(
                        label = R.string.nav_goals,
                        newIntent = GoalsListActivity.Companion::newIntent,
                        iconResource = R.drawable.ic_goals_black
                    )
                )
                add(
                    NavItem(
                        label = R.string.nav_work,
                        newIntent = WorkActivity.Companion::newIntent,
                        iconResource = R.drawable.ic_work_black
                    )
                )
            }
            withNewSection {
                add(
                    ProfileNavItem(
                        label = R.string.nav_profile,
                        newIntent = ProfileActivity.Companion::newIntent,
                        divider = true
                    )
                )
                add(
                    NavItem(
                        label = R.string.nav_grant_website,
                        newIntent = PlannerActivity.Companion::launchNewsLink
                    )
                )
                add(
                    NavItem(
                        label = R.string.settings,
                        newIntent = SettingsActivity.Companion::newIntent,
                        iconResource = R.drawable.ic_settings
                    )
                )
            }
        }
        rv.layoutManager = LinearLayoutManager(applicationContext)
        rv.itemAnimator = DefaultItemAnimator()
        rv.adapter = groupAdapter
    }

    fun showSnackBar(str: String) {
        val rootView = this.window.decorView.findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, str, Snackbar.LENGTH_LONG)
//            .setAction(R.string.settings) {
//                startActivityForResult(Intent(android.provider.Settings.ACTION_SETTINGS), 0)
//            }
            .show()
    }

    fun showSnackBarWithActionCallBack(str: String, action: String, func: () -> Unit) {
        val rootView = this.window.decorView.findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, str, Snackbar.LENGTH_LONG)
            .setAction(action) {
                func()
            }
            .show()
    }

    fun showApiError(result: String) {
        val rootView = this.window.decorView.findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, result, Snackbar.LENGTH_LONG).show()

        GCServiceLocator.resolve(CrashlyticsLogger::class.java).logException(Exception(result))
    }


    private fun showSignoutDialog(item: SignOutItem) {
        AlertDialog.Builder(this).apply {
            setMessage(getString(R.string.signout_confirm))
            setNegativeButton(R.string.cancel, null)
            setPositiveButton(R.string.nav_logout, { dialog, id -> signout(item) })
            show()
        }
    }

    private fun signout(item: SignOutItem) {
        item.lambda()
        finishAffinity()
        startActivity(item.newIntent(this))
    }

    fun changeStatusBarColor(color: Int?) {
        color?.let {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, color)
        }
    }

    private val onItemClickListener = OnItemClickListener { item, view ->
        if (item is SignOutItem) {
            showSignoutDialog(item)
        } else if (item is NavItem) {
            view.context.startActivity(item.newIntent(this))
            Bungee.slideRight(this)
        } else if (item is ProfileNavItem) {
            view.context.startActivity(item.newIntent(this, selectedItem))
            Bungee.slideRight(this)
        } else if (item is LambaNavItem) {
            item.lambda()
        }
    }

    private fun appVersion(): String {
        return packageManager.getPackageInfo(packageName, 0).versionName
    }

    private fun doBounceAnimation(targetView: View) {
        targetView.visibility = View.VISIBLE
        val animPos = dpToPx(21).toFloat()
        val animationMs: Long = 800
        val propertyPositionForward = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, animPos)
        val propertyAlphaForward = PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f)

        val forward = ObjectAnimator.ofPropertyValuesHolder(targetView, propertyPositionForward, propertyAlphaForward)
        forward.interpolator = EasingInterpolator(Ease.ELASTIC_IN_OUT)
        forward.duration = animationMs
        forward.start()
        forward.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                animation?.removeListener(this)
            }
        })
    }

    override fun onDrawerStateChanged(newState: Int) {

    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

    }

    override fun onDrawerClosed(drawerView: View) {
        navHeaderViewModel.stopTimer()
    }

    override fun onDrawerOpened(drawerView: View) {
        navHeaderViewModel.startTimer()
    }
}