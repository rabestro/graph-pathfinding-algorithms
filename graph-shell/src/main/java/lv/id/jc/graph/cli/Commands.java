package lv.id.jc.graph.cli;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lv.id.jc.algorithm.graph.BreadthFirstSearch;
import lv.id.jc.algorithm.graph.DijkstrasAlgorithm;
import lv.id.jc.algorithm.graph.Graph;
import lv.id.jc.algorithm.graph.SearchAlgorithm;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@ShellComponent
public class Commands implements PromptProvider, ConstraintValidator<Vertex, String> {
    private final SearchAlgorithm<String> bfgAlgorithm = new BreadthFirstSearch<>();
    private final SearchAlgorithm<String> dijkstrasAlgorithm = new DijkstrasAlgorithm<>();

    @Value("${graph:complex}")
    private String graphName;

    private Graph<String> graph;

    @PostConstruct
    public void loadGraph() throws IOException {
        var resource = new ClassPathResource(graphName + ".yaml");
        var schema = new YAMLMapper().readValue(resource.getInputStream(), Map.class);
        graph = Graph.of(schema);
    }

    @Override
    public AttributedString getPrompt() {
        return new AttributedString(graphName + ":> ", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }

    @Override
    public boolean isValid(String vertex, ConstraintValidatorContext context) {
        return graph.schema().containsKey(vertex);
    }

    @ShellMethod("Find the shortest path by using Breadth First Search Algorithm.")
    public List<String> shortest(@Vertex String source, @Vertex String target) {
        return bfgAlgorithm.findPath(graph, source, target);
    }

    @ShellMethod("Find the fastest path by using Dijkstra's Algorithm.")
    public List<String> fastest(@Vertex String source, @Vertex String target) {
        return dijkstrasAlgorithm.findPath(graph, source, target);
    }

    @ShellMethod("Print the edges of the given vertex.")
    public Map<String, Number> edges(@Vertex String vertex) {
        return graph.edges(vertex);
    }

    @ShellMethod("Print schema of the graph.")
    public Map<String, Map<String, Number>> schema() {
        return graph.schema();
    }

    @ShellMethod("Calculate the distance for the given path.")
    public double distance(@NotEmpty List<String> path) {
        return graph.getDistance(path);
    }

}
