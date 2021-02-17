package com.pmacademy.razvii_pt21.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmacademy.razvii_pt21.R
import com.pmacademy.razvii_pt21.databinding.PostListFragmentBinding
import com.pmacademy.razvii_pt21.datasource.local.UserPost
import com.pmacademy.razvii_pt21.datasource.local.UserPostsDatabase
import com.pmacademy.razvii_pt21.ui.PostViewModel
import com.pmacademy.razvii_pt21.ui.PostViewModelFactory
import com.pmacademy.razvii_pt21.ui.adapter.PostAdapter
import com.pmacademy.razvii_pt21.ui.model.PostUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        //repository = PostRepository(noteDatabase)
        factory = PostViewModelFactory(localUserPostDatabase)
        viewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]
        viewModel.getPosts()

        setupListeners()
        observePosts()
        initRecyclerView()

       // insertData()
    }


    var idd = 1

    private fun insertData() {
        val title = "this is sample title"
        val desc = "this is sample desc"

        val note = UserPost(id = idd, title = title, body = desc, userId = 1) //why id null? because id is auto generate
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("TAG", "insertData: ")
            viewModel.insertPost(note)
            idd++
        }
    }

//    //READ (show data)
//    private fun showData() {
//        CoroutineScope(Dispatchers.Main).launch {
//            viewModel.getAllNotes().observe(requireActivity(), { posts ->
//                Log.d("TAG post", "showData: $posts")
//            })
//        }
//    }




    private fun updatePostsRepos(items: List<PostUiModel>) {
        recyclerViewPostsAdapter.updatePosts(items)
    }

    private fun initRecyclerView() {
        binding.rvPosts.adapter = recyclerViewPostsAdapter
        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observePosts() {
        viewModel.postsLiveData.observe(viewLifecycleOwner, Observer {
            updatePostsRepos(it)
        })
    }

    private fun setupListeners() {
        binding.btnCreatePost.setOnClickListener {
            Log.d("TAG", "setupListeners: click")
            //navigator.showCreatePostFragment()
            insertData()
            //showData()
        }
    }

    companion object {
        fun newInstance(): PostListFragment {
            return PostListFragment().apply { }
        }
    }

}