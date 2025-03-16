package de.faktorzehn;

import java.math.BigDecimal;

public enum TarifVariante {

	KOMPAKT(1.0),
	OPTIMAL(1.2),
	PREMIUM(1.4);

	private final double faktor;

	TarifVariante(double faktor) {
		this.faktor = faktor;
	}

	public BigDecimal getFaktor() {
		return BigDecimal.valueOf(faktor);
	}
}
