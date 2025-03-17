package de.faktorzehn.hund;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Bernhardiner extends Hund {

	private static final BigDecimal AUFSCHLAG = new BigDecimal("1.1");

	public Bernhardiner(LocalDate geburtstag) {
		super(geburtstag);
	}

	@Override
	public BigDecimal getJahrespraemie(BigDecimal versicherungssumme) {
		return super.getJahrespraemie(versicherungssumme).multiply(AUFSCHLAG);
	}

}
