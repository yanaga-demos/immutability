package br.com.yanaga.immutability;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public class DataGenerator {

	private static final Random RANDOM = new SecureRandom();

	private static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

	private DataGenerator() {
	}

	public static List<Integer> generateIntegers(long amount) {
		Builder<Integer> builder = ImmutableList.builder();
		for (long i = 0; i < amount; i++) {
			builder.add(RANDOM.nextInt(1000));
		}
		return builder.build();
	}

	public static List<Integer> generateIntegersWithForkJoin(long amount) {
		return FORK_JOIN_POOL.invoke(RandomForkJoinTask.newInstance(amount));
	}

	public static List<Tuple> generateTuples(long amount) {
		Builder<Tuple> builder = ImmutableList.builder();
		String[] names = { "a", "b", "c", "d", "e" };
		for (long i = 0; i < amount; i++) {
			Tuple tuple = Tuple.newInstance(names[RANDOM.nextInt(5)], RANDOM.nextInt(1000));
			builder.add(tuple);
		}
		return builder.build();
	}

}