package algorithm

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class BreadthFirstSearchSpec extends Specification {
    @Subject
    def algorithm = new BreadthFirstSearch()

    @Unroll("from #source to #target the time is #time and the path is #expected")
    def 'should find a route for sample one'() {
        given:
        def graph = new Graph([
                A: [B: 7, C: 2],
                B: [A: 3, C: 5],
                C: [A: 1, B: 3]
        ])

        when:
        def path = algorithm.findPath(graph, source, target)

        then:
        path == expected

        and:
        graph.getDistance(path) == time as double

        where:
        source | target || time | expected
        'A'    | 'A'    || 0    | ['A']
        'A'    | 'B'    || 7    | ['A', 'B']
        'B'    | 'C'    || 5    | ['B', 'C']
    }

}
