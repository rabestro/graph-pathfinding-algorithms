package algorithm

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class SearchAlgorithmSpec extends Specification {
    @Subject
    def shortest = new BreadthFirstSearch<String>()

    @Subject
    def fastest = new DijkstrasAlgorithm<String>()

    @Unroll("from #source to #target the time is #time and the fastest path is #fastestPath")
    def 'should find a route for a complex graph'() {
        given:
        def graph = new Graph<String>([
                A: [B: 5, H: 2],
                B: [A: 5, C: 7],
                C: [B: 7, D: 3, G: 4],
                D: [C: 20, E: 4],
                E: [F: 5],
                F: [G: 6],
                G: [C: 4],
                H: [G: 3]
        ])

        when:
        def routeOne = shortest.findPath(graph, source, target)

        and:
        def routeTwo = fastest.findPath(graph, source, target)

        then:
        routeOne == shortestPath

        and:
        routeTwo == fastestPath

        where:
        source | target || time | shortestPath              | fastestPath
        'A'    | 'A'    || 0    | ['A']                     | ['A']
        'B'    | 'B'    || 0    | ['B']                     | ['B']
        'A'    | 'B'    || 5    | ['A', 'B']                | ['A', 'B']
        'B'    | 'A'    || 5    | ['B', 'A']                | ['B', 'A']
        'A'    | 'C'    || 9    | ['A', 'B', 'C']           | ['A', 'H', 'G', 'C']
        'C'    | 'A'    || 12   | ['C', 'B', 'A']           | ['C', 'B', 'A']
        'A'    | 'G'    || 5    | ['A', 'H', 'G']           | ['A', 'H', 'G']
        'C'    | 'D'    || 3    | ['C', 'D']                | ['C', 'D']
        'D'    | 'C'    || 19   | ['D', 'C']                | ['D', 'E', 'F', 'G', 'C']
        'B'    | 'D'    || 10   | ['B', 'C', 'D']           | ['B', 'C', 'D']
        'D'    | 'B'    || 26   | ['D', 'C', 'B']           | ['D', 'E', 'F', 'G', 'C', 'B']
        'D'    | 'H'    || 33   | ['D', 'C', 'B', 'A', 'H'] | ['D', 'E', 'F', 'G', 'C', 'B', 'A', 'H']
    }

}
