gitification-javabench
======================

Java application to benchmark gitification-server.
This software is for experimental usage only.

## Getting started

Read `JavaBench` class, it contains the main method of the software and information how to run it.

### How to create a new benchmark

Create a new class in `ch.mse.osf.gitification.benchmark.impl` which extends `Benchmark` class.

Take a look at `CreateEventBenchmark` class for an example.

### How to run a new benchmark

Add the benchmark in a collection and call `BenchmarkExecutor.execute` method with the list as argument.
```java
List<Benchmark> benchs = new ArrayList<Benchmark>();
benchs.add(new CreateEventBenchmark(100));
...
BenchmarkExecutor.execute(benchs);
```



