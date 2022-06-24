package thisiscote.chapter16

class MiningGold31 {

    fun solution(arrays: Array<IntArray>): Int {
        val arrayOfIntArrays = Array(arrays.size) { IntArray(arrays[0].size) { 0 } }

        for (i in arrays.indices) {
            arrayOfIntArrays[i][0] = arrays[i][0]
        }

        mine(arrays, arrayOfIntArrays)

        return arrayOfIntArrays.flatMap { intArray -> intArray.map { it } }.maxOf { it }
    }

    private fun mine(arrays: Array<IntArray>, arrayOfIntArrays: Array<IntArray>) {
        for (x in 1 until arrays[0].size) {
            for (y in arrays.indices) {
                var max = arrays[y][x] + arrayOfIntArrays[y][x - 1]
                if (y > 0 && y < arrays.size - 1) {
                    if (max < arrays[y][x] + arrayOfIntArrays[y + 1][x - 1]) {
                        max = arrays[y][x] + arrayOfIntArrays[y + 1][x - 1]
                    }

                    if (max < arrays[y][x] + arrayOfIntArrays[y - 1][x - 1]) {
                        max = arrays[y][x] + arrayOfIntArrays[y - 1][x - 1]
                    }

                } else if (y == arrays.size - 1) {
                    if (max < arrays[y][x] + arrayOfIntArrays[y - 1][x - 1]) {
                        max = arrays[y][x] + arrayOfIntArrays[y - 1][x - 1]
                    }
                } else if (y == 0) {
                    if (max < arrays[y][x] + arrayOfIntArrays[1][x - 1]) {
                        max = arrays[y][x] + arrayOfIntArrays[1][x - 1]
                    }
                }
                println("$y,$x = $max")
                arrayOfIntArrays[y][x] = max
            }
        }
    }
}

/*
세로 축을 먼저 계산하고 가로 축을 계산했어야 하는데 가로 축 부터 계산했었다.
0보다 크고 사이즈보다 작을 때 '왼쪽 아래' 계산 식을 잘못 세웠다.
 */