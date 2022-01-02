## Graph search algorithms

The project implements a class for the general structure of the graph, as well as two algorithms for finding a path in the graph.

There are implementations and tests for two algorithms:

- [Breadth-first search](https://en.wikipedia.org/wiki/Breadth-first_search)
- [Dijkstra's Algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)

The implementation is written in Java 17, the API documentation is available [here](api). 
You can also see the [specifications](spock-reports) for the classes generated with the spock-reports.

### How to use the classes in your program

The first step is create a graph structure. The [Graph class](http://algorithms.jc.id.lv/api/lv/id/jc/algorithm/graph/Graph.html) is generic, and you can use any Java type for vertex id and any [Number](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Number.html) type for distance. 

#### Example
In the following code we create a graph structure with eight nodes. We use Character class for vertex identification. You can see the graphic representation of the scheme [here](assets/complex.gif). 
```java
final var graph = new Graph<Character>(Map.of(
            'A', Map.of('B', 5, 'H', 2),
            'B', Map.of('A', 5, 'C', 7),
            'C', Map.of('B', 7, 'D', 3, 'G', 4),
            'D', Map.of('C', 20, 'E', 4),
            'E', Map.of('F', 5),
            'F', Map.of('G', 6),
            'G', Map.of('C', 4),
            'H', Map.of('G', 3)
    ));
```

The second step is creating a search algorithm class. You can choose one of the two algorithms. 

#### Example

```java
final var fastest = new DijkstrasAlgorithm<Character>();
final var shortest = new BreadthFirstSearch<Character>();
```

Now we can search for the route.

#### Example

```java
final var source = 'D';
final var target = 'C';

final var routeOne = shortest.findPath(graph, source, target);
final var routeTwo = fastest.findPath(graph, source, target);
```

As result, you get a list with the path. 

```java
routeOne == ['D', 'C']   
routeTwo == ['D', 'E', 'F', 'G', 'C']
```

### Unit Tests

Tests are written in groove language. For unit testing, the Spock framework was used. You can check the reports for [unit tests](spock-reports) and for [test coverage](coverage). To test the operation of the algorithms, the following sample graphs were created.

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

