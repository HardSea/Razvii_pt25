package com.pmacademy.razvii_pt21

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmacademy.razvii_pt21.databinding.ActivityMainBinding
import com.pmacademy.razvii_pt21.tools.PostComponent
import com.pmacademy.razvii_pt21.ui.MainViewModel
import com.pmacademy.razvii_pt21.ui.PostErrorsTypes
import com.pmacademy.razvii_pt21.ui.PostPresenter
import com.pmacademy.razvii_pt21.ui.PostView
import com.pmacademy.razvii_pt21.ui.adapter.PostAdapter
import com.pmacademy.razvii_pt21.ui.model.PostUiModel

// Main Activity on new branch
class MainActivity : AppCompatActivity(R.layout.activity_main) {


    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: PostPresenter
    private val recyclerViewPostsAdapter = PostAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        observePosts()
        initRecyclerView()
        setupListeners()
        viewModel.getPosts()
        //presenter = PostComponent.createPresenter(this)
        //presenter.attachView(this)

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

    override fun onDestroy() {
        super.onDestroy()
//        presenter.detachView()
    }



//    override fun showInfo(postList: List<PostUiModel>) {
//        updatePostsRepos(postList)
//    }
//
//    override fun showError(error: PostErrorsTypes) {
//        binding.rvPosts.visibility = View.GONE
//        binding.tvErrorText.visibility = View.VISIBLE
//        binding.tvErrorText.text = error.name
//    }
}