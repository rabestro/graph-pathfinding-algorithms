## Graph search algorithms

The project implements a class for the general structure of the graph, as well as two algorithms for finding a path in the graph.

There are implementations and tests for two algorithms:

- [Breadth-first search](https://en.wikipedia.org/wiki/Breadth-first_search)
- [Dijkstra's Algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)

The implementation is written in Java 17, the API documentation is available [here](api). 
You can also see the [specifications](spock-reports) for the classes generated with the spock-reports.

### Unit Tests

Tests are written in groove language. For unit testing, the Spock framework was used. To test the operation of the algorithms, the following sample graphs were created.

#### Small Graph Sample

```groovy
        def graph = new Graph([
                A: [B: 7, C: 2],
                B: [A: 3, C: 5],
                C: [A: 1, B: 3]
        ])

```

![Small Graph](assets/small.gif)


#### Medium Graph Sample

```groovy
        def graph = new Graph([
                A: [B: 5],
                B: [A: 5, C: 10],
                C: [B: 20, D: 5],
                D: [E: 5],
                E: [B: 5]
        ])
```

![Medium Graph](assets/medium.gif)


#### Complex Graph Sample

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
![Complex Graph](assets/complex.gif)

