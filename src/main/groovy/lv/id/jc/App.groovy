package lv.id.jc

import algorithm.BreadthFirstSearch
import algorithm.DijkstrasAlgorithm
import algorithm.Graph
import algorithm.SearchAlgorithm

class App {
    private static final Graph<String> complex = new Graph<>(Map.of(
            "A", Map.of("B", 5, "H", 2),
            "B", Map.of("A", 5, "C", 7),
            "C", Map.of("B", 7, "D", 3, "G", 4),
            "D", Map.of("C", 20, "E", 4),
            "E", Map.of("F", 5),
            "F", Map.of("G", 6),
            "G", Map.of("C", 4),
            "H", Map.of("G", 3)
    ))
    private static final SearchAlgorithm<String> fastest = new DijkstrasAlgorithm<>()
    private static final SearchAlgorithm<String> shortest = new BreadthFirstSearch<>()

    static void main(String[] args) {
        System.out.println(complex)

        printRoute(complex, "D", "C")
        printRoute(complex, "A", "G")
        printRoute(complex, "D", "H")
    }

    private static void printRoute(final Graph<String> graph, final String source, final String target) {
        final var routeOne = shortest.findPath(graph, source, target)
        final var routeTwo = fastest.findPath(graph, source, target)
        final var message = """
        Find the path from %s to %s
            - the shortest take %.0f min and the path is %s
            - the fastest take %.0f min and the path is %s"""
                .formatted(
                        source, target,
                        graph.getDistance(routeOne), routeOne,
                        graph.getDistance(routeTwo), routeTwo)

        System.out.println(message)
    }
}
