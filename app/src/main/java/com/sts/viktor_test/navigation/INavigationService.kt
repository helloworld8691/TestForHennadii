package com.sts.viktor_test.navigation

import android.content.Context
import com.google.android.material.bottomnavigation.BottomNavigationView

interface INavigationService {

    fun attachToActivity(context: Context, bottomNavigationView: BottomNavigationView)

    fun detachFromActivity()

    fun backToMainActivity()

    fun openHomeScreen()

    fun openNewsScreen()

    fun openSearchScreen()

    fun openProfileScreen()

    fun openMoreScreen()

    fun openFilterScreen()

    fun openFilterSectorScreen()

    fun navigationBack()
}