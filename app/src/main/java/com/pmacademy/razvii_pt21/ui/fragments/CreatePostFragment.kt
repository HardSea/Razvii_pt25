package com.pmacademy.razvii_pt21.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pmacademy.razvii_pt21.R
import com.pmacademy.razvii_pt21.databinding.CreatePostFragmentBinding

class CreatePostFragment : BaseFragment(R.layout.create_post_fragment) {

    private lateinit var binding: CreatePostFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("TAG", "onCreateView: CreatePostFragment")
        binding = CreatePostFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {
        fun newInstance(): CreatePostFragment {
            Log.d("TAG", "newInstance: ")
            return CreatePostFragment()
        }
    }

}