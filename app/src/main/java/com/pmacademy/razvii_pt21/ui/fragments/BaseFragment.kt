package com.pmacademy.razvii_pt21.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pmacademy.razvii_pt21.Application
import com.pmacademy.razvii_pt21.ui.FragmentNavigator
import com.pmacademy.razvii_pt21.ui.MainActivity

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDi()
    }

    protected val navigator: FragmentNavigator by lazy {
        (requireActivity() as MainActivity).fragmentNavigator
    }
    abstract fun setupDi()
}