package br.com.yanaga.immutability;

import java.util.concurrent.RecursiveTask;

public abstract class AbstractForkJoinTask<T> extends RecursiveTask<T> {

	private static final long serialVersionUID = 1L;

	@Override
	protected T compute() {
		if (isForkNeeded()) {
			return doWithForkJoin();
		}
		else {
			return doInSameThread();
		}
	}

	protected abstract boolean isForkNeeded();

	protected abstract T doWithForkJoin();

	protected abstract T doInSameThread();

}