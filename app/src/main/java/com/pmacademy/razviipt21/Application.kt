package com.pmacademy.razviipt21

import android.app.Application
import com.pmacademy.razviipt21.di.AppComponent
import com.pmacademy.razviipt21.di.AppModule
import com.pmacademy.razviipt21.di.DaggerAppComponent

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
