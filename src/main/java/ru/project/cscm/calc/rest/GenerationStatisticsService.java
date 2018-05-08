package ru.project.cscm.calc.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.project.cscm.calc.base.StatisticsService;
import ru.project.cscm.calc.base.items.SummaryUserStatistics;

@RestController
@RequestMapping("/CSCM")
public class GenerationStatisticsService extends ControllerWithExceptionHandler {

	private static final String PATH = "/stats/generate";
	
	@Autowired
	private StatisticsService statsService;
	
	@Autowired
	private StatsComposer statsComposer;

	@RequestMapping(value = PATH, method = RequestMethod.OPTIONS)
	@ResponseStatus(HttpStatus.OK)
	public void doOptions(final HttpServletRequest request,
			final HttpServletResponse response) {
		response.setHeader("Allow", "POST, OPTIONS");
		if (request.getHeader("Origin") != null) {
			response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
		}
	}

	@PostMapping(PATH)
	@ResponseStatus(HttpStatus.CREATED)
	public StatsResource generate(final @RequestBody GenerationParamsResource params) {
		final SummaryUserStatistics stats = statsComposer.generateStats(params);
		statsService.saveUserStats(stats);
		return new StatsResource(stats);
	}
}
