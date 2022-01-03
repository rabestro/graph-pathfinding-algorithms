package lv.id.jc.algorithm.graph;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * A generic graph representation
 *
 * @author Jegors Čemisovs
 * @param <T> the type of vertex id in this graph
 * @since 1.0
 */
public record Graph<T>(Map<T, Map<T, Number>> nodes) {

    /**
     * Edges of the given vertex
     *
     * @param id vertex (node)
     * @return all connections for the given vertex id
     */
    public Map<T, Number> edges(T id) {
        return nodes().get(id);
    }

    /**
     * Returns the calculated distance for the given path
     *
     * @param path the list of vertex ids representing the path
     * @return distance for the given path as double
     * @throws NullPointerException if path is incorrect and contains more than one vertex
     */
    public double getDistance(List<T> path) {
        return IntStream
                .range(1, path.size())
                .mapToObj(i -> edges(path.get(i - 1)).get(path.get(i)))
                .mapToDouble(Number::doubleValue)
                .sum();
    }
}
