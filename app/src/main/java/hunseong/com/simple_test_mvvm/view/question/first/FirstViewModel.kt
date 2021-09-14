package hunseong.com.simple_test_mvvm.view.question.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager.Companion.KEY_FIRST_ANSWER
import hunseong.com.simple_test_mvvm.util.ButtonType
import hunseong.com.simple_test_mvvm.view.base.BaseViewModel
import hunseong.com.simple_test_mvvm.state.ProcessState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FirstViewModel(
    private val preferenceManager: AppPreferenceManager,
) : BaseViewModel() {

    private val _processLiveData = MutableLiveData<ProcessState>(ProcessState.Wait)
    val processLiveData: LiveData<ProcessState>
        get() = _processLiveData

    fun calculatePercent(buttonType: ButtonType): Job = viewModelScope.launch {
        when (buttonType) {
            ButtonType.DEPRESSED -> {
                preferenceManager.setInt(AppPreferenceManager.KEY_SAD_PERCENT, 60)
                preferenceManager.setInt(AppPreferenceManager.KEY_EXCITED_PERCENT, 40)
                preferenceManager.setString(KEY_FIRST_ANSWER, "depressed")
            }
            ButtonType.SAD -> {
                preferenceManager.setInt(AppPreferenceManager.KEY_SAD_PERCENT, 55)
                preferenceManager.setInt(AppPreferenceManager.KEY_EXCITED_PERCENT, 45)
                preferenceManager.setString(KEY_FIRST_ANSWER, "sad")
            }
            ButtonType.GOOD -> {
                preferenceManager.setInt(AppPreferenceManager.KEY_SAD_PERCENT, 45)
                preferenceManager.setInt(AppPreferenceManager.KEY_EXCITED_PERCENT, 55)
                preferenceManager.setString(KEY_FIRST_ANSWER, "good")
            }
            ButtonType.EXCITED -> {
                preferenceManager.setInt(AppPreferenceManager.KEY_SAD_PERCENT, 40)
                preferenceManager.setInt(AppPreferenceManager.KEY_EXCITED_PERCENT, 60)
                preferenceManager.setString(KEY_FIRST_ANSWER, "excited")
            }
        }
        _processLiveData.postValue(ProcessState.Success)
    }
}