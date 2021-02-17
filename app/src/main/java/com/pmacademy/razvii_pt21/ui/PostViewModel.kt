package com.pmacademy.razvii_pt21.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.pmacademy.razvii_pt21.data.UserInfoLocalDataProvider
import com.pmacademy.razvii_pt21.data.mapper.PostMapper
import com.pmacademy.razvii_pt21.data.repository.PostRepository
import com.pmacademy.razvii_pt21.datasource.local.UserPost
import com.pmacademy.razvii_pt21.datasource.local.UserPostsDatabase
import com.pmacademy.razvii_pt21.datasource.remote.api.PostsReposApi
import com.pmacademy.razvii_pt21.domain.GetUiPostsUseCase
import com.pmacademy.razvii_pt21.domain.mapper.PostUiMapper
import com.pmacademy.razvii_pt21.ui.model.PostUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PostViewModel(private val localUserPostDatabase: UserPostsDatabase) : ViewModel() {

    private val _postsLiveData = MutableLiveData<List<PostUiModel>>()
    val postsLiveData: LiveData<List<PostUiModel>> = _postsLiveData

    private val repository: PostRepository = PostRepository(
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
        ),
        userPostsDatabase = localUserPostDatabase
    )


    private val getPostsUseCase: GetUiPostsUseCase = GetUiPostsUseCase(
        postUiMapper = PostUiMapper(),
        repository
    )

    fun insertPost(userPost: UserPost) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUserPostList(userPost)
        }
        getPosts()
    }


    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {

            //val result = null
            val result = getPostsUseCase.execute()

            withContext(Dispatchers.Main) {

                result.also { posts ->
                    _postsLiveData.postValue(posts)
                }
            }
        }
    }


//    suspend fun updateNote(userPost: UserPost) = repository.updateUserPost(userPost)
//
//    suspend fun deleteNote(userPost: UserPost) = repository.deleteUserPost(userPost)
//
//    suspend fun deleteNoteById(id: Int) = repository.deleteUserPostById(id)
//
//    suspend fun clearNote() = repository.clearUserPosts()
//
//    suspend fun getAllNotes() = repository.getPostsAndUserInfo()

}