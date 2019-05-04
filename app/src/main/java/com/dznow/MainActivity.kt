package com.dznow

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

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
                active = homeFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_for_you -> {
                fManager.beginTransaction().hide(active).show(forYouFragment).commit()
                active = forYouFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_browse -> {
                fManager.beginTransaction().hide(active).show(browseFragment).commit()
                active = browseFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_bookmarks -> {
                fManager.beginTransaction().hide(active).show(bookmarksFragment).commit()
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
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        fManager.beginTransaction().add(R.id.fragment_container, bookmarksFragment, "4").hide(bookmarksFragment)
            .commit()
        fManager.beginTransaction().add(R.id.fragment_container, browseFragment, "3").hide(browseFragment).commit()
        fManager.beginTransaction().add(R.id.fragment_container, forYouFragment, "2").hide(forYouFragment).commit()
        fManager.beginTransaction().add(R.id.fragment_container, homeFragment, "1").commit()
    }
}
