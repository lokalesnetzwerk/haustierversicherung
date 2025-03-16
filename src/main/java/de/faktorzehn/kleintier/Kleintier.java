package de.faktorzehn.kleintier;

import de.faktorzehn.Tier;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Kleintier extends Tier {
	public static final BigDecimal BASIS_VERSICHERUNGSSUMME = new BigDecimal("1000");

	public Kleintier(LocalDate birthday) {
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
