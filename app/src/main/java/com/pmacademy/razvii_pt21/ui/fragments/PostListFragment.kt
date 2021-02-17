package com.pmacademy.razvii_pt21.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmacademy.razvii_pt21.R
import com.pmacademy.razvii_pt21.databinding.PostListFragmentBinding
import com.pmacademy.razvii_pt21.datasource.local.UserPostsDatabase
import com.pmacademy.razvii_pt21.ui.PostViewModel
import com.pmacademy.razvii_pt21.ui.PostViewModelFactory
import com.pmacademy.razvii_pt21.ui.adapter.PostAdapter
import com.pmacademy.razvii_pt21.ui.model.PostUiModel

class PostListFragment : BaseFragment(R.layout.post_list_fragment) {

    private lateinit var binding: PostListFragmentBinding
    private lateinit var viewModel: PostViewModel
    private lateinit var localUserPostDatabase: UserPostsDatabase
    private lateinit var factory: PostViewModelFactory

    private val recyclerViewPostsAdapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        localUserPostDatabase = UserPostsDatabase(view.context)
        factory = PostViewModelFactory(localUserPostDatabase)
        viewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]
        viewModel.getPosts()

        setupListeners()
        observePosts()
        initRecyclerView()

    }

    private fun updatePostsRepos(items: List<PostUiModel>) {
        recyclerViewPostsAdapter.updatePosts(items)
        binding.rvPosts.smoothScrollToPosition(0)
    }

    private fun initRecyclerView() {
        binding.rvPosts.adapter = recyclerViewPostsAdapter
        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observePosts() {
        viewModel.postsLiveData.observe(viewLifecycleOwner, {
            if (it != null) {
                updatePostsRepos(it)
            }
        })
    }

    private fun setupListeners() {
        binding.btnCreatePost.setOnClickListener {
            navigator.showCreatePostFragment()
        }
    }

    companion object {
        fun newInstance(): PostListFragment {
            return PostListFragment().apply { }
        }
    }

}