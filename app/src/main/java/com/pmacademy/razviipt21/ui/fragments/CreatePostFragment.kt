package com.pmacademy.razviipt21.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pmacademy.razviipt21.Application
import com.pmacademy.razviipt21.R
import com.pmacademy.razviipt21.databinding.CreatePostFragmentBinding
import com.pmacademy.razviipt21.ui.PostCreationViewModel
import javax.inject.Inject

class CreatePostFragment : BaseFragment(R.layout.create_post_fragment) {

    private lateinit var binding: CreatePostFragmentBinding

    @Inject
    lateinit var viewModel: PostCreationViewModel

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
        setupListeners()
        observeCreatePostLiveData()
    }

    override fun setupDi() {
        val app = requireActivity().application as Application
        app.getComponent().inject(this)
    }

    private fun setupListeners() {
        binding.btnCreatePost.setOnClickListener {
            tryCreatePost()
        }
    }

    private fun tryCreatePost() {
        viewModel.createPost(
            title = binding.etTitle.text.toString(),
            body = binding.etBody.text.toString(),
            userId = binding.etUserId.text.toString().toInt()
        )
    }

    private fun observeCreatePostLiveData() {
        viewModel.postCreateLiveData.observe(viewLifecycleOwner, { result ->
            if (!result.isError) {
                activity?.onBackPressed()
            } else {
                Toast.makeText(
                    requireContext(), getString(result.errorResult.messageError), Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    companion object {
        fun newInstance(): CreatePostFragment {
            return CreatePostFragment()
        }
    }
}
