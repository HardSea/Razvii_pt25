package com.pmacademy.razvii_pt21.data.mapper

import com.pmacademy.razvii_pt21.data.model.UserInfoModel
import com.pmacademy.razvii_pt21.data.model.UserPostModel
import com.pmacademy.razvii_pt21.data.model.UserStatusType
import com.pmacademy.razvii_pt21.datasource.local.UserPost
import javax.inject.Inject


class PostMapper @Inject constructor(private val setUserStatusLocalDataModels: Set<UserInfoModel>) {

    fun map(userPostResponseResult: List<UserPost>): List<UserPostModel> {
        val resultList = mutableListOf<UserPostModel>()
        userPostResponseResult.forEach { userPost ->
            if (areUserInLocalData(userPost)) {
                resultList.add(createUserPostModel(userPost, getUserStatusFromLocal(userPost)))
            } else {
                resultList.add(createUserPostModel(userPost))
            }
        }
        return resultList
    }

    private fun areUserInLocalData(userPost: UserPost): Boolean {
        return setUserStatusLocalDataModels.any { localUserPost ->
            localUserPost.userId == userPost.userId
        }
    }

    private fun getUserStatusFromLocal(userPost: UserPost): UserStatusType {
        return setUserStatusLocalDataModels.find { it.userId == userPost.userId }?.status
            ?: UserStatusType.NORMAL
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