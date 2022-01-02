package lv.id.jc;

import lv.id.jc.algorithm.graph.BreadthFirstSearch;
import lv.id.jc.algorithm.graph.DijkstrasAlgorithm;
import lv.id.jc.algorithm.graph.Graph;
import lv.id.jc.algorithm.graph.SearchAlgorithm;

import java.util.Map;

public class AppExample {
    private static final Graph<Character> COMPLEX_GRAPH = new Graph<>(Map.of(
            'A', Map.of('B', 5, 'H', 2),
            'B', Map.of('A', 5, 'C', 7),
            'C', Map.of('B', 7, 'D', 3, 'G', 4),
            'D', Map.of('C', 20, 'E', 4),
            'E', Map.of('F', 5),
            'F', Map.of('G', 6),
            'G', Map.of('C', 4),
            'H', Map.of('G', 3)
    ));
    private static final SearchAlgorithm<Character> fastest = new DijkstrasAlgorithm<>();
    private static final SearchAlgorithm<Character> shortest = new BreadthFirstSearch<>();

    public static void main(String[] args) {
        System.out.println(COMPLEX_GRAPH);

        printRoute(COMPLEX_GRAPH, 'D', 'C');
        printRoute(COMPLEX_GRAPH, 'A', 'G');
        printRoute(COMPLEX_GRAPH, 'D', 'H');
    }

    private static void printRoute(final Graph<Character> graph,
                                   final Character source,
                                   final Character target) {
        final var routeOne = shortest.findPath(graph, source, target);
        final var routeTwo = fastest.findPath(graph, source, target);
        final var message = """
                        
                Find the path from %s to %s
                    - the shortest take %.0f min and the path is %s
                    - the fastest take %.0f min and the path is %s"""
                .formatted(
                        source, target,
                        graph.getDistance(routeOne), routeOne,
                        graph.getDistance(routeTwo), routeTwo);

        System.out.println(message);
    }
}
