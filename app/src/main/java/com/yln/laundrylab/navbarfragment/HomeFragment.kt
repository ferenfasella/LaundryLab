package com.yln.laundrylab.navbarfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.yln.laundrylab.R
import com.yln.laundrylab.R.id

class HomeFragment:Fragment(R.layout.fragment_home) {

    var fbAuth = FirebaseAuth.getInstance();
    var fbUser = fbAuth.currentUser;

    private val firebaseUser: FirebaseUser? = null
    private var textName: TextView? = null
    private var btnLogout: LayoutInflater? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        textName = activity?.findViewById(R.id.firebaseName)
//        btnLogout = layoutInflater?.findViewById(R.id.btnKeluar)
        if (firebaseUser != null) {
            textName?.text = firebaseUser.displayName
        } else {
            textName?.text = "Login gagal"
        }

        //activity?.setContentView(R.layout.fragment_home)
        super.onCreate(savedInstanceState)
//        btnLogout.setOnClickListener(){
//
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


}