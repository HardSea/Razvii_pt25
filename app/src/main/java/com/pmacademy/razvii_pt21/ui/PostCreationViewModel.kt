package com.pmacademy.razvii_pt21.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmacademy.razvii_pt21.domain.AddPostToLocalUseCase
import com.pmacademy.razvii_pt21.domain.ErrorType
import com.pmacademy.razvii_pt21.domain.Post
import com.pmacademy.razvii_pt21.tools.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostCreationViewModel @Inject constructor(
    private val addPostToLocalUseCase: AddPostToLocalUseCase
) : ViewModel() {

    private val _postCreateLiveData = MutableLiveData<Result<Boolean, ErrorType>>()
    val postCreateLiveData: LiveData<Result<Boolean, ErrorType>> = _postCreateLiveData

    fun createPost(title: String, body: String, userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result =
                addPostToLocalUseCase.invoke(Post(userId = userId, title = title, body = body))
            withContext(Dispatchers.Main) {
                _postCreateLiveData.value = result
            }
        }
    }
}