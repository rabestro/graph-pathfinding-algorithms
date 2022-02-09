package lv.id.jc.sample;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lv.id.jc.algorithm.graph.BreadthFirstSearch;
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

    @Value("${graph.file:complex}")
    private String file;

    private Graph<String> graph;
    private SearchAlgorithm<String> bfgAlgorithm = new BreadthFirstSearch<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        var in = Commands.class.getClassLoader().getResourceAsStream(file + ".yaml");
        var schema = new YAMLMapper().readValue(in, Map.class);
        graph = Graph.of(schema);
    }

    @ShellMethod("finds the shortest path by using Breadth First Search Algorithm")
    public List<String> shortest(String source, String target) {
        return bfgAlgorithm.findPath(graph, source, target);
    }

    @Override
    public AttributedString getPrompt() {
        return new AttributedString(file + ":>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}
