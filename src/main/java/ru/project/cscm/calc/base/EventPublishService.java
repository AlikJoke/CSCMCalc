package ru.project.cscm.calc.base;

import javax.validation.constraints.NotNull;

import org.springframework.context.ApplicationEvent;

public interface EventPublishService {

	void publish(@NotNull ApplicationEvent event);
}
