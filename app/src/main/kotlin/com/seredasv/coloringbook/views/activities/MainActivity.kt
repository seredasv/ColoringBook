package com.seredasv.coloringbook.views.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.Toolbar
import android.widget.FrameLayout
import com.seredasv.coloringbook.R
import com.seredasv.coloringbook.helpers.showToastShort
import com.seredasv.coloringbook.views.fragments.MainColoringTabsFragment

class MainActivity : AppCompatActivity() {
    private val BACKPRESS_MILLIS: Long = 2000
    private val LAST_FRAGMENT_COUNT: Int = 1

    private var backPressedTime: Long = 0

    private lateinit var container: FrameLayout
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findView()

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            openFragment(MainColoringTabsFragment.newInstance(), true)
        }
    }

    private fun findView() {
        toolbar = findViewById(R.id.toolbar) as Toolbar
        container = findViewById(R.id.container) as FrameLayout
    }

    override fun onBackPressed() {
        var count: Int = fragmentManager.backStackEntryCount

        if (count == LAST_FRAGMENT_COUNT) {
            if (backPressedTime + BACKPRESS_MILLIS > System.currentTimeMillis()) {
                this.finish()
            } else {
                showToastShort(this, getString(R.string.backpress_exit))
            }
            backPressedTime = System.currentTimeMillis();
        } else {
            System.gc()
            Runtime.getRuntime().gc()
            fragmentManager.popBackStack()
        }
    }

    fun openFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        if (addToBackStack) {
            transaction.addToBackStack(fragment.javaClass.simpleName)
                    .replace(R.id.container, fragment, fragment.javaClass.simpleName).commit()
        } else {
            transaction.replace(R.id.container, fragment).commit()
        }
    }
}
