package com.pmacademy.razviipt21

import com.pmacademy.razviipt21.data.model.UserPostModel
import com.pmacademy.razviipt21.data.model.UserStatusType
import com.pmacademy.razviipt21.domain.mapper.PostUiMapper
import com.pmacademy.razviipt21.ui.model.PostUiModelBanned
import com.pmacademy.razviipt21.ui.model.PostUiModelNormal
import org.junit.Test

class PostUiMapperTest {

    @Test
    fun `should create banned post ui model from banned user post model`() {
        val mapper = PostUiMapper()
        val userId = 0
        val postId = 0
        val userStatusType = UserStatusType.BANNED

        val userPostModel = UserPostModel(userId, postId, "", "", userStatusType)
        val expectedResult = PostUiModelBanned(
            postId = userId,
            userId = postId.toString(),
            title = PostUiMapper.bannedTitleString.format(userId)
        )
        val actualResult = mapper.map(listOf(userPostModel))[0]

        assert(expectedResult == actualResult)
    }

    @Test
    fun `should create normal post ui model from normal user post model`() {
        val mapper = PostUiMapper()
        val userId = 0
        val postId = 0
        val userStatusType = UserStatusType.NORMAL

        val userPostModel = UserPostModel(userId, postId, "", "", userStatusType)
        val expectedResult = PostUiModelNormal(
            postId = postId,
            userId = userId.toString(),
            title = "",
            body = "",
            backgroundColorRes = R.color.background_normal_container
        )
        val actualResult = mapper.map(listOf(userPostModel))[0]

        assert(expectedResult == actualResult)
    }

}