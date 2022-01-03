package graph

import lv.id.jc.algorithm.graph.BreadthFirstSearch
import lv.id.jc.algorithm.graph.DijkstrasAlgorithm
import lv.id.jc.algorithm.graph.Graph
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("Comparison of two algorithms")
class SearchAlgorithmSpec extends Specification {
    @Subject
    def bfsAlgorithm = new BreadthFirstSearch()

    @Subject
    def dijkstras = new DijkstrasAlgorithm()

    def 'should find a route for a complex graph'() {
        given: 'A complex graph sample'
        def graph = Graph.of([
                A: [B: 5, H: 2],
                B: [A: 5, C: 7],
                C: [B: 7, D: 3, G: 4],
                D: [C: 20, E: 4],
                E: [F: 5],
                F: [G: 6],
                G: [C: 4],
                H: [G: 3]
        ])

        when: 'we use Breadth First Search algorithm for the first route'
        def routeOne = bfsAlgorithm.findPath(graph, source, target)

        and: 'we use Dijkstras algorithm for the second route'
        def routeTwo = dijkstras.findPath(graph, source, target)

        then: "the first route is the shortest"
        routeOne == shortest

        and: 'the second route is the fastest'
        routeTwo == fastest

        and: 'the distance calculated correctly'
        graph.getDistance(routeOne) == d1 as double
        graph.getDistance(routeTwo) == d2 as double

        where:
        source | target || d1 | shortest                  | d2 | fastest
        'A'    | 'A'    || 0  | ['A']                     | 0  | ['A']
        'B'    | 'B'    || 0  | ['B']                     | 0  | ['B']
        'A'    | 'B'    || 5  | ['A', 'B']                | 5  | ['A', 'B']
        'B'    | 'A'    || 5  | ['B', 'A']                | 5  | ['B', 'A']
        'A'    | 'C'    || 12 | ['A', 'B', 'C']           | 9  | ['A', 'H', 'G', 'C']
        'C'    | 'A'    || 12 | ['C', 'B', 'A']           | 12 | ['C', 'B', 'A']
        'A'    | 'G'    || 5  | ['A', 'H', 'G']           | 5  | ['A', 'H', 'G']
        'C'    | 'D'    || 3  | ['C', 'D']                | 3  | ['C', 'D']
        'D'    | 'C'    || 20 | ['D', 'C']                | 19 | ['D', 'E', 'F', 'G', 'C']
        'B'    | 'D'    || 10 | ['B', 'C', 'D']           | 10 | ['B', 'C', 'D']
        'D'    | 'B'    || 27 | ['D', 'C', 'B']           | 26 | ['D', 'E', 'F', 'G', 'C', 'B']
        'D'    | 'H'    || 34 | ['D', 'C', 'B', 'A', 'H'] | 33 | ['D', 'E', 'F', 'G', 'C', 'B', 'A', 'H']
    }

}
