package com.pmacademy.razvii_pt21.ui.fragments

import androidx.fragment.app.Fragment
import com.pmacademy.razvii_pt21.ui.FragmentNavigator
import com.pmacademy.razvii_pt21.ui.MainActivity

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    protected val navigator: FragmentNavigator by lazy {
        (requireActivity() as MainActivity).fragmentNavigator
    }


}