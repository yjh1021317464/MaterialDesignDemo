package com.eajy.materialdesigndemo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.eajy.materialdesigndemo.R

/**
 * Created by zhang on 2016.08.07.
 */
class WidgetsFragment : Fragment() {

    private lateinit var et_main_3: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val nestedScrollView = inflater
                .inflate(R.layout.fragment_widgets, container, false)
        et_main_3 = nestedScrollView.findViewById(R.id.et_main_3)
        return nestedScrollView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        et_main_3.requestFocus()
    }

}
