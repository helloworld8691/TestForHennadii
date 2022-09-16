package com.sts.viktor_test.navigation

import android.content.Context
import android.view.View
import androidx.constraintlayout.solver.GoalRow
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sts.viktor_test.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NavigationService(cicerone: Cicerone<Router>) : INavigationService{

    private val router = cicerone.router
    private val navigationHolder = cicerone.getNavigatorHolder()
    private lateinit var bottomNavigationView : BottomNavigationView

    override fun attachToActivity(context: Context, bottomNavigationView: BottomNavigationView) {
        context as DaggerAppCompatActivity
        navigationHolder.setNavigator(AppNavigator(context, R.id.navigation_holder))
        this.bottomNavigationView = bottomNavigationView
    }

    override fun detachFromActivity() {
        navigationHolder.removeNavigator()
    }

    override fun backToMainActivity() {
        CoroutineScope(Dispatchers.Main).launch {
            bottomNavigationView.visibility = View.VISIBLE
            router.exit()
        }
    }

    override fun openHomeScreen() {
        newRootScreen(Screens.homeScreen())
    }

    override fun openNewsScreen() {
        replaceScreen(Screens.newsScreen())
    }

    override fun openSearchScreen() {
        replaceScreen(Screens.searchScreen())
    }

    override fun openProfileScreen() {
        replaceScreen(Screens.profileScreen())
    }

    override fun openMoreScreen() {
        replaceScreen(Screens.moreScreen())
    }

    override fun openFilterScreen() {
        navigationTo(Screens.filterScreen())
    }

    override fun openFilterSectorScreen() {
        navigationTo(Screens.filterSectorScreen())
    }

    override fun navigationBack() {
        navigateBack()
    }

    private fun navigateBackTo(screen: Screen) {
        CoroutineScope(Dispatchers.Main).launch {
            router.backTo(screen)
        }
    }

    private fun navigateBack(){
        CoroutineScope(Dispatchers.Main).launch {
            router.exit()
        }
    }

    private fun newRootScreen(screen: Screen) {
        CoroutineScope(Dispatchers.Main).launch {
            router.newRootScreen(screen)
        }
    }

    private fun replaceScreen(screen: Screen) {
        CoroutineScope(Dispatchers.Main).launch {
            router.replaceScreen(screen)
        }
    }

    private fun navigationTo(screen: Screen) {
        CoroutineScope(Dispatchers.Main).launch {
            bottomNavigationView.visibility = View.GONE
            router.navigateTo(screen)
        }
    }
}