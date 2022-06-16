package thisiscote.chpater15

import kotlin.math.floor

class SearchingFixedPoint28 {

    fun solution(array: IntArray): Int {
        val first = 0
        val last = array.size - 1
        return find(array, first, last)
    }

    private fun find(array: IntArray, first: Int, last: Int): Int {
        val middle = floor((first + last) / 2.0).toInt()

        if (first > last) return -1

        if (array[middle] == middle) return middle

        if (array[middle] < middle) return find(array, middle + 1, last)

        return find(array, first, middle - 1)
    }

}