package com.egoriku.ladyhappy.postcreator.domain.predefined

data class CategoryModel(
        val name: String,
        val subCategories: List<SubCategory>
)

data class SubCategory(
        val name: String,
        val type: Int
)

val allCategories = listOf(
        CategoryModel(
                name = "Фетр аксессуары",
                subCategories = listOf(
                        SubCategory(
                                name = "Шляпка-таблетка",
                                type = 1
                        )
                )
        ),
        CategoryModel(
                name = "Фетр шляпки",
                subCategories = emptyList()
        ),
        CategoryModel(
                name = "Синамей",
                subCategories = emptyList()
        ),
        CategoryModel(
                name = "Кринолин",
                subCategories = emptyList()
        ),
        CategoryModel(
                name = "Свадебные",
                subCategories = emptyList()
        ),
        CategoryModel(
                name = "Войлок",
                subCategories = emptyList()
        ),
        CategoryModel(
                name = "Реставрация шляпок",
                subCategories = emptyList()
        ),
        CategoryModel(
                name = "Шляпные коробки",
                subCategories = emptyList()
        ),
        CategoryModel(
                name = "Тканевые",
                subCategories = emptyList()
        )
)