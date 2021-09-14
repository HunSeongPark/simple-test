package hunseong.com.simple_test_mvvm.view.result

import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import hunseong.com.simple_test_mvvm.R
import hunseong.com.simple_test_mvvm.data.entity.ResultEntity
import hunseong.com.simple_test_mvvm.databinding.FragmentResultBinding
import hunseong.com.simple_test_mvvm.view.base.BaseFragment
import hunseong.com.simple_test_mvvm.state.PercentState
import hunseong.com.simple_test_mvvm.view.test.TestFragment
import kotlinx.coroutines.Job
import org.koin.android.viewmodel.ext.android.viewModel

class ResultFragment : BaseFragment<ResultViewModel, FragmentResultBinding>() {


    override val viewModel by viewModel<ResultViewModel>()

    override fun getViewBinding(): FragmentResultBinding =
        FragmentResultBinding.inflate(layoutInflater)

    override fun bindView() {
        viewModel.getPercent()

        binding.mainButton.setOnClickListener {
            showFragment(TestFragment.newInstance(), TAG)
        }
    }

    override fun observeData() = viewModel.percentStateLiveData.observe(viewLifecycleOwner) {
        when (it) {
            is PercentState.Loading -> Unit
            is PercentState.Success -> handleSuccess(it)
        }
    }

    private fun handleSuccess(state: PercentState.Success) {
        val resultText =
            when (state.sadPercent) {
                in 0..15 -> "Excited! 🤩"
                in 16..40 -> "Good! 😁"
                in 41..50 -> "SoSo.. 😐"
                in 51..70 -> "Sad... 🥲"
                in 71..100 -> "worst. 🤮"
                else -> ""
            }
        val percentText = "sad : ${state.sadPercent}% / excited : ${state.excitedPercent}%"
        binding.resultTextView.text = resultText
        binding.percentTextView.text = percentText
        viewModel.saveResult(ResultEntity(
            percent = percentText,
            result = resultText,
            id = System.currentTimeMillis()
        ))
    }

    companion object {
        const val TAG = "resultFragment"
        fun newInstance(): ResultFragment = ResultFragment()
    }

    override fun showFragment(fragment: Fragment, tag: String) {
        for (i in 0 until requireActivity().supportFragmentManager.backStackEntryCount) {
            requireActivity().supportFragmentManager.popBackStack()
        }

        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav).isVisible = true
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.horizon_enter_front,
                R.anim.none,
                R.anim.none,
                R.anim.horizon_exit_front)
            .show(fragment).commitAllowingStateLoss()
        viewModel.clearPreference()
    }

    override fun initViews() = Unit
}