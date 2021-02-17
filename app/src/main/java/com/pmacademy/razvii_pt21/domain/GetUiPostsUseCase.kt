package com.pmacademy.razvii_pt21.domain

import com.pmacademy.razvii_pt21.data.repository.PostRepository
import com.pmacademy.razvii_pt21.domain.mapper.PostUiMapper
import com.pmacademy.razvii_pt21.ui.model.PostUiModel

class GetUiPostsUseCase(
    private val postUiMapper: PostUiMapper,
    private val postRepository: PostRepository
) {
    fun invoke(): List<PostUiModel>? {
        val postsFromRepository = postRepository.getPostsAndUserInfo()
        return if (postsFromRepository != null) {
            postUiMapper.map(postsFromRepository)
        } else {
            null
        }
    }
}