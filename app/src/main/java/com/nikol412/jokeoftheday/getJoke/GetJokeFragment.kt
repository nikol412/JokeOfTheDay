package com.nikol412.jokeoftheday.getJoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nikol412.jokeoftheday.R
import com.nikol412.jokeoftheday.databinding.FragmentGetJokeBinding

class GetJokeFragment : Fragment() {

    lateinit var binding: FragmentGetJokeBinding

    private val viewModel by viewModels<GetJokeVM>()

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
        return binding.root
    }
}