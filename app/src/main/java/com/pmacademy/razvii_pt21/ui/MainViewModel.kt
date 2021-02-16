package com.pmacademy.razvii_pt21.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pmacademy.razvii_pt21.data.PostRepository
import com.pmacademy.razvii_pt21.data.UserInfoLocalDataProvider
import com.pmacademy.razvii_pt21.domain.PostMapper
import com.pmacademy.razvii_pt21.tools.PostService
import com.pmacademy.razvii_pt21.ui.model.PostUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _postsLiveData = MutableLiveData<List<PostUiModel>>()
    val postsLiveData: LiveData<List<PostUiModel>> = _postsLiveData

    private val getPostsUseCase: GetUiPostsUseCase = GetUiPostsUseCase(
        postUiMapper = PostUiMapper(
            resourceRepository = ResourceRepository(getApplication())
        ),
        postRepository = PostRepository(
            postService = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PostService::class.java),
            postMapper = PostMapper(UserInfoLocalDataProvider().getLocalSetStatusUser())
        )
    )


//    private val getPostsUseCase = PostPresenter(
//        multithreading = Multithreading(getApplication()),
//        getUiPostsUseCase = GetUiPostsUseCase(
//            postUiMapper = PostUiMapper(
//                resourceRepository = ResourceRepository(getApplication())
//            ),
//            postRepository = PostRepository(
//                postService = Retrofit.Builder()
//                    .baseUrl("https://jsonplaceholder.typicode.com/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                    .create(PostService::class.java),
//                postMapper = PostMapper(UserInfoLocalDataProvider().getLocalSetStatusUser())
//            )
//        )
//    )

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getPostsUseCase.execute()
            withContext(Dispatchers.Main) {
                result.also { repos ->
                    _postsLiveData.postValue(repos)
                }
            }
        }
    }


}