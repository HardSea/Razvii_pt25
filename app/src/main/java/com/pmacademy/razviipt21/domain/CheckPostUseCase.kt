package com.pmacademy.razviipt21.domain

import androidx.annotation.StringRes
import com.pmacademy.razviipt21.R
import com.pmacademy.razviipt21.tools.Result
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
        var result: Result<Boolean, ErrorType> = Result.success(true)

        if (checkTitleLength(post.title))
            result = Result.error(ErrorType.TitleLength)

        if (checkBodyLength(post.body))
            result = Result.error(ErrorType.BodyLength)

        if (checkTitleForBannedString(post.title))
            result = Result.error(ErrorType.BannedWord)

        return result
    }

    private fun checkTitleLength(title: String): Boolean {
        return title.length < minTitleLength || title.length > maxTitleLength
    }

    private fun checkBodyLength(body: String): Boolean {
        return body.length < minBodyLength || body.length > maxBodyLength
    }

    private fun checkTitleForBannedString(title: String): Boolean {
        var result = false
        bannedTitleStrings.forEach { bannedString ->
            result =
                bannedString.toLowerCase(Locale.getDefault()) in title.toLowerCase(Locale.getDefault())
            if (result) return true
        }
        return result
    }
}
