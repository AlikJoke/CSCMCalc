package ru.project.cscm.calc.base;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * Интерфейс, описывающий объект, который имеет данные для аутентификации в
 * приложении.
 * 
 * @author Alimurad A. Ramazanov
 *
 * @param <T>
 *            - см. {@link IdentifiableObject}
 */
public interface HasAuthenticationAccess<T> extends IdentifiableObject<T> {

	/**
	 * Возвращает роли объекта в приложении.
	 * <p>
	 * 
	 * @see IdentifiableObject
	 * @return не может быть {@code null}.
	 */
	@NotNullOrEmpty
	Collection<GrantedAuthority> getRoles();

	/**
	 * Возвращает пароль.
	 * <p>
	 * 
	 * @see IdentifiableObject
	 * @return не может быть {@code null}.
	 */
	@NotNullOrEmpty
	String getPassword();

	/**
	 * Возвращает признак активности объекта (фактически, возможность
	 * авторизации объекта в системе).
	 * 
	 * @return {@code true} - если объект может авторизовываться в системе,
	 *         {@code false} - иначе.
	 */
	boolean isActive();
}
