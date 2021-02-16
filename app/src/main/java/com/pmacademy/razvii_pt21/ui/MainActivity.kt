package com.pmacademy.razvii_pt21.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmacademy.razvii_pt21.R
import com.pmacademy.razvii_pt21.databinding.ActivityMainBinding
import com.pmacademy.razvii_pt21.ui.adapter.PostAdapter
import com.pmacademy.razvii_pt21.ui.model.PostUiModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private val recyclerViewPostsAdapter = PostAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        observePosts()
        initRecyclerView()
        setupListeners()
        viewModel.getPosts()

    }

    private fun setupListeners() {
        binding.btnCreatePost.setOnClickListener {
            //todo btn click
        }
    }

    private fun observePosts() {
        viewModel.postsLiveData.observe(this, Observer {
            updatePostsRepos(it)
        })
    }

    private fun updatePostsRepos(items: List<PostUiModel>) {
        recyclerViewPostsAdapter.updatePosts(items)
    }

    private fun initRecyclerView() {
        binding.rvPosts.adapter = recyclerViewPostsAdapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
    }

}