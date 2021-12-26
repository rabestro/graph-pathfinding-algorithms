package lv.id.jc

import algorithm.BreadthFirstSearch
import algorithm.DijkstrasAlgorithm
import algorithm.Graph
import algorithm.SearchAlgorithm

class AppKotlin {

    companion object {
        @JvmStatic
        private val complexGraph = Graph(
            mapOf(
                "A" to mapOf("B" to 5, "H" to 2),
                "B" to mapOf("A" to 5, "C" to 7),
                "C" to mapOf("B" to 7, "D" to 3, "G" to 4),
                "D" to mapOf("C" to 20, "E" to 4),
                "E" to mapOf("F" to 5),
                "F" to mapOf("G" to 6),
                "G" to mapOf("C" to 4),
                "H" to mapOf("G" to 3)
            )
        )

        @JvmStatic
        private val fastest: SearchAlgorithm<String> = DijkstrasAlgorithm()

        @JvmStatic
        private val shortest: SearchAlgorithm<String> = BreadthFirstSearch()

        @JvmStatic
        fun main(args: Array<String>) {
            println(complexGraph)
            printRoute(complexGraph, "D", "C")
            printRoute(complexGraph, "A", "G")
            printRoute(complexGraph, "D", "H")
        }

        @JvmStatic
        private fun printRoute(graph: Graph<String>, source: String, target: String) {
            val routeOne = shortest.findPath(graph, source, target)
            val routeTwo = fastest.findPath(graph, source, target)

            val message: String = String.format(
                    "%nFind the path from %s to %s%n" +
                            "    - the shortest take %.0f min and the path is %s%n" +
                            "    - the fastest take %.0f min and the path is %s",
                    source, target,
                    graph.getDistance(routeOne), routeOne,
                    graph.getDistance(routeTwo), routeTwo
                )
            println(message)
        }
    }


}