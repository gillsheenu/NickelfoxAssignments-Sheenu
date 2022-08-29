package com.example.nickelffoxassignments_sheenu

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(){
    lateinit var navigationView:NavigationView
    lateinit var firebaseAuth:FirebaseAuth
    lateinit var navController:NavController
//    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var drawerlayout:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViews()
        setupNavigationDrawer()
        setUpNavigationview()

//        testCrash=findViewById(R.id.btnTestCrash)
//        testCrash.setOnClickListener {
//            throw RuntimeException("Test Crash")

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,drawerlayout)
    }

    private fun setUpNavigationview() {
        navigationView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.menu_logout->{
                        firebaseAuth.signOut()
                        navController.navigate(R.id.loginFragment2)
                    }
                }
                return true
            }

        })
    }

    private fun setUpViews() {
        firebaseAuth=FirebaseAuth.getInstance()
        var navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigationView=findViewById(R.id.navigationView)
        navController=navHostFragment.navController
        drawerlayout=findViewById(R.id.Drawer)

    }

    private fun setupNavigationDrawer() {

        navigationView.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this,navController,drawerlayout)

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
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
           R.id.menu_logout->{
               navController.navigate(R.id.loginFragment2)
           }
        }
        return super.onOptionsItemSelected(item)
    }


 }



