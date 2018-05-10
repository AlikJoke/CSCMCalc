package ru.project.cscm.calc.base;

import org.springframework.data.repository.CrudRepository;

import ru.project.cscm.calc.base.model.CscmUser;

/**
 * Интерфейс-репозиторий для операций с сущностью {@link CscmUser}.
 * 
 * @author Alimurad A. Ramazanov
 *
 */
@Deprecated
public interface UserRepository extends CrudRepository<CscmUser, String> {

}
