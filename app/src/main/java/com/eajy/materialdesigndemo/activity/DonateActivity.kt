package com.eajy.materialdesigndemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.eajy.materialdesigndemo.R

class DonateActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        initView()
    }

    private fun initView() {
        setSupportActionBar(findViewById(R.id.toolbar_donate))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<Button>(R.id.btn_donate_low)
                .setOnClickListener(this)
        findViewById<Button>(R.id.btn_donate_high)
                .setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_donate_low -> {
                //todo
            }
            R.id.btn_donate_high -> {
                //todo
            }
        }
    }
}

