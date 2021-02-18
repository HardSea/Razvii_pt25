package com.pmacademy.razvii_pt21.di

import android.content.Context
import androidx.annotation.NonNull
import com.google.gson.GsonBuilder
import com.pmacademy.razvii_pt21.data.UserInfoLocalDataProvider
import com.pmacademy.razvii_pt21.data.mapper.PostMapper
import com.pmacademy.razvii_pt21.data.repository.PostRepository
import com.pmacademy.razvii_pt21.datasource.local.UserPostsDatabase
import com.pmacademy.razvii_pt21.datasource.remote.api.PostsReposApi
import com.pmacademy.razvii_pt21.ui.PostViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(@NonNull private val context: Context) {

    @Singleton
    @Provides
    @NonNull
    fun context(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(repository: PostRepository): PostViewModelFactory =
        PostViewModelFactory(repository)


    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create(GsonBuilder().setLenient().create())


    @Provides
    @Singleton
    fun providePostsReposApi(retrofit: Retrofit): PostsReposApi =
        retrofit.create(PostsReposApi::class.java)


    @Provides
    @Singleton
    fun providePostMapper(): PostMapper =
        PostMapper(UserInfoLocalDataProvider().getLocalSetStatusUser())


    @Provides
    @Singleton
    fun provideUserPostsDatabase(): UserPostsDatabase =
        UserPostsDatabase.invoke(context)

    @Provides
    @Singleton
    fun providePostRepository(
        postsReposApi: PostsReposApi,
        postMapper: PostMapper,
        postsDatabase: UserPostsDatabase
    ): PostRepository =
        PostRepository(
            postsReposApi = postsReposApi,
            postMapper = postMapper,
            userPostsDatabase = postsDatabase
        )
}