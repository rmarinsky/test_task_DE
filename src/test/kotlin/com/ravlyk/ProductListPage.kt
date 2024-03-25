package com.ravlyk

import com.codeborne.selenide.Condition.hidden
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.element

class ProductListPage {
    fun selectFlag(): String {
        return selectCheckBoxedFilter("flags")
    }

    fun selectGeschenk(type: String?): String {
        var targetGeschenk = ""
        type?.let {
            targetGeschenk = selectCheckBoxedFilter("Geschenk")
        }
        return targetGeschenk
    }

    fun selectClassificationClassName(): String {
        return selectCheckBoxedFilter("classificationClassName")
    }

    fun selectBrand(): String {
        return selectCheckBoxedFilter("brand")
    }

    fun selectGender(applyFor: String?): String {
        var targetGender = ""
        applyFor?.let {
            targetGender = selectCheckBoxedFilter("gender")
        }
        return targetGender
    }

    private fun selectCheckBoxedFilter(filterName: String): String {
        val targetFilter = element("[data-testid*=$filterName]")
        targetFilter.click()

        val facetMenu = element(".facet__menu a", 0)
        val targetFilterValue = facetMenu.shouldBe(visible).text()
        facetMenu.click()

        targetFilter.click()
        facetMenu.shouldBe(hidden)
        return targetFilterValue
    }

}
