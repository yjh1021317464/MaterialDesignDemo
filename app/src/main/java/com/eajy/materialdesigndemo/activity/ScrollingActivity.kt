package com.eajy.materialdesigndemo.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eajy.materialdesigndemo.Constant
import com.eajy.materialdesigndemo.R

class ScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fab = findViewById<FloatingActionButton>(R.id.fab_scrolling)
        fab.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, Constant.SHARE_CONTENT)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, getString(R.string.share_with)))
        }

        val image_scrolling_top = findViewById<ImageView>(R.id.image_scrolling_top)
        Glide.with(this)
                .load(R.drawable.material_design_3)
                .apply(RequestOptions().fitCenter())
                .into(image_scrolling_top)
    }

    override fun onResume() {
        super.onResume()

        val configuration = resources.configuration
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

            val collapsing_toolbar_layout =
                    findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar_layout)
            collapsing_toolbar_layout
                    .setExpandedTitleTextColor(ColorStateList.valueOf(Color.TRANSPARENT))
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
    }

}
