package br.com.yanaga.immutability.mean;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

public class SingleThreadMean implements Mean {

	@Override
	public float mean(List<Integer> numbers) {
		checkNotNull(numbers);
		checkArgument(!numbers.isEmpty());
		long sum = 0;
		for (Integer i : numbers) {
			sum += i;
		}
		return ((float) sum) / numbers.size();
	}

}