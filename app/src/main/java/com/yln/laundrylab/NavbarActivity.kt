package com.yln.laundrylab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yln.laundrylab.navbarfragment.*
import com.yln.laundrylab.overview.OpenDataFragment
import com.yln.laundrylab.overview.OverviewFragment
import com.yln.laundrylab.splashFragment.SplashScreen
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*


class NavbarActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //untuk navbar
//        val splash = SplashScreen()
//        setCurrentFragment(splash)

        val homeFragment = HomeFragment()
        val historyFragment = HistoryFragment()
        val cuciFragment = CuciFragment()

        val notificationFragment = NotificationFragment()
        val storeFragment = StoreFragment()

        setCurrentFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
//                R.id.home -> setCurrentFragment(homeFragment)
//                R.id.history -> setCurrentFragment(historyFragment)
////                R.id.fab->setCurrentFragment(cuciFragment)
//                R.id.notification -> setCurrentFragment(notificationFragment)
//                R.id.store -> setCurrentFragment(storeFragment)


                R.id.homeFragment -> setCurrentFragment(homeFragment)
                R.id.historyFragment -> setCurrentFragment(historyFragment)
//                R.id.fab->setCurrentFragment(cuciFragment)
                R.id.notificationFragment -> setCurrentFragment(notificationFragment)
                R.id.storeFragment -> setCurrentFragment(storeFragment)
            }
            Log.d("LL", "navbar fragment jalan gak")

            true
        }

    }

    public fun kedetailtoko(view : View) {
        // Do something in response to button click
        Log.d("LL", "kedetailtoko")

//        val i = Intent(applicationContext, StoreDetailFragment::class.java)
//        startActivity(i)
//        setContentView(R.layout.fragment_store_detail);
        setCurrentFragment(StoreDetailFragment())

//        Log.d("LL", "kedetailtoko")
    }

    public fun keprofil(view : View) {
        // Do something in response to button click
        Log.d("LL", "keprodil")

//        val i = Intent(applicationContext, StoreDetailFragment::class.java)
//        startActivity(i)
//        setContentView(R.layout.fragment_store_detail);
        setCurrentFragment(ProfilFragment())

//        Log.d("LL", "kedetailtoko")
    }

    public fun kegetdata(view : View) {
        // Do something in response to button click
        Log.d("LL", "kegetdata")

//        val i = Intent(applicationContext, StoreDetailFragment::class.java)
//        startActivity(i)
//        setContentView(R.layout.fragment_store_detail);
        setCurrentFragment(OpenDataFragment())

//        Log.d("LL", "kedetailtoko")
    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout,fragment)
            commit()
        }

}
