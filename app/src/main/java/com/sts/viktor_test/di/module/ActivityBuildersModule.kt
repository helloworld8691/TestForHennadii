package com.sts.viktor_test.di.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.sts.viktor_test.MainActivity
import com.sts.viktor_test.navigation.INavigationService
import com.sts.viktor_test.navigation.NavigationService
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            FragmentModules::class
        ]
    )
    abstract fun contributeMainActivity() : MainActivity

    @Module(includes = [FragmentModules::class])
    companion object  {
        @Provides
        @Singleton
        @JvmStatic
        fun cicerone() : Cicerone<Router> {
            return Cicerone.create()
        }

        @Provides
        @JvmStatic
        @Singleton
        fun navigationService(cicerone: Cicerone<Router>) : INavigationService{
            return NavigationService(cicerone)
        }
    }
}
