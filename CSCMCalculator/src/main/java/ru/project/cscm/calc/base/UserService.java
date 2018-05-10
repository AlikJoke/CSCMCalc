package ru.project.cscm.calc.base;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import ru.project.cscm.calc.base.model.CscmUser;
import ru.project.cscm.calc.sec.AppRole;

/**
 * Интерфейс взаимодействия с пользовательскими данными.
 * 
 * @author Alimurad A. Ramazanov
 *
 */
@Deprecated
public interface UserService {

	/**
	 * Получение списка пользователей.
	 * <p>
	 * 
	 * @see CscmUser
	 * @return не может быть {@code null}.
	 */
	@NotNull
	List<CscmUser> getUsers();

	/**
	 * Получение пользователя по его имени.
	 * <p>
	 * 
	 * @see CscmUser
	 * @param username
	 *            - имя пользователя, не может быть {@code null}.
	 * @return может быть {@code null}.
	 */
	CscmUser getUserByUsername(@NotNullOrEmpty String id);

	/**
	 * Создание или обновление пользователя с заданными именем, паролем и ролью.
	 * <p>
	 * 
	 * @see CscmUser
	 * @see AppRole
	 * 
	 * @param id
	 *            - идентификатор пользователя, не может быть {@code null}.
	 * @param password
	 *            - пароль пользователя; не может быть {@code null}.
	 * @param roles
	 *            - набор ролей пользователя; не может быть {@code null}.
	 * 
	 * @return может быть {@code null}.
	 */
	void load(@NotNullOrEmpty String id, @NotNullOrEmpty String password,
			@NotNull Set<AppRole> roles);

	/**
	 * Деактивация аккаунта с указанным идентификатором.
	 * <p
	 * .
	 * 
	 * @param id
	 *            - идентификатор пользователя; не может быть {@code null}.
	 */
	void deactivate(@NotNullOrEmpty String id);
}
