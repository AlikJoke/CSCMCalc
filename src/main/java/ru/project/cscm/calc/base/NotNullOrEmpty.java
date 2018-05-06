package ru.project.cscm.calc.base;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * Аннотация, который должны помечаться поля, методы и аргументы, которые не
 * могут быть пустыми или равными {@code null}.
 * 
 * @author Alimurad A. Ramazanov
 *
 */
@Documented
@NotNull
@NotEmpty
@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER })
public @interface NotNullOrEmpty {

}
