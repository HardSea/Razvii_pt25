package com.pmacademy.razvii_pt21.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pmacademy.razvii_pt21.data.repository.PostRepository
import com.pmacademy.razvii_pt21.domain.CheckPostRulesUseCase
import com.pmacademy.razvii_pt21.domain.GetUiPostsUseCase
import com.pmacademy.razvii_pt21.domain.mapper.PostUiMapper
import com.pmacademy.razvii_pt21.ui.model.PostUiModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PostViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    private val _postsLiveData = MutableLiveData<List<PostUiModel>>()
    val postsLiveData: LiveData<List<PostUiModel>> = _postsLiveData

    private val getPostsUseCase: GetUiPostsUseCase = GetUiPostsUseCase(
        postUiMapper = PostUiMapper(),
        repository
    )

    private fun insertPost(userId: Int, title: String, body: String) {
        repository.insertUserPostLocal(userId, title, body)
    }

    fun getPosts() {
        getPostsUseCase.invoke()
            ?.subscribe(object : Observer<List<PostUiModel>> {
                override fun onNext(t: List<PostUiModel>) {
                    _postsLiveData.value = t
                }

                override fun onSubscribe(d: Disposable) {}
                override fun onError(e: Throwable) {}
                override fun onComplete() {}
            })
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