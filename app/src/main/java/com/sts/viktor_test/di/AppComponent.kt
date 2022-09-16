package com.sts.viktor_test.di

import android.app.Application
import com.sts.viktor_test.BaseApplication
import com.sts.viktor_test.di.module.ActivityBuildersModule
import com.sts.viktor_test.di.module.AppModule
import com.sts.viktor_test.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class,
        NetworkModule::class
    ]
)

interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application) : Builder

        fun appModule(appModule: AppModule) : Builder

        fun build() : AppComponent
    }

    override fun inject(instance: BaseApplication?) {}
}