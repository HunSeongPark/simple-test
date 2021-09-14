package hunseong.com.simple_test_mvvm.view.question.first

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import hunseong.com.simple_test_mvvm.R
import hunseong.com.simple_test_mvvm.databinding.FragmentFirstBinding
import hunseong.com.simple_test_mvvm.util.ButtonType
import hunseong.com.simple_test_mvvm.view.base.BaseFragment
import hunseong.com.simple_test_mvvm.view.question.second.SecondFragment
import hunseong.com.simple_test_mvvm.state.ProcessState
import org.koin.android.viewmodel.ext.android.viewModel

class FirstFragment : BaseFragment<FirstViewModel, FragmentFirstBinding>() {

    override val viewModel by viewModel<FirstViewModel>()


    override fun getViewBinding(): FragmentFirstBinding =
        FragmentFirstBinding.inflate(layoutInflater)

    override fun bindView() = with(binding) {
        depressedButton.setOnClickListener {
            viewModel.calculatePercent(ButtonType.DEPRESSED)
        }
        sadButton.setOnClickListener {
            viewModel.calculatePercent(ButtonType.SAD)
        }
        goodButton.setOnClickListener {
            viewModel.calculatePercent(ButtonType.GOOD)
        }
        excitedButton.setOnClickListener {
            viewModel.calculatePercent(ButtonType.EXCITED)
        }
    }

    override fun observeData() = viewModel.processLiveData.observe(viewLifecycleOwner) {
        when (it) {
            is ProcessState.Wait -> Unit
            is ProcessState.Success -> showFragment(SecondFragment.newInstance(),
                SecondFragment.TAG)
        }

    }

    companion object {
        const val TAG = "firstFragment"
        fun newInstance(): FirstFragment = FirstFragment()

    }

    override fun showFragment(fragment: Fragment, tag: String) {
        val transaction: FragmentTransaction =
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.horizon_enter_front,
                    R.anim.none,
                    R.anim.none,
                    R.anim.horizon_exit_front)
                .add(R.id.fragmentContainer, fragment, tag)
        transaction.addToBackStack(tag).commitAllowingStateLoss()
    }

    override fun initViews() = Unit

}