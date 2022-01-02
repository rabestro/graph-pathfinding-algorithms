package graph

import lv.id.jc.algorithm.graph.DijkstrasAlgorithm
import lv.id.jc.algorithm.graph.Graph
import spock.lang.Specification
import spock.lang.Subject

class DijkstrasAlgorithmSpec extends Specification {
    @Subject
    def algorithm = new DijkstrasAlgorithm<String>()

    def 'should find a route for a simple graph'() {
        given: 'A simple graph'
        def graph = new Graph([
                A: [B: 7, C: 2],
                B: [A: 3, C: 5],
                C: [A: 1, B: 3]
        ])

        when: "we use Dijkstra's algorithm to find a path"
        def path = algorithm.findPath(graph, source, target)

        then: 'we get the fastest way'
        path == fastest

        and: 'the distance calculated correctly'
        graph.getDistance(path) == time as double

        where:
        source | target || time | fastest
        'A'    | 'A'    || 0    | ['A']
        'B'    | 'B'    || 0    | ['B']
        'C'    | 'C'    || 0    | ['C']
        'A'    | 'B'    || 5    | ['A', 'C', 'B']
    }

    def 'should find a route for a medium graph'() {
        given: 'A medium graph'
        def graph = new Graph([
                A: [B: 5],
                B: [A: 5, C: 10],
                C: [B: 20, D: 5],
                D: [E: 5],
                E: [B: 5]
        ])

        when: "we use Dijkstra's algorithm to find a path"
        def path = algorithm.findPath(graph, source, target)

        then: 'we get the fastest way'
        path == fastest

        and: 'the distance calculated correctly'
        graph.getDistance(path) == time as double

        where:
        source | target || time | fastest
        'A'    | 'A'    || 0    | ['A']
        'B'    | 'B'    || 0    | ['B']
        'A'    | 'B'    || 5    | ['A', 'B']
        'B'    | 'A'    || 5    | ['B', 'A']
        'A'    | 'C'    || 15   | ['A', 'B', 'C']
        'C'    | 'A'    || 20   | ['C', 'D', 'E', 'B', 'A']
    }

    def 'should find a route for a complex graph'() {
        given: 'A complex graph'
        def graph = new Graph([
                A: [B: 5, H: 2],
                B: [A: 5, C: 7],
                C: [B: 7, D: 3, G: 4],
                D: [C: 20, E: 4],
                E: [F: 5],
                F: [G: 6],
                G: [C: 4],
                H: [G: 3]
        ])

        when: "we use Dijkstra's algorithm to find a path"
        def path = algorithm.findPath(graph, source, target)

        then: 'we get the fastest way'
        path == fastest

        and: 'the distance calculated correctly'
        graph.getDistance(path) == time as double

        where:
        source | target || time | fastest
        'A'    | 'A'    || 0    | ['A']
        'B'    | 'B'    || 0    | ['B']
        'A'    | 'B'    || 5    | ['A', 'B']
        'B'    | 'A'    || 5    | ['B', 'A']
        'A'    | 'C'    || 9    | ['A', 'H', 'G', 'C']
        'C'    | 'A'    || 12   | ['C', 'B', 'A']
        'A'    | 'G'    || 5    | ['A', 'H', 'G']
        'C'    | 'D'    || 3    | ['C', 'D']
        'D'    | 'C'    || 19   | ['D', 'E', 'F', 'G', 'C']
        'B'    | 'D'    || 10   | ['B', 'C', 'D']
        'D'    | 'B'    || 26   | ['D', 'E', 'F', 'G', 'C', 'B']
        'D'    | 'H'    || 33   | ['D', 'E', 'F', 'G', 'C', 'B', 'A', 'H']
    }

}
