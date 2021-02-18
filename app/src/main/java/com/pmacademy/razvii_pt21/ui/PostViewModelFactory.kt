package com.pmacademy.razvii_pt21.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pmacademy.razvii_pt21.data.repository.PostRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostViewModelFactory @Inject constructor(private val repository: PostRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return modelClass.getConstructor(PostRepository::class.java)
                .newInstance(repository)
        } catch (e: Exception) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}