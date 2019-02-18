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

    private lateinit var img_main_card2_share: ImageView
    private lateinit var img_main_card2_bookmark: ImageView
    private lateinit var img_main_card2_favorite: ImageView
    private lateinit var ll_card_main3_rate: LinearLayout
    private lateinit var btn_card_main1_action1: Button
    private lateinit var btn_card_main1_action2: Button

    private lateinit var img_main_card_1: ImageView
    private lateinit var img_main_card_2: ImageView
    private lateinit var img_card_main_3: ImageView
    private lateinit var img_main_card_41: ImageView
    private lateinit var img_main_card_42: ImageView

    private lateinit var img_main_card41_favorite: ImageView
    private lateinit var img_main_card42_favorite: ImageView
    private lateinit var img_main_card41_bookmark: ImageView
    private lateinit var img_main_card42_bookmark: ImageView
    private lateinit var img_main_card41_share: ImageView
    private lateinit var img_main_card42_share: ImageView

    private lateinit var card_main_1_1: CardView
    private lateinit var card_main_1_2: CardView
    private lateinit var card_main_1_3: CardView
    private lateinit var card_main_1_4_1: CardView
    private lateinit var card_main_1_4_2: CardView

    private lateinit var alphaAnimation: AlphaAnimation
    private lateinit var alphaAnimationShowIcon: AlphaAnimation

    private var isBookmarkClicked = false
    private var isFavoriteClicked = false
    private var isBookmark41Clicked = false
    private var isBookmark42Clicked = false
    private var isFavorite41Clicked = false
    private var isFavorite42Clicked = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val nestedScrollView = inflater
                .inflate(R.layout.fragment_cards, container, false) as NestedScrollView

        nestedScrollView.apply {
            btn_card_main1_action1 = findViewById(R.id.btn_card_main1_action1)
            btn_card_main1_action2 = findViewById(R.id.btn_card_main1_action2)
            img_main_card2_share = findViewById(R.id.img_main_card2_share)
            img_main_card2_bookmark = findViewById(R.id.img_main_card2_bookmark)
            img_main_card2_favorite = findViewById(R.id.img_main_card2_favorite)
            ll_card_main3_rate = findViewById(R.id.ll_card_main3_rate)

            img_main_card_1 = findViewById(R.id.img_main_card_1)
            img_main_card_2 = findViewById(R.id.img_main_card_2)
            img_card_main_3 = findViewById(R.id.img_card_main_3)
            img_main_card_41 = findViewById(R.id.img_main_card_41)
            img_main_card_42 = findViewById(R.id.img_main_card_42)

            img_main_card41_favorite = findViewById(R.id.img_main_card41_favorite)
            img_main_card42_favorite = findViewById(R.id.img_main_card42_favorite)
            img_main_card41_bookmark = findViewById(R.id.img_main_card41_bookmark)
            img_main_card42_bookmark = findViewById(R.id.img_main_card42_bookmark)
            img_main_card41_share = findViewById(R.id.img_main_card41_share)
            img_main_card42_share = findViewById(R.id.img_main_card42_share)

            card_main_1_1 = findViewById(R.id.card_main_1_1)
            card_main_1_2 = findViewById(R.id.card_main_1_2)
            card_main_1_3 = findViewById(R.id.card_main_1_3)
            card_main_1_4_1 = findViewById(R.id.card_main_1_4_1)
            card_main_1_4_2 = findViewById(R.id.card_main_1_4_2)
        }

        Glide.with(context!!).apply {
            load(R.drawable.material_design_2)
                    .apply(RequestOptions().fitCenter()).into(img_main_card_1)
            load(R.drawable.material_design_4)
                    .apply(RequestOptions().fitCenter()).into(img_main_card_2)
            load(R.drawable.material_design_11)
                    .apply(RequestOptions().fitCenter()).into(img_card_main_3)
            load(R.drawable.material_design_1)
                    .apply(RequestOptions().fitCenter()).into(img_main_card_41)
            load(R.drawable.material_design_1)
                    .apply(RequestOptions().fitCenter()).into(img_main_card_42)
        }

        return nestedScrollView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_card_main1_action1.setOnClickListener(this)
        btn_card_main1_action2.setOnClickListener(this)
        img_main_card2_bookmark.setOnClickListener(this)
        img_main_card2_favorite.setOnClickListener(this)
        img_main_card2_share.setOnClickListener(this)
        ll_card_main3_rate.setOnClickListener(this)

        img_main_card41_favorite.setOnClickListener(this)
        img_main_card42_favorite.setOnClickListener(this)
        img_main_card41_bookmark.setOnClickListener(this)
        img_main_card42_bookmark.setOnClickListener(this)
        img_main_card41_share.setOnClickListener(this)
        img_main_card42_share.setOnClickListener(this)

        card_main_1_1.setOnClickListener(this)
        card_main_1_2.setOnClickListener(this)
        card_main_1_3.setOnClickListener(this)
        card_main_1_4_1.setOnClickListener(this)
        card_main_1_4_2.setOnClickListener(this)

        card_main_1_1.setOnTouchListener(this)
        card_main_1_2.setOnTouchListener(this)
        card_main_1_3.setOnTouchListener(this)
        card_main_1_4_1.setOnTouchListener(this)
        card_main_1_4_2.setOnTouchListener(this)

        alphaAnimation = AlphaAnimation(0.0f, 1.0f)
        alphaAnimation.duration = 700
        img_main_card_1.startAnimation(alphaAnimation)
        img_main_card_2.startAnimation(alphaAnimation)

        alphaAnimationShowIcon = AlphaAnimation(0.2f, 1.0f)
        alphaAnimationShowIcon.duration = 500

    }

    override fun onClick(view: View) {
        when (view.id) {

            R.id.btn_card_main1_action1 -> {
                Snackbar.make(view, getString(R.string.main_flat_button_1_clicked),
                        Snackbar.LENGTH_SHORT)
                        .show()
            }

            R.id.btn_card_main1_action2 -> {
                Snackbar.make(view, getString(R.string.main_flat_button_2_clicked),
                        Snackbar.LENGTH_SHORT)
                        .show()
            }

            R.id.img_main_card2_bookmark -> {
                isBookmarkClicked = if (!isBookmarkClicked) {
                    img_main_card2_bookmark.setImageResource(R.drawable.ic_bookmark_black_24dp)
                    img_main_card2_bookmark.startAnimation(alphaAnimationShowIcon)
                    true
                } else {
                    img_main_card2_bookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp)
                    img_main_card2_bookmark.startAnimation(alphaAnimationShowIcon)
                    false
                }
            }

            R.id.img_main_card2_favorite -> {
                isFavoriteClicked = if (!isFavoriteClicked) {
                    img_main_card2_favorite.setImageResource(R.drawable.ic_favorite_black_24dp)
                    img_main_card2_favorite.startAnimation(alphaAnimationShowIcon)
                    img_main_card2_favorite.startAnimation(alphaAnimationShowIcon)
                    true
                } else {
                    img_main_card2_favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                    img_main_card2_favorite.startAnimation(alphaAnimationShowIcon)
                    img_main_card2_favorite.startAnimation(alphaAnimationShowIcon)
                    false
                }
            }

            R.id.img_main_card41_favorite -> {
                isFavorite41Clicked = if (!isFavorite41Clicked) {
                    img_main_card41_favorite.setImageResource(R.drawable.ic_favorite_black_24dp)
                    img_main_card41_favorite.startAnimation(alphaAnimationShowIcon)
                    true
                } else {
                    img_main_card41_favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                    img_main_card41_favorite.startAnimation(alphaAnimationShowIcon)
                    false
                }
            }

            R.id.img_main_card42_favorite -> {
                isFavorite42Clicked = if (!isFavorite42Clicked) {
                    img_main_card42_favorite.setImageResource(R.drawable.ic_favorite_black_24dp)
                    img_main_card42_favorite.startAnimation(alphaAnimationShowIcon)
                    true
                } else {
                    img_main_card42_favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                    img_main_card42_favorite.startAnimation(alphaAnimationShowIcon)
                    false
                }
            }
            R.id.img_main_card41_bookmark -> {
                isBookmark41Clicked = if (!isBookmark41Clicked) {
                    img_main_card41_bookmark.setImageResource(R.drawable.ic_bookmark_black_24dp)
                    img_main_card41_bookmark.startAnimation(alphaAnimationShowIcon)
                    true
                } else {
                    img_main_card41_bookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp)
                    img_main_card41_bookmark.startAnimation(alphaAnimationShowIcon)
                    false
                }
            }

            R.id.img_main_card42_bookmark -> {
                isBookmark42Clicked = if (!isBookmark42Clicked) {
                    img_main_card42_bookmark.setImageResource(R.drawable.ic_bookmark_black_24dp)
                    img_main_card42_bookmark.startAnimation(alphaAnimationShowIcon)
                    true
                } else {
                    img_main_card42_bookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp)
                    img_main_card42_bookmark.startAnimation(alphaAnimationShowIcon)
                    false
                }
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
                val upAnim = ObjectAnimator
                        .ofFloat(view, "translationZ", 16f)
                upAnim.duration = 150
                upAnim.interpolator = DecelerateInterpolator()
                upAnim.start()
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                val downAnim = ObjectAnimator
                        .ofFloat(view, "translationZ", 0f)
                downAnim.duration = 150
                downAnim.interpolator = AccelerateInterpolator()
                downAnim.start()
            }
        }
        return false
    }

}
