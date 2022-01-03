package lv.id.jc.algorithm.graph;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * An interface for weighted directed graph (network)
 *
 * @author Jegors Čemisovs
 * @param <T> the type of vertex in this graph
 * @since 1.0
 */
@FunctionalInterface
public interface Graph<T> {
    /**
     * Schema of the graph
     *
     * @return the graph scheme
     */
    Map<T, Map<T, Number>> schema();

    /**
     * Edges of the given vertex
     *
     * @param id vertex
     * @return all links for the given vertex
     */
    default Map<T, Number> edges(T id) {
        return schema().get(id);
    }

    /**
     * Calculate the distance for the given path
     *
     * @param path the list of vertices representing the path
     * @return distance for the given path as double
     * @throws NullPointerException if path is incorrect and contains more than one vertex
     */
    default double getDistance(List<T> path) {
        return IntStream
                .range(1, path.size())
                .mapToObj(i -> edges(path.get(i - 1)).get(path.get(i)))
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    /**
     * Creates a Graph object by given schema
     *
     * @param schema of the graph
     * @param <T> the type of vertex in this graph
     * @return graph with given schema
     */
    static <T> Graph<T> of(Map<T, Map<T, Number>> schema) {
        return () -> schema;
    }
}
