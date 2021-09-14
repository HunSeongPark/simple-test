package hunseong.com.simple_test_mvvm.view.question.fourth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager
import hunseong.com.simple_test_mvvm.util.ButtonType
import hunseong.com.simple_test_mvvm.view.base.BaseViewModel
import hunseong.com.simple_test_mvvm.state.ProcessState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FourthViewModel(
    private val preferenceManager: AppPreferenceManager,
) : BaseViewModel() {

    private val _processLiveData = MutableLiveData<ProcessState>(ProcessState.Wait)
    val processLiveData: LiveData<ProcessState>
        get() = _processLiveData

    fun calculatePercent(buttonType: ButtonType): Job = viewModelScope.launch {
        val sadPercent = preferenceManager.getInt(AppPreferenceManager.KEY_SAD_PERCENT)
        val excitedPercent = preferenceManager.getInt(AppPreferenceManager.KEY_EXCITED_PERCENT)
        when (buttonType) {
            ButtonType.DEPRESSED -> {
                preferenceManager.setInt(AppPreferenceManager.KEY_SAD_PERCENT, sadPercent + 10)
                preferenceManager.setInt(AppPreferenceManager.KEY_EXCITED_PERCENT,
                    excitedPercent - 10)
                preferenceManager.setString(AppPreferenceManager.KEY_FOURTH_ANSWER, "depressed")
            }
            ButtonType.SAD -> {
                preferenceManager.setInt(AppPreferenceManager.KEY_SAD_PERCENT, sadPercent + 5)
                preferenceManager.setInt(AppPreferenceManager.KEY_EXCITED_PERCENT,
                    excitedPercent - 5)
                preferenceManager.setString(AppPreferenceManager.KEY_FOURTH_ANSWER, "sad")
            }
            ButtonType.GOOD -> {
                preferenceManager.setInt(AppPreferenceManager.KEY_SAD_PERCENT, sadPercent - 5)
                preferenceManager.setInt(AppPreferenceManager.KEY_EXCITED_PERCENT,
                    excitedPercent + 5)
                preferenceManager.setString(AppPreferenceManager.KEY_FOURTH_ANSWER, "good")
            }
            ButtonType.EXCITED -> {
                preferenceManager.setInt(AppPreferenceManager.KEY_SAD_PERCENT, sadPercent - 10)
                preferenceManager.setInt(AppPreferenceManager.KEY_EXCITED_PERCENT,
                    excitedPercent + 10)
                preferenceManager.setString(AppPreferenceManager.KEY_FOURTH_ANSWER, "excited")
            }
        }
        _processLiveData.postValue(ProcessState.Success)
    }
}