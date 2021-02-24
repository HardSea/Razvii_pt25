package com.pmacademy.razvii_pt21.domain

import com.pmacademy.razvii_pt21.data.repository.PostRepository
import com.pmacademy.razvii_pt21.domain.mapper.PostUiMapper
import com.pmacademy.razvii_pt21.ui.model.PostUiModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetUiPostsUseCase @Inject constructor(
    private val postUiMapper: PostUiMapper,
    private val postRepository: PostRepository
) {
    fun invoke(): Observable<List<PostUiModel>>? {
        return postRepository.getPostsAndUserInfo()
            ?.map { postUiMapper.map(it) }
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }
}
