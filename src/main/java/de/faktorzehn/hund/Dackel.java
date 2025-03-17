package de.faktorzehn.hund;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Dackel extends Hund {

	private static final BigDecimal RABATT = new BigDecimal("0.9");

	public Dackel(LocalDate geburtstag) {
		super(geburtstag);
	}

	@Override
	public BigDecimal getJahrespraemie(BigDecimal versicherungssumme) {
		return super.getJahrespraemie(versicherungssumme).multiply(RABATT);
	}

}
