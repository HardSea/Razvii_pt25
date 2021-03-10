package com.pmacademy.razviipt21.ui.model

sealed class PostUiModel {
    abstract val postId: Int
    abstract val userId: String
}

data class PostUiModelNormal(
    override val postId: Int,
    override val userId: String,
    val title: String,
    val body: String,
    val backgroundColorRes: Int
) : PostUiModel()

data class PostUiModelBanned(
    override val postId: Int,
    override val userId: String,
    val title: String
) : PostUiModel()
