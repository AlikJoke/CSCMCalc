package ru.project.cscm.calc.base.jasper;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import ru.project.cscm.calc.base.NotNullOrEmpty;

public abstract class SimpleReportBeanCollection<T> implements ReportBeanCollection<T> {
	
	private final String reportName;
	private final Collection<T> collection;

	public SimpleReportBeanCollection(@NotNullOrEmpty final String reportName, 
			@NotNull final Collection<T> collection) {
		this.reportName = reportName;
		this.collection = collection;
	}

	@Override
	@NotNull
	public String getReportName() {
		return reportName;
	}

	@Override
	@NotNull
	public Collection<T> getCollection() {
		return collection;
	}

}
