package lv.id.jc.graph.cli;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lv.id.jc.algorithm.graph.BreadthFirstSearch;
import lv.id.jc.algorithm.graph.DijkstrasAlgorithm;
import lv.id.jc.algorithm.graph.Graph;
import lv.id.jc.algorithm.graph.SearchAlgorithm;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Map;

@ShellComponent
public class Commands implements PromptProvider, InitializingBean {
    private final SearchAlgorithm<String> bfgAlgorithm = new BreadthFirstSearch<>();
    private final SearchAlgorithm<String> dijkstrasAlgorithm = new DijkstrasAlgorithm<>();

    @Value("${graph:complex}")
    private String graphName;

    private Graph<String> graph;

    @Override
    public void afterPropertiesSet() throws Exception {
        var in = Commands.class.getClassLoader().getResourceAsStream(graphName + ".yaml");
        var schema = new YAMLMapper().readValue(in, Map.class);
        graph = Graph.of(schema);
    }

    @Override
    public AttributedString getPrompt() {
        return new AttributedString(graphName + ":> ", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }

    @ShellMethod("finds the shortest path by using Breadth First Search Algorithm")
    public List<String> shortest(String source, String target) {
        return bfgAlgorithm.findPath(graph, source, target);
    }

    @ShellMethod("finds the fastest path by using Dijkstra's Algorithm")
    public List<String> fastest(String source, String target) {
        return dijkstrasAlgorithm.findPath(graph, source, target);
    }

    @ShellMethod("prints schema of the graph")
    public Map<String, Map<String, Number>> schema() {
        return graph.schema();
    }

    @ShellMethod("prints distance for the path")
    public double distance(List<String> path) {
        return graph.getDistance(path);
    }
}