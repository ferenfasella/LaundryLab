package com.yln.laundrylab

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yln.laundrylab.navbarfragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

    public fun sendMessage(view : View) {
        // Do something in response to button click
//        setContentView(R.layout.login);
        Log.d("LL", "ke login")

        val i = Intent(applicationContext, LoginActivity::class.java)
        startActivity(i)
    }

    public fun keregister(view : View) {
        // Do something in response to button click
        val i = Intent(applicationContext, RegisterActivity::class.java)
        startActivity(i)
        Log.d("LL", "keregister")
        }

//    public fun keprofil(view : View) {
//        // Do something in response to button click
//        val i = Intent(applicationContext, ProfilFragment::class.java)
//        startActivity(i)
//        Log.d("LL", "keprofil")
//    }

//    public fun kedetailtoko(view : View) {
//        // Do something in response to button click
//        Log.d("LL", "kedetailtoko")
//
////        val i = Intent(applicationContext, StoreDetailFragment::class.java)
////        startActivity(i)
//        setContentView(R.layout.fragment_store_detail);
//
////        Log.d("LL", "kedetailtoko")
//    }

    public fun panggilactivity2(view : View) {
        // Do something in response to button click
        setContentView(R.layout.activity_main3);
        val i = Intent(applicationContext, NavbarActivity::class.java)
        startActivity(i)

    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout,fragment)
            commit()
        }

}
