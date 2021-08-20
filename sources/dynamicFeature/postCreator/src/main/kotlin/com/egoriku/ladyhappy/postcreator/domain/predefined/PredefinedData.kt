package com.egoriku.ladyhappy.postcreator.domain.predefined

@Suppress("StringLiteralDuplication")
object PredefinedData {

    val structure = HatsStructure(
        hatColors = listOf(
            HatColor(colorId = 1, name = "Молочный", colorHex = "#fff9f0"),
            HatColor(colorId = 2, name = "Черный", colorHex = "#0e0c11"),
            HatColor(colorId = 3, name = "Оранжевый", colorHex = "#ef5b11"),
            HatColor(colorId = 4, name = "Желтый", colorHex = "#f8b52d"),
            HatColor(colorId = 5, name = "Красный", colorHex = "#9e1212"),
            HatColor(colorId = 6, name = "Фиолетовый", colorHex = "#68396f"),
            HatColor(colorId = 7, name = "Розовый", colorHex = "#df89a8"),
            HatColor(colorId = 8, name = "Синий", colorHex = "#2b6596"),
            HatColor(colorId = 9, name = "Зеленый", colorHex = "#38761d"),
            HatColor(colorId = 10, name = "Коричневый", colorHex = "#866041"),
            HatColor(colorId = 11, name = "Серый", colorHex = "#999999")
        ),
        hatCategories = listOf(
            HatCategory(
                categoryId = 1,
                name = "Фетр аксессуары",
                subcategories = listOf(
                    HatSubCategory(
                        name = "Шляпка-таблетка",
                        categoryId = 1,
                        subCategoryId = 1
                    ),
                    HatSubCategory(
                        name = "Накладка-менингитка",
                        categoryId = 1,
                        subCategoryId = 2
                    )
                )
            ),
            HatCategory(
                subcategories = listOf(
                    HatSubCategory(
                        name = "Федора",
                        categoryId = 2,
                        subCategoryId = 1
                    ),
                    HatSubCategory(
                        name = "Трилби",
                        categoryId = 2,
                        subCategoryId = 2
                    ),
                    HatSubCategory(
                        name = "Клош фактурный",
                        categoryId = 2,
                        subCategoryId = 3
                    ),
                    HatSubCategory(
                        name = "Клош гладкий (шапочка)",
                        categoryId = 2,
                        subCategoryId = 4
                    ),
                    HatSubCategory(
                        name = "Канотье",
                        categoryId = 2,
                        subCategoryId = 5
                    ),
                    HatSubCategory(
                        name = "Маркиза",
                        categoryId = 2,
                        subCategoryId = 6
                    ),
                    HatSubCategory(
                        name = "Амазонка",
                        categoryId = 2,
                        subCategoryId = 7
                    ),
                    HatSubCategory(
                        name = "Кепи",
                        categoryId = 2,
                        subCategoryId = 8
                    ),
                    HatSubCategory(
                        name = "Шляпка широкополая с мягкими полями",
                        categoryId = 2,
                        subCategoryId = 9
                    ),
                    HatSubCategory(
                        name = "Чалма",
                        categoryId = 2,
                        subCategoryId = 10
                    ),
                    HatSubCategory(
                        name = "Шляпка в стиле Ар-нуво",
                        categoryId = 2,
                        subCategoryId = 11
                    ),
                    HatSubCategory(
                        name = "Берет",
                        categoryId = 2,
                        subCategoryId = 12
                    ),
                    HatSubCategory(
                        name = "Шляпка-Сатурн",
                        categoryId = 2,
                        subCategoryId = 13
                    ),
                    HatSubCategory(
                        name = "Жокейка, бейсболка",
                        categoryId = 2,
                        subCategoryId = 14
                    ),
                    HatSubCategory(
                        name = "Шляпка тирольская",
                        categoryId = 2,
                        subCategoryId = 15
                    ),
                    HatSubCategory(
                        name = "Котелок",
                        categoryId = 2,
                        subCategoryId = 16
                    ),
                    HatSubCategory(
                        name = "Колокол",
                        categoryId = 2,
                        subCategoryId = 17
                    ),
                    HatSubCategory(
                        name = "Таблетка",
                        categoryId = 2,
                        subCategoryId = 18
                    ),
                    HatSubCategory(
                        name = "Панама",
                        categoryId = 2,
                        subCategoryId = 19
                    )
                ),
                name = "Фетр шляпки",
                categoryId = 2
            ),
            HatCategory(
                subcategories = listOf(
                    HatSubCategory(
                        name = "Шляпка",
                        categoryId = 3,
                        subCategoryId = 1
                    ),
                    HatSubCategory(
                        name = "Мини-шляпка",
                        categoryId = 3,
                        subCategoryId = 2
                    ),
                    HatSubCategory(
                        name = "Вуалетка",
                        categoryId = 3,
                        subCategoryId = 3
                    ),
                    HatSubCategory(
                        name = "Броши",
                        categoryId = 3,
                        subCategoryId = 4
                    ),
                    HatSubCategory(
                        name = "Аксессуар",
                        categoryId = 3,
                        subCategoryId = 5
                    )
                ),
                name = "Синамей",
                categoryId = 3
            ),
            HatCategory(
                subcategories = listOf(
                    HatSubCategory(
                        name = "Вуалетка",
                        categoryId = 4,
                        subCategoryId = 1
                    ),
                    HatSubCategory(
                        name = "Броши",
                        categoryId = 4,
                        subCategoryId = 2
                    )
                ),
                name = "Регилин",
                categoryId = 4
            ),
            HatCategory(
                subcategories = listOf(
                    HatSubCategory(
                        name = "Вуалетки",
                        categoryId = 5,
                        subCategoryId = 1
                    ),
                    HatSubCategory(
                        name = "Шляпки",
                        categoryId = 5,
                        subCategoryId = 2
                    )
                ),
                name = "Свадебные",
                categoryId = 5
            ),
            HatCategory(
                subcategories = listOf(
                    HatSubCategory(
                        name = "Мини-шляпки",
                        categoryId = 6,
                        subCategoryId = 1
                    ),
                    HatSubCategory(
                        name = "Броши",
                        categoryId = 6,
                        subCategoryId = 2
                    ),
                    HatSubCategory(
                        name = "Шапочки",
                        categoryId = 6,
                        subCategoryId = 3
                    )
                ),
                name = "Войлок",
                categoryId = 6
            ),
            HatCategory(
                subcategories = listOf(
                    HatSubCategory(
                        name = "Реставрации",
                        categoryId = 7,
                        subCategoryId = 1
                    )
                ),
                name = "Реставрация шляпок",
                categoryId = 7
            ),
            HatCategory(
                subcategories = listOf(
                    HatSubCategory(
                        name = "Подарочные коробки",
                        categoryId = 8,
                        subCategoryId = 1
                    )
                ),
                name = "Шляпные коробки",
                categoryId = 8
            ),
            HatCategory(
                subcategories = listOf(
                    HatSubCategory(
                        name = "Берет",
                        categoryId = 9,
                        subCategoryId = 1
                    ),
                    HatSubCategory(
                        name = "Картуз",
                        categoryId = 9,
                        subCategoryId = 2
                    ),
                    HatSubCategory(
                        name = "Шапочка",
                        categoryId = 9,
                        subCategoryId = 3
                    ),
                    HatSubCategory(
                        name = "Чалма",
                        categoryId = 9,
                        subCategoryId = 4
                    ),
                    HatSubCategory(
                        name = "Панама",
                        categoryId = 9,
                        subCategoryId = 5
                    ),
                    HatSubCategory(
                        name = "Шопер",
                        categoryId = 9,
                        subCategoryId = 6
                    ),
                    HatSubCategory(
                        name = "Кепи",
                        categoryId = 9,
                        subCategoryId = 7
                    )
                ),
                name = "Тканевые",
                categoryId = 9
            ),
            HatCategory(
                subcategories = listOf(
                    HatSubCategory(
                        name = "Картуз",
                        categoryId = 10,
                        subCategoryId = 1
                    )
                ),
                name = "Меховые",
                categoryId = 10
            ),
            HatCategory(
                subcategories = listOf(
                    HatSubCategory(
                        name = "Соломка риторто (вискоза)",
                        categoryId = 11,
                        subCategoryId = 1
                    )
                ),
                name = "Соломка риторто",
                categoryId = 11
            )
        )
    )

    fun getCategoriesNames() = ArrayList(structure.hatCategories.map { it.name })

    fun getSubCategoriesNames(categoryId: Int) = ArrayList(
        structure.hatCategories
            .find { it.categoryId == categoryId }
            ?.subcategories
            ?.map { it.name }
            ?: emptyList()
    )

    fun findCategory(name: String): HatCategory? = structure.hatCategories.find { it.name == name }

    fun findSubCategory(categoryId: Int, name: String): HatSubCategory? {
        return structure.hatCategories
            .find { it.categoryId == categoryId }
            ?.subcategories
            ?.find { it.name == name }
    }
}