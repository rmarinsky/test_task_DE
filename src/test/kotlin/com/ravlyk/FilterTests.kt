package com.ravlyk

import com.codeborne.selenide.CollectionCondition.textsInAnyOrder
import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.junit5.TextReportExtension
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@ExtendWith(TextReportExtension::class)
class FilterTests : BaseTest() {

    @BeforeEach
    fun openPage() {
        open("/c/parfum/01")
    }

    @ParameterizedTest(name = "Filter by flag {0}, Marke {1}, Highlights {2} and Geschenk fur {3}")
    @MethodSource("filterParams")
    fun `filter byt smth`(flag: String, brand: Int, type: String?, applyFor: String?) {
        ProductListPage().apply {
            val targetFlag = selectFlag()
            val targetGeschenk = selectGeschenk(type)
            val classificationClassName = selectClassificationClassName()
            val brandName = selectBrand()
            val targetGender = selectGender(applyFor)

            val expectedFilters =
                listOf(
                    targetFlag,
                    brandName,
                    classificationClassName,
                    targetGeschenk,
                    targetGender
                ).filter { it.isNotBlank() }
            elements(".selected-facets__value").shouldHave(textsInAnyOrder(expectedFilters))
        }

    }

    companion object {

        @JvmStatic
        @BeforeAll
        fun openHomePageAndAcceptCookies() {
            open("/")
            element(".uc-list-button__accept-all").apply {
                click()
                shouldBe(Condition.hidden)
            }
        }

        @JvmStatic
        fun filterParams() = listOf(
            Arguments.of("discountFlag", 1, "Eau de Cologne", null, "Unisex"),
            Arguments.of("computedNewFlag", 2, null, null, "Männlich"),
            Arguments.of("computedLimited", 3, "After Shave", "Ostern", "Weiblich"),
        )
    }


}
