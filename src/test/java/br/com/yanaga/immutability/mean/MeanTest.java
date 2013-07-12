package br.com.yanaga.immutability.mean;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;

import br.com.yanaga.immutability.DataGenerator;

public class MeanTest {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testMean() {
		Profiler profiler = new Profiler("Mean Test");
		profiler.setLogger(logger);
		profiler.start("RANDOM");
		List<Integer> integers = DataGenerator.generateIntegersWithForkJoin(10_000_000);
		profiler.start("Single Thread Mean");
		float mean1 = new SingleThreadMean().mean(integers);
		profiler.start("Fork Join Mean");
		float mean2 = new ForkJoinMean().mean(integers);
		profiler.stop().log();
		assertEquals(mean1, mean2, 0.0000001);
	}

}
