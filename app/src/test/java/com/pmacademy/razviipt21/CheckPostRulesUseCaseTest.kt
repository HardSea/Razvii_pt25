package com.pmacademy.razviipt21

import com.pmacademy.razviipt21.domain.CheckPostRulesUseCase
import com.pmacademy.razviipt21.domain.ErrorType
import com.pmacademy.razviipt21.domain.Post
import io.kotest.matchers.shouldBe
import org.junit.Test

class CheckPostRulesUseCaseTest {

    @Test
    fun `if title length less than 3 symbols or more than 50 symbols return title length error type`() {
        val useCase = CheckPostRulesUseCase()

        val postTooShortTitle = Post(4, "hi", "test body")
        useCase.invoke(postTooShortTitle).errorResult shouldBe ErrorType.TitleLength

        val postTooLongTitle =
            Post(4, "i am very long title string i am very long title string", "test body")
        useCase.invoke(postTooLongTitle).errorResult shouldBe ErrorType.TitleLength
    }

    @Test
    fun `if body length less than 5 symbols or more than 500 symbols return body length error type`() {
        val useCase = CheckPostRulesUseCase()

        val postTooShortBody = Post(4, "hello", "test")
        useCase.invoke(postTooShortBody).errorResult shouldBe ErrorType.BodyLength

        val postTooLongBody = Post(
            4,
            "hello",
            "hello i am very very very very long body length i am very long body length i am very long body length i am very long body length i am very long body length i am very long body length i am very long body length i am very long body length i am very long body length i am very long body length i am very long body length i am very long body length i am very long body length i am very long body length i am very long body length i am very long body length i am very long body length i am very long body length "
        )
        useCase.invoke(postTooLongBody).errorResult shouldBe ErrorType.BodyLength
    }

    @Test
    fun `if title contains banned string return banned word error type`() {
        val useCase = CheckPostRulesUseCase()

        val postWithBannedString = Post(4, "Це реклама", "Рекламне оголошення")
        useCase.invoke(postWithBannedString).errorResult shouldBe ErrorType.BannedWord

        val postWithBannedString2 = Post(4, "ТОваР продам", "Рекламне оголошення")
        useCase.invoke(postWithBannedString2).errorResult shouldBe ErrorType.BannedWord

        val postWithBannedString3 = Post(4, "куПЛЮ гараж", "Рекламне оголошення")
        useCase.invoke(postWithBannedString3).errorResult shouldBe ErrorType.BannedWord
    }
}
