package com.pmacademy.razvii_pt21.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.pmacademy.razvii_pt21.R
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val fragmentNavigator by lazy {
        FragmentNavigator(
            supportFragmentManager,
            R.id.container
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentNavigator.showPostListFragment()
    }
}