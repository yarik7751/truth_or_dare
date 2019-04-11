package by.yarik.truth_or_dare.core

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.StringRes

interface IResourceManager {

    fun getColor(@ColorRes res: Int): Int

    fun getString(@StringRes res: Int): String

    fun getString(@StringRes res: Int, vararg args: Any?): String
}