package com.eajy.materialdesigndemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.eajy.materialdesigndemo.Constant
import com.eajy.materialdesigndemo.R
import com.eajy.materialdesigndemo.adapter.MyAppsAdapter
import com.eajy.materialdesigndemo.model.MyAppsModel

class MyAppsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_apps)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_my_apps)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        val recycler_my_apps = findViewById<RecyclerView>(R.id.recycler_my_apps)
        val adapter = MyAppsAdapter(this, initData())
        val linearLayoutManager = LinearLayoutManager(this)
        recycler_my_apps.layoutManager = linearLayoutManager
        recycler_my_apps.adapter = adapter
    }

    private fun initData() = listOf(
            MyAppsModel(
                    name = Constant.MATERIAL_DESIGN_COLOR,
                    description = Constant.MATERIAL_DESIGN_COLOR_DESCRIPTION,
                    packageName = Constant.MATERIAL_DESIGN_COLOR_PACKAGE,
                    googlePlayUrl = Constant.MATERIAL_DESIGN_COLOR_URL,
                    imageUrl = "material_design_color"
            ),
            MyAppsModel(
                    name = Constant.FLUTTER_DEMO,
                    description = Constant.FLUTTER_DEMO_DESCRIPTION,
                    packageName = Constant.FLUTTER_DEMO_PACKAGE,
                    googlePlayUrl = Constant.FLUTTER_DEMO_URL,
                    imageUrl = "flutter_demo"
            ),
            MyAppsModel(
                    name = Constant.X_LAUNCHER,
                    description = Constant.X_LAUNCHER_DESCRIPTION,
                    packageName = Constant.X_LAUNCHER_PACKAGE,
                    googlePlayUrl = Constant.X_LAUNCHER_URL,
                    imageUrl = "x_launcher"
            )
    )

}


