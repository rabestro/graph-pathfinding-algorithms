package algorithm

import spock.lang.Specification

class GraphSpec extends Specification {

    def "should calculate distance"() {
        given: 'A simple graph'
        def graph = new Graph([
                A: [B: 7, C: 2],
                B: [A: 3, C: 5],
                C: [A: 1, B: 3]
        ])

        expect: 'the distance calculated correctly'
        graph.getDistance(path) == distance as double

        where:
        path                 | distance
        ['A']                | 0
        ['B']                | 0
        ['C']                | 0
        ['A', 'B']           | 7
        ['B', 'C']           | 5
        ['A', 'B', 'C']      | 12
        ['C', 'A']           | 1
        ['A', 'B', 'C', 'A'] | 13
    }

}
