package com.pmacademy.razviipt21.domain

import com.pmacademy.razviipt21.data.repository.PostRepository
import com.pmacademy.razviipt21.domain.mapper.PostUiMapper
import com.pmacademy.razviipt21.ui.model.PostUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUiPostsUseCase @Inject constructor(
    private val postUiMapper: PostUiMapper,
    private val postRepository: PostRepository
) {
    suspend fun invoke(): Flow<List<PostUiModel>> {
        val postsFromRepository = postRepository.getPostsAndUserInfo()
        return postsFromRepository.map { postUiMapper.map(it) }
    }
}
