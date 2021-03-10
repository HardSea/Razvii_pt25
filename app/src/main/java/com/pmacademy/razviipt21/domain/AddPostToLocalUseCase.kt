package com.pmacademy.razviipt21.domain

import com.pmacademy.razviipt21.data.repository.PostRepository
import com.pmacademy.razviipt21.tools.Result
import javax.inject.Inject

data class Post(val userId: Int, val title: String, val body: String)

class AddPostToLocalUseCase @Inject constructor(private val repository: PostRepository) {

    fun invoke(post: Post): Result<Boolean, ErrorType> {
        val checkPost = CheckPostRulesUseCase().invoke(post)
        return if (!checkPost.isError) {
            repository.insertUserPostLocal(post)
            Result.success(true)
        } else
            Result.error(checkPost.errorResult)
    }
}
