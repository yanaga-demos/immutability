package br.com.yanaga.immutability.mean;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinMean implements Mean {

	private static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

	@Override
	public float mean(List<Integer> numbers) {
		return ((float) FORK_JOIN_POOL.invoke(new SumTask(numbers))) / numbers.size();
	}

	private static class SumTask extends RecursiveTask<Long> {

		private static final long serialVersionUID = 1L;

		private static final int FORK_JOIN_MINIMUM_SIZE = 50;

		private final List<Integer> numbers;

		private SumTask(List<Integer> numbers) {
			this.numbers = numbers;
		}

		@Override
		protected Long compute() {
			if (isForkNeeded()) {
				return doWithForkJoin();
			}
			else {
				return doInSameThread();
			}
		}

		private Long doInSameThread() {
			long sum = 0;
			for (Integer number : numbers) {
				sum += number;
			}
			return sum;
		}

		private Long doWithForkJoin() {
			int size = numbers.size();
			int middle = size / 2;
			SumTask beginTask = new SumTask(numbers.subList(0, middle));
			beginTask.fork();
			SumTask endTask = new SumTask(numbers.subList(middle, size));
			return endTask.compute() + beginTask.join();
		}

		private boolean isForkNeeded() {
			return numbers.size() < FORK_JOIN_MINIMUM_SIZE;
		}

	}

}
