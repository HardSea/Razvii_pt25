package com.pmacademy.razvii_pt21.di

import android.content.Context
import com.pmacademy.razvii_pt21.ui.PostViewModelFactory
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun context(): Context
    fun getPostViewFactory(): PostViewModelFactory
// inject fun don`t create lateinit variables
}