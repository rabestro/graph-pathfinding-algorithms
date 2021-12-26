import algorithm.Dijkstras;
import algorithm.Graph;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello!");
        var algorithm = new Dijkstras<String>();

        var graph = new Graph<>(Map.of(
                "A", Map.of("B", 5),
                "B", Map.of("A", 7)
        ));

        var route = algorithm.findPath(graph, "A", "A");

        System.out.println(route);
        System.out.println(graph.getDistance(route));

        route = algorithm.findPath(graph, "A", "B");
        System.out.println(route);
        System.out.println(graph.getDistance(route));

        route = algorithm.findPath(graph, "B", "A");
        System.out.println(route);
        System.out.println(graph.getDistance(route));

    }


}
