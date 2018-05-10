package ru.project.cscm.auth.core;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Интерфейс взаимодействия с пользовательскими данными.
 * 
 * @author Alimurad A. Ramazanov
 *
 */
public interface UserService {

	/**
	 * Получение пользователя по его имени.
	 * <p>
	 * 
	 * @see CscmUser
	 * @param username
	 *            - имя пользователя, не может быть {@code null}.
	 * @return может быть {@code null}.
	 */
	CscmUser getUserByUsername(@NotNull @NotEmpty String username);
}
