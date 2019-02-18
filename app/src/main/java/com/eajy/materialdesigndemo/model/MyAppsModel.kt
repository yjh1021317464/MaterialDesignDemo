package com.eajy.materialdesigndemo.model

import java.io.Serializable

data class MyAppsModel(
        val name: String,
        val description: String,
        val imageUrl: String,
        val packageName: String,
        val googlePlayUrl: String
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }
}
