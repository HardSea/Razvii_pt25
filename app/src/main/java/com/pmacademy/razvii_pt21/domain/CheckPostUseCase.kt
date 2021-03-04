package com.pmacademy.razvii_pt21.domain

import androidx.annotation.StringRes
import com.pmacademy.razvii_pt21.R
import com.pmacademy.razvii_pt21.tools.Result
import java.util.*

enum class ErrorType(@StringRes val messageError: Int) {
    TitleLength(R.string.post_title_error_message),
    BodyLength(R.string.post_body_error_message),
    BannedWord(R.string.banned_word_error_message)
}

class CheckPostRulesUseCase {

    companion object {
        private val bannedTitleStrings = listOf("Реклама", "Товар", "Куплю")
        private const val minTitleLength = 3
        private const val maxTitleLength = 50
        private const val minBodyLength = 5
        private const val maxBodyLength = 500
    }

    fun invoke(post: Post): Result<Boolean, ErrorType> {
        if (post.title.length < minTitleLength || post.title.length > maxTitleLength) {
            return Result.error(ErrorType.TitleLength)
        }

        if (post.body.length < minBodyLength || post.body.length > maxBodyLength) {
            return Result.error(ErrorType.BodyLength)
        }

        bannedTitleStrings.forEach { bannedString ->
            if (bannedString.toLowerCase(Locale.getDefault()) in post.title.toLowerCase(Locale.getDefault())) {
                return Result.error(ErrorType.BannedWord)
            }
        }
        return Result.success(true)
    }
}