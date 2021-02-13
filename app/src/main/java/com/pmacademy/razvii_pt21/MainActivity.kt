package com.pmacademy.razvii_pt21

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmacademy.razvii_pt21.databinding.ActivityMainBinding
import com.pmacademy.razvii_pt21.tools.PostComponent
import com.pmacademy.razvii_pt21.ui.*

// Main Activity on new branch
class MainActivity : AppCompatActivity(R.layout.activity_main), PostView {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: PostPresenter
    private val recyclerViewPostsAdapter = PostAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = PostComponent.createPresenter(this)
        presenter.attachView(this)

        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.rvPosts.adapter = recyclerViewPostsAdapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showInfo(postList: List<PostUiModel>) {
        recyclerViewPostsAdapter.showPosts(postList)
    }

    override fun showError(error: PostErrorsTypes) {
        binding.rvPosts.visibility = View.GONE
        binding.tvErrorText.visibility = View.VISIBLE
        binding.tvErrorText.text = error.name
    }
}