package ru.project.cscm.calc.rest;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.project.cscm.calc.base.StatisticsService;
import ru.project.cscm.calc.base.items.SummaryUserStatistics;
import ru.project.cscm.calc.base.jasper.ReportBeanCollection;
import ru.project.cscm.calc.base.jasper.ReportBuilder;
import ru.project.cscm.calc.base.jasper.ReportBuilder.JasperExportFormat;
import ru.project.cscm.calc.base.jasper.SimpleReportBeanCollectionCreator;

import com.google.common.net.MediaType;

@RestController
@RequestMapping("/CSCM")
public class ReportGenerationService extends ControllerWithExceptionHandler {

	private static final String PATH = "/stats/report";
	
	@Autowired
	private StatisticsService statsService;
	
	@Autowired
	private ReportBuilder reportBuilder;
	
	@Autowired
	private SimpleReportBeanCollectionCreator creator;

	@RequestMapping(value = PATH, method = RequestMethod.OPTIONS)
	@ResponseStatus(HttpStatus.OK)
	public void doOptions(final HttpServletRequest request,
			final HttpServletResponse response) {
		response.setHeader("Allow", "GET, OPTIONS");
		if (request.getHeader("Origin") != null) {
			response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
		}
	}

	@GetMapping(PATH)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> generate(final @RequestBody GenerationParamsResource params) throws IOException, JRException {
		final SummaryUserStatistics stats = statsService.findUserStats(SecurityContextHolder.getContext().getAuthentication().getName());
		if (stats == null) {
			return ResponseEntity.noContent().build();
		}
		
		final ReportBeanCollection<?> statsAllCollection = creator.createCollection("statisticsAll.jrxml", stats.getStatsAllGroups());
		final ReportBeanCollection<?> statsByGroupCollection = creator.createCollection("statisticsByGroup.jrxml", stats.getStatsByGroup());
		final ByteArrayResource result = reportBuilder.build(Arrays.asList(statsAllCollection, statsByGroupCollection), JasperExportFormat.getXlsFormat());
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename("statistics.xls").build());
		headers.setContentType(new org.springframework.http.MediaType(MediaType.MICROSOFT_EXCEL.type(), MediaType.MICROSOFT_EXCEL.subtype()));
		headers.setContentLength(result.contentLength());
		headers.setLastModified(result.lastModified());

		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).headers(headers).body(result);
	}
}