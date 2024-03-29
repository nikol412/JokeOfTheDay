package com.nikol412.jokeoftheday.getJoke

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nikol412.jokeoftheday.R
import com.nikol412.jokeoftheday.databinding.FragmentGetJokeBinding
import com.nikol412.jokeoftheday.getJoke.adapter.JokeAdapter
import com.nikol412.jokeoftheday.getJoke.adapter.onItemClick

class GetJokeFragment : Fragment() {

    lateinit var binding: FragmentGetJokeBinding

    private val viewModel by viewModels<GetJokeVM>()

    private val adapter by lazy {
        JokeAdapter(object : onItemClick {
            override fun onCLick(preTextView: TextView, postTextView: TextView) {
                expandTextView(preTextView, postTextView)
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

        binding.recyclerViewJokes.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerViewJokes.adapter = adapter


        viewModel.jokeResponse.observe(viewLifecycleOwner, { joke ->
            adapter.setItem(joke)
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