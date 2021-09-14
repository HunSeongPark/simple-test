package hunseong.com.simple_test_mvvm.view.my

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hunseong.com.simple_test_mvvm.data.entity.ResultEntity
import hunseong.com.simple_test_mvvm.databinding.ItemTestLogBinding

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    var results: List<ResultEntity> = emptyList()

    inner class ViewHolder(private val binding: ItemTestLogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: ResultEntity) = with(binding) {
            percentTextView.text = result.percent
            resultTextView.text = result.result
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemTestLogBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int = results.size
}