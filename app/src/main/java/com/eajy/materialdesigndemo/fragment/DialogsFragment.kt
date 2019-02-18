package com.eajy.materialdesigndemo.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
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

/**
 * Created by zhang on 2016.08.07.
 */
class DialogsFragment : Fragment(), View.OnClickListener {

    private var btn_dialog_1: Button? = null
    private var btn_dialog_2: Button? = null
    private var btn_dialog_3: Button? = null
    private var btn_dialog_4: Button? = null
    private var btn_dialog_5: Button? = null
    private var btn_dialog_6: Button? = null
    private var btn_dialog_7: Button? = null
    private var btn_dialog_8: Button? = null
    private var btn_dialog_9: Button? = null
    private var btn_dialog_10: Button? = null
    private var btn_dialog_11: Button? = null
    private var calendar = Calendar.getInstance()
    //    private AdView ad_view_dialog;
//    private var card_ad_dialog: CardView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val nestedScrollView = inflater.inflate(R.layout.fragment_dialogs, container, false) as NestedScrollView

        btn_dialog_1 = nestedScrollView.findViewById(R.id.btn_dialog_1)
        btn_dialog_2 = nestedScrollView.findViewById(R.id.btn_dialog_2)
        btn_dialog_3 = nestedScrollView.findViewById(R.id.btn_dialog_3)
        btn_dialog_4 = nestedScrollView.findViewById(R.id.btn_dialog_4)
        btn_dialog_5 = nestedScrollView.findViewById(R.id.btn_dialog_5)
        btn_dialog_6 = nestedScrollView.findViewById(R.id.btn_dialog_6)
        btn_dialog_7 = nestedScrollView.findViewById(R.id.btn_dialog_7)
        btn_dialog_8 = nestedScrollView.findViewById(R.id.btn_dialog_8)
        btn_dialog_9 = nestedScrollView.findViewById(R.id.btn_dialog_9)
        btn_dialog_10 = nestedScrollView.findViewById(R.id.btn_dialog_10)
        btn_dialog_11 = nestedScrollView.findViewById(R.id.btn_dialog_11)

        //        ad_view_dialog = nestedScrollView.findViewById(R.id.ad_view_dialog);
//        card_ad_dialog = nestedScrollView.findViewById(R.id.card_ad_dialog)

        return nestedScrollView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_dialog_1!!.setOnClickListener(this)
        btn_dialog_2!!.setOnClickListener(this)
        btn_dialog_3!!.setOnClickListener(this)
        btn_dialog_4!!.setOnClickListener(this)
        btn_dialog_5!!.setOnClickListener(this)
        btn_dialog_6!!.setOnClickListener(this)
        btn_dialog_7!!.setOnClickListener(this)
        btn_dialog_8!!.setOnClickListener(this)
        btn_dialog_9!!.setOnClickListener(this)
        btn_dialog_10!!.setOnClickListener(this)
        btn_dialog_11!!.setOnClickListener(this)

        //        showAd();
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_dialog_1 -> AlertDialog.Builder(context!!)
                    .setMessage(getString(R.string.main_dialog_simple_title))
                    .setPositiveButton(getString(R.string.dialog_ok), null)
                    .show()

            R.id.btn_dialog_2 -> AlertDialog.Builder(context!!)
                    .setTitle(getString(R.string.main_dialog_simple_title))
                    .setMessage(getString(R.string.main_dialog_simple_message))
                    .setPositiveButton(getString(R.string.dialog_ok), null)
                    .setNegativeButton(getString(R.string.dialog_cancel), null)
                    .setNeutralButton(getString(R.string.dialog_neutral), null)
                    .show()

            R.id.btn_dialog_3 -> {
                val singleChoiceItems = resources.getStringArray(R.array.dialog_choice_array)
                val itemSelected = 0
                AlertDialog.Builder(context!!)
                        .setTitle(getString(R.string.main_dialog_single_choice))
                        .setSingleChoiceItems(singleChoiceItems, itemSelected) { dialogInterface, i -> dialogInterface.dismiss() }
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
                val progressDialog = ProgressDialog(context)
                progressDialog.setMessage(getString(R.string.main_dialog_progress_title))
                progressDialog.show()
            }

            R.id.btn_dialog_6 -> {
                val horizontalProgressDialog = ProgressDialog(context)
                horizontalProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                horizontalProgressDialog.setMessage(getString(R.string.main_dialog_progress_title))
                horizontalProgressDialog.setCancelable(false)
                horizontalProgressDialog.max = 100
                horizontalProgressDialog.show()

                Thread(object : Runnable {
                    var progress = 0

                    override fun run() {
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
                }).start()
            }

            R.id.btn_dialog_7 -> {
                val datePickerDialog = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.time)
                    btn_dialog_7!!.text = date
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                datePickerDialog.show()
            }

            R.id.btn_dialog_8 -> {
                val timePickerDialog = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener { timePicker, i, i1 ->
                    calendar.set(Calendar.HOUR_OF_DAY, i)
                    calendar.set(Calendar.MINUTE, i1)
                    val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.time)
                    btn_dialog_8!!.text = time
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)
                timePickerDialog.show()
            }

            R.id.btn_dialog_9 -> {
                val mBottomSheetDialog = BottomSheetDialog(context!!)
                val dialogView = activity!!.layoutInflater.inflate(R.layout.dialog_bottom_sheet, null)
                val btn_dialog_bottom_sheet_ok = dialogView.findViewById<Button>(R.id.btn_dialog_bottom_sheet_ok)
                val btn_dialog_bottom_sheet_cancel = dialogView.findViewById<Button>(R.id.btn_dialog_bottom_sheet_cancel)
                val img_bottom_dialog = dialogView.findViewById<ImageView>(R.id.img_bottom_dialog)
                Glide.with(context!!).load(R.drawable.bottom_dialog).into(img_bottom_dialog)
                mBottomSheetDialog.setContentView(dialogView)

                btn_dialog_bottom_sheet_ok.setOnClickListener { mBottomSheetDialog.dismiss() }
                btn_dialog_bottom_sheet_cancel.setOnClickListener { mBottomSheetDialog.dismiss() }
                mBottomSheetDialog.show()
            }

            R.id.btn_dialog_10 -> {
                val fullscreenDialog = Dialog(context!!, R.style.DialogFullscreen)
                fullscreenDialog.setContentView(R.layout.dialog_fullscreen)
                val img_full_screen_dialog = fullscreenDialog.findViewById<ImageView>(R.id.img_full_screen_dialog)
                Glide.with(context!!).load(R.drawable.google_assistant).into(img_full_screen_dialog)
                val img_dialog_fullscreen_close = fullscreenDialog.findViewById<ImageView>(R.id.img_dialog_fullscreen_close)
                img_dialog_fullscreen_close.setOnClickListener { fullscreenDialog.dismiss() }
                fullscreenDialog.show()
            }

            R.id.btn_dialog_11 -> {
                val popupMenu = PopupMenu(context!!, btn_dialog_11!!)
                popupMenu.menuInflater.inflate(R.menu.popup_menu_main, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { false }
                popupMenu.show()
            }
        }
    }

    //    public void showAd() {
    //        try {
    //            SharedPreferences sharedPreferences = getContext().getSharedPreferences("app", MODE_PRIVATE);
    //            if (!sharedPreferences.getBoolean("isDonated", false)) {
    //                AdRequest adRequest = new AdRequest.Builder().build();
    //                ad_view_dialog.loadAd(adRequest);
    //
    //                Animation animation = new AlphaAnimation(0.0f, 1.0f);
    //                animation.setDuration(500);
    //                card_ad_dialog.setVisibility(View.VISIBLE);
    //                card_ad_dialog.startAnimation(animation);
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }

}
