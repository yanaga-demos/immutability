package br.com.yanaga.immutability;

import static com.google.common.base.Preconditions.checkNotNull;

public class Tuple {

	private final String name;

	private final Integer amount;

	private Tuple(String name, Integer amount) {
		this.name = name;
		this.amount = amount;
	}

	public static Tuple newInstance(String name, Integer amount) {
		checkNotNull(name);
		checkNotNull(amount);
		return new Tuple(name, amount);
	}

	public String getName() {
		return name;
	}

	public Integer getAmount() {
		return amount;
	}

}