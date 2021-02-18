package com.pmacademy.razvii_pt21.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmacademy.razvii_pt21.data.repository.PostRepository
import com.pmacademy.razvii_pt21.domain.CheckPostRulesUseCase
import com.pmacademy.razvii_pt21.domain.GetUiPostsUseCase
import com.pmacademy.razvii_pt21.domain.mapper.PostUiMapper
import com.pmacademy.razvii_pt21.ui.model.PostUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    private val _postsLiveData = MutableLiveData<List<PostUiModel>>()
    val postsLiveData: LiveData<List<PostUiModel>> = _postsLiveData

    private val getPostsUseCase: GetUiPostsUseCase = GetUiPostsUseCase(
        postUiMapper = PostUiMapper(),
        repository
    )

    private fun insertPost(userId: Int, title: String, body: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUserPostLocal(userId, title, body)
        }
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getPostsUseCase.invoke()

            withContext(Dispatchers.Main) {
                result.also { posts ->
                    _postsLiveData.postValue(posts)
                }
            }
        }
    }

    fun createPost(title: String, body: String, userId: Int): Boolean {
        return if (CheckPostRulesUseCase(title, body).invoke() && userId > 0) {
            insertPost(
                userId = userId,
                title = title,
                body = body
            )
            true
        } else {
            false
        }
    }
}