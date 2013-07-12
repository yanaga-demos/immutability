package br.com.yanaga.immutability;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;

public class DataGeneratorTest {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testGenerateRandom() {
		Profiler profiler = new Profiler("Random Test");
		profiler.setLogger(logger);
		profiler.start("Random");
		List<Integer> integers = DataGenerator.generateIntegers(5_000_000);
		profiler.stop().log();
		assertEquals(5_000_000, integers.size());
	}

	@Test
	public void testGenerateRandomWithForkJoin() {
		Profiler profiler = new Profiler("Random Fork Join Test");
		profiler.setLogger(logger);
		profiler.start("Random");
		List<Integer> integers = DataGenerator.generateIntegersWithForkJoin(5_000_000);
		profiler.stop().log();
		assertEquals(5_000_000, integers.size());
	}

	@Test
	public void testGenerateTuples() {
		List<Tuple> tuples = DataGenerator.generateTuples(100_000);
		assertEquals(100_000, tuples.size());
	}

}