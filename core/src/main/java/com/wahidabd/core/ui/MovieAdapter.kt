package com.wahidabd.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.core.databinding.ItemMovieBinding
import com.wahidabd.core.domain.model.MovieModel
import com.wahidabd.core.utils.Constant.IMAGE_URL
import com.wahidabd.core.utils.loadImageUrl

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean =
            oldItem == newItem

    }

    private val listDiffer = AsyncListDiffer(this, differCallback)
    var setData: List<MovieModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    private var listener: ((Int) -> Unit)? = null
    fun setOnItemClick(listener: ((Int) -> Unit)?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(setData[position], listener)
    }

    override fun getItemCount(): Int = setData.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: MovieModel, listener: ((Int) -> Unit)?){
                binding.tvTitle.text = data.title
                binding.imgPhoto.loadImageUrl( IMAGE_URL + data.poster_path)

                binding.itemCard.setOnClickListener {
                    listener?.let { listener(data.id) }
                }
            }
        }

}
