package com.kiteworks.kiteworkskmmpoc.android.presentation

import android.app.Application
import com.kiteworks.kiteworkskmmpoc.android.dependencyinjection.androidModule
import com.kiteworks.kiteworkskmmpoc.dependencyinjection.sharedAndroidModule
import com.kiteworks.kiteworkskmmpoc.dependencyinjection.sharedCommonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(
                sharedCommonModule,
                sharedAndroidModule,
                androidModule,
            )
        }
    }
}
