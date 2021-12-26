package algorithm;

import java.util.List;

@FunctionalInterface
public interface SearchAlgorithm<T> {
    List<T> findPath(Graph<T> graph, T source, T target);
}
