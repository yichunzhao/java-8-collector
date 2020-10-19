# java-8-collector
Java 8 Collector

*Collectors.toSet()*

*Collectors.toList()*

*Collectors.toCollection(Supplier<T>)*

collecting a pipe of elements into a specific collection impl.

*Collectors.toMap(keyMapper,valueMapper,mergeFunction)*

accumulating a line of elements<T> into a Map

KeyMapper: R=Function(T), valueMapper: R=function(T), mergerFunction: o1 or o2 = mergeFunction(o1,o2) applied when two keys conflict each other

*Collectors.toMap(keyMapper,valueMapper,mergeFunction, supplier)*

acculating a line of elements into a specific Map, which is supplied by the supplier function. 

*<T> Collector<T,?,Optional<T>> maxBy(Comparator<? super T> comparator)*

*<T> Collector<T,?,Optional<T>> minBy(Comparator<? super T> comparator)*

Collecting elements in a pipeline, and using an external comparator to find out the max. or min. element.

*Collecotrs.grounpingBy(function classifier, Collector)*
Group by classifier function, and then collecting each group by a Collector function. 

*Collecotrs.grounpingBy(function classifier)*

grouping a line of elements according to the classifier, K=function(T);  it returns Map<K,List<T>>

*Collecotrs.partitionBy(prediction)*

partitioning a line of elements according to a criterion; return a Map<Boolean, List<T>> 

*Collecotrs.summarizingInt/Double/Long(ToInt/Double/LongFunction())*

collecting and reducting a pipe line of elements

*Collectors.joining()*

*Collectors.joining(CharSequence)*

Both linking all elements in the pipline and return a String 






