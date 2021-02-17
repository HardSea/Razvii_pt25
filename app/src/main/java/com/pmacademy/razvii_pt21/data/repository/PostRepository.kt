package com.pmacademy.razvii_pt21.data.repository

import com.pmacademy.razvii_pt21.data.mapper.PostMapper
import com.pmacademy.razvii_pt21.data.model.UserPostModel
import com.pmacademy.razvii_pt21.datasource.local.UserPost
import com.pmacademy.razvii_pt21.datasource.local.UserPostsDatabase
import com.pmacademy.razvii_pt21.datasource.remote.api.PostsReposApi
import com.pmacademy.razvii_pt21.datasource.remote.model.UserPostResponse

class PostRepository(
    private val userPostsDatabase: UserPostsDatabase,
    private val postsReposApi: PostsReposApi,
    private val postMapper: PostMapper
) {

    fun insertUserPostList(userPost: UserPost) =
        userPostsDatabase.getUserPostDao().insertUserPostFromApi(userPost)

//    suspend fun updateUserPost(userPost: UserPost) =
//        userPostsDatabase.getUserPostDao().updateUserPost(userPost)
//
//    suspend fun deleteUserPost(userPost: UserPost) =
//        userPostsDatabase.getUserPostDao().deleteUserPost(userPost)
//
//    suspend fun deleteUserPostById(id: Int) = userPostsDatabase.getUserPostDao().deleteUserPostById(id)
//
//    suspend fun clearUserPosts() = userPostsDatabase.getUserPostDao().clearUserPost()

    private fun getAllLocalUserPosts(): List<UserPost> =
        userPostsDatabase.getUserPostDao().getAllUserPosts()

//    fun getPostsAndUserInfo(): List<UserPostModel>? {
//        return try {
//            val listOfPosts = postsReposApi.getPostsList().execute().body()
//
//            listOfPosts?.let(
//                postMapper::map
//            )
//        } catch (e: Exception) {
//            null
//        }
//    }


    fun getPostsAndUserInfo(): List<UserPostModel>? {
        // TODO delete == 1
        return if (getAllLocalUserPosts().isEmpty() || getAllLocalUserPosts().size == 1) {
            try {
                val listOfPosts = postsReposApi.getPostsList().execute().body()
                saveDataToLocal(listOfPosts)
                getAllLocalUserPosts().let(
                    postMapper::map
                )
            } catch (e: Exception) {
                null
            }
        } else {
            return getAllLocalUserPosts().let(
                postMapper::map
            )
            //Log.d("TAG", "getPostsAndUserInfo: ${getAllLocalUserPosts()}")
            //Log.d("TAG", "getPostsAndUserInfo: NULL")

        }

    }

    private fun saveDataToLocal(listOfPosts: List<UserPostResponse>?) {
        listOfPosts?.forEach { userPostResponse ->
            insertUserPostList(
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

//class PostRepository(
//    private val postsReposApi: PostsReposApi,
//    private val postMapper: PostMapper
//) {
//    fun getPostsAndUserInfo(): List<UserPostModel>? {
//        return try {
//            val listOfPosts = postsReposApi.getPostsList().execute().body()
//
//            listOfPosts?.let(
//                postMapper::map
//            )
//        } catch (e: Exception) {
//            null
//        }
//    }
//}