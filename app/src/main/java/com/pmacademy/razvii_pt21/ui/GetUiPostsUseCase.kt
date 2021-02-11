package com.pmacademy.razvii_pt21.ui

import com.pmacademy.razvii_pt21.data.PostRepository

class GetUiPostsUseCase(
    private val postUiMapper: PostUiMapper,
    private val postRepository: PostRepository
) {
    fun execute(): List<PostUiModel> {
        return postUiMapper.map(postRepository.getPostsAndUserInfo())
    }
}