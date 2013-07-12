package br.com.yanaga.immutability.tuple;

import java.util.List;
import java.util.Map;

import br.com.yanaga.immutability.Tuple;

public interface TupleSum {

	public Map<String, Integer> sum(List<Tuple> tuples);

}