package com.pmacademy.razvii_pt21.ui

import android.app.Application
import androidx.lifecycle.*
import com.google.gson.GsonBuilder
import com.pmacademy.razvii_pt21.data.repository.PostRepository
import com.pmacademy.razvii_pt21.data.UserInfoLocalDataProvider
import com.pmacademy.razvii_pt21.domain.GetUiPostsUseCase
import com.pmacademy.razvii_pt21.data.mapper.PostMapper
import com.pmacademy.razvii_pt21.domain.mapper.PostUiMapper
import com.pmacademy.razvii_pt21.datasource.api.PostsReposApi
import com.pmacademy.razvii_pt21.ui.model.PostUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel() : ViewModel() {

    private val _postsLiveData = MutableLiveData<List<PostUiModel>>()
    val postsLiveData: LiveData<List<PostUiModel>> = _postsLiveData

    private val getPostsUseCase: GetUiPostsUseCase = GetUiPostsUseCase(
        postUiMapper = PostUiMapper(),
        postRepository = PostRepository(
            postsReposApi = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setLenient().create()
                    )
                )
                .build()
                .create(PostsReposApi::class.java),
            postMapper = PostMapper(
                UserInfoLocalDataProvider().getLocalSetStatusUser()
            )
        )
    )


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