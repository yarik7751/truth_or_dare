package by.yarik.truth_or_dare.sources.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class Preferences(val context: Context?) {

    companion object{
        const val PREFERENCES_KEY = "by.yarik.truth_or_dare_SP"

        const val PREFERENCES_VERSION_DATA = "PREFERENCES_VERSION_DATA"
        const val VERSION_DATA_DEFAULT_VALUE = "-1"

        @SuppressLint("StaticFieldLeak")
        private var preferences: Preferences? = null

        fun getInstance(context: Context?): Preferences? {
            if(preferences == null) {
                preferences = Preferences(context)
            }

            return preferences
        }
    }

    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context!!.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

    fun setDataVersion(version: String) {
        sharedPreferences.edit().putString(PREFERENCES_VERSION_DATA, version).apply()
    }

    fun getDataVersion(): String {
        return sharedPreferences.getString(PREFERENCES_VERSION_DATA, VERSION_DATA_DEFAULT_VALUE)
    }
}