package ru.project.cscm.calc.base;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventPublishServiceImpl implements EventPublishService {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	public void publish(@NotNull final ApplicationEvent event) {
		publisher.publishEvent(event);
	}

}
