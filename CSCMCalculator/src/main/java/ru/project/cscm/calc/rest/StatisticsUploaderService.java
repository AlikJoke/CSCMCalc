package ru.project.cscm.calc.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import ru.project.cscm.calc.base.StatisticsService;
import ru.project.cscm.calc.protobuf.AtmTransListProtos;
import ru.project.cscm.calc.protobuf.AtmTransListProtos.AtmTransList;

@Controller
@RequestMapping("/CSCM")
public class StatisticsUploaderService extends ControllerWithExceptionHandler {

	private static final String PATH = "/stats/upload";
	
	@Autowired
	private StatisticsService statsService;

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
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void upload(final @RequestParam("file") MultipartFile file)
			throws IllegalStateException, IOException {
		final File tempFile = File.createTempFile(file.getOriginalFilename()
				+ "_" + UUID.randomUUID().toString(), null);
		file.transferTo(tempFile);
		uploadFile(tempFile);
		tempFile.delete();
	}

	private void uploadFile(final File file) throws FileNotFoundException, IOException {
		final AtmTransList transList = AtmTransListProtos.AtmTransList.parseFrom(new FileInputStream(file));
		statsService.saveTransactions(transList);
	}
}
