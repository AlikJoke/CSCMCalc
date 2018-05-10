package ru.project.cscm.calc.sec;

/**
 * Перечисление ролей пользователей в приложении.
 * 
 * @author Alimurad A. Ramazanov
 *
 */
public enum AppRole {

	/**
	 * Пользователь
	 */
	ROLE_CLIENT,
	
	/**
	 * Доверенный пользователь
	 */
	ROLE_TRUSTED_CLIENT,

	/**
	 * Администратор
	 */
	ROLE_ADMIN,

	/**
	 * Аналитик
	 */
	ROLE_ANALYST;
}
