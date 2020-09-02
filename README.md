# java-8-collector
Java 8 Collector

*Collectors.toSet()*

*Collectors.toList()*

*Collectors.toMap(keyMapper,valueMapper,mergeFunction)*

accumulating a line of elements into a Map

KeyMapper: T=Function(R), valueMapper: T=function(R), mergerFunction: o1 or o2 = mergeFunction(o1,o2) applied when a key confliction.

*Collectors.toMap(keyMapper,valueMapper,mergeFunction, supplier)*

acculating a line of elements into a specific Map, which is supplied by the supplier function. 

*Collecotrs.mayBy(Comparator) or minBy(Comparator)*

Collecting elements in the pipeline, and find out the max. or min. ref. to the comparator.

*Collecotrs.grounpingBy(function classifier)*

grouping a line of elements according to the classifier, K=function(T);  

*Collecotrs.partitionBy(prediction)*

partitioning a line of elements according to a criterion; return a Map<Boolean, List<T>> 

*Collecotrs.toCollection()*

collecting a pipe of elements into a specific collection impl.

*Collecotrs.summarizingInt/Double/Long(ToInt/Double/LongFunction())*

collecting and reducting a pipe line of elements

*Collectors.joining()*

*Collectors.joining(CharSequence)*

Both linking all elements in the pipline and return a String 






