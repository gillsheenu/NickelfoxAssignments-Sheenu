package com.example.nickelffoxassignments_sheenu

import androidx.core.content.ContextCompat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.paging.ExperimentalPagingApi
import com.example.nickelffoxassignments_sheenu.auth.AuthViewModel
import com.example.nickelffoxassignments_sheenu.auth.AuthViewModelFactory
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController
    private lateinit var mWindow: Window
    private lateinit var drawerLayout: DrawerLayout
    private var setItemEnabled = true
    private var setColorEnabled = false
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        setContentView(R.layout.activity_main)
        authViewModel= ViewModelProvider(this@MainActivity,AuthViewModelFactory(application))[AuthViewModel::class.java]
        setUpViews()
        setupNavigationDrawer()
        setUpNavigationView()
        setUpFeatures()

        authViewModel.statusMutableLiveData.observe(this) {

                navController.popBackStack()

                navController.navigate(R.id.loginFragment2)

        }

//        testCrash=findViewById(R.id.btnTestCrash)
//        testCrash.setOnClickListener {
//            throw RuntimeException("Test Crash")

    }

    private fun setUpFeatures() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.registrationFragment) {
                //                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                navigationView.menu.findItem(R.id.menu_logout).isEnabled = false
                setItemEnabled = false
                invalidateOptionsMenu()
            } else {
                setItemEnabled = true
                invalidateOptionsMenu()
            }
            if (destination.id == R.id.loginFragment2) {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                setItemEnabled = false
                invalidateOptionsMenu()
            } else {
                setItemEnabled = true
                invalidateOptionsMenu()
            }

            when (destination.id) {
                R.id.mainFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                R.id.calculatorFragment -> {

                    setColorEnabled = true
                    setAppBarColor(R.color.calculator_color)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                R.id.stopWatchFragment -> {
                    setColorEnabled = true
                    setAppBarColor(R.color.stopWatch_color)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                R.id.newsFragment -> {
                    setColorEnabled = true
                    setAppBarColor(R.color.news_color)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                R.id.musicPlayerFragment -> {
                    setColorEnabled = true
                    setAppBarColor(R.color.musicPlayer_color)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                R.id.historyFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)

                }
                else -> {
                    setColorEnabled = false
                    setAppBarColor(R.color.colorPrimary)
                    //                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
            }
        }
    }

    private fun setUpViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigationView = findViewById(R.id.navigationView)
        navController = navHostFragment.navController
        drawerLayout = findViewById(R.id.Drawer)
        mWindow = this@MainActivity.window

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
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
//        supportActionBar?.setDisplayHomeAsUpEnabled(false)
//        supportActionBar?.setDisplayShowHomeEnabled(false)


    }

    private fun setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_logout -> {
                    authViewModel.logout()
                }
            }
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.menu_logout)!!.isEnabled = setItemEnabled
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_logout -> {
                authViewModel.logout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    override fun onBackPressed() {
//        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START)
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









