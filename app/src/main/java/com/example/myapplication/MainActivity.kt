package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.example.myapplication.ui.Screens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private val navigator = object : AppNavigator(this, R.id.nav_host_fragment) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TheApplication.appComponent?.inject(this)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        setupDrawer(drawerLayout, toolbar, navView)
        if (savedInstanceState == null) {
            router.newRootScreen(Screens.galleryFragment())
        }

        supportFragmentManager.addOnBackStackChangedListener {
            toggle.isDrawerIndicatorEnabled = supportFragmentManager.backStackEntryCount <= 1
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        }
    }

    private fun setupDrawer(
        drawerLayout: DrawerLayout,
        toolbar: Toolbar,
        navView: NavigationView
    ) {
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()

        setDrawerNavigation(navView, drawerLayout)
        toggle.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        toggle.toolbarNavigationClickListener = View.OnClickListener {
            onBackPressed()
        }
    }

    private fun setDrawerNavigation(
        navView: NavigationView,
        drawerLayout: DrawerLayout
    ) {
        navView.setNavigationItemSelectedListener {
            if (it.itemId == R.id.nav_home) {
                router.newRootScreen(Screens.homeFragment())
            } else if (it.itemId == R.id.nav_gallery) {
                router.newRootScreen(Screens.galleryFragment())
            } else if (it.itemId == R.id.nav_slideshow) {
                router.newRootScreen(Screens.slideShowFragment())
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
    }
}