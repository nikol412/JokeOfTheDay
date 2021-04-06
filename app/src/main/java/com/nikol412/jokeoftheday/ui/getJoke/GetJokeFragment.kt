package com.nikol412.jokeoftheday.ui.getJoke

import android.animation.ObjectAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nikol412.jokeoftheday.R
import com.nikol412.jokeoftheday.databinding.FragmentGetJokeBinding
import com.nikol412.jokeoftheday.ui.getJoke.adapter.JokeAdapter
import com.nikol412.jokeoftheday.ui.getJoke.adapter.JokeItemViewHolder
import com.nikol412.jokeoftheday.ui.getJoke.adapter.onItemClick
import com.nikol412.jokeoftheday.ui.getJoke.adapter.onItemTouchAdapter
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs


class GetJokeFragment : Fragment() {

    lateinit var binding: FragmentGetJokeBinding

    private val viewModel by viewModel<GetJokeVM>()

    private val adapter by lazy {
        JokeAdapter(object : onItemClick {
            override fun onCLick(preTextView: TextView, postTextView: TextView) {
                expandTextView(preTextView, postTextView)
            }

            override fun onSwipe(position: Int, direction: Int) {
                viewModel.addJokeToFavourite(position)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_joke, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        binding.recyclerViewJokes.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        val touchCallback = SimpleTouchHelper(adapter)
        val itemTouchHelper = ItemTouchHelper(touchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewJokes)

        binding.recyclerViewJokes.adapter = adapter


        lifecycleScope.launchWhenResumed {
            viewModel.jokesList.collect { list ->
                adapter.setItems(list)
            }
        }
        viewModel.jokeResponse.observe(viewLifecycleOwner, { joke ->
//            adapter.setItem(joke)
        })
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
}

class SimpleTouchHelper(private val adapter: onItemTouchAdapter) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END
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

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)

        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if(viewHolder is JokeItemViewHolder) {
                viewHolder.changeBackground(Color.YELLOW)
            }
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val value = 1 - abs(dX) / viewHolder.itemView.width
            viewHolder.itemView.alpha = value
//            viewHolder.itemView.foreground = viewHolder.itemView.context.resources.getDrawable()
        }
    }
}