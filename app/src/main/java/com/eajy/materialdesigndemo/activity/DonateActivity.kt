package com.eajy.materialdesigndemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.eajy.materialdesigndemo.R
//import com.eajy.materialdesigndemo.billing.IabBroadcastReceiver

class DonateActivity : AppCompatActivity()/*, IabBroadcastReceiver.IabBroadcastListener*/ {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        initView()
    }

    private fun initView() {
        val toolbar = findViewById<View>(R.id.toolbar_donate) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

//    override fun receivedBroadcast() {
//
//    }
}

