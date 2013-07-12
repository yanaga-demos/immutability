package br.com.yanaga.immutability;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public class RandomForkJoinTask extends AbstractForkJoinTask<List<Integer>> {

	private static final long serialVersionUID = 1L;

	private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

	private final long amount;

	private RandomForkJoinTask(long amount) {
		this.amount = amount;
	}

	public static RandomForkJoinTask newInstance(long amount) {
		return new RandomForkJoinTask(amount);
	}

	@Override
	protected boolean isForkNeeded() {
		return amount > 5000;
	}

	@Override
	protected List<Integer> doWithForkJoin() {
		RandomForkJoinTask firstTask = new RandomForkJoinTask(amount / 2);
		firstTask.fork();
		RandomForkJoinTask secondTask = new RandomForkJoinTask(amount / 2 + amount % 2);
		Builder<Integer> builder = ImmutableList.builder();
		builder.addAll(secondTask.compute());
		builder.addAll(firstTask.join());
		return builder.build();
	}

	@Override
	protected List<Integer> doInSameThread() {
		Builder<Integer> builder = ImmutableList.builder();
		for (int i = 0; i < amount; i++) {
			builder.add(RANDOM.nextInt(1000));
		}
		return builder.build();
	}

}
