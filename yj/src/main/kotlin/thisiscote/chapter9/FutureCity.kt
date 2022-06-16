package thisiscote.chapter9

class FutureCity {

    fun solution(array: Array<IntArray>, k: Int, x: Int): Int {
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

        val routeToK = routes[0][k - 1]
        val routeToXFromB = routes[k - 1][x - 1]

        if (routeToK == INFINITE || routeToXFromB == INFINITE) {
            return -1
        }

        return routeToK + routeToXFromB
    }

    companion object {
        private const val INFINITE = 987654321
    }
}