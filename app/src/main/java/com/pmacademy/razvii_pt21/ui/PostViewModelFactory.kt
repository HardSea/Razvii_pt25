package com.pmacademy.razvii_pt21.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pmacademy.razvii_pt21.data.model.UserPostModel
import com.pmacademy.razvii_pt21.data.repository.PostRepository
import com.pmacademy.razvii_pt21.datasource.local.UserPostsDatabase
import com.pmacademy.razvii_pt21.datasource.remote.model.UserPostResponse

class PostViewModelFactory(private val localUserPostsDatabase: UserPostsDatabase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(UserPostsDatabase::class.java)
            return constructor.newInstance(localUserPostsDatabase)
        }catch (e: Exception) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}