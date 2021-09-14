package hunseong.com.simple_test_mvvm.view.my

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hunseong.com.simple_test_mvvm.databinding.FragmentMyBinding
import hunseong.com.simple_test_mvvm.state.ResultState
import hunseong.com.simple_test_mvvm.view.base.BaseFragment
import kotlinx.coroutines.Job
import org.koin.android.viewmodel.ext.android.viewModel

class MyFragment : BaseFragment<MyViewModel, FragmentMyBinding>() {

    override val viewModel by viewModel<MyViewModel>()

    override fun getViewBinding(): FragmentMyBinding = FragmentMyBinding.inflate(layoutInflater)

    private val adapter: ResultAdapter by lazy {
        ResultAdapter()
    }

    private lateinit var resultJob: Job

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultJob = viewModel.getAllResults()
    }

    companion object {
        const val TAG = "myFragment"
        fun newInstance() = MyFragment()
    }

    override fun observeData() = viewModel.resultLiveData.observe(viewLifecycleOwner) {
        when (it) {
            is ResultState.Uninitialized -> Unit
            is ResultState.Loading -> {
                handleLoadingState()
            }
            is ResultState.Success -> {
                handleSuccessState(it)
            }
            is ResultState.Error -> {
                handleErrorState()
            }
        }
    }

    private fun handleLoadingState() = with(binding) {
        progressBar.isVisible = true
        recyclerView.isGone = true
        emptyTextView.isGone = true
        errorTextView.isGone = true
    }

    private fun handleSuccessState(state: ResultState.Success) = with(binding) {
        progressBar.isGone = true
        errorTextView.isGone = true
        if (state.results.isNullOrEmpty()) {
            emptyTextView.isVisible = true
            recyclerView.isGone = true
        } else {
            emptyTextView.isGone = true
            recyclerView.isVisible = true
            (recyclerView.adapter as ResultAdapter).apply {
                results = state.results
                notifyDataSetChanged()
            }
        }
    }

    private fun handleErrorState() = with(binding) {
        progressBar.isGone = true
        recyclerView.isGone = true
        emptyTextView.isGone = true
        errorTextView.isVisible = true
    }

    override fun initViews() = with(binding) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    override fun onDestroy() {
        if (resultJob.isActive) {
            resultJob.cancel()
        }
        super.onDestroy()
    }

    override fun showFragment(fragment: Fragment, tag: String) = Unit

    override fun bindView() = Unit

}