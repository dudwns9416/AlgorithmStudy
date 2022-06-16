package thisiscote.chapter9

class Telegram {

    private lateinit var totalVisit: IntArray
    private lateinit var maxOfDistance: IntArray

    fun solution(array: Array<IntArray>, city: Int): IntArray {
        totalVisit = IntArray(array[city].size) { 0 }
        maxOfDistance = IntArray(array[city].size) { INFINITE }

        val routes = array.copyOf()
        for (route in array.indices) {
            for (row in array.indices) {
                if (route == row) continue
                for (column in 0 until array[0].size) {
                    if (column == route || column == row) continue
                    routes[row][column] = minOf(routes[row][column], routes[row][route] + routes[route][column])
                }
            }
        }

        dfs(routes, totalVisit, city - 1, 0)

        return intArrayOf(
            totalVisit.filter { it != 0 }.size - 1,
            maxOfDistance.filter { it != INFINITE }.maxOf { it }
        )
    }

    var max = 0

    private fun dfs(routes: Array<IntArray>, visits: IntArray, row: Int, distance: Int) {
        if (visits[row] != 0) return
        if (totalVisit[row] != 0) return


        max = maxOf(distance, max)
        val currentVisits = visits.copyOf()
        currentVisits[row] = 1

        for (c in 0 until routes[row].size) {
            if (c == routes[row].size - 1) {
                totalVisit[row] = 1
            }

            if (c == row) continue
            if (routes[row][c] == INFINITE) continue
            if (routes[row][c] == 0) continue

            maxOfDistance[c] = minOf(distance + routes[row][c], maxOfDistance[c])
            dfs(routes, currentVisits, c, distance + routes[row][c])
        }
    }

    companion object {
        private const val INFINITE = 987654321
    }
}