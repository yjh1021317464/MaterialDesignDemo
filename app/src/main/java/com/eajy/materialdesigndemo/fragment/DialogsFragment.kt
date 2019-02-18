package com.eajy.materialdesigndemo.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.PopupMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.eajy.materialdesigndemo.R
import java.text.DateFormat
import java.util.*
import kotlin.concurrent.thread

/**
 * Created by zhang on 2016.08.07.
 */
class DialogsFragment : Fragment(), View.OnClickListener {

    private lateinit var btn_dialog_1: Button
    private lateinit var btn_dialog_2: Button
    private lateinit var btn_dialog_3: Button
    private lateinit var btn_dialog_4: Button
    private lateinit var btn_dialog_5: Button
    private lateinit var btn_dialog_6: Button
    private lateinit var btn_dialog_7: Button
    private lateinit var btn_dialog_8: Button
    private lateinit var btn_dialog_9: Button
    private lateinit var btn_dialog_10: Button
    private lateinit var btn_dialog_11: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val nestedScrollView = inflater
                .inflate(R.layout.fragment_dialogs, container, false)

        nestedScrollView.apply {
            btn_dialog_1 = findViewById(R.id.btn_dialog_1)
            btn_dialog_2 = findViewById(R.id.btn_dialog_2)
            btn_dialog_3 = findViewById(R.id.btn_dialog_3)
            btn_dialog_4 = findViewById(R.id.btn_dialog_4)
            btn_dialog_5 = findViewById(R.id.btn_dialog_5)
            btn_dialog_6 = findViewById(R.id.btn_dialog_6)
            btn_dialog_7 = findViewById(R.id.btn_dialog_7)
            btn_dialog_8 = findViewById(R.id.btn_dialog_8)
            btn_dialog_9 = findViewById(R.id.btn_dialog_9)
            btn_dialog_10 = findViewById(R.id.btn_dialog_10)
            btn_dialog_11 = findViewById(R.id.btn_dialog_11)
        }

        return nestedScrollView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arrayOf(
                btn_dialog_1,
                btn_dialog_2,
                btn_dialog_3,
                btn_dialog_4,
                btn_dialog_5,
                btn_dialog_6,
                btn_dialog_7,
                btn_dialog_8,
                btn_dialog_9,
                btn_dialog_10,
                btn_dialog_11
        ).forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_dialog_1 -> {
                AlertDialog.Builder(context!!)
                        .setMessage(getString(R.string.main_dialog_simple_title))
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show()
            }

            R.id.btn_dialog_2 -> {
                AlertDialog.Builder(context!!)
                        .setTitle(getString(R.string.main_dialog_simple_title))
                        .setMessage(getString(R.string.main_dialog_simple_message))
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .setNegativeButton(getString(R.string.dialog_cancel), null)
                        .setNeutralButton(getString(R.string.dialog_neutral), null)
                        .show()
            }

            R.id.btn_dialog_3 -> {
                val singleChoiceItems = resources.getStringArray(R.array.dialog_choice_array)
                val itemSelected = 0
                AlertDialog.Builder(context!!)
                        .setTitle(getString(R.string.main_dialog_single_choice))
                        .setSingleChoiceItems(singleChoiceItems, itemSelected) { dialogInterface, i ->
                            dialogInterface.dismiss()
                        }
                        .setNegativeButton(getString(R.string.dialog_cancel), null)
                        .show()
            }

            R.id.btn_dialog_4 -> {
                val multiChoiceItems = resources.getStringArray(R.array.dialog_choice_array)
                val checkedItems = booleanArrayOf(true, false, false, false, false)
                AlertDialog.Builder(context!!)
                        .setTitle(getString(R.string.main_dialog_multi_choice))
                        .setMultiChoiceItems(multiChoiceItems, checkedItems, null)
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .setNegativeButton(getString(R.string.dialog_cancel), null)
                        .show()
            }

            R.id.btn_dialog_5 -> {
                ProgressDialog(context).apply {
                    setMessage(getString(R.string.main_dialog_progress_title))
                    show()
                }
            }

            R.id.btn_dialog_6 -> {
                val horizontalProgressDialog = ProgressDialog(context).apply {
                    setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                    setMessage(getString(R.string.main_dialog_progress_title))
                    setCancelable(false)
                    max = 100
                    show()
                }

                thread {
                    var progress = 0
                    while (progress <= 100) {
                        horizontalProgressDialog.progress = progress
                        if (progress == 100) {
                            horizontalProgressDialog.dismiss()
                        }
                        try {
                            Thread.sleep(35)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                        progress++
                    }
                }

            }

            R.id.btn_dialog_7 -> {
                val calendar = Calendar.getInstance()
                DatePickerDialog(context!!,
                        DatePickerDialog.OnDateSetListener { view1, year, monthOfYear, dayOfMonth ->
                            calendar.set(Calendar.YEAR, year)
                            calendar.set(Calendar.MONTH, monthOfYear)
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                            btn_dialog_7.text = DateFormat.getDateInstance(DateFormat.MEDIUM)
                                    .format(calendar.time)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            R.id.btn_dialog_8 -> {
                val calendar = Calendar.getInstance()
                TimePickerDialog(context,
                        TimePickerDialog.OnTimeSetListener { timePicker, i, i1 ->
                            calendar.set(Calendar.HOUR_OF_DAY, i)
                            calendar.set(Calendar.MINUTE, i1)
                            btn_dialog_8.text = DateFormat.getTimeInstance(DateFormat.SHORT)
                                    .format(calendar.time)
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                ).show()
            }

            R.id.btn_dialog_9 -> {
                val mBottomSheetDialog = BottomSheetDialog(context!!)
                val dialogView = activity!!.layoutInflater
                        .inflate(R.layout.dialog_bottom_sheet, null)

                val btn_dialog_bottom_sheet_ok: Button =
                        dialogView.findViewById(R.id.btn_dialog_bottom_sheet_ok)
                val btn_dialog_bottom_sheet_cancel: Button =
                        dialogView.findViewById(R.id.btn_dialog_bottom_sheet_cancel)
                val img_bottom_dialog: ImageView =
                        dialogView.findViewById(R.id.img_bottom_dialog)

                Glide.with(context!!)
                        .load(R.drawable.bottom_dialog)
                        .into(img_bottom_dialog)

                mBottomSheetDialog.setContentView(dialogView)

                btn_dialog_bottom_sheet_ok.setOnClickListener {
                    mBottomSheetDialog.dismiss()
                }
                btn_dialog_bottom_sheet_cancel.setOnClickListener {
                    mBottomSheetDialog.dismiss()
                }
                mBottomSheetDialog.show()
            }

            R.id.btn_dialog_10 -> {
                val fullscreenDialog = Dialog(context!!, R.style.DialogFullscreen)
                fullscreenDialog.setContentView(R.layout.dialog_fullscreen)
                val img_full_screen_dialog: ImageView =
                        fullscreenDialog.findViewById(R.id.img_full_screen_dialog)
                val img_dialog_fullscreen_close: ImageView =
                        fullscreenDialog.findViewById(R.id.img_dialog_fullscreen_close)
                Glide.with(context!!)
                        .load(R.drawable.google_assistant)
                        .into(img_full_screen_dialog)
                img_dialog_fullscreen_close.setOnClickListener {
                    fullscreenDialog.dismiss()
                }
                fullscreenDialog.show()
            }

            R.id.btn_dialog_11 -> {
                val popupMenu = PopupMenu(context!!, btn_dialog_11)
                popupMenu.menuInflater
                        .inflate(R.menu.popup_menu_main, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { false }
                popupMenu.show()
            }
        }
    }

}
