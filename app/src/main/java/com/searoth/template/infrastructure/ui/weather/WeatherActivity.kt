package com.searoth.template.infrastructure.ui.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.searoth.template.R
import com.searoth.template.databinding.ActivityWeatherBinding
import com.searoth.template.infrastructure.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.bottom_nav.*
import kotlinx.android.synthetic.main.include_navigation_drawer.*
import timber.log.Timber

class WeatherActivity : BaseActivity() , OnMapReadyCallback {
    private val weatherActivityViewModel: WeatherActivityViewModel by lazy {
        ViewModelProviders.of(this).get(WeatherActivityViewModel::class.java)
    }

    private lateinit var mMap: GoogleMap
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

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
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