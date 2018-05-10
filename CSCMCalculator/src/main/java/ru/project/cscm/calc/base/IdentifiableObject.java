package ru.project.cscm.calc.base;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * Интерфейс, определяющий объект, у которого есть уникальный идентификатор, по
 * которому его можно однозначно идентифицировать.
 * 
 * @author Alimurad A. Ramazanov
 *
 * @param <T>
 *            - тип идентификатора.
 */
public interface IdentifiableObject<T> {

	/**
	 * Возвращает уникальный идентификатор объекта.
	 * 
	 * @return не может быть {@code null}.
	 */
	@NotNull
	T getId();

	/**
	 * Возвращает время создания объекта.
	 * <p>
	 * 
	 * @see Calendar
	 * 
	 * @return не может быть {@code null}.
	 */
	@Past
	@NotNull
	Calendar getCreatedTime();

	/**
	 * Возвращает время модификации объекта.
	 * <p>
	 * 
	 * @see Calendar
	 * 
	 * @return не может быть {@code null}.
	 */
	@NotNull
	@Past
	Calendar getModifiedTime();
}
