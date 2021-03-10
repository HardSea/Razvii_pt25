package com.pmacademy.razviipt21.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmacademy.razviipt21.domain.GetUiPostsUseCase
import com.pmacademy.razviipt21.ui.model.PostUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetUiPostsUseCase
) : ViewModel() {

    private val _postsLiveData = MutableLiveData<List<PostUiModel>>()
    val postsLiveData: LiveData<List<PostUiModel>> = _postsLiveData


    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val posts = getPostsUseCase.invoke()
            withContext(Dispatchers.Main) {
                _postsLiveData.value = posts.first()
            }
        }
    }
}
