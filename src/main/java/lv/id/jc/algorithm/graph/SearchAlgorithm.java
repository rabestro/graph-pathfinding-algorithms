package lv.id.jc.algorithm.graph;

import java.util.List;

/**
 * A functional interface for graph search algorithm
 *
 * @param <T> type of vertex id
 */
@FunctionalInterface
public interface SearchAlgorithm<T> {
    List<T> findPath(Graph<T> graph, T source, T target);
}
