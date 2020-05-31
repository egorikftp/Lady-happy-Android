package com.egoriku.ladyhappy.postcreator.domain.predefined

object PredefinedData {

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

    val categoriesName = ArrayList(
            allCategories.map {
                it.name
            }
    )
}