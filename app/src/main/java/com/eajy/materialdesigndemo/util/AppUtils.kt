package com.eajy.materialdesigndemo.util

import android.content.Context
import android.content.pm.PackageManager
import com.eajy.materialdesigndemo.R

/**
 * Created by zhang on 2017.11.22.
 */

object AppUtils {

    fun checkAppInstalled(context: Context, packageName: String): Boolean {
        try {
            context.packageManager
                    .getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return false
        }
        return true
    }

    fun getVersionName(context: Context): String {
        var versionName = ""
        try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            val version = info.versionName
            versionName = context.getString(R.string.about_version) + " " + version
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return versionName
    }

}
