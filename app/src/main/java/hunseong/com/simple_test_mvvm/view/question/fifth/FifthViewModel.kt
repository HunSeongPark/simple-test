package hunseong.com.simple_test_mvvm.view.question.fifth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager.Companion.KEY_EXCITED_PERCENT
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager.Companion.KEY_SAD_PERCENT
import hunseong.com.simple_test_mvvm.util.ButtonType
import hunseong.com.simple_test_mvvm.view.base.BaseViewModel
import hunseong.com.simple_test_mvvm.state.ProcessState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FifthViewModel(
    private val preferenceManager: AppPreferenceManager,
) : BaseViewModel() {

    private val _processLiveData = MutableLiveData<ProcessState>(ProcessState.Wait)
    val processLiveData: LiveData<ProcessState>
        get() = _processLiveData

    fun calculatePercent(buttonType: ButtonType): Job = viewModelScope.launch {
        val sadPercent = preferenceManager.getInt(KEY_SAD_PERCENT)
        val excitedPercent = preferenceManager.getInt(KEY_EXCITED_PERCENT)
        when (buttonType) {
            ButtonType.DEPRESSED -> {
                preferenceManager.setInt(KEY_SAD_PERCENT, sadPercent + 10)
                preferenceManager.setInt(KEY_EXCITED_PERCENT, excitedPercent - 10)
            }
            ButtonType.SAD -> {
                preferenceManager.setInt(KEY_SAD_PERCENT, sadPercent + 5)
                preferenceManager.setInt(KEY_EXCITED_PERCENT, excitedPercent - 5)
            }
            ButtonType.GOOD -> {
                preferenceManager.setInt(KEY_SAD_PERCENT, sadPercent - 5)
                preferenceManager.setInt(KEY_EXCITED_PERCENT, excitedPercent + 5)
            }
            ButtonType.EXCITED -> {
                preferenceManager.setInt(KEY_SAD_PERCENT, sadPercent - 10)
                preferenceManager.setInt(KEY_EXCITED_PERCENT, excitedPercent + 10)
            }
        }
        _processLiveData.postValue(ProcessState.Success)
    }
}