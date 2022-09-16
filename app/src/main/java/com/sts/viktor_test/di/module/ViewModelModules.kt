package com.sts.viktor_test.di.module

import androidx.lifecycle.ViewModel
import com.sts.viktor_test.di.ViewModelKey
import com.sts.viktor_test.ui.home.HomeViewModel
import com.sts.viktor_test.ui.more.MoreViewModel
import com.sts.viktor_test.ui.news.NewsViewModel
import com.sts.viktor_test.ui.profile.ProfileViewModel
import com.sts.viktor_test.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module

import dagger.multibindings.IntoMap


@Module
abstract  class HomeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel) : ViewModel
}

@Module
abstract  class NewsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(newsViewModel: NewsViewModel) : ViewModel
}

@Module
abstract  class SearchViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindNewsViewModel(searchViewModel: SearchViewModel) : ViewModel
}

@Module
abstract  class ProfileViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindNewsViewModel(profileViewModel: ProfileViewModel) : ViewModel
}

@Module
abstract  class MoreViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoreViewModel::class)
    abstract fun bindNewsViewModel(moreViewModel: MoreViewModel) : ViewModel
}