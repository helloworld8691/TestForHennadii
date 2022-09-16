package com.sts.viktor_test.di.module

import android.content.Context
import com.google.gson.Gson
import com.sts.viktor_test.BaseApplication
//import com.sts.viktor_test.network.exceptions.ApiError
import com.sts.viktor_test.ui.common.CoroutineContextProvider
import com.sts.viktor_test.utils.rx.AppSchedulerProvider
import com.sts.viktor_test.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule(private val app : BaseApplication) {

    @Provides
    @Singleton
    fun provideContext() : Context = app.applicationContext

    @Provides
    fun provideCoroutineContext(): CoroutineContextProvider {
        return CoroutineContextProvider()
    }

    @Provides
    internal fun provideSchedulerProvider() : SchedulerProvider = AppSchedulerProvider()

    @Provides
    internal fun provideCompositeDispossible() : CompositeDisposable = CompositeDisposable()
}