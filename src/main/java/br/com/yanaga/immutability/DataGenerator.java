package br.com.yanaga.immutability;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public class DataGenerator {

	private static final Random RANDOM = new SecureRandom();

	private DataGenerator() {
	}

	public static List<Integer> generateIntegers(long amount) {
		Builder<Integer> builder = ImmutableList.builder();
		for (long i = 0; i < amount; i++) {
			builder.add(RANDOM.nextInt(1000));
		}
		return builder.build();
	}

}