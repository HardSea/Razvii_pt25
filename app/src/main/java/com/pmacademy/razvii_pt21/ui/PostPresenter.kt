package com.pmacademy.razvii_pt21.ui

import com.pmacademy.razvii_pt21.tools.Result
import com.pmacademy.razvii_pt21.tools.threading.CancelableOperation
import com.pmacademy.razvii_pt21.tools.threading.Multithreading

interface PostView {
    fun showInfo(postList: List<PostUiModel>)
    fun showError(error: PostErrorsTypes)
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
        multithreading.async<Result<List<PostUiModel>, PostErrorsTypes>> {
            val result: List<PostUiModel>? = getUiPostsUseCase.execute()
            return@async if (result == null){
                Result.error(PostErrorsTypes.POSTS_NOT_LOADED)
            } else {
                Result.success(result)
            }
        }.postOnMainThread(::showResult)
    }

    fun detachView() {
        view = null
        cancelableOperation?.cancel()
    }

    //private fun showResult(resultList: List<PostUiModel>?) {
    private fun showResult(resultList: Result<List<PostUiModel>, PostErrorsTypes>) {
        if (resultList.isError){
            view?.showError(resultList.errorResult)
        } else {
            view?.showInfo(resultList.successResult)
        }

    }
}
