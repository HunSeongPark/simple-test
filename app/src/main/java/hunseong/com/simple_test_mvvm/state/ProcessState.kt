package hunseong.com.simple_test_mvvm.state

sealed class ProcessState {
    object Wait: ProcessState()

    object Success: ProcessState()
}
