package com.sts.viktor_test.navigation

import android.content.Context
import android.content.Intent
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.sts.viktor_test.ui.filter.FilterFragment
import com.sts.viktor_test.ui.filter.FilterSectorFragment
import com.sts.viktor_test.ui.home.HomeFragment
import com.sts.viktor_test.ui.more.MoreFragment
import com.sts.viktor_test.ui.news.NewsFragment
import com.sts.viktor_test.ui.profile.ProfileFragment
import com.sts.viktor_test.ui.search.SearchFragment


object Screens {
    fun homeScreen() = FragmentScreen{HomeFragment()}
    fun newsScreen() = FragmentScreen{NewsFragment()}
    fun searchScreen() = FragmentScreen{SearchFragment()}
    fun profileScreen() = FragmentScreen{ProfileFragment()}
    fun moreScreen() = FragmentScreen{MoreFragment()}
    fun filterScreen() = FragmentScreen{FilterFragment()}
    fun filterSectorScreen() = FragmentScreen{FilterSectorFragment()}
}