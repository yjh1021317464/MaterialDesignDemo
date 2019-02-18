package com.eajy.materialdesigndemo.activity

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.widget.*
import com.eajy.materialdesigndemo.Constant
import com.eajy.materialdesigndemo.R
import com.eajy.materialdesigndemo.util.AppUtils

class AboutActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_about)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        window.navigationBarColor = resources.getColor(R.color.colorPrimary)

        initView()
    }

    private fun initView() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show)
        val scroll_about = findViewById<ScrollView>(R.id.scroll_about)
        scroll_about.startAnimation(animation)

        val ll_card_about_2_shop = findViewById<LinearLayout>(R.id.ll_card_about_2_shop)
        val ll_card_about_2_email = findViewById<LinearLayout>(R.id.ll_card_about_2_email)
        val ll_card_about_2_git_hub = findViewById<LinearLayout>(R.id.ll_card_about_2_git_hub)
        val ll_card_about_2_website = findViewById<LinearLayout>(R.id.ll_card_about_2_website)
        val ll_card_about_source_licenses = findViewById<LinearLayout>(R.id.ll_card_about_source_licenses)
        ll_card_about_2_shop.setOnClickListener(this)
        ll_card_about_2_email.setOnClickListener(this)
        ll_card_about_2_git_hub.setOnClickListener(this)
        ll_card_about_2_website.setOnClickListener(this)
        ll_card_about_source_licenses.setOnClickListener(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab_about_share)
        fab.setOnClickListener(this)

        val alphaAnimation = AlphaAnimation(0.0f, 1.0f)
        alphaAnimation.duration = 300
        alphaAnimation.startOffset = 600

        val tv_about_version = findViewById<TextView>(R.id.tv_about_version)
        tv_about_version.text = AppUtils.getVersionName(this)
        tv_about_version.startAnimation(alphaAnimation)
    }

    override fun onClick(view: View) {
        val intent = Intent()

        when (view.id) {
            R.id.ll_card_about_2_shop -> {
                intent.data = Uri.parse(Constant.APP_URL)
                intent.action = Intent.ACTION_VIEW
                startActivity(intent)
            }

            R.id.ll_card_about_2_email -> {
                intent.action = Intent.ACTION_SENDTO
                intent.data = Uri.parse(Constant.EMAIL)
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.about_email_intent))
                //intent.putExtra(Intent.EXTRA_TEXT, "Hi,");
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(this@AboutActivity, getString(R.string.about_not_found_email), Toast.LENGTH_SHORT).show()
                }

            }

            R.id.ll_card_about_source_licenses -> {
                val dialog = Dialog(this, R.style.DialogFullscreenWithTitle)
                dialog.setTitle(getString(R.string.about_source_licenses))
                dialog.setContentView(R.layout.dialog_source_licenses)

                val webView = dialog.findViewById<WebView>(R.id.web_source_licenses)
                webView.loadUrl("file:///android_asset/open_source_license.html")

                val btn_source_licenses_close = dialog.findViewById<Button>(R.id.btn_source_licenses_close)
                btn_source_licenses_close.setOnClickListener { dialog.dismiss() }

                dialog.show()
            }

            R.id.ll_card_about_2_git_hub -> {
                intent.data = Uri.parse(Constant.GIT_HUB)
                intent.action = Intent.ACTION_VIEW
                startActivity(intent)
            }

            R.id.ll_card_about_2_website -> {
                intent.data = Uri.parse(Constant.MY_WEBSITE)
                intent.action = Intent.ACTION_VIEW
                startActivity(intent)
            }

            R.id.fab_about_share -> {
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, Constant.SHARE_CONTENT)
                intent.type = "text/plain"
                startActivity(Intent.createChooser(intent, getString(R.string.share_with)))
            }
        }
    }

}
