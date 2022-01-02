## Graph search algorithms

The project implements a class for the general structure of the graph, as well as two algorithms for finding a path in the graph.

There are implementations and tests for two algorithms:

- [Breadth-first search](src/main/java/graph/BreadthFirstSearch.java)
- [Dijkstra's Algorithm](src/main/java/graph/DijkstrasAlgorithm.java)

The implementation is written in Java 17, the API documentation is available [here](api). 
You can also see the [specifications](spock-reports) for the classes generated with the spock-reports.

### Unit Tests

Tests are written in groove language. For unit testing, the Spock framework was used. To test the operation of the algorithms, the following sample graphs were created.

#### Small Graph

![Small Graph](small.gif)

```groovy
        def graph = new Graph([
                A: [B: 7, C: 2],
                B: [A: 3, C: 5],
                C: [A: 1, B: 3]
        ])

```

#### Medium Graph

![Medium Graph](medium.gif)

```groovy
        def graph = new Graph([
                A: [B: 5],
                B: [A: 5, C: 10],
                C: [B: 20, D: 5],
                D: [E: 5],
                E: [B: 5]
        ])
```

#### Complex Graph

![Complex Graph](complex.gif)

```groovy
        def graph = new Graph([
                A: [B: 5, H: 2],
                B: [A: 5, C: 7],
                C: [B: 7, D: 3, G: 4],
                D: [C: 20, E: 4],
                E: [F: 5],
                F: [G: 6],
                G: [C: 4],
                H: [G: 3]
        ])
```