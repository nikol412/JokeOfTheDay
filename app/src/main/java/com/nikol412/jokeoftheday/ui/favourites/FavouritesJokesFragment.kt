package com.nikol412.jokeoftheday.ui.favourites

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nikol412.jokeoftheday.R
import com.nikol412.jokeoftheday.databinding.FragmentFavouritesJokesBinding
import com.nikol412.jokeoftheday.ui.getJoke.adapter.JokeAdapter
import com.nikol412.jokeoftheday.ui.getJoke.adapter.onItemClick
import com.nikol412.jokeoftheday.ui.getJoke.adapter.onItemTouchAdapter
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavouritesJokesFragment : Fragment() {

    private val viewModel by viewModel<FavouritesJokesViewModel>()
    private lateinit var binding: FragmentFavouritesJokesBinding

    private val adapter by lazy {
        JokeAdapter(object : onItemClick {
            override fun onCLick(preTextView: TextView, postTextView: TextView) {
                expandTextView(preTextView, postTextView)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favourites_jokes, container, false)
        binding.lifecycleOwner = this

        binding.recyclerViewJokes.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val touchCallback = FavouritesTouchHelper(adapter)
        val touchHelper = ItemTouchHelper(touchCallback)
        touchHelper.attachToRecyclerView(binding.recyclerViewJokes)

        binding.recyclerViewJokes.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.favouritesJokesList.collect {
                adapter.setItems(it)
            }
        }

        return binding.root
    }

    private fun expandTextView(textView1: TextView, textView2: TextView) {
        val animation = ObjectAnimator.ofInt(textView1, "maxLines", textView1.lineCount)
        animation.duration = 500
        animation.start()

        val animation2 = ObjectAnimator.ofInt(textView2, "maxLines", textView1.lineCount)
        animation2.duration = 500
        animation2.start()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchJokes()
    }
}

class FavouritesTouchHelper(private val adapter: onItemTouchAdapter) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags =
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.onSwipe(viewHolder.adapterPosition, direction)
    }
}