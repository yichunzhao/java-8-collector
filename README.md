In Java 8, the introduction of the `Stream` API brought powerful new capabilities for handling collections, including a set of collector implementations. Collectors are used to accumulate the elements of a stream into a different form, such as a collection, a single value, or a map.

Here's a brief introduction to Java 8 `Collector` and how to use it:

### What is a `Collector`?

A `Collector` is an interface in the `java.util.stream` package. It provides methods for collecting the results of a stream operation. The `Collector` interface is parameterized with the type of input elements and the type of result that will be produced.

### Key Methods of `Collector`:

1. **`Supplier`**: Provides a new result container.
2. **`Accumulator`**: Adds elements to the result container.
3. **`Combiner`**: Merges two result containers into one.
4. **`Finisher`**: Transforms the intermediate result into the final result.
5. **`Characteristics`**: Provides information about the collector's behavior.

### Common Collectors:

Java 8 provides several predefined implementations of the `Collector` interface in the `Collectors` utility class:

1. **`toList()`**:
   Collects the elements of a stream into a `List`.
   ```java
   List<String> list = stream.collect(Collectors.toList());
   ```

2. **`toSet()`**:
   Collects the elements of a stream into a `Set`.
   ```java
   Set<String> set = stream.collect(Collectors.toSet());
   ```

3. **`toMap()`**:
   Collects the elements into a `Map`, using provided functions to generate keys and values.
   ```java
   Map<Integer, String> map = stream.collect(Collectors.toMap(String::length, Function.identity()));
   ```

4. **`joining()`**:
   Concatenates the elements of a stream into a single `String`.
   ```java
   String result = stream.collect(Collectors.joining(", "));
   ```

5. **`groupingBy()`**:
   Groups the elements by a classifier function, resulting in a `Map` where the keys are the result of applying the classifier function, and the values are lists of items.
   ```java
   Map<Integer, List<String>> groupedByLength = stream.collect(Collectors.groupingBy(String::length));
   ```

6. **`partitioningBy()`**:
   Partitions the elements into two groups based on a predicate, resulting in a `Map<Boolean, List<T>>`.
   ```java
   Map<Boolean, List<String>> partitioned = stream.collect(Collectors.partitioningBy(s -> s.length() > 3));
   ```

7. **`counting()`**:
   Counts the number of elements in the stream.
   ```java
   long count = stream.collect(Collectors.counting());
   ```

8. **`summarizingInt()`**:
   Collects statistics, such as count, sum, min, average, and max, for an `int` property.
   ```java
   IntSummaryStatistics stats = stream.collect(Collectors.summarizingInt(String::length));
   ```

9. **`mapping()`**:
   Applies a mapping function to each element and collects the results.
   ```java
   Set<Integer> lengths = stream.collect(Collectors.mapping(String::length, Collectors.toSet()));
   ```

### Example Usage:

Here's a practical example demonstrating the use of several collectors:

```java
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        // Collect into a List
        List<String> list = words.stream().collect(Collectors.toList());
        System.out.println("List: " + list);

        // Collect into a Set
        Set<String> set = words.stream().collect(Collectors.toSet());
        System.out.println("Set: " + set);

        // Collect into a Map
        Map<Integer, String> map = words.stream()
                                        .collect(Collectors.toMap(String::length, Function.identity()));
        System.out.println("Map: " + map);

        // Join strings
        String joined = words.stream().collect(Collectors.joining(", "));
        System.out.println("Joined: " + joined);

        // Group by length
        Map<Integer, List<String>> groupedByLength = words.stream()
                                                          .collect(Collectors.groupingBy(String::length));
        System.out.println("Grouped by length: " + groupedByLength);

        // Count elements
        long count = words.stream().collect(Collectors.counting());
        System.out.println("Count: " + count);
    }
}
```

### Summary:
- **Collectors** are used to accumulate the results of a stream into various forms, such as collections, maps, or single values.
- **Common Collectors** include `toList()`, `toSet()`, `toMap()`, `joining()`, `groupingBy()`, and more.
- They provide a flexible way to perform complex data manipulations and aggregations in a declarative manner.

This introduction covers the basics, and you can explore more complex use cases and custom collectors as needed for your specific requirements.
