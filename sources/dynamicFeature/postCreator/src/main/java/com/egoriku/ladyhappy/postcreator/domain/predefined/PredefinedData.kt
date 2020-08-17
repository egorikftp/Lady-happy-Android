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
            SubCategoryModel(
                    name = "Шляпка-таблетка",
                    categoryId = 1,
                    subCategoryId = 1
            ),
            SubCategoryModel(
                    name = "Накладка-менингитка",
                    categoryId = 1,
                    subCategoryId = 2
            ),
            SubCategoryModel(
                    name = "Федора",
                    categoryId = 2,
                    subCategoryId = 1
            ),
            SubCategoryModel(
                    name = "Трилби",
                    categoryId = 2,
                    subCategoryId = 2
            ),
            SubCategoryModel(
                    name = "Клош фактурный",
                    categoryId = 2,
                    subCategoryId = 3
            ),
            SubCategoryModel(
                    name = "Клош гладкий (шапочка)",
                    categoryId = 2,
                    subCategoryId = 4
            ),
            SubCategoryModel(
                    name = "Канотье",
                    categoryId = 2,
                    subCategoryId = 5
            ),
            SubCategoryModel(
                    name = "Маркиза",
                    categoryId = 2,
                    subCategoryId = 6
            ),
            SubCategoryModel(
                    name = "Амазонка",
                    categoryId = 2,
                    subCategoryId = 7
            ),
            SubCategoryModel(
                    name = "Кепи",
                    categoryId = 2,
                    subCategoryId = 8
            ),
            SubCategoryModel(
                    name = "Шляпка широкополая с мягкими полями",
                    categoryId = 2,
                    subCategoryId = 9
            ),
            SubCategoryModel(
                    name = "Чалма",
                    categoryId = 2,
                    subCategoryId = 10
            ),
            SubCategoryModel(
                    name = "Шляпка в стиле Ар-нуво",
                    categoryId = 2,
                    subCategoryId = 11
            ),
            SubCategoryModel(
                    name = "Берет",
                    categoryId = 2,
                    subCategoryId = 12
            ),
            SubCategoryModel(
                    name = "Шляпка-Сатурн",
                    categoryId = 2,
                    subCategoryId = 13
            ),
            SubCategoryModel(
                    name = "Жокейка, бейсболка",
                    categoryId = 2,
                    subCategoryId = 14
            ),
            SubCategoryModel(
                    name = "Шляпка тирольская",
                    categoryId = 2,
                    subCategoryId = 15
            ),
            SubCategoryModel(
                    name = "Котелок",
                    categoryId = 2,
                    subCategoryId = 16
            ),
            SubCategoryModel(
                    name = "Колокол",
                    categoryId = 2,
                    subCategoryId = 17
            ),
            SubCategoryModel(
                    name = "Таблетка",
                    categoryId = 2,
                    subCategoryId = 18
            ),
            SubCategoryModel(
                    name = "Шляпка",
                    categoryId = 3,
                    subCategoryId = 1
            ),
            SubCategoryModel(
                    name = "Мини-шляпка",
                    categoryId = 3,
                    subCategoryId = 2
            ),
            SubCategoryModel(
                    name = "Вуалетка",
                    categoryId = 3,
                    subCategoryId = 3
            ),
            SubCategoryModel(
                    name = "Броши",
                    categoryId = 3,
                    subCategoryId = 4
            ),
            SubCategoryModel(
                    name = "Аксессуар",
                    categoryId = 3,
                    subCategoryId = 5
            ),
            SubCategoryModel(
                    name = "Вуалетка",
                    categoryId = 4,
                    subCategoryId = 1
            ),
            SubCategoryModel(
                    name = "Броши",
                    categoryId = 4,
                    subCategoryId = 2
            ),
            SubCategoryModel(
                    name = "Вуалетки",
                    categoryId = 5,
                    subCategoryId = 1
            ),
            SubCategoryModel(
                    name = "Шляпки",
                    categoryId = 5,
                    subCategoryId = 2
            ),
            SubCategoryModel(
                    name = "Мини-шляпки",
                    categoryId = 6,
                    subCategoryId = 1
            ),
            SubCategoryModel(
                    name = "Броши",
                    categoryId = 6,
                    subCategoryId = 2
            ),
            SubCategoryModel(
                    name = "Шапочки",
                    categoryId = 6,
                    subCategoryId = 3
            ),
            SubCategoryModel(
                    name = "Реставрации",
                    categoryId = 7,
                    subCategoryId = 1
            ),
            SubCategoryModel(
                    name = "Коробки",
                    categoryId = 8,
                    subCategoryId = 1
            ),
            SubCategoryModel(
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