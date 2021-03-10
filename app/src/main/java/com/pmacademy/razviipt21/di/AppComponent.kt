package com.pmacademy.razviipt21.di

import android.content.Context
import com.pmacademy.razviipt21.ui.MainActivity
import com.pmacademy.razviipt21.ui.fragments.CreatePostFragment
import com.pmacademy.razviipt21.ui.fragments.PostListFragment
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun context(): Context

    fun inject(activity: MainActivity)
    fun inject(fragment: PostListFragment)
    fun inject(fragment: CreatePostFragment)

}
