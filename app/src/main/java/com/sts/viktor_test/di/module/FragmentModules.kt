package com.sts.viktor_test.di.module

import com.sts.viktor_test.ui.filter.FilterFragment
import com.sts.viktor_test.ui.home.HomeFragment
import com.sts.viktor_test.ui.more.MoreFragment
import com.sts.viktor_test.ui.news.NewsFragment
import com.sts.viktor_test.ui.profile.ProfileFragment
import com.sts.viktor_test.ui.search.SearchFragment
import com.sts.viktor_test.ui.filter.FilterSectorFragment
import com.sts.viktor_test.ui.search.SortBottomSheetFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModules {
    @ContributesAndroidInjector(
        modules = [
            HomeViewModelModule::class
        ]
    )
    abstract fun homeFragment() : HomeFragment

    @ContributesAndroidInjector(
        modules = [NewsViewModelModule::class]
    )
    abstract fun newsFragment() : NewsFragment

    @ContributesAndroidInjector(
        modules = [SearchViewModelModule::class]
    )
    abstract fun searchScreen() : SearchFragment

    @ContributesAndroidInjector(
        modules = [SearchViewModelModule::class]
    )
    abstract fun searchSectorScreen() : FilterSectorFragment

    @ContributesAndroidInjector(
        modules = [ProfileViewModelModule::class]
    )
    abstract fun profileScreen() : ProfileFragment

    @ContributesAndroidInjector(
        modules = [MoreViewModelModule::class]
    )
    abstract fun moreScreen() : MoreFragment

    @ContributesAndroidInjector(
        modules = [SearchViewModelModule::class]
    )
    abstract fun filterScreen() : FilterFragment

    @ContributesAndroidInjector(
        modules = [SearchViewModelModule::class]
    )
    abstract fun shortBottomSheet() : SortBottomSheetFragment
}