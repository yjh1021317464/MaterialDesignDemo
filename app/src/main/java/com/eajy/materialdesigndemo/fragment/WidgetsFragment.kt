package com.eajy.materialdesigndemo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

import com.eajy.materialdesigndemo.R

/**
 * Created by zhang on 2016.08.07.
 */
class WidgetsFragment : Fragment() {

    private var et_main_3: EditText? = null
    //    private AdView ad_view_widget;
    //    private CardView card_ad_widget;
    //    private TextView tv_ad;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val nestedScrollView = inflater.inflate(R.layout.fragment_widgets, container, false) as NestedScrollView
        et_main_3 = nestedScrollView.findViewById(R.id.et_main_3)

        //        ad_view_widget = nestedScrollView.findViewById(R.id.ad_view_widget);
        //        card_ad_widget = nestedScrollView.findViewById(R.id.card_ad_widget);
        //        tv_ad = nestedScrollView.findViewById(R.id.tv_ad);

        return nestedScrollView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        et_main_3!!.requestFocus()

        //        showAd();
    }

    //    public void showAd() {
    //        try {
    //            SharedPreferences sharedPreferences = getContext().getSharedPreferences("app", MODE_PRIVATE);
    //            if (!sharedPreferences.getBoolean("isDonated", false)) {
    //                AdRequest adRequest = new AdRequest.Builder().build();
    //                ad_view_widget.loadAd(adRequest);
    //
    //                Animation animation = new AlphaAnimation(0.0f, 1.0f);
    //                animation.setDuration(500);
    //                tv_ad.setVisibility(View.VISIBLE);
    //                tv_ad.startAnimation(animation);
    //                card_ad_widget.setVisibility(View.VISIBLE);
    //                card_ad_widget.startAnimation(animation);
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }

}
