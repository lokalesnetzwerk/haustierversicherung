package de.faktorzehn;

import de.faktorzehn.hund.Bernhardiner;
import de.faktorzehn.hund.Dackel;
import de.faktorzehn.hund.Hund;
import de.faktorzehn.hund.Husky;
import de.faktorzehn.katze.Katze;
import de.faktorzehn.kleintier.Kleintier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VersicherungTest {

	@Nested
	class AcceptanceTests {

		@Test
		void premiumHuskyAge7() {
			Versicherung versicherungHusky = new Versicherung(TarifVariante.PREMIUM, new Husky(LocalDate.now().minusYears(7)), LocalDate.now().plusYears(1));
			assertThat(versicherungHusky.calculateVersicherungssumme()).isEqualTo(new BigDecimal("3500.00"));
			assertThat(versicherungHusky.calculateJahrespraemie()).isEqualTo(new BigDecimal("980.00"));
		}

		@Test
		void premiumDackelAge7() {
			Versicherung versicherungDackel = new Versicherung(TarifVariante.PREMIUM, new Dackel(LocalDate.now().minusYears(7)), LocalDate.now().plusYears(1));
			assertThat(versicherungDackel.calculateVersicherungssumme()).isEqualTo(new BigDecimal("3500.00"));
			assertThat(versicherungDackel.calculateJahrespraemie()).isEqualTo(new BigDecimal("882.00"));
		}

		@Test
		void premiumBernhardinerAge7() {
			Versicherung versicherungBernhardiner = new Versicherung(TarifVariante.PREMIUM, new Bernhardiner(LocalDate.now().minusYears(7)), LocalDate.now().plusYears(1));
			assertThat(versicherungBernhardiner.calculateVersicherungssumme()).isEqualTo(new BigDecimal("3500.00"));
			assertThat(versicherungBernhardiner.calculateJahrespraemie()).isEqualTo(new BigDecimal("1078.00"));
		}

		@Test
		void optimalKatzeAge3() {
			Versicherung versicherungKatze = new Versicherung(TarifVariante.OPTIMAL, new Katze(LocalDate.now().minusYears(3)), LocalDate.now().plusYears(1));
			assertThat(versicherungKatze.calculateVersicherungssumme()).isEqualTo(new BigDecimal("2400.00"));
			assertThat(versicherungKatze.calculateJahrespraemie()).isEqualTo(new BigDecimal("600.00"));
		}

		@Test
		void kompaktKleintierAge1() {
			Versicherung versicherungKleintier = new Versicherung(TarifVariante.KOMPAKT, new Kleintier(LocalDate.now()), LocalDate.now().plusYears(1));
			assertThat(versicherungKleintier.calculateVersicherungssumme()).isEqualTo(new BigDecimal("1000.00"));
			assertThat(versicherungKleintier.calculateJahrespraemie()).isEqualTo(new BigDecimal("200.00"));
		}
	}
	
	@Nested
	class BasisVersicherungsssummenTests {

		@Test
		void calculateVersicherungssummeHund() {
			Versicherung versicherung = new Versicherung(TarifVariante.KOMPAKT, new Hund(LocalDate.now()), LocalDate.now().plusYears(1));
			assertThat(versicherung.calculateVersicherungssumme()).isEqualTo(new BigDecimal("2500.00"));
		}

		@Test
		void calculateVersicherungssummeKatze() {
			Versicherung versicherung = new Versicherung(TarifVariante.KOMPAKT, new Katze(LocalDate.now()), LocalDate.now().plusYears(1));
			assertThat(versicherung.calculateVersicherungssumme()).isEqualTo(new BigDecimal("2000.00"));
		}

		@Test
		void calculateVersicherungssummeKleintier() {
			Versicherung versicherung = new Versicherung(TarifVariante.KOMPAKT, new Kleintier(LocalDate.now()), LocalDate.now().plusYears(1));
			assertThat(versicherung.calculateVersicherungssumme()).isEqualTo(new BigDecimal("1000.00"));
		}
	}

	@Nested
	class JahrespraemienTests {

		@Test
		void calculateJahrespraemieHund() {
			Versicherung versicherung = new Versicherung(TarifVariante.KOMPAKT, new Hund(LocalDate.now()), LocalDate.now().plusYears(1));
			assertThat(versicherung.calculateJahrespraemie()).isEqualTo(new BigDecimal("500.00"));
		}

		@Test
		void calculateJahrespraemieKatze() {
			Versicherung versicherung = new Versicherung(TarifVariante.KOMPAKT, new Katze(LocalDate.now()), LocalDate.now().plusYears(1));
			assertThat(versicherung.calculateJahrespraemie()).isEqualTo(new BigDecimal("400.00"));
		}

		@Test
		void calculateJahrespraemieKleintier() {
			Versicherung versicherung = new Versicherung(TarifVariante.KOMPAKT, new Kleintier(LocalDate.now()), LocalDate.now().plusYears(1));
			assertThat(versicherung.calculateJahrespraemie()).isEqualTo(new BigDecimal("200.00"));
		}

		private static Stream<Arguments> provideBirthdayAndJahrespraemie() {
			return Stream.of(
					Arguments.of(LocalDate.now(), new BigDecimal("200.00")),
					Arguments.of(LocalDate.now().minusYears(3), new BigDecimal("250.00")),
					Arguments.of(LocalDate.now().minusYears(7), new BigDecimal("280.00")),
					Arguments.of(LocalDate.now().minusYears(8), new BigDecimal("300.00"))
			);
		}
		@ParameterizedTest
		@MethodSource("provideBirthdayAndJahrespraemie")
		void calculateJahrespraemieDifferentAges(LocalDate birthday, BigDecimal expectedJahrespraemie) {
			Versicherung versicherung = new Versicherung(TarifVariante.KOMPAKT, new Kleintier(birthday), birthday);
			assertThat(versicherung.calculateJahrespraemie()).isEqualTo(expectedJahrespraemie);
		}
	}

	@Nested
	class TarifvariantenTests {

		@Test
		void calculateVersicherungssummeOptimal() {
			Versicherung versicherung = new Versicherung(TarifVariante.OPTIMAL, new Kleintier(LocalDate.now()), LocalDate.now().plusYears(1));
			assertThat(versicherung.calculateVersicherungssumme()).isEqualTo(new BigDecimal("1200.00"));
		}

		@Test
		void calculateVersicherungssummePremium() {
			Versicherung versicherung = new Versicherung(TarifVariante.PREMIUM, new Kleintier(LocalDate.now()), LocalDate.now().plusYears(1));
			assertThat(versicherung.calculateVersicherungssumme()).isEqualTo(new BigDecimal("1400.00"));
		}
	}

	@Nested
	class RabattUndAufschlagTests {

		@Test
		void calculateVersicherungspraemieDackel() {
			Versicherung versicherung = new Versicherung(TarifVariante.KOMPAKT, new Dackel(LocalDate.now()), LocalDate.now().plusYears(1));
			assertThat(versicherung.calculateJahrespraemie()).isEqualTo(new BigDecimal("450.00"));
		}

		@Test
		void calculateVersicherungspraemieBernhardiner() {
			Versicherung versicherung = new Versicherung(TarifVariante.KOMPAKT, new Bernhardiner(LocalDate.now()), LocalDate.now().plusYears(1));
			assertThat(versicherung.calculateJahrespraemie()).isEqualTo(new BigDecimal("550.00"));
		}

		@Test
		void calculateVersicherungspraemieHusky() {
			Versicherung versicherung = new Versicherung(TarifVariante.KOMPAKT, new Husky(LocalDate.now()), LocalDate.now().plusYears(1));
			assertThat(versicherung.calculateJahrespraemie()).isEqualTo(new BigDecimal("500.00"));
		}
		
		@Nested
		class BerechtigungsTests {
			
			@Test
			void versicherungsbeginnDiesesJahrTarifKompaktThrowsIllegalArgument() {
				Kleintier kleintier = new Kleintier(LocalDate.now());
				LocalDate versicherungsBeginn = LocalDate.now();
				assertThatThrownBy(() -> new Versicherung(TarifVariante.OPTIMAL, kleintier, versicherungsBeginn))
						.hasMessageContaining("Zum " + versicherungsBeginn + " bieten wir einen " + TarifVariante.OPTIMAL + " Tarif leider noch nicht an.")
						.isInstanceOf(IllegalArgumentException.class);
			}
		}
	}

}