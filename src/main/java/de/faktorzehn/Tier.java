package de.faktorzehn;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public abstract class Tier implements Versicherbar{

	protected final LocalDate birthday;

	protected Tier(LocalDate birthday) {
		this.birthday = birthday;
	}

	public BigDecimal calculateAltersfaktor() {
		if (age() <= 2) return new BigDecimal("0.2");
		else if (age() <= 5) return new BigDecimal("0.25");
		else if (age() <= 7) return new BigDecimal("0.28");
		else return new BigDecimal("0.3");
	}
	
	private int age() {
		return Period.between(birthday, LocalDate.now()).getYears();
	}
}
