package com.yln.laundrylab.splashFragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.yln.laundrylab.R

class SplashScreen : Fragment(R.layout.fragment_splash_screen) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("LL", "Jalan gak");
        // Inflate the layout for this fragment
        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashScreen_to_viewPagerFragment)
        }, 3000)
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }
//            if(onBoardingFinished()) {
//                findNavController().navigate(
//                    R.id.action_splashScreen_to_viewPagerFragment
//                )
//            }
////            }else{
////                findNavController().navigate(
////                    R.id.action_splashScreen_to_splashSatu3
////                )
////            }
//        },3000)
//
//        return inflater.inflate(R.layout.fragment_splash_screen,
//            container, false)
//    }
//
//    private fun onBoardingFinished(): Boolean{
//        val sharedPref = requireActivity()
//            .getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
//        return sharedPref.getBoolean("Selesai", false)
//    }
}