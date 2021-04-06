package com.nikol412.jokeoftheday.ui.getJoke.adapter

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.nikol412.jokeoftheday.api.JokeResponse
import com.nikol412.jokeoftheday.databinding.JokeItemRowBinding
import com.nikol412.jokeoftheday.db.model.JokeOfficial
import java.util.*

class JokeAdapter(private val onItemClick: onItemClick) :
    RecyclerView.Adapter<JokeItemViewHolder>(), onItemTouchAdapter {

    private var jokeList: MutableList<JokeOfficial> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeItemViewHolder {
        return JokeItemViewHolder(
            JokeItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: JokeItemViewHolder, position: Int) {
        holder.onBind(jokeList[position], onItemClick)
    }

    override fun getItemCount(): Int = jokeList.size

    fun setItem(item: JokeOfficial) {
        jokeList.add(item)
        notifyItemInserted(jokeList.lastIndex)
    }

    fun setItems(items: List<JokeOfficial>) {
        if (jokeList.isEmpty()) {
            jokeList = items.toMutableList()
            notifyDataSetChanged()
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean = jokeList[oldItemPosition] == items[newItemPosition]

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    jokeList[oldItemPosition] == items[newItemPosition]

                override fun getNewListSize(): Int = items.size

                override fun getOldListSize(): Int = jokeList.size
            })

            jokeList = items.toMutableList()
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onItemMove(startPosition: Int, endPosition: Int): Boolean {
        if (startPosition < endPosition) {
            for (i in startPosition until endPosition) {
                Collections.swap(jokeList, i, i + 1)
            }
        } else {
            for (i in endPosition downTo startPosition + 1) {
                Collections.swap(jokeList, i, i - 1)
            }
        }
        notifyItemMoved(startPosition, endPosition)
        return true
    }

    override fun onSwipe(position: Int, direction: Int) {
        onItemClick.onSwipe(position, direction)

        when (direction) {
            ItemTouchHelper.END -> {
                jokeList.getOrNull(position)?.let {
                    jokeList.remove(it)
                }
                notifyItemRemoved(position)
            }
            ItemTouchHelper.START -> {
                //TODO implement saving to favourites with animation
                jokeList.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }
}

class JokeItemViewHolder(private val binding: JokeItemRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: JokeOfficial, clickListener: onItemClick) {
        binding.linearLayoutRoot.setOnClickListener {
            clickListener.onCLick(binding.textViewSetup, binding.textViewPunch)
        }
        binding.textViewSetup.text = item.setup
        binding.textViewPunch.text = item.punchLine
    }

    fun changeBackground(color: Int) {
        binding.linearLayoutRoot.background = ColorDrawable(color)
    }
}

interface onItemClick {
    fun onCLick(preTextView: TextView, postTextView: TextView)

    fun onSwipe(position: Int, direction: Int)
}

interface onItemTouchAdapter {
    fun onItemMove(startPosition: Int, endPosition: Int): Boolean

    fun onSwipe(position: Int, direction: Int)
}