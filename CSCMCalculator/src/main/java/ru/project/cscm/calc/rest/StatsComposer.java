package ru.project.cscm.calc.rest;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ru.project.cscm.calc.base.items.SummaryUserStatistics;

@Component
public class StatsComposer {

	@NotNull
	public SummaryUserStatistics generateStats(@NotNull final GenerationParamsResource params) {
		// TODO
		
		return new SummaryUserStatistics(SecurityContextHolder.getContext().getAuthentication().getName(), null, null);
	}
}
