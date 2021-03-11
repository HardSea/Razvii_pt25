package com.pmacademy.razviipt21

import com.pmacademy.razviipt21.data.mapper.PostMapper
import com.pmacademy.razviipt21.data.model.UserInfoModel
import com.pmacademy.razviipt21.data.model.UserPostModel
import com.pmacademy.razviipt21.data.model.UserStatusType
import com.pmacademy.razviipt21.datasource.local.UserPost
import org.junit.jupiter.api.Test

internal class PostMapperTest {

    @Test
    fun `should return the correct data after mapping`() {
        val fakeSetUserStatusLocalDataModels = setOf<UserInfoModel>()
        val mapper = PostMapper(fakeSetUserStatusLocalDataModels)

        val userId = 100
        val postId = 100
        val title = "test"
        val body = "test"
        val userPostResponseList = listOf<UserPost>(
            UserPost(userId, postId, title, body)
        )

        val actualResult = mapper.map(userPostResponseList)[0]
        val expectedResult = UserPostModel(userId, postId, title, body, UserStatusType.NORMAL)

        assert(expectedResult == actualResult)
    }
}
