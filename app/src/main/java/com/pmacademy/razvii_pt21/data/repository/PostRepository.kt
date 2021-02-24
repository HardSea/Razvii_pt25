package com.pmacademy.razvii_pt21.data.repository

import com.pmacademy.razvii_pt21.data.mapper.PostMapper
import com.pmacademy.razvii_pt21.data.model.UserPostModel
import com.pmacademy.razvii_pt21.datasource.local.UserPost
import com.pmacademy.razvii_pt21.datasource.local.UserPostsDatabase
import com.pmacademy.razvii_pt21.datasource.remote.api.PostsReposApi
import com.pmacademy.razvii_pt21.datasource.remote.model.UserPostResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PostRepository @Inject constructor(
    private val userPostsDatabase: UserPostsDatabase,
    private val postsReposApi: PostsReposApi,
    private val postMapper: PostMapper
) {

    private fun insertUserPostFromApi(userPost: UserPost) {
        Completable.fromRunnable {
            userPostsDatabase.getUserPostDao().insertUserPost(userPost)
        }.subscribeOn(Schedulers.io()).subscribe()
    }

    fun insertUserPostLocal(userId: Int, title: String, body: String) {
        Completable.fromRunnable {
            val insertUser = UserPost(
                id = userPostsDatabase.getUserPostDao().getMinLocalUserPostId() - 1,
                userId = userId,
                title = title,
                body = body
            )
            userPostsDatabase.getUserPostDao().insertUserPost(insertUser)
        }.subscribeOn(Schedulers.io()).subscribe()
    }

    private fun getAllLocalUserPosts(): Observable<List<UserPost>> =
        userPostsDatabase.getUserPostDao().getAllUserPosts()

    fun getPostsAndUserInfo(): Observable<List<UserPostModel>>? {
        return if (getAllLocalUserPosts().blockingFirst().isEmpty()) {
            try {
                saveDataToLocal(postsReposApi.getPostsList().blockingFirst())
                getAllLocalUserPosts().map { postMapper.map(it) }
            } catch (e: Exception) {
                null
            }
        } else {
            return this.getAllLocalUserPosts().map { postMapper.map(it) }
        }
    }

    private fun saveDataToLocal(listOfPosts: List<UserPostResponse>?) {
        listOfPosts?.forEach { userPostResponse ->
            insertUserPostFromApi(
                UserPost(
                    id = userPostResponse.id + 1000,
                    title = userPostResponse.title,
                    body = userPostResponse.body,
                    userId = userPostResponse.userId
                )
            )
        }
    }
}

