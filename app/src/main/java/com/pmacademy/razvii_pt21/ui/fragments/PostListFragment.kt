package com.pmacademy.razvii_pt21.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmacademy.razvii_pt21.Application
import com.pmacademy.razvii_pt21.R
import com.pmacademy.razvii_pt21.databinding.PostListFragmentBinding
import com.pmacademy.razvii_pt21.ui.PostViewModel
import com.pmacademy.razvii_pt21.ui.adapter.PostAdapter
import com.pmacademy.razvii_pt21.ui.model.PostUiModel
import javax.inject.Inject

class PostListFragment : BaseFragment(R.layout.post_list_fragment) {

    //@Inject
    //private lateinit var factory: PostViewModelFactory

    private lateinit var binding: PostListFragmentBinding

    @Inject
    lateinit var viewModel: PostViewModel

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

        viewModel.getPosts()
        setupListeners()
        observePosts()
        initRecyclerView()
    }

    override fun setupDi() {
        val app = requireActivity().application as Application
        app.getComponent().inject(this)
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