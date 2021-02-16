package com.pmacademy.razvii_pt21.ui.model

//import androidx.annotation.ColorInt
//
//
//data class PostUiModel(
//    val userId: String,
//    val title: String,
//    val body: String,
//    @ColorInt val backgroundColor: Int
//)

import androidx.annotation.ColorInt

interface PostUiModel {
    val postId: Int
    val userId: String
}

data class PostUiModelNormal(
    override val postId: Int,
    override val userId: String,
    val title: String,
    val body: String,
    @ColorInt val backgroundColor: Int
) : PostUiModel

data class PostUiModelBanned(
    override val postId: Int,
    override val userId: String,
    val title: String
) : PostUiModel