package com.pmacademy.razvii_pt21.data.mapper

import com.pmacademy.razvii_pt21.data.model.UserInfoModel
import com.pmacademy.razvii_pt21.data.model.UserPostModel
import com.pmacademy.razvii_pt21.data.model.UserStatusType
import com.pmacademy.razvii_pt21.datasource.local.UserPost
import com.pmacademy.razvii_pt21.datasource.remote.model.UserPostResponse


class PostMapper(private val setUserStatusLocalDataModels: Set<UserInfoModel>) {

    fun map(userPostResponseResult: List<UserPost>): List<UserPostModel> {
        val resultList = mutableListOf<UserPostModel>()
        userPostResponseResult.forEach { userPost ->
            if (setUserStatusLocalDataModels.any { it.userId == userPost.userId }) {
                val userStatus =
                    setUserStatusLocalDataModels.find { it.userId == userPost.userId }?.status
                        ?: UserStatusType.NORMAL
                resultList.add(createUserPostModel(userPost, userStatus))
            } else {
                resultList.add(createUserPostModel(userPost))
            }
        }
        return resultList
    }

    private fun createUserPostModel(
        userPostResponse: UserPost,
        userStatusType: UserStatusType = UserStatusType.NORMAL
    ): UserPostModel = UserPostModel(
        userPostResponse.userId,
        userPostResponse.id,
        userPostResponse.title,
        userPostResponse.body,
        userStatusType
    )

}