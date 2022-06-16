package thisiscote.chapter10

class Disjoint2 {

    // 특정 원소가 속한 집합을 찾기
    private fun findParent(x: Int): Int {
        // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        return if (x == this.parents[x]) x else {
            val findParent = findParent(this.parents[x])
            parents[x] = findParent
            findParent
        }
    }

    // 두 원소가 속한 집합을 합치기
    private fun unionParent(a: Int, b: Int) {
        var a = a
        var b = b
        a = findParent(a)
        b = findParent(b)
        if (a < b) this.parents[b] = a else this.parents[a] = b
    }

    lateinit var parents: IntArray
    fun solution(count: Int, arrays: Array<IntArray>): IntArray {
        this.parents = IntArray(count) { it }

        // Union 연산을 각각 수행
        for (array in arrays) {
            val a: Int = array[0] - 1
            val b: Int = array[1] - 1
            unionParent(a, b)
        }

        // 각 원소가 속한 집합 출력하기
        print("각 원소가 속한 집합: ")
        for (i in 0 until count) {
            print(findParent(i).toString() + " ")
        }
        println()

        // 부모 테이블 내용 출력하기
        print("부모 테이블: ")
        for (i in 0 until count) {
            print(this.parents[i].toString() + " ")
        }
        println()
        return parents
    }
}