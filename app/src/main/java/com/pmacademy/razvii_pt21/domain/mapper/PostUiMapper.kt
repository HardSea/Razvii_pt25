package com.pmacademy.razvii_pt21.domain.mapper

import com.pmacademy.razvii_pt21.R
import com.pmacademy.razvii_pt21.data.model.UserPostModel
import com.pmacademy.razvii_pt21.data.model.UserStatusType
import com.pmacademy.razvii_pt21.ui.model.PostUiModel
import com.pmacademy.razvii_pt21.ui.model.PostUiModelBanned
import com.pmacademy.razvii_pt21.ui.model.PostUiModelNormal
import com.pmacademy.razvii_pt21.ui.model.PostUiModelWarning

class PostUiMapper {

    companion object {
        private const val backgroundWarningColor = R.color.background_warning_post_container
        private const val backgroundNormalColor = R.color.background_normal_container
        private const val bannedTitleStringResource = R.string.user_banned_title_text
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
            titleResource = bannedTitleStringResource
            //title = resourceRepository.getStringWithValue(bannedTitleStringResource, userPost.userId.toString())
        )
    }


    private fun createWarningPostUiModel(
        userPost: UserPostModel
    ): PostUiModel {
        return PostUiModelWarning(
            postId = userPost.id,
            //userId = resourceRepository.getStringWithValue(warningUserIdString, userPost.userId.toString()),
            userId = userPost.userId.toString(),
            warningTextRes = warningUserIdString,
            title = userPost.title,
            body = userPost.body,
            backgroundColorRes = backgroundWarningColor
            //backgroundColor = resourceRepository.getColor(backgroundWarningColor)
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
            backgroundColorRes = backgroundNormalColor
            //backgroundColor = resourceRepository.getColor(backgroundNormalColor)
        )
    }
}