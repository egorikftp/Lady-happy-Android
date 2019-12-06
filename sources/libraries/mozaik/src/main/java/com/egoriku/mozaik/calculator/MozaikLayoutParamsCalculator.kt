package com.egoriku.mozaik.calculator

import android.util.SparseIntArray
import com.egoriku.mozaik.model.MozaikImageItem

internal class MozaikLayoutParamsCalculator(
        private val matrix: Array<IntArray>,
        private val imageItems: List<MozaikImageItem>,
        private val maxWidth: Int,
        private val spacing: Int
) {
    private val rowHeight: SparseIntArray = SparseIntArray(1)
    private val photoWidth: SparseIntArray = SparseIntArray(1)

    private fun getAspectRatioSumForRow(row: Int): Float {
        var sum = 0f
        val rowArray = matrix[row]
        for (index in rowArray) {
            if (index == -1) {
                break
            }
            sum += imageItems[index].aspectRatio
        }
        return sum
    }

    fun getPostImagePosition(index: Int): PostImagePosition {
        val photo = imageItems[index]
        val rowNumber = getRowNumberForIndex(matrix, index)
        val numberInrow = getColumnNumberForIndex(matrix, index)
        val propotrionRowSum = getAspectRatioSumForRow(rowNumber).toDouble()
        val currentPhotoProportion = photo.aspectRatio.toDouble()
        val coeficien = currentPhotoProportion / propotrionRowSum
        val width = (maxWidth.toDouble() * coeficien).toInt()
        val height = (photo.height.toDouble() * (width.toDouble() / photo.width.toDouble())).toInt()
        var marginLeft = 0
        val firstIndexInRow = index - numberInrow
        for (i in firstIndexInRow until index) {
            marginLeft += photoWidth[i] + spacing
        }
        var marginTop = 0
        for (i in 0 until rowNumber) {
            marginTop += rowHeight[i] + spacing
        }

        val position = PostImagePosition().apply {
            sizeY = height
            sizeX = width
            marginX = marginLeft
            marginY = marginTop
        }

        photoWidth.put(index, width)
        rowHeight.put(rowNumber, height)

        return position
    }

    companion object {
        private fun getRowNumberForIndex(array: Array<IntArray>, index: Int): Int {
            for (i in array.indices) {
                val inner = array[i]
                for (a in inner) {
                    if (a == index) {
                        return i
                    }
                }
            }
            throw IllegalStateException("Value does not exist")
        }

        private fun getColumnNumberForIndex(array: Array<IntArray>, index: Int): Int {
            for (inner in array) {
                for (i in inner.indices) {
                    if (inner[i] == index) {
                        return i
                    }
                }
            }
            throw IllegalStateException("Value does not exist")
        }
    }

}