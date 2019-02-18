package com.eajy.materialdesigndemo.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by zhang on 2016.08.07.
 * Modified by yjh1021317464 on 2019.2.19
 *
 */
class FragmentAdapter(
        fm: FragmentManager,
        private val mFragments: List<Fragment>,
        private val mTitles: List<String>)
    : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) = mFragments[position]

    override fun getCount() = mFragments.size

    override fun getPageTitle(position: Int) = mTitles[position]

}
