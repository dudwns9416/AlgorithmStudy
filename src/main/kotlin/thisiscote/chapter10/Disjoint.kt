package thisiscote.chapter10

class Disjoint {

    data class Node(
        val index: Int,
        var parent: Int,
    ) {
        fun isRoot(): Boolean {
            return this.parent == this.index
        }
    }

    lateinit var nodes: Array<Node>
    fun solution(count: Int, arrays: Array<IntArray>): IntArray {
        nodes = Array(count) { Node(it, it) }

        arrays.forEach { ints ->
            unionParent(ints)
        }

        nodes.forEach {
            print("$it ")
        }
        return nodes.map { it.parent }.toIntArray()
    }

    private fun unionParent(ints: IntArray) {
        val first = ints[0] - 1
        val second = ints[1] - 1
        val minIndex = minOf(first, second)
        val maxIndex = maxOf(first, second)

        val minNode = findParent(nodes[minIndex])
        val maxNode = findParent(nodes[maxIndex])

        if (minNode.parent < maxNode.parent) {
            maxNode.parent = minNode.parent
        } else {
            minNode.parent = maxNode.parent
        }
    }

    private fun findParent(node: Node): Node {
        return if (node.isRoot()) {
            node
        } else {
            val parent = findParent(nodes[node.parent])
            node.parent = parent.parent
            parent
        }
    }

}