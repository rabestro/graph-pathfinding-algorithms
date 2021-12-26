package algorithm;

import java.util.*;
import java.util.stream.Stream;

public class Dijkstras<T> implements Algorithm<T> {

    @Override
    public List<T> findPath(Graph<T> graph, T source, T target) {
        final var queue = new LinkedList<T>();
        final var visited = new HashSet<T>();
        final var distances = new HashMap<T, Double>();
        final var previous = new HashMap<T, T>();
        queue.add(source);

        while (!queue.isEmpty()) {
            final var prev = queue.pollFirst();
            final var edges = graph.nodes().get(prev);
            edges.forEach((node, time) -> {
                final var distance = distances.getOrDefault(prev, .0) + time.doubleValue();
                if (!visited.contains(node)) {
                    queue.add(node);
                    visited.add(node);
                }
                if (distance < distances.getOrDefault(node, Double.MAX_VALUE)) {
                    previous.put(node, prev);
                    distances.put(node, distance);
                }
            });
        }

        final var path = new LinkedList<T>();
        Stream.iterate(target, Objects::nonNull, previous::get).forEach(path::addFirst);
        return path;
    }

}
