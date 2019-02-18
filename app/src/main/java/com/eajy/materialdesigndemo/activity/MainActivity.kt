package com.eajy.materialdesigndemo.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.preference.PreferenceManager
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.eajy.materialdesigndemo.R
import com.eajy.materialdesigndemo.adapter.FragmentAdapter
import com.eajy.materialdesigndemo.fragment.CardsFragment
import com.eajy.materialdesigndemo.fragment.DialogsFragment
import com.eajy.materialdesigndemo.fragment.WidgetsFragment

class MainActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    companion object {
        private var isShowPageStart = true
        private const val MESSAGE_SHOW_DRAWER_LAYOUT = 0x001
        private const val MESSAGE_SHOW_START_PAGE = 0x002
    }

    private lateinit var drawer: DrawerLayout
    private lateinit var fab: FloatingActionButton
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager
    private lateinit var relative_main: RelativeLayout
    private lateinit var img_page_start: ImageView

    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MESSAGE_SHOW_DRAWER_LAYOUT -> {
                    drawer.openDrawer(GravityCompat.START)
                    val sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("isFirst", false)
                    editor.apply()
                }
                MESSAGE_SHOW_START_PAGE -> {
                    val alphaAnimation = AlphaAnimation(1.0f, 0.0f)
                    alphaAnimation.duration = 300
                    alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation) {

                        }

                        override fun onAnimationEnd(animation: Animation) {
                            relative_main.visibility = View.GONE
                        }

                        override fun onAnimationRepeat(animation: Animation) {

                        }
                    })
                    relative_main.startAnimation(alphaAnimation)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findView()
        initView()
        initViewPager()
        initPreferences()
    }

    private fun findView() {
        relative_main = findViewById(R.id.relative_main)
        img_page_start = findViewById(R.id.img_page_start)
        drawer = findViewById(R.id.drawer)
        fab = findViewById(R.id.fab_main)
        mTabLayout = findViewById(R.id.tab_layout_main)
        mViewPager = findViewById(R.id.view_pager_main)
    }

    private fun initView() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.itemIconTintList = null

        val headerView = navigationView.getHeaderView(0)
        val nav_header = headerView.findViewById<LinearLayout>(R.id.nav_header)
        nav_header.setOnClickListener(this)

        fab.setOnClickListener(this)

    }

    private fun initViewPager() {

        // 添加 TAB 的三项
        val titles = arrayListOf(
                getString(R.string.tab_title_main_1),
                getString(R.string.tab_title_main_2),
                getString(R.string.tab_title_main_3)
        )
        titles.forEach {
            mTabLayout.addTab(mTabLayout.newTab().setText(it))
        }

        val fragments = arrayListOf(
                CardsFragment(),
                DialogsFragment(),
                WidgetsFragment()
        )

        val mFragmentAdapter =
                FragmentAdapter(supportFragmentManager, fragments, titles)

        mTabLayout.setupWithViewPager(mViewPager)
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapter)
        mViewPager.offscreenPageLimit = 2

        mViewPager.adapter = mFragmentAdapter
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                // 切换到 TAB 的第三页才显示 FAB
                if (position == 2) {
                    fab!!.show()
                } else {
                    fab!!.hide()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

    private fun initPreferences() {
        try {
            PreferenceManager.setDefaultValues(this, R.xml.preferences_settings, false)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE)

        if (isShowPageStart) {
            relative_main.visibility = View.VISIBLE
            Glide.with(this@MainActivity)
                    .load(R.drawable.ic_launcher_big)
                    .into(img_page_start)
            mHandler.sendEmptyMessageDelayed(MESSAGE_SHOW_START_PAGE,
                    if (sharedPreferences.getBoolean("isFirst", true)) 2000 else 1000)
            isShowPageStart = false
        }

        if (sharedPreferences.getBoolean("isFirst", true)) {
            mHandler.sendEmptyMessageDelayed(MESSAGE_SHOW_DRAWER_LAYOUT, 2500)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.nav_header -> {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                drawer.closeDrawer(GravityCompat.START)
            }

            R.id.fab_main -> {
                Snackbar.make(view, getString(R.string.main_snack_bar), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.main_snack_bar_action)) { }
                        .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_menu_main_about -> {
                val aboutIntent = Intent(this, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
            R.id.action_menu_main_donate -> {
                val donateIntent = Intent(this, DonateActivity::class.java)
                startActivity(donateIntent)
            }
            R.id.action_menu_main_my_app -> {
                val myAppsIntent = Intent(this, MyAppsActivity::class.java)
                startActivity(myAppsIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val intent = Intent()

        when (item.itemId) {
            R.id.nav_recycler_and_swipe_refresh -> {
                intent.setClass(this, RecyclerViewActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_scrolling -> {
                intent.setClass(this, ScrollingActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_full_screen -> {
                intent.setClass(this, FullscreenActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_bottom_navigation -> {
                intent.setClass(this, BottomNavigationActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_settings -> {
                intent.setClass(this, SettingsActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_about -> {
                intent.setClass(this, AboutActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_donate -> {
                intent.setClass(this, DonateActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_my_apps -> {
                intent.setClass(this, MyAppsActivity::class.java)
                startActivity(intent)
            }
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        mHandler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

}
