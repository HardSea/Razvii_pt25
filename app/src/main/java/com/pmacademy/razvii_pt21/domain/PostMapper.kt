package com.pmacademy.razvii_pt21.domain

import com.pmacademy.razvii_pt21.data.UserPostResponse


class PostMapper(private val setUserStatusLocalData: Set<UserInfo>) {

    fun map(userPostResponseResult: List<UserPostResponse>): List<UserPostModel> {
        val resultList = mutableListOf<UserPostModel>()
        userPostResponseResult.forEach { userPost ->
            if (setUserStatusLocalData.any { it.userId == userPost.userId }) {
                val userStatus =
                    setUserStatusLocalData.find { it.userId == userPost.userId }?.status
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