package algorithm

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class DijkstrasSpec extends Specification {

    static final SAMPLE_ONE = [
            A: [B: 7, C: 2],
            B: [A: 3, C: 5],
            C: [A: 1, B: 3]
    ] as Graph<String>

    @Subject
    def algorithm = new Dijkstras<String>()

    @Unroll("from #source to #target the time is #time and the path is #expected")
    def 'should find a route for sample one'() {
        given:
        def graph = SAMPLE_ONE

        when:
        def path = algorithm.findPath(graph, source, target)

        then:
        path == expected

        where:
        source | target || time | expected
        'A'    | 'B'    || 7    | ['A', 'B']
    }
}
