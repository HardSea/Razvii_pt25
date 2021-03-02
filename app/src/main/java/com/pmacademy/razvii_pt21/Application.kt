package com.pmacademy.razvii_pt21

import android.app.Application
import com.pmacademy.razvii_pt21.di.AppComponent
import com.pmacademy.razvii_pt21.di.AppModule
import com.pmacademy.razvii_pt21.di.DaggerAppComponent

class Application: Application() {
    private lateinit var daggerComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        daggerComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getComponent(): AppComponent {
        return daggerComponent
    }
}