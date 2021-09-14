package hunseong.com.simple_test_mvvm.state

import hunseong.com.simple_test_mvvm.data.entity.ResultEntity

sealed class ResultState {
    object Uninitialized: ResultState()

    object Loading: ResultState()

    data class Success(
        val results: List<ResultEntity>
    ) : ResultState()

    object Error: ResultState()
}
