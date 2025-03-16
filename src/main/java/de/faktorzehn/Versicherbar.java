package de.faktorzehn;

import java.math.BigDecimal;

public interface Versicherbar {
	BigDecimal getBasisversicherungssumme();
	BigDecimal getJahrespraemie(BigDecimal versicherungssumme);
}
