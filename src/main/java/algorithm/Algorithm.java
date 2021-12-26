package algorithm;

import java.util.List;

public interface Algorithm<T> {
    List<T> findRoute(Graph<T> graph, T source, T target);
}
