package com.eajy.materialdesigndemo.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.eajy.materialdesigndemo.R
import com.eajy.materialdesigndemo.activity.ShareViewActivity
import com.eajy.materialdesigndemo.interf.MoveAndSwipedListener
import java.util.*

/**
 * Created by zhang on 2016.08.07.
 */

// todo refactor
class RecyclerViewAdapter(private val context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(), MoveAndSwipedListener {

    private val mItems: MutableList<String>
    private var color = 0
    private lateinit var parentView: View

    private val TYPE_NORMAL = 1
    private val TYPE_FOOTER = 2
    private val TYPE_HEADER = 3
    private val FOOTER = "footer"
    private val HEADER = "header"

    init {
        mItems = ArrayList()
    }

    fun setItems(data: List<String>) {
        this.mItems.addAll(data)
        notifyDataSetChanged()
    }

    fun addItem(position: Int, insertData: String) {
        mItems.add(position, insertData)
        notifyItemInserted(position)
    }

    fun addItems(data: List<String>) {
        mItems.add(HEADER)
        mItems.addAll(data)
        notifyItemInserted(mItems.size - 1)
    }

    fun addHeader() {
        this.mItems.add(HEADER)
        notifyItemInserted(mItems.size - 1)
    }

    fun addFooter() {
        mItems.add(FOOTER)
        notifyItemInserted(mItems.size - 1)
    }

    fun removeFooter() {
        mItems.removeAt(mItems.size - 1)
        notifyItemRemoved(mItems.size)
    }

    fun setColor(color: Int) {
        this.color = color
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        parentView = parent
        return when (viewType) {
            TYPE_NORMAL -> {
                val view = LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_recycler_view, parent, false)
                RecyclerViewHolder(view)
            }
            TYPE_FOOTER -> {
                val view = LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_recycler_footer, parent, false)
                FooterViewHolder(view)
            }
            TYPE_HEADER -> {
                val view = LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_recycler_header, parent, false)
                HeaderViewHolder(view)
            }
            else -> {
                throw Exception()
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is RecyclerViewHolder) {

            val animation = AnimationUtils.loadAnimation(context, R.anim.anim_recycler_item_show)
            holder.mView.startAnimation(animation)

            val aa1 = AlphaAnimation(1.0f, 0.1f)
            aa1.duration = 400
            holder.rela_round.startAnimation(aa1)

            val aa = AlphaAnimation(0.1f, 1.0f)
            aa.duration = 400

            holder.rela_round.backgroundTintList =
                    ColorStateList.valueOf(context.resources.getColor(
                            when (color) {
                                1 -> R.color.google_blue
                                2 -> R.color.google_green
                                3 -> R.color.google_yellow
                                4 -> R.color.google_red
                                else -> R.color.gray
                            }

                    ))

            holder.rela_round.startAnimation(aa)

            holder.mView.setOnClickListener {
                val intent = Intent(context, ShareViewActivity::class.java)
                intent.putExtra("color", color)
                context.startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(
                                context as Activity,
                                holder.rela_round,
                                "shareView"
                        ).toBundle()
                )
            }
        }
    }

    override fun getItemViewType(position: Int) = when (mItems[position]) {
        HEADER -> TYPE_HEADER
        FOOTER -> TYPE_FOOTER
        else -> TYPE_NORMAL
    }

    override fun getItemCount() = mItems.size

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(mItems, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        mItems.removeAt(position)
        notifyItemRemoved(position)

        Snackbar.make(parentView, context.getString(R.string.item_swipe_dismissed), Snackbar.LENGTH_SHORT)
                .setAction(context.getString(R.string.item_swipe_undo)) { addItem(position, mItems[position]) }.show()
    }

    private data class RecyclerViewHolder(
            val mView: View)
        : RecyclerView.ViewHolder(mView) {

        val rela_round: RelativeLayout = mView.findViewById(R.id.rela_round)

    }

    private data class FooterViewHolder(
            val mView: View)
        : RecyclerView.ViewHolder(mView) {

        val progress_bar_load_more: ProgressBar = mView.findViewById(R.id.progress_bar_load_more)

    }

    private data class HeaderViewHolder(
            val mView: View)
        : RecyclerView.ViewHolder(mView) {

        val header_text: TextView = mView.findViewById(R.id.header_text)
    }

}
