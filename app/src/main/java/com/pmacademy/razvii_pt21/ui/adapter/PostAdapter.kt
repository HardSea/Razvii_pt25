package com.pmacademy.razvii_pt21.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pmacademy.razvii_pt21.R
import com.pmacademy.razvii_pt21.ui.model.PostUiModel
import com.pmacademy.razvii_pt21.ui.model.PostUiModelBanned
import com.pmacademy.razvii_pt21.ui.model.PostUiModelNormal

private val diffCallback = object : DiffUtil.ItemCallback<PostUiModel>() {

    override fun areItemsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
        return oldItem == newItem
    }
}

class PostAdapter : ListAdapter<PostUiModel, RecyclerView.ViewHolder>(diffCallback) {
    enum class ViewType {
        NORMAL,
        BANNED
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PostUiModelNormal -> ViewType.NORMAL.ordinal
            is PostUiModelBanned -> ViewType.BANNED.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewTypeEnum = ViewType.values()[viewType]
        val layout = when (viewTypeEnum) {
            ViewType.BANNED -> R.layout.view_holder_post_banned
            else -> R.layout.view_holder_post
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return when (viewTypeEnum) {
            ViewType.BANNED -> BannedPostViewHolder(view)
            ViewType.NORMAL -> NormalPostViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NormalPostViewHolder -> holder.bind(getItem(position) as PostUiModelNormal)
            is BannedPostViewHolder -> holder.bind(getItem(position) as PostUiModelBanned)
        }
    }

    fun updatePosts(lists: List<PostUiModel>) {
        this.submitList(lists)
    }

    class NormalPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvUserId: TextView? = null
        private var tvTitle: TextView? = null
        private var tvBody: TextView? = null
        private var container: LinearLayout? = null

        init {
            tvUserId = view.findViewById(R.id.tv_user_id)
            tvTitle = view.findViewById(R.id.tv_title)
            tvBody = view.findViewById(R.id.tv_body)
            container = view.findViewById(R.id.container)
        }

        fun bind(post: PostUiModelNormal) {
            tvUserId?.text = post.userId
            tvTitle?.text = post.title
            tvBody?.text = post.body
            container?.setBackgroundColor(itemView.context.getColor(post.backgroundColorRes))
        }
    }

    class BannedPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvTitle: TextView? = null

        init {
            tvTitle = view.findViewById(R.id.tv_title)
        }

        fun bind(post: PostUiModelBanned) {
            tvTitle?.text = post.title
        }
    }
}