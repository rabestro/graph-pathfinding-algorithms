package algorithm;

import java.util.List;
import java.util.Map;

public record Graph<T>(Map<T, Map<T, Number>> nodes) {

    public double getDistance(final List<T> path) {
        double distance = 0;
        for (int i = 1; i < path.size(); ++i) {
            final var previous = nodes.get(path.get(i - 1));
            distance += previous.get(path.get(i)).doubleValue();
        }
        return distance;
    }
}
