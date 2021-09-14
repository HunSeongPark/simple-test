package hunseong.com.simple_test_mvvm.view.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import hunseong.com.simple_test_mvvm.R
import kotlinx.coroutines.Job

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {

    abstract val viewModel: VM

    protected lateinit var binding: VB

    abstract fun getViewBinding(): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        initViews()
        bindView()
    }

    abstract fun initViews()

    abstract fun bindView()

    abstract fun observeData()

    abstract fun showFragment(fragment: Fragment, tag: String)

}