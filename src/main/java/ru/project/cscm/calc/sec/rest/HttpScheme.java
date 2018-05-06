package ru.project.cscm.calc.sec.rest;

import ru.project.cscm.calc.base.NotNullOrEmpty;

import com.google.common.base.Objects;

/**
 * Перечисление схем http-запросов.
 * 
 * @author Alimurad A. Ramazanov
 * @since 11.11.2017
 *
 */
public enum HttpScheme {

	/**
	 * http
	 */
	HTTP("http"),

	/**
	 * https
	 */
	HTTPS("https"),

	/**
	 * ftp
	 */
	FTP("ftp");

	private final String schemeName;

	private HttpScheme(@NotNullOrEmpty final String schemeName) {
		this.schemeName = schemeName;
	}

	/**
	 * Возвращает строкое представление имени схемы.
	 * 
	 * @return не может быть {@code null}.
	 */
	@NotNullOrEmpty
	public String getSchemeName() {
		return schemeName;
	}

	/**
	 * Возвращает признак, является ли данная схема
	 * {@linkplain HttpScheme#HTTPS}} или нет.
	 * <p>
	 * 
	 * @param scheme
	 *            - строкое имя схемы для проверки; не может быть {@code null}.
	 * @return признак, является ли данная схема {@linkplain HttpScheme#HTTPS}}
	 *         или нет.
	 */
	public static boolean isHttps(@NotNullOrEmpty final String scheme) {
		return Objects.equal(HTTPS.getSchemeName().toLowerCase(), scheme.toLowerCase());
	}
}
