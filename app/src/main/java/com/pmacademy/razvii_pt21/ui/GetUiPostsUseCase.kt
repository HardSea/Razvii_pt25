package com.pmacademy.razvii_pt21.ui

import com.pmacademy.razvii_pt21.data.PostRepository
import com.pmacademy.razvii_pt21.ui.model.PostUiModel

class GetUiPostsUseCase(
    private val postUiMapper: PostUiMapper,
    private val postRepository: PostRepository
) {
    fun execute(): List<PostUiModel>? {
        val postsFromRepository = postRepository.getPostsAndUserInfo()
        return if (postsFromRepository != null){
            postUiMapper.map(postsFromRepository)
        } else {
            null
        }
    }
}