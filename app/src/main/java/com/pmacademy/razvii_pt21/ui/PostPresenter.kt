package com.pmacademy.razvii_pt21.ui

import com.pmacademy.razvii_pt21.tools.threading.CancelableOperation
import com.pmacademy.razvii_pt21.tools.threading.Multithreading

interface PostView {
    fun showInfo(postList: List<PostUiModel>)
    //fun showError(error: String)
}

class PostPresenter(
    private val multithreading: Multithreading,
    private val getUiPostsUseCase: GetUiPostsUseCase
) {
    private var view: PostView? = null
    private var cancelableOperation: CancelableOperation? = null

    fun attachView(postView: PostView) {
        view = postView
        getPosts()
    }

    private fun getPosts() {
        multithreading.async {
            return@async getUiPostsUseCase.execute()
        }.postOnMainThread(::showResult)
    }

    fun detachView() {
        view = null
        cancelableOperation?.cancel()
    }

    private fun showResult(resultList: List<PostUiModel>?) {
        resultList?.let {
            view?.showInfo(resultList)
        }

    }
}
