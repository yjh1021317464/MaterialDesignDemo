package com.eajy.materialdesigndemo.activity

import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.widget.RelativeLayout
import android.widget.TextView

import com.eajy.materialdesigndemo.R

class ShareViewActivity : AppCompatActivity() {

    private lateinit var tv_share_view_tip: TextView

    private val touchListener = View.OnTouchListener { view, motionEvent ->
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                val upAnim = ObjectAnimator
                        .ofFloat(view, "translationZ", 0f)
                upAnim.duration = 200
                upAnim.interpolator = DecelerateInterpolator()
                upAnim.start()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                val downAnim = ObjectAnimator
                        .ofFloat(view, "translationZ", 20f)
                downAnim.duration = 200
                downAnim.interpolator = AccelerateInterpolator()
                downAnim.start()
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_view)

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar_share_view)
                .apply {
                    subtitle = "Shared Element Transitions"
                    setNavigationIcon(R.drawable.ic_close_white_24dp)
                    setNavigationOnClickListener {
                        onBackPressed()
                    }
                }
        )

        initView()
    }

    private fun initView() {
        val card_share_view: CardView = findViewById(R.id.card_share_view)
        val rela_round_big: RelativeLayout = findViewById<RelativeLayout>(R.id.rela_round_big)
        tv_share_view_tip = findViewById(R.id.tv_share_view_tip)

        if (intent != null) {
            val color = intent.getIntExtra("color", 0)
            rela_round_big.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(
                            when (color) {
                                1 -> R.color.google_blue
                                2 -> R.color.google_green
                                3 -> R.color.google_yellow
                                4 -> R.color.google_red
                                else -> R.color.gray
                            })
                    )
        }

        card_share_view.setOnTouchListener(touchListener)

        val alphaAnimation = AlphaAnimation(1.0f, 0.0f)
        alphaAnimation.duration = 1500
        alphaAnimation.startOffset = 1000
        alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                tv_share_view_tip.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        tv_share_view_tip.startAnimation(alphaAnimation)
    }

}
