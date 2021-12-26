package algorithm;

import java.util.*;
import java.util.stream.Stream;

public class Dijkstras<T> implements Algorithm<T> {

    @Override
    public List<T> findPath(Graph<T> graph, T source, T target) {
        final var queue = new LinkedList<T>();
        final var visited = new HashSet<T>();
        final var distances = new HashMap<T, Double>();
        final var path = new HashMap<T, T>();
        queue.add(source);

        while (!queue.isEmpty()) {
            final var previous = queue.pollFirst();
            final var edges = graph.get(previous);
            edges.forEach((node, time) -> {
                final var distance = distances.getOrDefault(previous, .0) + time.doubleValue();
                if (!visited.contains(node)) {
                    queue.add(node);
                    visited.add(node);
                }
                if (distance < distances.getOrDefault(node, Double.MAX_VALUE)) {
                    path.put(node, previous);
                    distances.put(node, distance);
                }
            });
        }

        final var route = new LinkedList<T>();
        Stream.iterate(target, Objects::nonNull, path::get).forEach(route::addFirst);
        return route;
    }

}
