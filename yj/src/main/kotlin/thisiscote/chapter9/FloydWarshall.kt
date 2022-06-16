package thisiscote.chapter9

class FloydWarshall {

    fun solution(array: Array<IntArray>): Array<IntArray> {
        val newArray = array.copyOf()

        for (d in newArray.indices) {
            for (r in newArray.indices) {
                if (r == d) continue
                for (c in 0 until newArray[0].size) {
                    if (r == c) continue
                    if (c == d) continue
                    newArray[r][c] = minOf(newArray[r][c], newArray[r][d] + newArray[d][c])
                }
            }
        }

        array.forEach {
            it.forEach { it ->
                print("$it ")
            }
            println()
        }

        return newArray
    }
}