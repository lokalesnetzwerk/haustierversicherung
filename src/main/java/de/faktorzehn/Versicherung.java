package de.faktorzehn;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;

public class Versicherung {

	private final TarifVariante tarifVariante;
	private final Versicherbar versicherbar;
	private final LocalDate versicherungsBeginn;

	public Versicherung(TarifVariante tarifVariante, Versicherbar versicherbar, LocalDate versicherungsBeginn) {
		this.tarifVariante = tarifVariante;
		this.versicherbar = versicherbar;
		this.versicherungsBeginn = versicherungsBeginn;
		evaluateVersicherungsbeginn();
	}

	public BigDecimal calculateVersicherungssumme() {
		return versicherbar.getBasisversicherungssumme().multiply(tarifVariante.getFaktor()).setScale(2, RoundingMode.UP);
	}

	public BigDecimal calculateJahrespraemie() {
		return versicherbar.getJahrespraemie(calculateVersicherungssumme()).setScale(2, RoundingMode.UP);
	}

	private void evaluateVersicherungsbeginn() {
		int nextYear = LocalDate.now().plusYears(1).getYear();
		LocalDate startPremiumTarif = LocalDate.of(nextYear, Month.JULY, 1);
		LocalDate startKompaktUndOptimalTarif = LocalDate.of(nextYear, Month.JANUARY, 1);
		if (tarifVariante.equals(TarifVariante.PREMIUM) && versicherungsBeginn.isBefore(startPremiumTarif)) {
			throw new IllegalArgumentException(TarifVariante.PREMIUM + "-Tarife bieten wir leider erst ab dem " + startPremiumTarif.getDayOfMonth() + "." + startPremiumTarif.getMonth().getValue() + " des Folgejahres an.");
		} else if (versicherungsBeginn.isBefore(startKompaktUndOptimalTarif)) {
			throw new IllegalArgumentException(TarifVariante.OPTIMAL + "- und " + TarifVariante.KOMPAKT + "-Tarife bieten wir leider erst ab dem " + startKompaktUndOptimalTarif.getDayOfMonth() + "." + startKompaktUndOptimalTarif.getMonth().getValue() + " des Folgejahres an.");
		}
	}
}
