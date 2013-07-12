package br.com.yanaga.immutability.tuple;

import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

import br.com.yanaga.immutability.Tuple;

public abstract class AbstractTupleSumForkJoinTask extends RecursiveTask<Map<String, Integer>> {

	private static final long serialVersionUID = 1L;

	private static final int FORK_JOIN_MINIMUM_SIZE = 50;

	private final List<Tuple> tuples;

	private AbstractTupleSumForkJoinTask(List<Tuple> tuples) {
		this.tuples = tuples;
	}

	@Override
	protected Map<String, Integer> compute() {
		if (isForkNeeded()) {
			return doWithForkJoin();
		}
		else {
			return doInSameThread();
		}
	}

	private boolean isForkNeeded() {
		return tuples.size() > FORK_JOIN_MINIMUM_SIZE;
	}

	protected abstract Map<String, Integer> doWithForkJoin();

	protected abstract Map<String, Integer> doInSameThread();

}