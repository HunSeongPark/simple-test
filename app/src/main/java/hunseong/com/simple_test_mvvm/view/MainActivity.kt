package hunseong.com.simple_test_mvvm.view

import android.util.Log
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import hunseong.com.simple_test_mvvm.R
import hunseong.com.simple_test_mvvm.databinding.ActivityMainBinding
import hunseong.com.simple_test_mvvm.view.base.BaseActivity
import hunseong.com.simple_test_mvvm.view.my.MyFragment
import hunseong.com.simple_test_mvvm.view.question.fifth.FifthFragment
import hunseong.com.simple_test_mvvm.view.question.first.FirstFragment
import hunseong.com.simple_test_mvvm.view.question.fourth.FourthFragment
import hunseong.com.simple_test_mvvm.view.question.second.SecondFragment
import hunseong.com.simple_test_mvvm.view.question.third.ThirdFragment
import hunseong.com.simple_test_mvvm.view.result.ResultFragment
import hunseong.com.simple_test_mvvm.view.test.TestFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(),
    NavigationBarView.OnItemSelectedListener {

    override val viewModel by viewModel<MainViewModel>()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {
        showFragment(TestFragment.newInstance(), TestFragment.TAG)
        bottomNav.setOnItemSelectedListener(this@MainActivity)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_test -> {
                showFragment(TestFragment.newInstance(), TestFragment.TAG)
                true
            }
            R.id.menu_my -> {
                showFragment(MyFragment.newInstance(), MyFragment.TAG)
                true
            }
            else -> false
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment, tag)
            .commitAllowingStateLoss()
    }

    override fun onBackPressed() {

        when (supportFragmentManager.fragments.last { it.isVisible }) {

            is FirstFragment -> {
                binding.bottomNav.isVisible = true
                viewModel.clearPreference()
            }

            is SecondFragment -> {
                viewModel.undoPercent(1)
            }

            is ThirdFragment -> {
                viewModel.undoPercent(2)
            }

            is FourthFragment -> {
                viewModel.undoPercent(3)
            }

            is FifthFragment -> {
                viewModel.undoPercent(4)
            }

            is ResultFragment -> return
        }
        super.onBackPressed()
    }

}