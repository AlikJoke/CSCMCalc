package ru.project.cscm.calc.sec;

/**
 * Перечисление типов скоупов для OAuth2.0 аутентификации.
 * 
 * @author Alimurad A. Ramazanov
 *
 */
public enum Scope {

	READ,

	WRITE,

	TRUST;

	public String getScope() {
		return this.name().toLowerCase();
	}
}
