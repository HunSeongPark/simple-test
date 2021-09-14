package hunseong.com.simple_test_mvvm.data.preference

import android.content.Context
import android.content.SharedPreferences

class AppPreferenceManager(
    private val context: Context
) {

    companion object {
        const val PREFERENCES_NAME = "preference_name"
        private const val DEFAULT_VALUE_INT = -1
        private const val DEFAULT_VALUE_STRING = ""

        const val KEY_SAD_PERCENT = "key_sad_percent"
        const val KEY_EXCITED_PERCENT = "key_excited_percent"

        const val KEY_FIRST_ANSWER = "key_first_answer"
        const val KEY_SECOND_ANSWER = "key_second_answer"
        const val KEY_THIRD_ANSWER = "key_third_answer"
        const val KEY_FOURTH_ANSWER = "key_fourth_answer"

    }

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    private val prefs by lazy { getPreferences(context) }

    private val editor by lazy { prefs.edit() }


    fun setInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String): Int {
        return prefs.getInt(key, DEFAULT_VALUE_INT)
    }

    fun setString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key:String): String? {
        return prefs.getString(key, DEFAULT_VALUE_STRING)
    }



    fun clear() {
        editor.clear()
        editor.apply()
    }

}