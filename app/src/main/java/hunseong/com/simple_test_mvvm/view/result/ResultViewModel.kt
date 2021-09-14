package hunseong.com.simple_test_mvvm.view.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hunseong.com.simple_test_mvvm.data.entity.ResultEntity
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager.Companion.KEY_EXCITED_PERCENT
import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager.Companion.KEY_SAD_PERCENT
import hunseong.com.simple_test_mvvm.data.repository.ResultRepository
import hunseong.com.simple_test_mvvm.view.base.BaseViewModel
import hunseong.com.simple_test_mvvm.state.PercentState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ResultViewModel(
    private val preferenceManager: AppPreferenceManager,
    private val resultRepository: ResultRepository,
) : BaseViewModel() {

    private val _percentStateLiveData = MutableLiveData<PercentState>(PercentState.Loading)
    val percentStateLiveData: LiveData<PercentState>
        get() = _percentStateLiveData

    fun getPercent() = viewModelScope.launch {
        val sadPercent = preferenceManager.getInt(KEY_SAD_PERCENT)
        val excitedPercent = preferenceManager.getInt(KEY_EXCITED_PERCENT)
        _percentStateLiveData.postValue(PercentState.Success(sadPercent, excitedPercent))
    }

    fun clearPreference() = viewModelScope.launch {
        preferenceManager.clear()
    }

    fun saveResult(resultEntity: ResultEntity) = viewModelScope.launch {
        resultRepository.insertResult(resultEntity)
    }
}