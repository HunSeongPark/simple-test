package hunseong.com.simple_test_mvvm.view.test

import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import hunseong.com.simple_test_mvvm.R
import hunseong.com.simple_test_mvvm.databinding.FragmentTestBinding
import hunseong.com.simple_test_mvvm.view.base.BaseFragment
import hunseong.com.simple_test_mvvm.view.question.first.FirstFragment
import org.koin.android.viewmodel.ext.android.viewModel


class TestFragment : BaseFragment<TestViewModel, FragmentTestBinding>() {

    override val viewModel by viewModel<TestViewModel>()

    override fun getViewBinding(): FragmentTestBinding = FragmentTestBinding.inflate(layoutInflater)

    override fun bindView() = with(binding) {
        startButton.setOnClickListener {
            val image: AnimationDrawable = imageView.background as AnimationDrawable
            image.start()
            Handler().postDelayed({
                showFragment(FirstFragment.newInstance(), FirstFragment.TAG)
                image.stop()
            }, 750)
        }
    }

    companion object {
        const val TAG = "testFragment"
        fun newInstance() = TestFragment()
    }

    override fun observeData() = Unit

    override fun showFragment(fragment: Fragment, tag: String) {
        val transaction: FragmentTransaction =
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.horizon_enter_front,
                    R.anim.none,
                    R.anim.none,
                    R.anim.horizon_exit_front)
                .add(R.id.fragmentContainer, fragment, tag)
        transaction.addToBackStack(tag).commitAllowingStateLoss()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav).isGone = true
    }

    override fun initViews() = Unit
}