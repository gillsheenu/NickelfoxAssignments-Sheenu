package com.example.nickelffoxassignments_sheenu

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import com.example.nickelffoxassignments_sheenu.R

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var navigationView: NavigationView
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var navController: NavController
    lateinit var mWindow: Window
    lateinit var drawerlayout: DrawerLayout
    var setItemEnabled: Boolean = true
    var setColorEnabled: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViews()
        setupNavigationDrawer()
        setUpNavigationview()
        setUpFeatures()

//        testCrash=findViewById(R.id.btnTestCrash)
//        testCrash.setOnClickListener {
//            throw RuntimeException("Test Crash")

    }

    private fun setUpFeatures() {
        navController.addOnDestinationChangedListener(object :
            NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {

                if (destination.id == R.id.registrationFragment) {
                    setItemEnabled = false
                    invalidateOptionsMenu()
                } else {
                    setItemEnabled = true
                    invalidateOptionsMenu()
                }

                if (destination.id == R.id.calculatorFragment) {

                    setColorEnabled = true
                    setAppBarColor(R.color.calculator_color)
                } else if (destination.id == R.id.stopWatchFragment) {
                    setColorEnabled = true
                    setAppBarColor(R.color.stopWatch_color)
                } else if (destination.id == R.id.newsFragment) {
                    setColorEnabled = true
                    setAppBarColor(R.color.news_color)
                } else if (destination.id == R.id.musicPlayerFragment) {
                    setColorEnabled = true
                    setAppBarColor(R.color.musicPlayer_color)
                } else {
                    setColorEnabled = false
                    setAppBarColor(R.color.colorPrimary)
                }
            }
        })
    }

    private fun setUpViews() {
        firebaseAuth = FirebaseAuth.getInstance()
        var navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigationView = findViewById(R.id.navigationView)
        navController = navHostFragment.navController
        drawerlayout = findViewById(R.id.Drawer)
        mWindow = this@MainActivity.getWindow()

    }

    private fun setAppBarColor(mColor: Int) {
        if (setColorEnabled) {
            mWindow.statusBarColor = ContextCompat.getColor(this, mColor)
            supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, mColor))
        } else {
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            mWindow.statusBarColor = ContextCompat.getColor(this, mColor)
            supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, mColor))

        }
    }

    private fun setupNavigationDrawer() {

        navigationView.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerlayout)

    }

    private fun setUpNavigationview() {
        navigationView.setNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.menu_logout -> {
                        firebaseAuth.signOut()
                        navController.navigate(R.id.loginFragment2)
                    }
                }
                return true
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerlayout)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (setItemEnabled) {
            menu?.findItem(R.id.menu_logout)!!.setEnabled(true)
        } else {
            menu?.findItem(R.id.menu_logout)!!.setEnabled(false)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_logout -> {
                navController.navigate(R.id.loginFragment2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    override fun onBackPressed() {
//        if(drawerlayout.isDrawerOpen(GravityCompat.START)){
//            drawerlayout.closeDrawer(GravityCompat.START)
//        }else{
//            super.onBackPressed()
//        }
//    }

//    fun showStatusBar(hideStatusBar:Boolean){
//        if(hideStatusBar){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                window.decorView.windowInsetsController!!.hide(android.view.WindowInsets.Type.statusBars())
//                supportActionBar?.hide()
//
//            }
//
//        }else{
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                window.decorView.windowInsetsController!!.show(android.view.WindowInsets.Type.statusBars())
//                supportActionBar?.show()
//            }
//
}









