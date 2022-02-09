import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lv.id.jc.algorithm.graph.BreadthFirstSearch;
import lv.id.jc.algorithm.graph.DijkstrasAlgorithm;
import lv.id.jc.algorithm.graph.Graph;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class GraphCli {
    public static void main(String[] args) throws IOException {
        var in = GraphCli.class.getClassLoader().getResourceAsStream("complex.yaml");
        var schema = new YAMLMapper().readValue(in, Map.class);
        var graph = Graph.of(schema);

        var scanner = new Scanner(System.in);

        System.out.print("Source node: ");
        var source = scanner.next().toUpperCase();

        System.out.print("Target node:");
        var target = scanner.next().toUpperCase();

        var shortest = new BreadthFirstSearch<>().findPath(graph, source, target);
        var fastest = new DijkstrasAlgorithm<>().findPath(graph, source, target);

        System.out.println("The shortest path: " + shortest);
        System.out.println("The fastest path: " + fastest);
    }

}
