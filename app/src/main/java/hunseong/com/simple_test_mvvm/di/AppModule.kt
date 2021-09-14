package hunseong.com.simple_test_mvvm.di

import hunseong.com.simple_test_mvvm.data.preference.AppPreferenceManager
import hunseong.com.simple_test_mvvm.data.repository.ResultRepository
import hunseong.com.simple_test_mvvm.data.repository.ResultRepositoryImpl
import hunseong.com.simple_test_mvvm.view.MainViewModel
import hunseong.com.simple_test_mvvm.view.my.MyViewModel
import hunseong.com.simple_test_mvvm.view.question.fifth.FifthViewModel
import hunseong.com.simple_test_mvvm.view.question.first.FirstViewModel
import hunseong.com.simple_test_mvvm.view.question.fourth.FourthViewModel
import hunseong.com.simple_test_mvvm.view.question.second.SecondViewModel
import hunseong.com.simple_test_mvvm.view.question.third.ThirdViewModel
import hunseong.com.simple_test_mvvm.view.result.ResultViewModel
import hunseong.com.simple_test_mvvm.view.test.TestViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Dispatcher
    single { Dispatchers.IO }

    // ViewModel
    viewModel { MainViewModel(get()) }
    viewModel { MyViewModel(get()) }
    viewModel { TestViewModel() }
    viewModel { FirstViewModel(get()) }
    viewModel { SecondViewModel(get()) }
    viewModel { ThirdViewModel(get()) }
    viewModel { FourthViewModel(get()) }
    viewModel { FifthViewModel(get()) }
    viewModel { ResultViewModel(get(), get()) }

    // Preferences
    single { AppPreferenceManager(androidContext()) }

    // Room DB
    single { provideDB(androidApplication()) }
    single { provideResultDao(get()) }

    // Repository
    single<ResultRepository> { ResultRepositoryImpl(get(),get()) }
}