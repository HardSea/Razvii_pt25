package com.pmacademy.razvii_pt21.data.mapper

import com.pmacademy.razvii_pt21.data.model.UserInfoModel
import com.pmacademy.razvii_pt21.data.model.UserPostModel
import com.pmacademy.razvii_pt21.data.model.UserStatusType
import com.pmacademy.razvii_pt21.datasource.model.UserPostResponse


class PostMapper(private val setUserStatusLocalDatumModels: Set<UserInfoModel>) {

    fun map(userPostResponseResult: List<UserPostResponse>): List<UserPostModel> {
        val resultList = mutableListOf<UserPostModel>()
        userPostResponseResult.forEach { userPost ->
            if (setUserStatusLocalDatumModels.any { it.userId == userPost.userId }) {
                val userStatus =
                    setUserStatusLocalDatumModels.find { it.userId == userPost.userId }?.status
                        ?: UserStatusType.NORMAL
                resultList.add(createUserPostModel(userPost, userStatus))
            } else {
                resultList.add(createUserPostModel(userPost))
            }
        }
        return resultList
    }

    private fun createUserPostModel(
        userPostResponse: UserPostResponse,
        userStatusType: UserStatusType = UserStatusType.NORMAL
    ): UserPostModel = UserPostModel(
        userPostResponse.userId,
        userPostResponse.id,
        userPostResponse.title,
        userPostResponse.body,
        userStatusType
    )

}