package com.eajy.materialdesigndemo.activity

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.VideoView
import com.eajy.materialdesigndemo.R

class FullscreenActivity : AppCompatActivity() {

    private lateinit var video_fullscreen: VideoView
    private lateinit var relative_fullscreen: RelativeLayout
    private lateinit var progress_fullscreen: ProgressBar

    private var isShowBar: Boolean = false

    private val MESSAGE_HIDE_BARS = 0x001
    private val MESSAGE_VIDEO_PREPARED = 0x002

    var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MESSAGE_HIDE_BARS -> hideBars()
                MESSAGE_VIDEO_PREPARED -> {
                    val animation = AlphaAnimation(1.0f, 0.0f)
                    animation.duration = 500
                    relative_fullscreen.startAnimation(animation)
                    relative_fullscreen.visibility = View.GONE
                }
            }
        }
    }

    /*private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hideBars();
        }
    };*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            hide()
        }

        progress_fullscreen = findViewById(R.id.progress_fullscreen)
        relative_fullscreen = findViewById(R.id.relative_fullscreen)
        video_fullscreen = findViewById(R.id.video_fullscreen)
    }

    private fun playVideo() {
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.full_screen_google)
        video_fullscreen.apply {
            setVideoURI(uri)
            start()
            setOnPreparedListener {
                mHandler.sendEmptyMessageDelayed(MESSAGE_VIDEO_PREPARED, 500)
            }
            setOnCompletionListener {
                start()
            }
            setOnErrorListener { mp, what, extra ->
                progress_fullscreen.visibility = View.VISIBLE
                true
            }
            setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN)
                    if (!isShowBar) {
                        showBars()
                    } else {
                        hideBars()
                    }
                true
            }
        }
    }

    override fun onResume() {
        super.onResume()
        hideBars()
        playVideo()
    }

    override fun onStop() {
        super.onStop()
        relative_fullscreen.visibility = View.VISIBLE
    }

    private fun showBars() {
        isShowBar = true
        video_fullscreen.systemUiVisibility = View.VISIBLE

        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            )
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE

            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.GRAY
            navigationBarColor = Color.GRAY
        }

        mHandler.removeMessages(MESSAGE_HIDE_BARS)
        mHandler.sendEmptyMessageDelayed(MESSAGE_HIDE_BARS, 2000)

        /*mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, 2000);*/
    }

    private fun hideBars() {
        isShowBar = false
        video_fullscreen.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    override fun onDestroy() {
        mHandler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}

