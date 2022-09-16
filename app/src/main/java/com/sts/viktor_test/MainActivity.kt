package com.sts.viktor_test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.sts.viktor_test.databinding.ActivityMainBinding
import com.sts.viktor_test.navigation.INavigationService
import com.sts.viktor_test.sqlite.SqliteHelper
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navigationService: INavigationService

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        init()
        setOnBottomNavigationViewClickListener()
        setOnBottomNavigationViewClickListener()

        setContentView(binding.root)
    }

    private fun init(){
        sqliteHelper = SqliteHelper(this)
        navigationService.openHomeScreen()
    }

    private fun setOnBottomNavigationViewClickListener(){
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menuHome -> {
                    navigationService.openHomeScreen()
                }
                R.id.menuNews -> {
                    navigationService.openNewsScreen()
                }
                R.id.menuSearch -> {
                    navigationService.openSearchScreen()
                }
                R.id.menuProfile -> {
                    navigationService.openProfileScreen()
                }
                R.id.menuMore -> {
                    navigationService.openMoreScreen()
                }
            }
            true
        }
    }

    override fun onPause() {
        super.onPause()
        navigationService.detachFromActivity()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationService.attachToActivity(this, binding.bottomNavigationView)
    }

    override fun onDestroy() {
        sqliteHelper.close()
        super.onDestroy()
    }

    companion object {

        lateinit var sqliteHelper : SqliteHelper

        fun getIntent(context: Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}