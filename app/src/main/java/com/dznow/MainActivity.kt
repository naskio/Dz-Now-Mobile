package com.dznow

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.dznow.browse.BrowseFragment
import com.dznow.home.HomeFragment

// for using ids directly without findViewById
import kotlinx.android.synthetic.main.activity_main.*;
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var fManager: FragmentManager
    private final val homeFragment = HomeFragment()
    private final val forYouFragment = ForYouFragment()
    private final val browseFragment = BrowseFragment()
    private final val bookmarksFragment = BookmarksFragment()
    private lateinit var active: Fragment

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                fManager.beginTransaction().hide(active).show(homeFragment).commit()
                toolbarTitle.text = getString(R.string.app_name);
                active = homeFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_for_you -> {
                fManager.beginTransaction().hide(active).show(forYouFragment).commit()
                toolbarTitle.text = getString(R.string.title_for_you);
                active = forYouFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_browse -> {
                fManager.beginTransaction().hide(active).show(browseFragment).commit()
                toolbarTitle.text = getString(R.string.title_browse);
                active = browseFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_bookmarks -> {
                fManager.beginTransaction().hide(active).show(bookmarksFragment).commit()
                toolbarTitle.text = getString(R.string.title_bookmarks);
                active = bookmarksFragment
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fManager = supportFragmentManager
        active = homeFragment
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        fManager.beginTransaction().add(R.id.fragmentContainer, bookmarksFragment, "4").hide(bookmarksFragment)
            .commit()
        fManager.beginTransaction().add(R.id.fragmentContainer, browseFragment, "3").hide(browseFragment).commit()
        fManager.beginTransaction().add(R.id.fragmentContainer, forYouFragment, "2").hide(forYouFragment).commit()
        fManager.beginTransaction().add(R.id.fragmentContainer, homeFragment, "1").commit()
    }
}
