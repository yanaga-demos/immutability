package br.com.yanaga.immutability.tuple;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;

import br.com.yanaga.immutability.DataGenerator;
import br.com.yanaga.immutability.Tuple;

public class TupleSumTest {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testMeanWith1_000_000() {
		Profiler profiler = new Profiler("Tuple Sum Test");
		profiler.setLogger(logger);
		List<Tuple> tuples = DataGenerator.generateTuples(100_000);
		profiler.start("Mutable Tuple Sum");
		profiler.start("Immutable Tuple Sum");
		profiler.stop().log();
	}

}
