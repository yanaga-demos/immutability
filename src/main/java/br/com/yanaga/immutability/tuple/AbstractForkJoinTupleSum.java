package br.com.yanaga.immutability.tuple;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import br.com.yanaga.immutability.Tuple;

public abstract class AbstractForkJoinTupleSum implements TupleSum {

	protected static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

	@Override
	public Map<String, Integer> sum(List<Tuple> tuples) {
		return FORK_JOIN_POOL.invoke(getRecursiveTask());
	}

	protected abstract RecursiveTask<Map<String, Integer>> getRecursiveTask();

}
