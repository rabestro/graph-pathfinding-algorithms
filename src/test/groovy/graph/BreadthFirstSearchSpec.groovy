package graph

import lv.id.jc.algorithm.graph.BreadthFirstSearch
import lv.id.jc.algorithm.graph.Graph
import spock.lang.*

@Title("Breadth First Search Algorithm")
@See("https://en.wikipedia.org/wiki/Breadth-first_search")
@Narrative("""
Breadth First Search algorithm for finding the shortest paths between nodes in a graph
""")
class BreadthFirstSearchSpec extends Specification {
    @Subject
    def algorithm = new BreadthFirstSearch()

    def 'should find a route for simple graph'() {
        given: 'A simple graph'
        def graph = Graph.of([
                A: [B: 7, C: 2],
                B: [A: 3, C: 5],
                C: [A: 1, B: 3]
        ])

        when: 'we use Breadth First Search algorithm to find a path'
        def path = algorithm.findPath(graph, source, target)

        then: 'we get the shortest path'
        path == shortest

        and: 'the distance calculated correctly'
        graph.getDistance(path) == time as double

        where:
        source | target || time | shortest
        'A'    | 'A'    || 0    | ['A']
        'A'    | 'B'    || 7    | ['A', 'B']
        'B'    | 'C'    || 5    | ['B', 'C']
        'C'    | 'B'    || 3    | ['C', 'B']
    }

    def 'should find a route for complex graph'() {
        given: 'A complex graph'
        def graph = Graph.of([
                A: [B: 1],
                B: [A: 1, D: 1],
                C: [A: 1],
                D: [C: 1, E: 1],
                E: [F: 1],
                F: [D: 1, E: 1]])

        when: 'we use Breadth First Search algorithm to find a path'
        def path = algorithm.findPath(graph, source, target)

        then: 'we get the shortest path'
        path == shortest

        and: 'the distance calculated correctly'
        graph.getDistance(path) == time as double

        where:
        source | target || shortest
        'A'    | 'A'    || ['A']
        'B'    | 'B'    || ['B']
        'A'    | 'B'    || ['A', 'B']
        'B'    | 'A'    || ['B', 'A']
        'A'    | 'C'    || ['A', 'B', 'D', 'C']
        'C'    | 'A'    || ['C', 'A']
        'E'    | 'B'    || ['E', 'F', 'D', 'C', 'A', 'B']

        and:
        time = shortest.size() - 1
    }

    def "should return an empty path if can't find a route"() {
        given: 'a simple graph with no edge between nodes'
        def graph = Graph.of([A: [:], B: [:]])

        when: 'we use Breadth First Search algorithm to find a path'
        def path = algorithm.findPath(graph, 'A', 'B')

        then: 'we get an empty path'
        path == []
    }
}
