package ru.project.cscm.calc.sec;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ru.project.cscm.calc.base.NotNullOrEmpty;

/**
 * Интерфейс входа / выхода в / из систему.
 * 
 * @author Alimurad A. Ramazanov
 *
 */
public interface Security {

	/**
	 * Авторизация в системе.
	 * <p>
	 * 
	 * @see SecurityContextHolder
	 * @see AuthenticationManager
	 * 
	 * @param username
	 *            - имя пользователя, не может быть {@code null}.
	 * @param password
	 *            - пароль пользователя, не может быть {@code null}.
	 * @throws UsernameNotFoundException
	 */
	void login(@NotNullOrEmpty String username, @NotNullOrEmpty String password);

	/**
	 * Выход из системы.
	 * <p>
	 * 
	 * @see SecurityContextHolder
	 * @see AuthenticationManager
	 */
	void logout();
}
