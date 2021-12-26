package algorithm;

import java.util.List;

public interface Algorithm<T> {
    List<T> findPath(Graph<T> graph, T source, T target);
}
