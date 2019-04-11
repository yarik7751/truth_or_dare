package by.yarik.truth_or_dare.core

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.StringRes

class ResourceManager(private val context: Context?) : IResourceManager {

    override fun getColor(@ColorRes res: Int): Int {
        return context!!.resources.getColor(res)
    }

    override fun getString(@StringRes res: Int): String {
        return context!!.getString(res)
    }

    override fun getString(@StringRes res: Int, vararg args: Any?): String {
        return context!!.getString(res, args)
    }
}