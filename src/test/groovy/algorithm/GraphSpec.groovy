package algorithm

import spock.lang.Specification
import spock.lang.Unroll

class GraphSpec extends Specification {

    @Unroll("distance is #distance for path #path")
    def "should calculate distance"() {
        given:
        def graph = new Graph([
                A: [B: 7, C: 2],
                B: [A: 3, C: 5],
                C: [A: 1, B: 3]
        ])

        expect:
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
