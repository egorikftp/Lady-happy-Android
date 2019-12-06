package com.egoriku.mozaik.calculator

internal class MatrixCalculator(
        private val count: Int,
        private val libra: Libra
) {

    interface Libra {
        fun getWeight(index: Int): Float
    }

    private class Result {
        var minDiff = Float.MAX_VALUE
        var matrix: Array<IntArray> = emptyArray()
    }

    private fun analyze(matrix: Array<IntArray>, result: Result) {
        val maxDiff = getMaxDiff(libra, matrix)
        if (maxDiff < result.minDiff) {
            result.minDiff = maxDiff
            result.matrix = cloneArray(matrix)
        }
    }

    fun calculate(rows: Int): Array<IntArray> {
        val result = checkAllVariants(rows)
        return result.matrix
    }

    private fun checkAllVariants(rowsCount: Int): Result {
        val result = Result()
        val rows = Array(rowsCount) { IntArray(count) }

        for (i in rowsCount - 1 downTo 0) {
            val array = IntArray(count)
            for (a in 0 until count) {
                array[a] = -1
            }
            rows[i] = array
        }

        val forFirst = count - rowsCount

        for (i in 0 until count) {
            val toFirst = i < forFirst + 1
            rows[if (toFirst) 0 else i - forFirst][if (toFirst) i else 0] = i
        }
        doShuffle(rows, result)

        return result
    }

    private fun doShuffle(data: Array<IntArray>, result: Result) {
        analyze(data, result)
        moveAll(data, 0, result)
    }

    private fun moveAll(data: Array<IntArray>, startFromIndex: Int, result: Result) {
        while (canMoveToNext(startFromIndex, data)) {
            move(startFromIndex, data)
            analyze(data, result)
            if (startFromIndex + 1 < data.size - 1) {
                moveAll(cloneArray(data), startFromIndex + 1, result)
            }
        }
    }

    companion object {
        private fun getMaxDiff(libra: Libra, variant: Array<IntArray>): Float {

            val sums = FloatArray(variant.size)
            for (i in variant.indices) {
                sums[i] = getWeightSum(libra, *variant[i])
            }

            val average = getAverage(*sums)
            var maxDiff = 0f
            for (sum in sums) {
                val diff = Math.abs(sum - average)
                if (diff > maxDiff) {
                    maxDiff = diff
                }
            }
            return maxDiff
        }

        private fun getWeightSum(libra: Libra, vararg positions: Int): Float {
            var s = 0f
            for (position in positions) {
                if (position == -1) {
                    continue
                }
                s += libra.getWeight(position)
            }
            return s
        }

        private fun getAverage(vararg values: Float): Float {
            var sum = 0f
            var nonZeroValuesCount = 0
            for (value in values) {
                sum += value
                if (value != 0f) {
                    nonZeroValuesCount++
                }
            }
            return sum / nonZeroValuesCount.toFloat()
        }

        private fun cloneArray(src: Array<IntArray>): Array<IntArray> {
            val length = src.size
            val target = Array(length) { IntArray(src[0].size) }
            for (i in 0 until length) {
                System.arraycopy(src[i], 0, target[i], 0, src[i].size)
            }
            return target
        }


        private fun canMoveToNext(row: Int, data: Array<IntArray>): Boolean {
            return data[row][1] != -1 && data.size > row + 1
        }


        private fun move(row: Int, data: Array<IntArray>) {
            val rowArray = data[row]
            val nextRowArray = data[row + 1]
            if (nextRowArray[nextRowArray.size - 1] != -1) {
                move(row + 1, data)
            }
            val moveIndex = getLastNoNegativeIndex(rowArray)

            val value = rowArray[moveIndex]
            shiftByOneToRight(nextRowArray)
            nextRowArray[0] = value
            rowArray[moveIndex] = -1
        }

        private fun shiftByOneToRight(array: IntArray) {
            for (i in array.indices.reversed()) {
                if (i == 0) {
                    array[i] = -1
                } else {
                    array[i] = array[i - 1]
                }
            }
        }

        private fun getLastNoNegativeIndex(array: IntArray): Int {
            for (i in array.indices.reversed()) {
                if (array[i] != -1) {
                    return i
                }
            }
            return -1
        }
    }
}