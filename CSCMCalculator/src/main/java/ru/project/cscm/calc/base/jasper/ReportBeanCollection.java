package ru.project.cscm.calc.base.jasper;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import ru.project.cscm.calc.base.NotNullOrEmpty;

public interface ReportBeanCollection<T> {

	@NotNullOrEmpty
	String getReportPath();
	
	@NotNullOrEmpty
	String getReportName();
	
	@NotNull
	Collection<T> getCollection();
}
