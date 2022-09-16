package com.sts.viktor_test


import com.sts.viktor_test.di.DaggerAppComponent
import com.sts.viktor_test.di.module.AppModule
import com.sts.viktor_test.di.module.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .appModule(AppModule(this))
            .build()
    }
}