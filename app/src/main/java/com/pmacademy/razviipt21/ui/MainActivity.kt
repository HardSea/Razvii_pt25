package com.pmacademy.razviipt21.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pmacademy.razviipt21.R

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
