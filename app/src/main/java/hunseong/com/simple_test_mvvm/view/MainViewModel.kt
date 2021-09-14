package hunseong.com.simple_test_mvvm.view

import androidx.lifecycle.viewModelScope
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager.Companion.KEY_EXCITED_PERCENT
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager.Companion.KEY_FIRST_ANSWER
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager.Companion.KEY_FOURTH_ANSWER
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager.Companion.KEY_SAD_PERCENT
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager.Companion.KEY_SECOND_ANSWER
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager.Companion.KEY_THIRD_ANSWER
import hunseong.com.simple_test_mvvm.view.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val preferenceManager: AppPreferenceManager
) : BaseViewModel() {

    fun clearPreference() = viewModelScope.launch {
        preferenceManager.clear()
    }

    fun undoPercent(undoPosition: Int) = viewModelScope.launch {
        val result = when(undoPosition) {
            1 -> preferenceManager.getString(KEY_FIRST_ANSWER)
            2 -> preferenceManager.getString(KEY_SECOND_ANSWER)
            3 -> preferenceManager.getString(KEY_THIRD_ANSWER)
            4 -> preferenceManager.getString(KEY_FOURTH_ANSWER)
            else -> null
        }

        val sadPercent = preferenceManager.getInt(KEY_SAD_PERCENT)
        val excitedPercent = preferenceManager.getInt(KEY_EXCITED_PERCENT)

        when (result) {
            "depressed" -> {
                preferenceManager.setInt(KEY_SAD_PERCENT, sadPercent - 10)
                preferenceManager.setInt(KEY_EXCITED_PERCENT, excitedPercent + 10)
            }
            "sad" -> {
                preferenceManager.setInt(KEY_SAD_PERCENT, sadPercent - 5)
                preferenceManager.setInt(KEY_EXCITED_PERCENT, excitedPercent + 5)
            }
            "good" -> {
                preferenceManager.setInt(KEY_SAD_PERCENT, sadPercent + 5)
                preferenceManager.setInt(KEY_EXCITED_PERCENT, excitedPercent - 5)
            }
            "excited" -> {
                preferenceManager.setInt(KEY_SAD_PERCENT, sadPercent + 10)
                preferenceManager.setInt(KEY_EXCITED_PERCENT, excitedPercent - 10)
            }
            else -> Unit
        }
    }
}