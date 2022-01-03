package lv.id.jc.algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static java.util.function.Predicate.not;
import static java.util.stream.Stream.iterate;

/**
 * Algorithm for finding the shortest paths between nodes in a graph.
 *
 * The algorithm doesn't take into account the distance between nodes.
 *
 * @author Jegors Čemisovs
 * @param <T> the type of vertex id
 */
public class BreadthFirstSearch<T> implements SearchAlgorithm<T> {

    @Override
    public List<T> findPath(Graph<T> graph, T source, T target) {
        final var queue = new LinkedList<T>();
        final var visited = new HashSet<T>();
        final var previous = new HashMap<T, T>();
        queue.add(source);

        while (!queue.isEmpty()) {
            final var node = queue.removeFirst();
            if (target.equals(node)) {
                final var path = new LinkedList<T>();
                iterate(node, Objects::nonNull, previous::get).forEach(path::addFirst);
                return path;
            }
            visited.add(node);
            graph.edges(node).keySet().stream()
                    .filter(not(visited::contains))
                    .forEach(it -> {
                        previous.computeIfAbsent(it, x -> node);
                        queue.addLast(it);
                    });
        }
        return List.of();
    }

}
