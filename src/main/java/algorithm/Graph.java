package algorithm;

import java.util.List;
import java.util.Map;

public interface Graph<T> extends Map<T, Map<T, Number>> {

    default double getDistance(List<T> path) {
        double distance = 0;
        for (int i = 1; i < path.size(); ++i) {
            final var previous = this.get(path.get(i - 1));
            distance += previous.get(path.get(i)).doubleValue();
        }
        return distance;
    }
}
