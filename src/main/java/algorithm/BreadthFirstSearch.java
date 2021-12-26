package algorithm;

import java.util.*;

import static java.util.function.Predicate.not;
import static java.util.stream.Stream.iterate;

public class BreadthFirstSearch<T> implements Algorithm<T> {
    @Override
    public List<T> findPath(Graph<T> graph, T source, T target) {
        final var queue = new LinkedList<T>();

        final var visited = new HashSet<T>();
        final var previous = new HashMap<T, T>();

        queue.add(source);

        while (!queue.isEmpty()) {
            final var node = queue.pollFirst();
            if (target.equals(node)) {
                final var path = new LinkedList<T>();
                iterate(node, Objects::nonNull, previous::get).forEach(path::addFirst);
                return path;
            }
            visited.add(node);
            graph.nodes().get(node).keySet().stream()
                    .filter(not(visited::contains))
                    .forEach(it -> {
                        previous.put(it, node);
                        queue.add(it);
                    });
        }
        return new LinkedList<>();
    }

}
