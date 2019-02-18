package com.eajy.materialdesigndemo.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eajy.materialdesigndemo.R

/**
 * Created by zhang on 2016.08.07.
 */
class CardsFragment : Fragment(), View.OnClickListener, View.OnTouchListener {

    private var img_main_card2_share: ImageView? = null
    private var img_main_card2_bookmark: ImageView? = null
    private var img_main_card2_favorite: ImageView? = null
    private var isBookmarkClicked: Boolean = false
    private var isFavoriteClicked: Boolean = false
    private var isBookmark41Clicked: Boolean = false
    private var isBookmark42Clicked: Boolean = false
    private var isFavorite41Clicked: Boolean = false
    private var isFavorite42Clicked: Boolean = false
    private var ll_card_main3_rate: LinearLayout? = null
    private var btn_card_main1_action1: Button? = null
    private var btn_card_main1_action2: Button? = null
    private var img_main_card_1: ImageView? = null
    private var img_main_card_2: ImageView? = null
    private var img_card_main_3: ImageView? = null
    private var img_main_card_41: ImageView? = null
    private var img_main_card_42: ImageView? = null
    private var img_main_card41_favorite: ImageView? = null
    private var img_main_card42_favorite: ImageView? = null
    private var img_main_card41_bookmark: ImageView? = null
    private var img_main_card42_bookmark: ImageView? = null
    private var img_main_card41_share: ImageView? = null
    private var img_main_card42_share: ImageView? = null
    private var card_main_1_1: CardView? = null
    private var card_main_1_2: CardView? = null
    private var card_main_1_3: CardView? = null
    private var card_main_1_4_1: CardView? = null
    private var card_main_1_4_2: CardView? = null
    private var alphaAnimation: AlphaAnimation? = null
    private var alphaAnimationShowIcon: AlphaAnimation? = null
    //    private AdView ad_view_card;
    private var card_ad_card: CardView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val nestedScrollView = inflater.inflate(R.layout.fragment_cards, container, false) as NestedScrollView

        btn_card_main1_action1 = nestedScrollView.findViewById(R.id.btn_card_main1_action1)
        btn_card_main1_action2 = nestedScrollView.findViewById(R.id.btn_card_main1_action2)
        img_main_card2_share = nestedScrollView.findViewById(R.id.img_main_card2_share)
        img_main_card2_bookmark = nestedScrollView.findViewById(R.id.img_main_card2_bookmark)
        img_main_card2_favorite = nestedScrollView.findViewById(R.id.img_main_card2_favorite)
        ll_card_main3_rate = nestedScrollView.findViewById(R.id.ll_card_main3_rate)

        img_main_card_1 = nestedScrollView.findViewById(R.id.img_main_card_1)
        img_main_card_2 = nestedScrollView.findViewById(R.id.img_main_card_2)
        img_card_main_3 = nestedScrollView.findViewById(R.id.img_card_main_3)
        img_main_card_41 = nestedScrollView.findViewById(R.id.img_main_card_41)
        img_main_card_42 = nestedScrollView.findViewById(R.id.img_main_card_42)

        img_main_card41_favorite = nestedScrollView.findViewById(R.id.img_main_card41_favorite)
        img_main_card42_favorite = nestedScrollView.findViewById(R.id.img_main_card42_favorite)
        img_main_card41_bookmark = nestedScrollView.findViewById(R.id.img_main_card41_bookmark)
        img_main_card42_bookmark = nestedScrollView.findViewById(R.id.img_main_card42_bookmark)
        img_main_card41_share = nestedScrollView.findViewById(R.id.img_main_card41_share)
        img_main_card42_share = nestedScrollView.findViewById(R.id.img_main_card42_share)

        card_main_1_1 = nestedScrollView.findViewById(R.id.card_main_1_1)
        card_main_1_2 = nestedScrollView.findViewById(R.id.card_main_1_2)
        card_main_1_3 = nestedScrollView.findViewById(R.id.card_main_1_3)
        card_main_1_4_1 = nestedScrollView.findViewById(R.id.card_main_1_4_1)
        card_main_1_4_2 = nestedScrollView.findViewById(R.id.card_main_1_4_2)

        Glide.with(context!!).load(R.drawable.material_design_2).apply(RequestOptions().fitCenter()).into(img_main_card_1!!)
        Glide.with(context!!).load(R.drawable.material_design_4).apply(RequestOptions().fitCenter()).into(img_main_card_2!!)
        Glide.with(context!!).load(R.drawable.material_design_11).apply(RequestOptions().fitCenter()).into(img_card_main_3!!)
        Glide.with(context!!).load(R.drawable.material_design_1).apply(RequestOptions().fitCenter()).into(img_main_card_41!!)
        Glide.with(context!!).load(R.drawable.material_design_1).apply(RequestOptions().fitCenter()).into(img_main_card_42!!)

        //        ad_view_card = nestedScrollView.findViewById(R.id.ad_view_card);
        card_ad_card = nestedScrollView.findViewById(R.id.card_ad_card)

        return nestedScrollView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_card_main1_action1!!.setOnClickListener(this)
        btn_card_main1_action2!!.setOnClickListener(this)
        img_main_card2_bookmark!!.setOnClickListener(this)
        img_main_card2_favorite!!.setOnClickListener(this)
        img_main_card2_share!!.setOnClickListener(this)
        ll_card_main3_rate!!.setOnClickListener(this)

        img_main_card41_favorite!!.setOnClickListener(this)
        img_main_card42_favorite!!.setOnClickListener(this)
        img_main_card41_bookmark!!.setOnClickListener(this)
        img_main_card42_bookmark!!.setOnClickListener(this)
        img_main_card41_share!!.setOnClickListener(this)
        img_main_card42_share!!.setOnClickListener(this)

        card_main_1_1!!.setOnClickListener(this)
        card_main_1_2!!.setOnClickListener(this)
        card_main_1_3!!.setOnClickListener(this)
        card_main_1_4_1!!.setOnClickListener(this)
        card_main_1_4_2!!.setOnClickListener(this)

        card_main_1_1!!.setOnTouchListener(this)
        card_main_1_2!!.setOnTouchListener(this)
        card_main_1_3!!.setOnTouchListener(this)
        card_main_1_4_1!!.setOnTouchListener(this)
        card_main_1_4_2!!.setOnTouchListener(this)

        alphaAnimation = AlphaAnimation(0.0f, 1.0f)
        alphaAnimation!!.duration = 700
        img_main_card_1!!.startAnimation(alphaAnimation)
        img_main_card_2!!.startAnimation(alphaAnimation)

        alphaAnimationShowIcon = AlphaAnimation(0.2f, 1.0f)
        alphaAnimationShowIcon!!.duration = 500

        //        showAd();
    }

    override fun onClick(view: View) {
        when (view.id) {

            R.id.btn_card_main1_action1 -> Snackbar.make(view, getString(R.string.main_flat_button_1_clicked), Snackbar.LENGTH_SHORT).show()

            R.id.btn_card_main1_action2 -> Snackbar.make(view, getString(R.string.main_flat_button_2_clicked), Snackbar.LENGTH_SHORT).show()

            R.id.img_main_card2_bookmark -> if (!isBookmarkClicked) {
                img_main_card2_bookmark!!.setImageResource(R.drawable.ic_bookmark_black_24dp)
                img_main_card2_bookmark!!.startAnimation(alphaAnimationShowIcon)
                isBookmarkClicked = true
            } else {
                img_main_card2_bookmark!!.setImageResource(R.drawable.ic_bookmark_border_black_24dp)
                img_main_card2_bookmark!!.startAnimation(alphaAnimationShowIcon)
                isBookmarkClicked = false
            }

            R.id.img_main_card2_favorite -> if (!isFavoriteClicked) {
                img_main_card2_favorite!!.setImageResource(R.drawable.ic_favorite_black_24dp)
                img_main_card2_favorite!!.startAnimation(alphaAnimationShowIcon)
                img_main_card2_favorite!!.startAnimation(alphaAnimationShowIcon)
                isFavoriteClicked = true
            } else {
                img_main_card2_favorite!!.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                img_main_card2_favorite!!.startAnimation(alphaAnimationShowIcon)
                img_main_card2_favorite!!.startAnimation(alphaAnimationShowIcon)
                isFavoriteClicked = false
            }

            R.id.img_main_card41_favorite -> if (!isFavorite41Clicked) {
                img_main_card41_favorite!!.setImageResource(R.drawable.ic_favorite_black_24dp)
                img_main_card41_favorite!!.startAnimation(alphaAnimationShowIcon)
                isFavorite41Clicked = true
            } else {
                img_main_card41_favorite!!.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                img_main_card41_favorite!!.startAnimation(alphaAnimationShowIcon)
                isFavorite41Clicked = false
            }

            R.id.img_main_card42_favorite -> if (!isFavorite42Clicked) {
                img_main_card42_favorite!!.setImageResource(R.drawable.ic_favorite_black_24dp)
                img_main_card42_favorite!!.startAnimation(alphaAnimationShowIcon)
                isFavorite42Clicked = true
            } else {
                img_main_card42_favorite!!.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                img_main_card42_favorite!!.startAnimation(alphaAnimationShowIcon)
                isFavorite42Clicked = false
            }

            R.id.img_main_card41_bookmark -> if (!isBookmark41Clicked) {
                img_main_card41_bookmark!!.setImageResource(R.drawable.ic_bookmark_black_24dp)
                img_main_card41_bookmark!!.startAnimation(alphaAnimationShowIcon)
                isBookmark41Clicked = true
            } else {
                img_main_card41_bookmark!!.setImageResource(R.drawable.ic_bookmark_border_black_24dp)
                img_main_card41_bookmark!!.startAnimation(alphaAnimationShowIcon)
                isBookmark41Clicked = false
            }

            R.id.img_main_card42_bookmark -> if (!isBookmark42Clicked) {
                img_main_card42_bookmark!!.setImageResource(R.drawable.ic_bookmark_black_24dp)
                img_main_card42_bookmark!!.startAnimation(alphaAnimationShowIcon)
                isBookmark42Clicked = true
            } else {
                img_main_card42_bookmark!!.setImageResource(R.drawable.ic_bookmark_border_black_24dp)
                img_main_card42_bookmark!!.startAnimation(alphaAnimationShowIcon)
                isBookmark42Clicked = false
            }

            R.id.img_main_card2_share -> {
            }

            R.id.img_main_card41_share -> {
            }

            R.id.img_main_card42_share -> {
            }

            R.id.ll_card_main3_rate -> {
            }

            R.id.card_main_1_1 -> {
            }

            R.id.card_main_1_2 -> {
            }

            R.id.card_main_1_3 -> {
            }

            R.id.card_main_1_4_1 -> {
            }

            R.id.card_main_1_4_2 -> {
            }
        }
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                val upAnim = ObjectAnimator.ofFloat(view, "translationZ", 16f)
                upAnim.duration = 150
                upAnim.interpolator = DecelerateInterpolator()
                upAnim.start()
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                val downAnim = ObjectAnimator.ofFloat(view, "translationZ", 0f)
                downAnim.duration = 150
                downAnim.interpolator = AccelerateInterpolator()
                downAnim.start()
            }
        }
        return false
    }

    //    public void showAd() {
    //        try {
    //            SharedPreferences sharedPreferences = getContext().getSharedPreferences("app", MODE_PRIVATE);
    //            if (!sharedPreferences.getBoolean("isDonated", false)) {
    //                AdRequest adRequest = new AdRequest.Builder().build();
    //                ad_view_card.loadAd(adRequest);
    //
    //                Animation animation = new AlphaAnimation(0.0f, 1.0f);
    //                animation.setDuration(500);
    //                card_ad_card.setVisibility(View.VISIBLE);
    //                card_ad_card.startAnimation(animation);
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }

}
