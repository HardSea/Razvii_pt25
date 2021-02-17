package com.pmacademy.razvii_pt21.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pmacademy.razvii_pt21.R
import com.pmacademy.razvii_pt21.databinding.CreatePostFragmentBinding
import com.pmacademy.razvii_pt21.datasource.local.UserPostsDatabase
import com.pmacademy.razvii_pt21.ui.PostViewModel
import com.pmacademy.razvii_pt21.ui.PostViewModelFactory

class CreatePostFragment : BaseFragment(R.layout.create_post_fragment) {

    private lateinit var binding: CreatePostFragmentBinding
    private lateinit var viewModel: PostViewModel
    private lateinit var factory: PostViewModelFactory
    private lateinit var localUserPostDatabase: UserPostsDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CreatePostFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        localUserPostDatabase = UserPostsDatabase(view.context)
        factory = PostViewModelFactory(localUserPostDatabase)
        viewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnCreatePost.setOnClickListener {
            createPost()
        }

    }

    private fun createPost() {
        if (viewModel.createPost(
                title = binding.etTitle.text.toString(),
                body = binding.etBody.text.toString(),
                userId = binding.etUserId.text.toString().toInt()
            )
        ) {
            activity?.onBackPressed()
        } else {
            Toast.makeText(requireContext(), "Error. Please, check inputs", Toast.LENGTH_SHORT)
                .show()
        }
    }

    companion object {
        fun newInstance(): CreatePostFragment {
            return CreatePostFragment()
        }
    }
}


//    var idd = 1
//
//    private fun insertData() {
//        val title = "this is sample title"
//        val desc = "this is sample desc"
//
//        val note = UserPost(id = idd, title = title, body = desc, userId = 1) //why id null? because id is auto generate
//        CoroutineScope(Dispatchers.IO).launch {
//            Log.d("TAG", "insertData: ")
//            viewModel.insertPost(note)
//            idd++
//        }
//    }