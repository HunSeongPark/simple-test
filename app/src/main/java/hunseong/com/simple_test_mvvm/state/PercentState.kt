package hunseong.com.simple_test_mvvm.state

sealed class PercentState {
    object Loading: PercentState()

    data class Success(
        val sadPercent: Int,
        val excitedPercent: Int
    ) : PercentState()
}
