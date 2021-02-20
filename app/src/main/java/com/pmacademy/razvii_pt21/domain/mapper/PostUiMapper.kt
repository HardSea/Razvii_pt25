package com.pmacademy.razvii_pt21.domain.mapper

import com.pmacademy.razvii_pt21.R
import com.pmacademy.razvii_pt21.data.model.UserPostModel
import com.pmacademy.razvii_pt21.data.model.UserStatusType
import com.pmacademy.razvii_pt21.ui.model.PostUiModel
import com.pmacademy.razvii_pt21.ui.model.PostUiModelBanned
import com.pmacademy.razvii_pt21.ui.model.PostUiModelNormal

class PostUiMapper {

    companion object {
        private const val bannedTitleString =
            "There could have been a post by user %1s, but he was banned"
        private const val warningUserIdString = "With warning"
    }

    fun map(userPostResult: List<UserPostModel>): List<PostUiModel> {
        val returnList = mutableListOf<PostUiModel>()
        userPostResult.forEach { userPost ->
            when (userPost.userStatus) {
                UserStatusType.BANNED -> returnList.add(createBannedPostUiModel(userPost))
                UserStatusType.WARNING -> returnList.add(createNormalPostUiModel(userPost))
                UserStatusType.NORMAL -> returnList.add(createNormalPostUiModel(userPost))
            }
        }
        return returnList
    }

    private fun createNormalPostUiModel(
        userPost: UserPostModel
    ): PostUiModel {

        val backgroundColorRes = when (userPost.userStatus) {
            UserStatusType.NORMAL -> R.color.background_normal_container
            else -> R.color.background_warning_post_container
        }
        val userId = when (userPost.userStatus) {
            UserStatusType.NORMAL -> "${userPost.userId}"
            else -> "${userPost.userId} $warningUserIdString"
        }

        return PostUiModelNormal(
            postId = userPost.id,
            userId = userId,
            title = userPost.title,
            body = userPost.body,
            backgroundColorRes = backgroundColorRes
        )
    }

    private fun createBannedPostUiModel(userPost: UserPostModel): PostUiModel {
        return PostUiModelBanned(
            postId = userPost.id,
            userId = userPost.userId.toString(),
            title = bannedTitleString.format(userPost.userId)
        )
    }
}