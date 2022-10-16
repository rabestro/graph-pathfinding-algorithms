package lv.id.jc.algorithm.graph;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Java 8 + JUnit 5 + Parameterized Test
class DijkstrasAlgorithmTest {

    // Subject under test
    SearchAlgorithm<String> algorithm = new DijkstrasAlgorithm<>();

    // Sample data
    private static Stream<Arguments> providePathTime() {
        return Stream.of(
                Arguments.of("A", "A", Collections.singletonList("A"), 0),
                Arguments.of("B", "A", Arrays.asList("B", "A"), 3),
                Arguments.of("A", "B", Arrays.asList("A", "C", "B"), 5)
        );
    }

    @ParameterizedTest
    @MethodSource("providePathTime")
    @DisplayName("should find a route for a simple graph")
    void testRouteForSimpleGraph(String source, String target, List<String> fastestPath, double fastestTime) {
        // given
        Map<String, Number> fromA = new HashMap<>();
        fromA.put("B", 7);
        fromA.put("C", 2);
        Map<String, Number> fromB = new HashMap<>();
        fromB.put("A", 3);
        fromB.put("C", 5);
        Map<String, Number> fromC = new HashMap<>();
        fromC.put("A", 1);
        fromC.put("B", 3);
        Map<String, Map<String, Number>> nodes = new HashMap<>();
        nodes.put("A", fromA);
        nodes.put("B", fromB);
        nodes.put("C", fromC);
        Graph<String> graph = Graph.of(nodes);

        // when
        List<String> path = algorithm.findPath(graph, source, target);
        double time = graph.getDistance(path);

        // then
        assertEquals(fastestPath, path);
        assertEquals(fastestTime, time);
    }
}