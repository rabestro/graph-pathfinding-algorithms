package algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class DijkstrasAlgorithm<T> implements SearchAlgorithm<T> {

    @Override
    public List<T> findPath(Graph<T> graph, T source, T target) {
        final var queue = new LinkedList<T>();
        final var visited = new HashSet<T>();
        final var distances = new HashMap<T, Double>();
        final var previous = new HashMap<T, T>();
        queue.add(source);
        distances.put(source, .0);

        while (!queue.isEmpty()) {
            final var prev = queue.removeFirst();
            final var edges = graph.nodes().get(prev);
            edges.forEach((node, time) -> {
                final var distance = distances.get(prev) + time.doubleValue();
                if (!visited.contains(node)) {
                    queue.addLast(node);
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
