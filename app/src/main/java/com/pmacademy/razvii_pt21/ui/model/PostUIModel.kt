package com.pmacademy.razvii_pt21.ui.model

import androidx.annotation.StringRes

interface PostUiModel {
    val postId: Int
    val userId: String
}

data class PostUiModelWarning(
    override val postId: Int,
    override val userId: String,
    val warningTextRes: Int,
    val title: String,
    val body: String,
    val backgroundColorRes: Int
) : PostUiModel

data class PostUiModelNormal(
    override val postId: Int,
    override val userId: String,
    val title: String,
    val body: String,
    val backgroundColorRes: Int
) : PostUiModel

data class PostUiModelBanned(
    override val postId: Int,
    override val userId: String,
    @StringRes val titleResource: Int
) : PostUiModel