package ru.project.cscm.calc.base.jasper;

import java.io.IOException;
import java.util.Collection;

import javax.validation.constraints.NotNull;

import net.sf.jasperreports.engine.JRException;

import org.springframework.core.io.ByteArrayResource;

import ru.project.cscm.calc.base.NotNullOrEmpty;

public interface ReportBuilder {

	@NotNull
	ByteArrayResource build(ReportBeanCollection<?> collection, @NotNull JasperExportFormat format) throws JRException, IOException;
	
	@NotNull
	ByteArrayResource build(@NotNullOrEmpty Collection<ReportBeanCollection<?>> reports, @NotNull JasperExportFormat format) throws JRException, IOException;

	enum JasperExportFormat {

		PDF,

		XLS;

		public static JasperExportFormat getDefaultFormat() {
			return PDF;
		}

		public static JasperExportFormat getXlsFormat() {
			return XLS;
		}

		public static JasperExportFormat getPdfFormat() {
			return PDF;
		}
	}
}
