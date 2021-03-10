package com.pmacademy.razviipt21.data.repository

import com.pmacademy.razviipt21.data.mapper.PostMapper
import com.pmacademy.razviipt21.data.model.UserPostModel
import com.pmacademy.razviipt21.datasource.local.UserPost
import com.pmacademy.razviipt21.datasource.local.UserPostsDatabase
import com.pmacademy.razviipt21.datasource.remote.api.PostsReposApi
import com.pmacademy.razviipt21.datasource.remote.model.UserPostResponse
import com.pmacademy.razviipt21.domain.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class PostRepository @Inject constructor(
    private val userPostsDatabase: UserPostsDatabase,
    private val postsReposApi: PostsReposApi,
    private val postMapper: PostMapper
) {
    private companion object {
        const val addIndexNumber = 1000
    }

    private fun insertUserPostFromApi(userPost: UserPost) =
        userPostsDatabase.getUserPostDao().insertUserPost(userPost)

    fun insertUserPostLocal(post: Post) =
        userPostsDatabase.getUserPostDao().insertUserPost(
            UserPost(
                id = userPostsDatabase.getUserPostDao().getMinLocalUserPostId() - 1,
                userId = post.userId,
                title = post.title,
                body = post.body
            )
        )

    private fun getAllLocalUserPosts(): Flow<List<UserPost>> {
        return userPostsDatabase.getUserPostDao().getAllUserPosts()
    }

    private fun getAllRemotePosts(): List<UserPostResponse>? {
        return postsReposApi.getPostsList().execute().body()
    }

    suspend fun getPostsAndUserInfo(): Flow<List<UserPostModel>> {
        return if (getAllLocalUserPosts().first().isEmpty()) {
            saveDataToLocal(getAllRemotePosts())
            getAllLocalUserPosts().map { postMapper.map(it) }
        } else {
            return this.getAllLocalUserPosts().map { postMapper.map(it) }
        }
    }

    private fun saveDataToLocal(listOfPosts: List<UserPostResponse>?) {
        listOfPosts?.forEach { userPostResponse ->
            insertUserPostFromApi(
                UserPost(
                    id = userPostResponse.id + addIndexNumber,
                    title = userPostResponse.title,
                    body = userPostResponse.body,
                    userId = userPostResponse.userId
                )
            )
        }
    }
}

