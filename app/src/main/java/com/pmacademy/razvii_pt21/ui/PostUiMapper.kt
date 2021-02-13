package com.pmacademy.razvii_pt21.ui

import com.pmacademy.razvii_pt21.R
import com.pmacademy.razvii_pt21.domain.UserPostModel
import com.pmacademy.razvii_pt21.domain.UserStatusType

class PostUiMapper(private val resourceRepository: ResourceRepository) {

    companion object {
        private const val backgroundWarningColor = R.color.gray
        private const val backgroundNormalColor = R.color.white
        private const val bannedTitleString = R.string.user_banned_title_text
        private const val warningUserIdString = R.string.warning_user_id_string
    }

    fun map(userPostResult: List<UserPostModel>): List<PostUiModel> {
        val returnList = mutableListOf<PostUiModel>()

        userPostResult.forEach { userPost ->
            when (userPost.userStatus) {
                UserStatusType.BANNED -> {
                    returnList.add(createBannedPostUiModel(userPost))
                }
                UserStatusType.WARNING -> {
                    returnList.add(createWarningPostUiModel(userPost))
                }
                UserStatusType.NORMAL -> {
                    returnList.add(createNormalPostUiModel(userPost))
                }
            }
        }
        return returnList
    }

    private fun createBannedPostUiModel(userPost: UserPostModel): PostUiModel {
        return PostUiModelBanned(
            postId = userPost.id,
            userId = userPost.userId.toString(),
            //title = resourceRepository.getString(bannedTitleString)
            title = resourceRepository.getStringWithValue(bannedTitleString, userPost.userId.toString())
        )
    }


    private fun createWarningPostUiModel(
        userPost: UserPostModel
    ): PostUiModel {
        return PostUiModelNormal(
            postId = userPost.id,
            userId = resourceRepository.getStringWithValue(warningUserIdString, userPost.userId.toString()),
            title = userPost.title,
            body = userPost.body,
            backgroundColor = resourceRepository.getColor(backgroundWarningColor)
        )
    }

    private fun createNormalPostUiModel(
        userPost: UserPostModel
    ): PostUiModel {
        return PostUiModelNormal(
            postId = userPost.id,
            userId = userPost.userId.toString(),
            title = userPost.title,
            body = userPost.body,
            backgroundColor = resourceRepository.getColor(backgroundNormalColor)
        )
    }
}