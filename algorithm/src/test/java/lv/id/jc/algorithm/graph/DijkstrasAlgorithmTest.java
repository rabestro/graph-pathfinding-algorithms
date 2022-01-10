package lv.id.jc.algorithm.graph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DijkstrasAlgorithmTest {

    private SearchAlgorithm<String> algorithm;
    private Graph<String> graph;

    @BeforeEach
    void setUp() {
        LinkedHashMap<String, Number> fromA = new LinkedHashMap<>();
        fromA.put("B", 5);
        fromA.put("H", 2);
        LinkedHashMap<String, Number> fromB = new LinkedHashMap<>();
        fromB.put("A", 5);
        fromB.put("C", 7);
        LinkedHashMap<String, Number> fromC = new LinkedHashMap<>();
        fromC.put("B", 7);
        fromC.put("D", 3);
        fromC.put("G", 4);
        LinkedHashMap<String, Number> fromD = new LinkedHashMap<>();
        fromD.put("C", 20);
        fromD.put("E", 4);
        LinkedHashMap<String, Number> fromE = new LinkedHashMap<>();
        fromE.put("F", 5);
        LinkedHashMap<String, Number> fromF = new LinkedHashMap<>();
        fromF.put("G", 6);
        LinkedHashMap<String, Number> fromG = new LinkedHashMap<>();
        fromG.put("C", 4);
        LinkedHashMap<String, Number> fromH = new LinkedHashMap<>();
        fromH.put("G", 3);

        LinkedHashMap<String, Map<String, Number>> nodes = new LinkedHashMap<>();
        nodes.put("A", fromA);
        nodes.put("B", fromB);
        nodes.put("C", fromC);
        nodes.put("D", fromD);
        nodes.put("E", fromE);
        nodes.put("F", fromF);
        nodes.put("G", fromG);
        nodes.put("H", fromH);

        graph = Graph.of(nodes);
        algorithm = new DijkstrasAlgorithm<>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findPathAA() {
        // Java 8

        String source = "A";
        String target = "A";

        List<String> expected = new ArrayList<>();
        expected.add("A");

        List<String> actual = algorithm.findPath(graph, source, target);

        assertEquals(expected, actual);
    }

    @Test
    void findPathBB() {
        // Java 11

        var source = "B";
        var target = "B";

        var expected = List.of("B");

        var actual = algorithm.findPath(graph, source, target);

        assertEquals(expected, actual);
    }

    @Test
    void findPathCD() {
        var source = "C";
        var target = "D";

        var expected = List.of("C", "D");
        var actual = algorithm.findPath(graph, source, target);

        assertEquals(expected, actual);
    }

    @Test
    void findPathDC() {
        var source = "D";
        var target = "C";

        var expected = List.of("D", "E", "F", "G", "C");
        var actual = algorithm.findPath(graph, source, target);

        assertEquals(expected, actual);
    }

}