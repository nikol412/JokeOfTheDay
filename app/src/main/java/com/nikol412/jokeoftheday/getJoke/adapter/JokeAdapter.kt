package com.nikol412.jokeoftheday.getJoke.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikol412.jokeoftheday.api.JokeResponse
import com.nikol412.jokeoftheday.databinding.JokeItemRowBinding

class JokeAdapter(private val onItemClick: onItemClick) : RecyclerView.Adapter<JokeItemViewHolder>() {

    private var jokeList: MutableList<JokeResponse> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeItemViewHolder {
        return JokeItemViewHolder(
                JokeItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: JokeItemViewHolder, position: Int) {
        holder.onBind(jokeList[position], onItemClick)
    }

    override fun getItemCount(): Int = jokeList.size

    fun setItem(item: JokeResponse) {
        jokeList.add(item)
        notifyItemInserted(jokeList.lastIndex)
    }

    fun setItems(items: List<JokeResponse>) {
        if (jokeList.isEmpty()) {
            jokeList = items.toMutableList()
            notifyDataSetChanged()
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areContentsTheSame(
                        oldItemPosition: Int,
                        newItemPosition: Int
                ): Boolean = jokeList[oldItemPosition] == items[newItemPosition]

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = jokeList[oldItemPosition] == items[newItemPosition]

                override fun getNewListSize(): Int = items.size

                override fun getOldListSize(): Int = items.size
            })

            jokeList = items.toMutableList()
            result.dispatchUpdatesTo(this)
        }
    }
}

class JokeItemViewHolder(private val binding: JokeItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: JokeResponse, clickListener: onItemClick) {
        binding.linearLayoutRoot.setOnClickListener {
            clickListener.onCLick(binding.textViewSetup, binding.textViewPunch)
        }
        binding.textViewSetup.text = item.setup
        binding.textViewPunch.text = item.punchline
    }
}

interface onItemClick {
    fun onCLick(preTextView: TextView, postTextView: TextView)
}