package com.pmacademy.razvii_pt21.tools

import android.content.Context
import android.util.Log
import com.pmacademy.razvii_pt21.data.PostRepository
import com.pmacademy.razvii_pt21.data.UserInfoLocalDataProvider
import com.pmacademy.razvii_pt21.domain.PostMapper
import com.pmacademy.razvii_pt21.tools.threading.Multithreading
import com.pmacademy.razvii_pt21.ui.GetUiPostsUseCase
import com.pmacademy.razvii_pt21.ui.PostPresenter
import com.pmacademy.razvii_pt21.ui.PostUiMapper
import com.pmacademy.razvii_pt21.ui.ResourceRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object PostComponent {
    fun createPresenter(context: Context): PostPresenter {
        return PostPresenter(
            multithreading = Multithreading(context),
            getUiPostsUseCase = GetUiPostsUseCase(
                postUiMapper = PostUiMapper(
                    resourceRepository = ResourceRepository(context)
                ),
                postRepository = PostRepository(
                    postService = createService(),
                    postMapper = PostMapper(UserInfoLocalDataProvider().getLocalSetStatusUser())
                )
            )
        )
    }

    private fun createService(): PostService {
        Log.d("mainss", "createService: ")
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostService::class.java)
    }
}