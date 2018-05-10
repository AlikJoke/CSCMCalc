package ru.project.cscm.calc.base;

import org.springframework.core.env.Environment;

/**
 * Интерфейс для получения свойств из файлов конфигурации.
 * 
 * @author Alimurad A. Ramazanov
 * @version 1.0.0
 *
 */
public interface Properties {

	/**
	 * Получение некоторого свойства по имени.
	 * <p>
	 * 
	 * @see Environment
	 * @param name
	 *            - имя свойства, не может быть {@code null}.
	 * @return свойство, может быть {@code null}.
	 */
	String getProperty(@NotNullOrEmpty String name);
}
