package com.eajy.materialdesigndemo.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.eajy.materialdesigndemo.R
import com.eajy.materialdesigndemo.model.MyAppsModel
import com.eajy.materialdesigndemo.util.AppUtils

class MyAppsAdapter(
        private val context: Context,
        private val items: List<MyAppsModel>)
    : RecyclerView.Adapter<MyAppsAdapter.MyAppsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAppsHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_my_apps, parent, false)
        return MyAppsHolder(view)
    }

    override fun onBindViewHolder(holder: MyAppsHolder, position: Int) {

        holder.setIsRecyclable(false)

        val (name, description, imageUri, packageName, googlePlayUrl)
                = items[holder.adapterPosition]
//        val resId = context.resources.getIdentifier(imageUri, "drawable", context.packageName)
//
//        holder.image_app.setImageResource(resId)

        holder.image_app.setImageURI(Uri.parse(imageUri))

        holder.apply {
            tv_app_name.text = name
            tv_app_description.text = description

            // 启动 google play
            itemView.setOnClickListener {
                var intent = Intent()
                if (AppUtils.checkAppInstalled(context, packageName)) {
                    intent = context.packageManager.getLaunchIntentForPackage(packageName) as Intent
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or
                            Intent.FLAG_ACTIVITY_CLEAR_TOP
                    context.startActivity(intent)
                } else {
                    intent.data = Uri.parse(googlePlayUrl)
                    intent.action = Intent.ACTION_VIEW
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount() = items.size

    data class MyAppsHolder(private val mView: View)
        : RecyclerView.ViewHolder(mView) {

        val image_app: ImageView = mView.findViewById(R.id.image_app)
        val tv_app_name: TextView = mView.findViewById(R.id.tv_app_name)
        val tv_app_description: TextView = mView.findViewById(R.id.tv_app_description)

    }

}
