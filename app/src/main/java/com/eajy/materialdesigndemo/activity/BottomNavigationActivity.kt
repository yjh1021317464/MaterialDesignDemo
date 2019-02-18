package com.eajy.materialdesigndemo.activity

import android.animation.ArgbEvaluator
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.eajy.materialdesigndemo.R

class BottomNavigationActivity : AppCompatActivity() {

    private var viewPager: ViewPager? = null
    private var navigation: BottomNavigationView? = null
    private var viewList: List<View>? = null

    private val pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            val evaluator = ArgbEvaluator()
            var evaluate = when (position) {
                0 -> evaluator.evaluate(positionOffset,
                        resources.getColor(R.color.app_blue),
                        resources.getColor(R.color.app_green)) as Int
                1 -> evaluator.evaluate(positionOffset,
                        resources.getColor(R.color.app_green),
                        resources.getColor(R.color.app_yellow)) as Int
                2 -> evaluator.evaluate(positionOffset,
                        resources.getColor(R.color.app_yellow),
                        resources.getColor(R.color.app_red)) as Int
                else -> resources.getColor(R.color.app_red)
            }
            (viewPager!!.parent as View).setBackgroundColor(evaluate)
        }

        override fun onPageSelected(position: Int) {
            when (position) {
                0 -> navigation!!.selectedItemId = R.id.bottom_navigation_blue
                1 -> navigation!!.selectedItemId = R.id.bottom_navigation_green
                2 -> navigation!!.selectedItemId = R.id.bottom_navigation_yellow
                3 -> navigation!!.selectedItemId = R.id.bottom_navigation_red
            }
        }

        override fun onPageScrollStateChanged(state: Int) {

        }
    }

    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.bottom_navigation_blue -> {
                        viewPager!!.currentItem = 0
                        true
                    }
                    R.id.bottom_navigation_green -> {
                        viewPager!!.currentItem = 1
                        true
                    }
                    R.id.bottom_navigation_yellow -> {
                        viewPager!!.currentItem = 2
                        true
                    }
                    R.id.bottom_navigation_red -> {
                        viewPager!!.currentItem = 3
                        true
                    }
                    else -> false
                }
            }

    private val pagerAdapter = object : PagerAdapter() {

        override fun getCount() = viewList!!.size

        override fun isViewFromObject(view: View, `object`: Any) =
                view === `object`

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(viewList!![position])
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(viewList!![position])
            return viewList!![position]
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        val window = window
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.argb(33, 0, 0, 0)

        initView()
    }

    private fun initView() {

        viewList = listOf(
                layoutInflater.inflate(R.layout.item_view_pager_1, null),
                layoutInflater.inflate(R.layout.item_view_pager_2, null),
                layoutInflater.inflate(R.layout.item_view_pager_3, null),
                layoutInflater.inflate(R.layout.item_view_pager_4, null)
        )

        viewPager = findViewById(R.id.view_pager_bottom_navigation)
        viewPager!!.adapter = pagerAdapter
        viewPager!!.addOnPageChangeListener(pageChangeListener)

        navigation = findViewById(R.id.bottom_navigation)
        navigation!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

}

