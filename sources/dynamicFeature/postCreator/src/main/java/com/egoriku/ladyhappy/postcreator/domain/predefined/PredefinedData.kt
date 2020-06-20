package com.egoriku.ladyhappy.postcreator.domain.predefined

object PredefinedData {

    val allCategories = listOf(
            CategoryModel(
                    categoryId = 1,
                    name = "Фетр аксессуары"
            ),
            CategoryModel(
                    name = "Фетр шляпки",
                    categoryId = 2
            ),
            CategoryModel(
                    name = "Синамей",
                    categoryId = 3
            ),
            CategoryModel(
                    name = "Кринолин",
                    categoryId = 4
            ),
            CategoryModel(
                    name = "Свадебные",
                    categoryId = 5
            ),
            CategoryModel(
                    name = "Войлок",
                    categoryId = 6
            ),
            CategoryModel(
                    name = "Реставрация шляпок",
                    categoryId = 7
            ),
            CategoryModel(
                    name = "Шляпные коробки",
                    categoryId = 8
            ),
            CategoryModel(
                    name = "Тканевые",
                    categoryId = 9
            )
    )

    val allSubCategories = listOf(
            SubCategory(
                    name = "Шляпка-таблетка",
                    categoryId = 1,
                    subCategoryId = 1
            ),
            SubCategory(
                    name = "Накладка-менингитка",
                    categoryId = 1,
                    subCategoryId = 2
            ),
            SubCategory(
                    name = "Федора",
                    categoryId = 2,
                    subCategoryId = 1
            ),
            SubCategory(
                    name = "Трилби",
                    categoryId = 2,
                    subCategoryId = 2
            ),
            SubCategory(
                    name = "Клош фактурный",
                    categoryId = 2,
                    subCategoryId = 3
            ),
            SubCategory(
                    name = "Клош гладкий (шапочка)",
                    categoryId = 2,
                    subCategoryId = 4
            ),
            SubCategory(
                    name = "Канотье",
                    categoryId = 2,
                    subCategoryId = 5
            ),
            SubCategory(
                    name = "Маркиза",
                    categoryId = 2,
                    subCategoryId = 6
            ),
            SubCategory(
                    name = "Амазонка",
                    categoryId = 2,
                    subCategoryId = 7
            ),
            SubCategory(
                    name = "Кепи",
                    categoryId = 2,
                    subCategoryId = 8
            ),
            SubCategory(
                    name = "Шляпка широкополая с мягкими полями",
                    categoryId = 2,
                    subCategoryId = 9
            ),
            SubCategory(
                    name = "Чалма",
                    categoryId = 2,
                    subCategoryId = 10
            ),
            SubCategory(
                    name = "Шляпка в стиле Ар-нуво",
                    categoryId = 2,
                    subCategoryId = 11
            ),
            SubCategory(
                    name = "Берет",
                    categoryId = 2,
                    subCategoryId = 12
            ),
            SubCategory(
                    name = "Шляпка-Сатурн",
                    categoryId = 2,
                    subCategoryId = 13
            ),
            SubCategory(
                    name = "Жокейка, бейсболка",
                    categoryId = 2,
                    subCategoryId = 14
            ),
            SubCategory(
                    name = "Шляпка тирольская",
                    categoryId = 2,
                    subCategoryId = 15
            ),
            SubCategory(
                    name = "Котелок",
                    categoryId = 2,
                    subCategoryId = 16
            ),
            SubCategory(
                    name = "Колокол",
                    categoryId = 2,
                    subCategoryId = 17
            ),
            SubCategory(
                    name = "Таблетка",
                    categoryId = 2,
                    subCategoryId = 18
            ),
            SubCategory(
                    name = "Шляпка",
                    categoryId = 3,
                    subCategoryId = 1
            ),
            SubCategory(
                    name = "Мини-шляпка",
                    categoryId = 3,
                    subCategoryId = 2
            ),
            SubCategory(
                    name = "Вуалетка",
                    categoryId = 3,
                    subCategoryId = 3
            ),
            SubCategory(
                    name = "Броши",
                    categoryId = 3,
                    subCategoryId = 4
            ),
            SubCategory(
                    name = "Аксессуар",
                    categoryId = 3,
                    subCategoryId = 5
            ),
            SubCategory(
                    name = "Вуалетка",
                    categoryId = 4,
                    subCategoryId = 1
            ),
            SubCategory(
                    name = "Броши",
                    categoryId = 4,
                    subCategoryId = 2
            ),
            SubCategory(
                    name = "Вуалетки",
                    categoryId = 5,
                    subCategoryId = 1
            ),
            SubCategory(
                    name = "Шляпки",
                    categoryId = 5,
                    subCategoryId = 2
            ),
            SubCategory(
                    name = "Мини-шляпки",
                    categoryId = 6,
                    subCategoryId = 1
            ),
            SubCategory(
                    name = "Броши",
                    categoryId = 6,
                    subCategoryId = 2
            ),
            SubCategory(
                    name = "Шапочки",
                    categoryId = 6,
                    subCategoryId = 3
            ),
            SubCategory(
                    name = "Реставрации",
                    categoryId = 7,
                    subCategoryId = 1
            ),
            SubCategory(
                    name = "Коробки",
                    categoryId = 8,
                    subCategoryId = 1
            ),
            SubCategory(
                    name = "Берет",
                    categoryId = 9,
                    subCategoryId = 1
            )

    )

    fun getCategoriesNames() = ArrayList(
            allCategories.map { it.name }
    )

    fun getSubCategoriesNames(categoryId: Int) = ArrayList(
            allSubCategories
                    .filter { it.categoryId == categoryId }
                    .map { it.name }
    )
}