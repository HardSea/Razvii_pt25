package com.pmacademy.razvii_pt21.ui

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ResourceRepository(private val context: Context) {
    fun getColor(@ColorRes colorRes: Int) = ContextCompat.getColor(context, colorRes)

    fun getString(@StringRes stringRes: Int) = context.resources.getString(stringRes)

    fun getStringWithValue(@StringRes stringRes: Int, stringValue: String) = context.resources.getString(stringRes, stringValue)
}