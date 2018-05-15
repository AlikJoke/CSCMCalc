package ru.project.cscm.calc.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;

import ru.project.cscm.calc.base.items.SummaryUserStatistics;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class StatsResource {

	public StatsResource(final SummaryUserStatistics stats) {
		
	}
	
	@JsonCreator
	public StatsResource() {
		
	}
}
