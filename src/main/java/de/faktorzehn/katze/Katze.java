package de.faktorzehn.katze;

import de.faktorzehn.Tier;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Katze extends Tier {
	public static final BigDecimal BASIS_VERSICHERUNGSSUMME = new BigDecimal("2000");

	public Katze(LocalDate birthday) {
		super(birthday);
	}

	@Override
	public BigDecimal getBasisversicherungssumme() {
		return BASIS_VERSICHERUNGSSUMME;
	}

	@Override
	public BigDecimal getJahrespraemie(BigDecimal versicherungssumme) {
		return versicherungssumme.multiply(super.calculateAltersfaktor());
	}
}
