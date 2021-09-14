package hunseong.com.simple_test_mvvm.view.my

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hunseong.com.simple_test_mvvm.data.repository.ResultRepository
import hunseong.com.simple_test_mvvm.state.ResultState
import hunseong.com.simple_test_mvvm.view.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyViewModel(
    private val resultRepository: ResultRepository,
) : BaseViewModel() {

    private val _resultLiveData = MutableLiveData<ResultState>(ResultState.Uninitialized)
    val resultLiveData: LiveData<ResultState>
        get() = _resultLiveData

    fun getAllResults(): Job = viewModelScope.launch {
        _resultLiveData.postValue(ResultState.Loading)
        try {
            val results = resultRepository.getAllResult().reversed()
            _resultLiveData.postValue(ResultState.Success(results))
        } catch (e: Exception) {
            e.printStackTrace()
            _resultLiveData.postValue(ResultState.Error)
        }
    }

}