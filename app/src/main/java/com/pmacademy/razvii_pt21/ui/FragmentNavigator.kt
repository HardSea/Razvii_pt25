package com.pmacademy.razvii_pt21.ui

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.pmacademy.razvii_pt21.ui.fragments.CreatePostFragment
import com.pmacademy.razvii_pt21.ui.fragments.PostListFragment

class FragmentNavigator(
    private val fragmentManager: FragmentManager,
    @IdRes private val container: Int,
) {

    fun showPostListFragment() {
        fragmentManager.beginTransaction()
            .replace(container, PostListFragment.newInstance())
            .commit()

    }

    fun showCreatePostFragment() {
        fragmentManager.beginTransaction()
            .replace(container, CreatePostFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

}